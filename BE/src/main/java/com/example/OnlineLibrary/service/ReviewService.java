package com.example.OnlineLibrary.service;

import org.springframework.security.core.Authentication;

import com.example.OnlineLibrary.common.PageResponse;
import com.example.OnlineLibrary.controller.review.ReviewResponse;
import com.example.OnlineLibrary.request.ReviewRequest;

public interface ReviewService {

  Long save(ReviewRequest reviewRequest, Authentication currentUser);

  PageResponse<ReviewResponse> findAllReviewsByBook(Long bookId, int page, int size, Authentication currentUser);

}
