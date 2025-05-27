package com.example.OnlineLibrary.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.OnlineLibrary.common.PageResponse;
import com.example.OnlineLibrary.controller.book.BookMapper;
import com.example.OnlineLibrary.controller.book.BookResponse;
import com.example.OnlineLibrary.controller.book.BorrowedBookResponse;
import com.example.OnlineLibrary.exception.OperationNotPermittedException;
import com.example.OnlineLibrary.file.FileStorageService;
import com.example.OnlineLibrary.module.Book;
import com.example.OnlineLibrary.module.BookBorrowingHistory;
import com.example.OnlineLibrary.module.User;
import com.example.OnlineLibrary.repository.BookBorrowingHistoryRepository;
import com.example.OnlineLibrary.repository.BookRepository;
import com.example.OnlineLibrary.request.BookRequest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;
  private final BookBorrowingHistoryRepository bookBorrowingHistoryRepository;

  private final FileStorageService fileStorageService;

  private final BookMapper bookMapper;

  @Override
  public Long save(final BookRequest bookRequest, final Authentication currentUser) {
    User user = (User) currentUser.getPrincipal();
    Book book = bookMapper.toBook(bookRequest);
    book.setAddedBy(user);
    return bookRepository.save(book).getId();
  }

  @Override
  public BookResponse findById(final Long bookId) {
    return bookRepository.findById(bookId)
        .map(bookMapper::toBookResponse)
        .orElseThrow(() -> new EntityNotFoundException("No book found with the ID: " + bookId));
  }

  @Override
  public PageResponse<BookResponse> findAllBooks(final int page, final int size, final Authentication currentUser) {
    User user = (User) currentUser.getPrincipal();
    Pageable pageable = PageRequest.of(page,size, Sort.by("createdDate").descending());
    Page<Book> books = bookRepository.findAll(pageable);
    List<BookResponse> bookResponses = books.stream().map(bookMapper::toBookResponse).toList();
    return new PageResponse<>(
        bookResponses,
        books.getNumber(),
        books.getSize(),
        books.getTotalElements(),
        books.getTotalPages(),
        books.isFirst(),
        books.isLast());
  }

  @Override
  public PageResponse<BorrowedBookResponse> findAllBorrowedBooks(final int page, final int size,
      final Authentication currentUser) {
    User user = (User) currentUser.getPrincipal();
    Pageable pageable = PageRequest.of(page,size, Sort.by("createdDate").descending());
    Page<BookBorrowingHistory> allBorrowedBooks = bookBorrowingHistoryRepository.findAllBorrowedBooks(pageable, user.getId());
    List<BorrowedBookResponse> bookResponses = allBorrowedBooks.stream().map(bookMapper::toBorrowedBookResponse).toList();
    return new PageResponse<>(
        bookResponses,
        allBorrowedBooks.getNumber(),
        allBorrowedBooks.getSize(),
        allBorrowedBooks.getTotalElements(),
        allBorrowedBooks.getTotalPages(),
        allBorrowedBooks.isFirst(),
        allBorrowedBooks.isLast());
  }

  @Override
  public Long borrowBook(final Long bookId, final Authentication currentUser) {
    Book book = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("No book found with the ID: " + bookId));
    User user = (User) currentUser.getPrincipal();
    BookBorrowingHistory bookBorrowingHistory = BookBorrowingHistory.builder().user(user).book(book).returned(false).build();
    return bookBorrowingHistoryRepository.save(bookBorrowingHistory).getId();
  }

  @Override
  public Long returnBook(final Long bookId, final Authentication currentUser) {
    Book book = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("No book found with the ID: " + bookId));
    User user = (User) currentUser.getPrincipal();
    // check if user has already borrowed this book
    BookBorrowingHistory bookBorrowingHistory = bookBorrowingHistoryRepository.findByBookIdAndUserId(bookId, user.getId())
        .orElseThrow(() -> new OperationNotPermittedException("You did not borrow this book"));
    bookBorrowingHistory.setReturned(true);
    return bookBorrowingHistoryRepository.save(bookBorrowingHistory).getId();
  }

  @Override
  public Long approveReturnBook(final Long bookId, final Authentication currentUser) {
    Book book = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("No book found with the ID: " + bookId));
    User user = (User) currentUser.getPrincipal();
    BookBorrowingHistory bookBorrowingHistory = bookBorrowingHistoryRepository.findByBookIdAndAdminId(bookId, user.getId())
        .orElseThrow(() -> new OperationNotPermittedException("You cannot approve the return of this book"));
    bookBorrowingHistory.setReturned(true);
    bookBorrowingHistoryRepository.save(bookBorrowingHistory).getId();
    return 0L;
  }

  @Override
  public void uploadBookCoverImage(final MultipartFile image, final Authentication currentUser, final Long bookId) {
    Book book = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("No book found with the ID: " + bookId));
    User user = (User) currentUser.getPrincipal();
    var bookCover = fileStorageService.saveBookCover(image, user.getId());
    book.setCoverImage(bookCover);
    bookRepository.save(book);
  }

}
