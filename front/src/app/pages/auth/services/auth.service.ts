import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoginRequest } from '../interfaces/loginRequest.interface';
import { Observable, tap } from 'rxjs';
import { AuthSuccess } from '../interfaces/authSucess.interface';
import { User } from 'src/app/interfaces/user.interface';
import { RegisterRequest } from '../interfaces/registerRequest.interface';
import { UpdateRequest } from '../interfaces/updateRequest.interface';

@Injectable({
  providedIn: 'root',
})

/**
 * Service responsible for handling authentication-related operations.
 */
export class AuthService {
  private pathService = 'http://localhost:9000/api/auth';

  constructor(private http: HttpClient) {}

  /**
   * Registers a new user.
   * @param registerRequest The registration request data.
   * @returns An observable that emits the authentication success response.
   */
  public register(registerRequest: RegisterRequest): Observable<AuthSuccess> {
    return this.http.post<AuthSuccess>(`${this.pathService}/register`, registerRequest);
  }

  /**
   * Logs in a user.
   * @param loginRequest The login request data.
   * @returns An observable that emits the authentication success response.
   */
  public login(loginRequest: LoginRequest): Observable<AuthSuccess> {
    return this.http.post<AuthSuccess>(
      `${this.pathService}/login`,
      loginRequest
    );
  }

  /**
   * Retrieves the currently authenticated user.
   * @returns An observable that emits the user data.
   */
  public me(): Observable<User> {
    return this.http.get<User>(`${this.pathService}/me`);
  }

  /**
   * Updates the currently authenticated user.
   * @param updateRequest The update request data.
   * @returns An observable that emits the authentication success response.
   */
  public update(updateRequest: UpdateRequest): Observable<AuthSuccess> {
    return this.http.put<AuthSuccess>(`${this.pathService}/me`, updateRequest).pipe(
       tap((response: AuthSuccess) => {
         localStorage.setItem('token', response.token);
       })
    );
  }
}
