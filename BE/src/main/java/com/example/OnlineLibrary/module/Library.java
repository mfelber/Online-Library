// package com.example.OnlineLibrary.module;
//
// import java.util.Set;
//
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.JoinTable;
// import jakarta.persistence.ManyToMany;
// import jakarta.persistence.Table;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.NoArgsConstructor;
//
// @Data
// @Builder
// @NoArgsConstructor
// @AllArgsConstructor
// @Entity
// @Table(name = "Library")
// public class Library {
//
//   @Id
//   @GeneratedValue(strategy = GenerationType.IDENTITY)
//   private Long library_id;
//
//   private String name;
//
//   @ManyToMany
//   @JoinTable(
//       name = "library_book",
//       schema = "online_lib",
//       joinColumns = @JoinColumn(name = "library_id"),
//       inverseJoinColumns = @JoinColumn(name = "book_id")
//   )
//   private Set<Book> books;
//
// }