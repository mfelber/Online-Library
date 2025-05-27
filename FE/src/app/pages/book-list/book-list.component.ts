import {Component, OnInit} from '@angular/core';
import {BookService} from '../../services/services/book.service';
import {Router} from '@angular/router';
import {PageResponseBookResponse} from '../../services/models/page-response-book-response';
import {NgForOf} from '@angular/common';
import {BookCardComponent} from '../../modules/book/components/book-card/book-card.component';

@Component({
  selector: 'app-book-list',
  imports: [
    NgForOf,
    BookCardComponent
  ],
  templateUrl: './book-list.component.html',
  styleUrl: './book-list.component.css'
})
export class BookListComponent implements OnInit{
  bookResponse: PageResponseBookResponse = {};
  page = 0;
  size = 10;

  ngOnInit() {
    this.fetchBooks();
  }

  constructor(
    private bookService: BookService,
    private router: Router
  ) {
  }

  private fetchBooks() {
    this.bookService.findAllBooks({
      page: this.page,
      size: this.size,
    }).subscribe({
      next: (books => {
        this.bookResponse = books;
      })
    })
  }
}
