package com.example.OnlineLibrary.common;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {

  private List<T> content;
  private int pageNumber;
  private int sizeOfPage;
  private Long totalElements;
  private int totalPages;
  private boolean firstPage;
  private boolean lastPage;

}
