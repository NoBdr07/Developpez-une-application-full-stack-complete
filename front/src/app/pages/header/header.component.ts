import { Component, OnInit } from '@angular/core';
import { SessionService } from 'src/app/services/session.service';
import { Router } from '@angular/router';

/**
 * Represents the HeaderComponent of the application.
 */
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  /**
   * Represents the logged-in status of the user.
   */
  isLogged: boolean | undefined;

  /**
   * Represents the state of the menu (open or closed).
   */
  menuOpen = false;

  /**
   * Represents whether the screen size is desktop or not.
   */
  isDesktop = window.innerWidth > 768;

  /**
   * Creates an instance of HeaderComponent.
   * @param sessionService - The session service used for authentication.
   * @param router - The router service used for navigation.
   */
  constructor(private sessionService: SessionService, private router: Router) {
    this.checkScreenSize.bind(this);
  }

  /**
   * Initializes the component.
   */
  ngOnInit(): void {
    this.sessionService.$isLogged().subscribe((isLogged: boolean) => {
      this.isLogged = isLogged;
    });
  }

  /**
   * Toggles the menu state.
   */
  toggleMenu() {
    this.menuOpen = !this.menuOpen;
  }

  /**
   * Checks the screen size and updates the isDesktop property accordingly.
   */
  checkScreenSize() {
    this.isDesktop = window.innerWidth > 768;
  }

  /**
   * Checks if the current page is the authenticated user's profile page.
   * To set the icon in the header in violet if its the current page.
   * @returns A boolean indicating whether the current page is the authenticated user's profile page.
   */
  isAuthMePage(): boolean {
    return this.router.url === '/me';
  }
}
