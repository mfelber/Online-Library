package com.example.OnlineLibrary.module;
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
// @Table(name = "Book")
// public class Book {
//
//   @Id
//   @GeneratedValue(strategy = GenerationType.IDENTITY)
//   private Long book_id;
//
//   private String title;
//
//   private String author;
//
//   private String publisher;
//
//   private String description;
//
//   private String NoOfPages;
//
//   private String NoOfCopies;
//
//   private String publicationDate;
//
//   private String isbn;
//
//   private String language;
//
//   private String coverImage;
//
//   @ManyToMany
//   // @JoinTable(
//   //     name = "book_genre",
//   //     schema = "online_lib",
//   //     joinColumns = @JoinColumn(name = "book_id"),
//   //     inverseJoinColumns = @JoinColumn(name = "genre_id")
//   // )
//   private Set<Genre> genres;
//
// }

import java.util.List;
import com.example.OnlineLibrary.common.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
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
public class Book extends BaseEntity {

      private String title;

      private String author;

      private String description;

      private String noOfPages;

      private String NoOfCopies;

      private String isbn;

      private String language;

      private String coverImage;

      @ManyToOne
      @JoinColumn(name = "addedBy_id")
      private User addedBy;

      @OneToMany(mappedBy = "book")
      private List<Review> reviews;

      @OneToMany(mappedBy = "book")
      private List<BookBorrowingHistory> histories;

      @Transient
      public double getRating() {
            if (reviews == null || reviews.isEmpty()) {
                  return 0.0;
            }
            var rate = this.reviews.stream().mapToDouble(Review::getRating).average().orElse(0.0);
            double roundedRate = Math.round(rate * 10.0) / 10.0;
            return roundedRate;
      }


    //
    //   @ManyToMany
    //   // @JoinTable(
    //   //     name = "book_genre",
    //   //     schema = "online_lib",
    //   //     joinColumns = @JoinColumn(name = "book_id"),
    //   //     inverseJoinColumns = @JoinColumn(name = "genre_id")
    //   // )
    //   private Set<Genre> genres;
    //

}