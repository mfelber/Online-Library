package com.example.OnlineLibrary.controller.book;

import org.springframework.stereotype.Service;

import com.example.OnlineLibrary.file.FileUtils;
import com.example.OnlineLibrary.module.Book;
import com.example.OnlineLibrary.module.BookBorrowingHistory;
import com.example.OnlineLibrary.request.BookRequest;

@Service
public class BookMapper {

  public Book toBook(final BookRequest bookRequest) {
    return Book.builder()
        .id(bookRequest.id())
        .title(bookRequest.title())
        .author(bookRequest.author())
        .isbn(bookRequest.isbn())
        .description(bookRequest.description())
        .noOfPages(bookRequest.NoOfPages())
        .language(bookRequest.language())
        .build();
  }

  public BookResponse toBookResponse(Book book) {
  return BookResponse.builder()
      .id(book.getId())
      .title(book.getTitle())
      .author(book.getAuthor())
      .description(book.getDescription())
      .noOfPages(book.getNoOfPages())
      .language(book.getLanguage())
      .isbn(book.getIsbn())
      .rating(book.getRating())
      .cover(FileUtils.readCoverFromLocation(book.getCoverImage()))
      .build();
  }

  public BorrowedBookResponse toBorrowedBookResponse(BookBorrowingHistory history) {
    return BorrowedBookResponse.builder()
        .id(history.getBook().getId())
        .title(history.getBook().getTitle())
        .author(history.getBook().getAuthor())
        .isbn(history.getBook().getIsbn())
        .rating(history.getBook().getRating())
        .returned(history.isReturned())
        .returnApproved(history.isReturnedApproved())
        .cover(FileUtils.readCoverFromLocation(history.getBook().getCoverImage()))
        .build();
  }

}
