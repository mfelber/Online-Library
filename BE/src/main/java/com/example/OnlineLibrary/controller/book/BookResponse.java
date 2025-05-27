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
public class BookResponse {

  private Long id;
  private String title;
  private String author;
  private String description;
  private String noOfPages;
  private String language;
  private String isbn;
  private byte[] cover;
  private double rating;

}
