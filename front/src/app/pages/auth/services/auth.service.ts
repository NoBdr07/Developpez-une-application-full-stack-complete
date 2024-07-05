import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoginRequest } from '../interfaces/loginRequest.interface';
import { Observable, tap } from 'rxjs';
import { AuthSuccess } from '../interfaces/authSucess.interface';
import { User } from 'src/app/interfaces/user.interface';
import { RegisterRequest } from '../interfaces/registerRequest.interface';
import { UpdateRequest } from '../interfaces/updateRequest.interface';
import { SessionService } from 'src/app/services/session.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private pathService = 'http://localhost:9000/api/auth';

  constructor(private http: HttpClient, private sessionService: SessionService) {}

  public register(registerRequest: RegisterRequest): Observable<AuthSuccess> {
    return this.http.post<AuthSuccess>(`${this.pathService}/register`, registerRequest);
  }

  public login(loginRequest: LoginRequest): Observable<AuthSuccess> {
    return this.http.post<AuthSuccess>(
      `${this.pathService}/login`,
      loginRequest
    );
  }

  public me(): Observable<User> {
    return this.http.get<User>(`${this.pathService}/me`);
  }

  public update(updateRequest: UpdateRequest): Observable<AuthSuccess> {
    return this.http.put<AuthSuccess>(`${this.pathService}/me`, updateRequest).pipe(
       tap((response: AuthSuccess) => {
         localStorage.setItem('token', response.token);
       })
    );
  }
}
