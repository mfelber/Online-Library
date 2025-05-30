package com.example.OnlineLibrary.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OnlineLibrary.token.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {

  Optional<Token> findByToken(String token);



}
