package com.example.OnlineLibrary.module;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Book", schema = "online_lib")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long book_id;

  private String title;

  private String coverImage;

  private String description;

  private String author;

  private String publisher;

  private String publicationDate;

  private String isbn;

  private String language;

  @ManyToMany
  @JoinTable(
      name = "book_genre",
      schema = "online_lib",
      joinColumns = @JoinColumn(name = "book_id"),
      inverseJoinColumns = @JoinColumn(name = "genre_id")
  )
  private Set<Genre> genres;

}