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
// import jakarta.persistence.ManyToOne;
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
// @Table(name = "Borrowing_Basket")
// public class BorrowingBasket {
//
//   @Id
//   @GeneratedValue(strategy = GenerationType.IDENTITY)
//   private Long borrowing_basket_id;
//
//   @ManyToOne
//   @JoinColumn(name = "user_id")
//   private User user_id;
//
//   @ManyToMany
//   // @JoinTable(
//   //     name = "borrowing_basket",
//   //     schema = "online_lib",
//   //     joinColumns = @JoinColumn(name = "borrowing_basket_id"),
//   //     inverseJoinColumns = @JoinColumn(name = "book_id")
//   // )
//   private Set<Book> books;
//
// }