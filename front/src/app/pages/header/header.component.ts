import { Component, OnInit } from '@angular/core';
import { SessionService } from 'src/app/services/session.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  isLogged: boolean | undefined ;
  menuOpen = false;
  isDesktop = window.innerWidth > 768;


  constructor(private sessionService: SessionService, private router: Router) {
    this.checkScreenSize.bind(this);
    console.log(this.isDesktop);
   }

  ngOnInit(): void {
    this.sessionService.$isLogged().subscribe((isLogged: boolean) => {
      this.isLogged = isLogged;
    });
  }

  toggleMenu() {
    this.menuOpen = !this.menuOpen;
  }

  checkScreenSize() {
    this.isDesktop = window.innerWidth > 768;
  }

  isAuthMePage(): boolean {
    return this.router.url === '/auth/me';
  }

}
