package com.example.OnlineLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OnlineLibrary.module.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
