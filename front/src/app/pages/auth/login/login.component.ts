import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/pages/auth/services/auth.service';
import { FormBuilder, Validators } from '@angular/forms';
import { LoginRequest } from 'src/app/pages/auth/interfaces/loginRequest.interface';
import { AuthSuccess } from 'src/app/pages/auth/interfaces/authSucess.interface';
import { User } from 'src/app/interfaces/user.interface';
import { SessionService } from 'src/app/services/session.service';

/**
 * Represents the LoginComponent class.
 * This component is responsible for handling the login functionality.
 */
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent { 
  /**
   * Represents whether an error occurred during login.
   */
  public onError = false;

  /**
   * Represents the login form.
   */
  public form = this.fb.group({
    login: [''],
    password: ['', [Validators.required, Validators.pattern('^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W_]).{8,}$')]]
  });

  constructor(
    private authService: AuthService,
    private fb: FormBuilder,
    private router: Router,
    private sessionService: SessionService
  ) { }

  /**
   * Navigates back to the previous page.
   */
  back() {
    window.history.back();
  }

  /**
   * Submits the login form.
   * Performs the login request and handles the response.
   */
  public submit(): void {
    const loginRequest = this.form.value as LoginRequest;
    this.authService.login(loginRequest).subscribe(
      (response: AuthSuccess) => {
        localStorage.setItem('token', response.token);
        console.log(localStorage.getItem);
        this.authService.me().subscribe((user: User) => {
          this.sessionService.logIn(user);
          this.router.navigate(['/posts/feed'])
        });
        this.router.navigate(['/posts/feed'])
      },
      error => this.onError = true
    );
  }
}
