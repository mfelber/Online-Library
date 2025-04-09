package com.example.OnlineLibrary.module;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@Table(name = "Borrowed_Books", schema = "online_lib")
public class BorrowedBooks {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long borrowed_books_id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user_id;

  @ManyToMany
  @JoinTable(
      name = "borrowed_books",
      schema = "online_lib",
      joinColumns = @JoinColumn(name = "borrowed_books_id"),
      inverseJoinColumns = @JoinColumn(name = "book_id")
  )
  private Set<Book> books;

  private String date_from;

  private String date_to;

}