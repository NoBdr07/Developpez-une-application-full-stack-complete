import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/pages/auth/services/auth.service';
import { FormBuilder, Validators } from '@angular/forms';
import { LoginRequest } from 'src/app/pages/auth/interfaces/loginRequest.interface';
import { AuthSuccess } from 'src/app/pages/auth/interfaces/authSucess.interface';
import { User } from 'src/app/interfaces/user.interface';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  public onError = false;

  public form = this.fb.group({
    login: [''],
    password: ['', [Validators.required, Validators.pattern('^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W_]).{8,}$')]]
  });

  constructor(private authService: AuthService,
    private fb: FormBuilder,
    private router: Router,
    private sessionService: SessionService
  ) { }

  back() {
    window.history.back();
  }

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
