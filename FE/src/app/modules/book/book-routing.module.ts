import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MainPageComponent} from './pages/main-page/main-page.component';
import {BookListComponent} from '../../pages/book-list/book-list.component';

const routes: Routes = [ {
  path: '',
  component: MainPageComponent,
  children: [
    {
      path: '',
      component: BookListComponent
    }
  ]
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BookRoutingModule { }
