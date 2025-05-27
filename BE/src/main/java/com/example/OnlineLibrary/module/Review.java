package com.example.OnlineLibrary.module;


import com.example.OnlineLibrary.common.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review extends BaseEntity {

  private Double rating;

  private String comment;

  @ManyToOne
  @JoinColumn(name = "book_id")
  private Book book;

}
