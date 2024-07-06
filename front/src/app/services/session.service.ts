import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from '../interfaces/user.interface';

/**
 * Service responsible for managing user session.
 */
@Injectable({
  providedIn: 'root'
})
export class SessionService {

  constructor() {
    this.checkTokenPresence();
  }

  /**
   * Indicates whether a user is currently logged in.
   */
  public isLogged = false;

  /**
   * The currently logged in user.
   */
  public user: User | undefined;

  private isLoggedSubject = new BehaviorSubject<boolean>(this.isLogged);

  /**
   * Observable that emits the current login status.
   */
  public $isLogged(): Observable<boolean> {
    return this.isLoggedSubject.asObservable();
  }

  /**
   * Logs in the user.
   * @param user The user to log in.
   */
  public logIn(user: User): void {
    this.user = user;
    this.isLogged = true;
    this.next();
  }

  /**
   * Logs out the user.
   */
  public logOut(): void {
    localStorage.removeItem('token');
    this.user = undefined;
    this.isLogged = false;
    this.next();
  }

  private next(): void {
    this.isLoggedSubject.next(this.isLogged);
  }

  /**
   * Checks the presence of a token in the local storage to determine if the user is logged in.
   * This prevents the `isLogged` property from becoming false on page refresh.
   */
  private checkTokenPresence(): void {
    const token = localStorage.getItem('token');
    this.isLogged = !!token;
    this.next();
  }
}
