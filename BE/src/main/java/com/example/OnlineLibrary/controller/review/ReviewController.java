package com.example.OnlineLibrary.controller.review;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.OnlineLibrary.common.PageResponse;
import com.example.OnlineLibrary.request.ReviewRequest;
import com.example.OnlineLibrary.service.ReviewService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("reviews")
@RequiredArgsConstructor
@Tag(name = "Reviews")
public class ReviewController {

  private final ReviewService reviewService;

  @PostMapping
  public ResponseEntity<Long> saveReview(@Valid @RequestBody ReviewRequest reviewRequest, Authentication currentUser){
    return ResponseEntity.ok(reviewService.save(reviewRequest, currentUser));
  }

  @GetMapping("/book/{bookId}")
  public ResponseEntity<PageResponse<ReviewResponse>> findAllReviewsByBook(
      @PathVariable("bookId") Long bookId,
      @RequestParam(name = "page", defaultValue = "0" ,required = false) int page,
      @RequestParam(name = "size", defaultValue = "10", required = false) int size, Authentication currentUser) {
    return ResponseEntity.ok(reviewService.findAllReviewsByBook(bookId, page, size, currentUser));
  }

}
