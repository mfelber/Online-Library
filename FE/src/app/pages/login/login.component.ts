import { Component } from '@angular/core';
import {AuthenticationRequest} from '../../services/models/authentication-request';
import {NgForOf, NgIf} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../services/services/authentication.service';
import {TokenService} from '../../services/token/token.service';

@Component({
  selector: 'app-login',
  imports: [
    NgForOf,
    FormsModule,
    NgIf,
    CommonModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  authenticationRequest: AuthenticationRequest = {email:'', password:''};
  errorMessage: Array<string> = [];

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private tokenService: TokenService
  ) {
  }

  login() {
    this.errorMessage = [];
    this.authenticationService.authenticate({
      body: this.authenticationRequest
    }).subscribe({
      next: (res) => {
        this.tokenService.token = res.token as string;
        this.router.navigate(['books'])
      },
      error: (err) => {
        console.log(err)
        if (err.error.validationErrors){
          this.errorMessage = err.error.validationErrors
        } else {
          this.errorMessage.push(err.error.error)
        }
      }
    })
  }

  register() {
    this.router.navigate(['register']);
  }
}
