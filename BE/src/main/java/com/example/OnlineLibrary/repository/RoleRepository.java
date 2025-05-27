package com.example.OnlineLibrary.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OnlineLibrary.module.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

  Optional<Role> findByName(String name);

}
