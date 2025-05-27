package com.example.OnlineLibrary.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.OnlineLibrary.common.PageResponse;
import com.example.OnlineLibrary.controller.review.ReviewMapper;
import com.example.OnlineLibrary.controller.review.ReviewResponse;
import com.example.OnlineLibrary.module.Book;
import com.example.OnlineLibrary.module.Review;
import com.example.OnlineLibrary.module.User;
import com.example.OnlineLibrary.repository.BookRepository;
import com.example.OnlineLibrary.repository.ReviewRepository;
import com.example.OnlineLibrary.request.ReviewRequest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

  private final BookRepository bookRepository;
  private final ReviewRepository reviewRepository;
  private final ReviewMapper reviewMapper;

  @Override
  public Long save(final ReviewRequest reviewRequest, final Authentication currentUser) {
    Book book = bookRepository.findById(reviewRequest.bookId()).orElseThrow(() -> new EntityNotFoundException("No book found with the ID: " + reviewRequest.bookId()));
    Review review = reviewMapper.toReview(reviewRequest);
    return reviewRepository.save(review).getId();
  }

  @Override
  public PageResponse<ReviewResponse> findAllReviewsByBook(final Long bookId, final int page, final int size,
      final Authentication currentUser) {
    Pageable pageable = PageRequest.of(page, size);
    User user = (User) currentUser.getPrincipal();
    Page<Review> reviews = reviewRepository.findAllByBookId(bookId,pageable);
    List<ReviewResponse> reviewResponses = reviews.stream().map(review-> reviewMapper.toReviewResponse(review,user.getId())).toList();
    return new PageResponse<>(
        reviewResponses,
        reviews.getNumber(),
        reviews.getSize(),
        reviews.getTotalElements(),
        reviews.getTotalPages(),
        reviews.isFirst(),
        reviews.isLast()
    );
  }

}
