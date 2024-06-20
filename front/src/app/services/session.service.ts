import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { AuthService } from '../pages/auth/services/auth.service';
import { User } from '../interfaces/user.interface';

@Injectable({
    providedIn: 'root'
  })
  export class SessionService {

    constructor() {
      this.checkTokenPresence();
    }
  
    public isLogged = false;
    public user: User | undefined;
  
    private isLoggedSubject = new BehaviorSubject<boolean>(this.isLogged);
  
    public $isLogged(): Observable<boolean> {
      return this.isLoggedSubject.asObservable();
    }
  
    public logIn(user: User): void {
      this.user = user;
      this.isLogged = true;
      this.next();
    }
  
    public logOut(): void {
      localStorage.removeItem('token');
      this.user = undefined;
      this.isLogged = false;
      this.next();
    }
  
    private next(): void {
      this.isLoggedSubject.next(this.isLogged);
    }

    // to avoid that isLogged become false on page refresh
    private checkTokenPresence(): void {
      const token = localStorage.getItem('token');
      this.isLogged = !!token;
      this.next();
  }

  }