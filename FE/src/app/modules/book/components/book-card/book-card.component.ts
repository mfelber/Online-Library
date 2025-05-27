import {Component, EventEmitter, Input, Output} from '@angular/core';
import {BookResponse} from '../../../../services/models/book-response';

@Component({
  selector: 'app-book-card',
  imports: [],
  templateUrl: './book-card.component.html',
  styleUrl: './book-card.component.css'
})
export class BookCardComponent {


  private _book: BookResponse = {};
  private _bookCover: string | undefined;


  get book(): BookResponse {
    return this._book;
  }

  @Input()
  set book(value: BookResponse) {
    this._book = value;
  }

  get bookCover(): string | undefined {
    if (this._book.cover) {
      return 'data:image/jpg;base64,' + this._book.cover;
    }
    return 'https://picsum.photos/200/300?random' + this._book.cover;
  }

  set bookCover(value: string | undefined) {
    this._bookCover = value;
  }

  @Output() private borrow: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();

  onBorrow() {
    this.borrow.emit(this._book)
  }
}
