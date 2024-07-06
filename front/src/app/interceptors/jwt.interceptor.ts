import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpResponse, HttpErrorResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Router } from '@angular/router';

/**
 * Interceptor to add JWT token to outgoing requests and handle token expiration errors.
 */
@Injectable({ providedIn: 'root' })
export class JwtInterceptor implements HttpInterceptor {
  constructor(private router: Router) {}

  /**
   * Intercepts the outgoing HTTP request and adds the JWT token to the request headers.
   * Also handles token expiration errors and redirects to the home page with session expiration indication.
   * @param request - The outgoing HTTP request.
   * @param next - The next HTTP handler in the interceptor chain.
   * @returns An observable of the HTTP event.
   */
  public intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem('token');
    if (token) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
        },
      });
    }
    
    return next.handle(request).pipe(
      tap((event: HttpEvent<any>) => {
        if (event instanceof HttpResponse) {
          const newToken = event.headers.get('Authorization');
          if (newToken) {
            localStorage.setItem('token', newToken.replace('Bearer ', ''));
          }
        }
      }),
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401 && error.error === 'Token expired') {
          // Remove token from localStorage or any other storage
          localStorage.removeItem('token');
          // Redirect to home page with queryParams indicating session expiration
          this.router.navigate(['/'], { queryParams: { sessionExpired: true } });
        }
        return throwError(() => new Error(error.message));
      })
    );
  }
}
