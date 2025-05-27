package com.example.OnlineLibrary.controller.review;

import org.springframework.stereotype.Service;

import com.example.OnlineLibrary.module.Book;
import com.example.OnlineLibrary.module.Review;
import com.example.OnlineLibrary.request.ReviewRequest;

@Service
public class ReviewMapper {

  public Review toReview(final ReviewRequest reviewRequest) {
    return Review.builder()
        .rating(reviewRequest.rating())
        .comment(reviewRequest.comment())
        .book(Book.builder()
            .id(reviewRequest.bookId())
            .build())
        .build();
  }

  public ReviewResponse toReviewResponse(final Review review, final Long id) {
    return ReviewResponse.builder()
        .rating(review.getRating())
        .comment(review.getComment())
        .build();
  }

}
