/**
 * A guard that checks if the user is authenticated before allowing access to a route.
 */
import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { SessionService } from '../services/session.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private sessionService: SessionService, private router: Router) {}

  /**
   * Determines whether the user is allowed to activate the route.
   * If the user is not logged in, it redirects to the home page and returns false.
   * Otherwise, it allows access to the route and returns true.
   * @returns A boolean indicating whether the user is allowed to activate the route.
   */
  canActivate(): boolean {
    if (!this.sessionService.isLogged) {
      this.router.navigate(['']); 
      return false;
    }
    return true;
  }
}