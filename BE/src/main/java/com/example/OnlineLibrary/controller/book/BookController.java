package com.example.OnlineLibrary.controller.book;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.OnlineLibrary.common.PageResponse;
import com.example.OnlineLibrary.request.BookRequest;
import com.example.OnlineLibrary.service.BookService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
@Tag(name = "Book")
public class BookController {

  private final BookService bookService;

  @PostMapping("/save-book")
  public ResponseEntity<Long> saveBook(@Valid @RequestBody BookRequest bookRequest, Authentication currentUser) {
    return ResponseEntity.ok(bookService.save(bookRequest,currentUser));
  }

  @GetMapping("{bookId}")
  public ResponseEntity<BookResponse> findBookById(@PathVariable("bookId") Long bookId) {
    return ResponseEntity.ok(bookService.findById(bookId));
  }

  @GetMapping("/books")
  public ResponseEntity<PageResponse<BookResponse>> findAllBooks(
      @RequestParam(name = "page", defaultValue = "0", required = false) int page,
      @RequestParam(name = "size", defaultValue = "10", required = false) int size, Authentication currentUser) {
    return ResponseEntity.ok(bookService.findAllBooks(page, size, currentUser));
  }

  @GetMapping("/borrowed-books")
  public ResponseEntity<PageResponse<BorrowedBookResponse>> findAllBorrowedBooks(
      @RequestParam(name = "page", defaultValue = "0", required = false) int page,
      @RequestParam(name = "size", defaultValue = "10", required = false) int size, Authentication currentUser){
    return ResponseEntity.ok(bookService.findAllBorrowedBooks(page,size,currentUser));
  }

  @PostMapping("/borrow-book/{bookId}")
  public ResponseEntity<Long> borrowBook(@PathVariable("bookId") Long bookId, Authentication currentUser) {
    return ResponseEntity.ok(bookService.borrowBook(bookId,currentUser));
  }

  @PatchMapping("/borrow/return/{bookId}")
  public ResponseEntity<Long> returnBook(@PathVariable("bookId") Long bookId, Authentication currentUser) {
    return ResponseEntity.ok(bookService.returnBook(bookId,currentUser));
  }

  @PatchMapping("/borrow/approve/{bookId}")
  public ResponseEntity<Long> approveReturnBook(@PathVariable("bookId") Long bookId, Authentication currentUser) {
    return ResponseEntity.ok(bookService.approveReturnBook(bookId,currentUser));
  }


  @PostMapping(value = "/cover/{bookId}", consumes = "multipart/form-data")
  public ResponseEntity<?> uploadBookCoverPicture(@PathVariable("bookId") Long bookId,
      @Parameter()
      @RequestPart("file") MultipartFile picture,
      Authentication currentUser) {

    bookService.uploadBookCoverImage(picture, currentUser, bookId);
    return ResponseEntity.accepted().build();

  }

}
