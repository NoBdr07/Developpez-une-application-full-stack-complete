import { Component, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { FormBuilder } from '@angular/forms';
import { LoginRequest } from 'src/app/pages/auth/interfaces/loginRequest.interface';
import { AuthSuccess } from 'src/app/pages/auth/interfaces/authSucess.interface';
import { User } from 'src/app/interfaces/user.interface';
import { SessionService } from 'src/app/services/session.service';
import { Subscription } from 'rxjs';

/**
 * Represents the LoginComponent class.
 * This component is responsible for handling the login functionality.
 */
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnDestroy { 

  /**
   * Represents the subscriptions of the component.
   */
  private subscriptions = new Subscription();

  /**
   * Represents whether an error occurred during login.
   */
  public onError = false;

  /**
   * Represents the login form.
   */
  public form = this.fb.group({
    login: [''],
    password: [''],
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
    const sub = this.authService.login(loginRequest).subscribe(
      (response: AuthSuccess) => {
        localStorage.setItem('token', response.token);
        this.authService.me().subscribe((user: User) => {
          this.sessionService.logIn(user);
          this.router.navigate(['/posts/feed'])
        });
        this.router.navigate(['/posts/feed'])
      },
      error => this.onError = true
    );
    this.subscriptions.add(sub);
  }

   /**
   * Unsubscribe to observable when the component is destroyed.
   */
  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }
}
