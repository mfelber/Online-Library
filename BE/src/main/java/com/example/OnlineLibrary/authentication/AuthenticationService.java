package com.example.OnlineLibrary.authentication;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.OnlineLibrary.email.EmailService;
import com.example.OnlineLibrary.email.EmailTemplateName;
import com.example.OnlineLibrary.module.User;
import com.example.OnlineLibrary.repository.RoleRepository;
import com.example.OnlineLibrary.repository.TokenRepository;
import com.example.OnlineLibrary.repository.UserRepository;
import com.example.OnlineLibrary.security.JwtService;
import com.example.OnlineLibrary.token.Token;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final RoleRepository roleRepository;

  private final PasswordEncoder passwordEncoder;

  private final UserRepository userRepository;

  private final TokenRepository tokenRepository;

  private final EmailService emailService;

  private final AuthenticationManager authenticationManager;

  private final JwtService jwtService;

  @Value("${application.mailing.frontend.activation-url}")
  private String activationUrl;

  public void register(RegistrationRequest request) throws MessagingException {
    var userRole = roleRepository.findByName("USER").orElseThrow(() -> new IllegalStateException("Role USER was not found"));

    var user = User.builder()
        .firstName(request.getFirstName())
        .lastName(request.getLastName())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .accountLocked(false)
        .enabled(false)
        .roles(List.of(userRole))
        .build();

    userRepository.save(user);
    sendValidationEmail(user);
  }

  private void sendValidationEmail(final User user) throws MessagingException {
    var newToken = generateAndSaveActivationToken(user);
    emailService.sendEmail(user.getEmail(),user.fullName(), EmailTemplateName.ACTIVATE_ACCOUNT,activationUrl,newToken,"Account activation");
  }

  private String generateAndSaveActivationToken(final User user) {
    // generate token
    String generatedToken = generateActivationCode(6);
    var token = Token.builder()
        .token(generatedToken)
        .createdAt(LocalDateTime.now())
        .expiresAt(LocalDateTime.now().plusMinutes(15))
        .user(user)
        .build();

    tokenRepository.save(token);
    return generatedToken;
  }

  private String generateActivationCode(int length) {
    String characters = "0123456789";
    StringBuilder codeBuilder = new StringBuilder();
    // secure random secure that generated random code is cryp. secured
    SecureRandom secureRandom = new SecureRandom();
    for(int i = 0; i < length; i++) {
      int randomIndex = secureRandom.nextInt(characters.length());
      codeBuilder.append(characters.charAt(randomIndex));
    }
    return codeBuilder.toString();
  }

  // it will take care of authentication process if email and password are correct
  public AuthenticationResponse authenticate(final @Valid AuthenticationRequest request) {
    var auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    var claims = new HashMap<String, Object>();
    var user = (User) auth.getPrincipal();
    claims.put("fullName", user.fullName());
    var jwtToken = jwtService.generateToken(claims,user);
    return AuthenticationResponse.builder().token(jwtToken).build();
  }

  public void activateAccount(final String token) throws MessagingException {
    Token savedToken = tokenRepository.findByToken(token).orElseThrow(() -> new RuntimeException("Invalid token"));

    // check if token is not expired
    if (LocalDateTime.now().isAfter(savedToken.getExpiresAt())) {
      sendValidationEmail(savedToken.getUser());
      throw new RuntimeException("Token is expired a new token has been sent to your email");
    }

    var user = userRepository.findById(savedToken.getUser().getId()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    user.setEnabled(true);
    userRepository.save(user);
    savedToken.setValidatedAt(LocalDateTime.now());
    tokenRepository.save(savedToken);
  }

}
