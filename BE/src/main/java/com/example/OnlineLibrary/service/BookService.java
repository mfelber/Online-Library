package com.example.OnlineLibrary.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import com.example.OnlineLibrary.common.PageResponse;
import com.example.OnlineLibrary.controller.book.BookResponse;
import com.example.OnlineLibrary.controller.book.BorrowedBookResponse;
import com.example.OnlineLibrary.request.BookRequest;

public interface BookService {

  Long save(BookRequest bookRequest, Authentication currentUser);

  BookResponse findById(Long bookId);

  PageResponse<BookResponse> findAllBooks(int page, int size, Authentication currentUser);

  PageResponse<BorrowedBookResponse> findAllBorrowedBooks(int page, int size, Authentication currentUser);

  Long borrowBook(Long bookId, Authentication currentUser);

  Long returnBook(Long bookId, Authentication currentUser);

  Long approveReturnBook(Long bookId, Authentication currentUser);

  void uploadBookCoverImage(MultipartFile image, Authentication currentUser, Long bookId);

}
