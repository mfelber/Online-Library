package com.example.OnlineLibrary.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.OnlineLibrary.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(final String userEmail) throws UsernameNotFoundException {
    return userRepository.findByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException("User with email " + userEmail + " not found"));
  }

}
