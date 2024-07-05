import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpResponse, HttpErrorResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class JwtInterceptor implements HttpInterceptor {
  constructor(private router: Router) {}

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
        return throwError(error);
      })
    );
  }
}
