package com.example.OnlineLibrary.token;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.OnlineLibrary.module.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "token")
public class Token {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // generated token
  private String token;

  // when the token was created
  private LocalDateTime createdAt;

  // when the token will expire
  private LocalDateTime expiresAt;

  // when the token was validated
  private LocalDateTime validatedAt;

  @ManyToOne
  @JoinColumn(name = "userId", nullable = false)
  private User user;
}
