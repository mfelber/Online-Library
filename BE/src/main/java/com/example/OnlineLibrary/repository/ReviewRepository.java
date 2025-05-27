package com.example.OnlineLibrary.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.OnlineLibrary.module.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

  @Query("""
         SELECT review FROM Review review
         WHERE review.book.id = :bookId
         """)
  Page<Review> findAllByBookId(Long bookId, Pageable pageable);

}
