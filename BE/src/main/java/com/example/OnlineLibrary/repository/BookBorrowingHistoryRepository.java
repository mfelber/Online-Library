package com.example.OnlineLibrary.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.OnlineLibrary.module.BookBorrowingHistory;

public interface BookBorrowingHistoryRepository extends JpaRepository<BookBorrowingHistory, Long> {

  @Query(
      """
      SELECT history FROM BookBorrowingHistory history 
      WHERE history.user.id = :userId
      """
  )
  Page<BookBorrowingHistory> findAllBorrowedBooks(Pageable pageable, Long userId);

  @Query(
      """
      SELECT history FROM BookBorrowingHistory history
      WHERE history.user.id = :userId
      AND history.book.id = :bookId
      AND history.returned = false
      AND history.returnedApproved = false
      """
  )
  Optional<BookBorrowingHistory> findByBookIdAndUserId(Long bookId, Long userId);

  @Query(
      """
      SELECT history FROM BookBorrowingHistory history
      WHERE history.book.addedBy.id = :userId
      AND history.book.id = :bookId
      AND history.returned = true
      AND history.returnedApproved = false
      """
  )
  Optional<BookBorrowingHistory> findByBookIdAndAdminId(Long bookId, Long id);

}
