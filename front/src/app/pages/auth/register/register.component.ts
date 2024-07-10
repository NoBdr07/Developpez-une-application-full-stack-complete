/**
 * Represents the RegisterComponent class.
 * This component is responsible for handling user registration.
 */
import { Component, OnDestroy } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterRequest } from '../interfaces/registerRequest.interface';
import { catchError, Subscription, throwError } from 'rxjs';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnDestroy {
  /**
   * A subscription to handle the component's subscriptions.
   */
  private subscriptions = new Subscription();

  /**
   * Represents the flag indicating whether an error occurred during registration.
   */
  public errorMessage: string | null = null;

  constructor(
    private authService: AuthService,
    private fb: FormBuilder,
    private router: Router
  ) {}

  /**
   * Represents the form group for user registration.
   * It contains the form controls for username, email, and password.
   */
  public form = this.fb.group({
    username: ['', [Validators.required, Validators.minLength(4)]],
    email: ['', [Validators.required, Validators.email]],
    password: [
      '',
      [
        Validators.required,
        Validators.minLength(8),
        Validators.pattern(
          '^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W_]).{8,}$'
        ),
      ],
    ],
  });

  /**
   * Navigates back to the previous page.
   */
  back() {
    window.history.back();
  }

  /**
   * Submits the registration form.
   * Sends a register request to the AuthService and navigates to the login page upon success.
   */
  public submit(): void {
    const registerRequest = this.form.value as RegisterRequest;
    const sub = this.authService.register(registerRequest).subscribe({
      next: () => {
        this.router.navigate(['/auth/login']);
      },
      error: (errorMsg) => {
        this.errorMessage = errorMsg;
      },
    });
    this.subscriptions.add(sub);
  }

  /**
   * Unsubscribe to observable when the component is destroyed.
   */
  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }
}
