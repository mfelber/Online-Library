package com.example.OnlineLibrary.controller.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowedBookResponse {

  private Long id;
  private String title;
  private String author;
  private String isbn;
  private double rating;
  private boolean returned;
  private boolean returnApproved;
  private byte[] cover;
}
