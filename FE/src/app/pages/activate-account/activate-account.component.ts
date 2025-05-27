import { Component } from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../services/services/authentication.service';
import {CodeInputModule} from 'angular-code-input';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-activate-account',
  imports: [
    CodeInputModule,
    NgIf
  ],
  templateUrl: './activate-account.component.html',
  styleUrl: './activate-account.component.css'
})
export class ActivateAccountComponent {

  message = '';
  isOkay= true;
  submitted = false;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService) {
  }

  onCodeCompleted(token: string) {
    this.confirmAccount(token);
  }

  redirectToLogin() {
    this.router.navigate(['/login']);
  }

  private confirmAccount(token: string) {
    this.authenticationService.confirm({
      token
    }).subscribe({
      next: () => {
        this.message = 'Your account has been successfully activated';
        this.submitted = true;
        this.isOkay = true;
    },
      error: () => {
        this.message = 'Token has been expired or is invalid';
        this.submitted = true;
        this.isOkay = false;
      }
    })
  }
}
