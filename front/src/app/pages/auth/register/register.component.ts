import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SessionService } from 'src/app/services/session.service';
import { User } from 'src/app/interfaces/user.interface';
import { RegisterRequest } from '../interfaces/registerRequest.interface';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {
  public onError = false;

  constructor(
    private authService: AuthService,
    private fb: FormBuilder,
    private router: Router,
    private sessionService: SessionService
  ) {}

  public form = this.fb.group({
    username: [''],
    email: [''],
    password: [
      '',
      [
        Validators.required,
        Validators.pattern(
          '^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W_]).{8,}$'
        ),
      ],
    ],
  });

  public submit(): void {
    const registerRequest = this.form.value as RegisterRequest;
    this.authService.register(registerRequest).subscribe(() => {
      this.router.navigate(['/auth/login']);
    });
    (_error: any) => (this.onError = true);
  }
}
