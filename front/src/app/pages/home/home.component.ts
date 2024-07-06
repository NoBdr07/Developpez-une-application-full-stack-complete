/**
 * Represents the HomeComponent of the application.
 * This component is responsible for displaying the home page.
 */
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {

  /**
   * Represents whether the session has expired or not.
   */
  sessionExpired = false;

  constructor(private router: Router, private route: ActivatedRoute) {}

  /**
   * Navigates to the login page.
   */
  login() {
    this.router.navigate(['auth/login']);
  }

  /**
   * Navigates to the register page.
   */
  register() {
    this.router.navigate(['auth/register']);
  }

  /**
   * Initializes the component and checks if the session has expired.
   */
  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      if (params['sessionExpired']) {
        this.sessionExpired = true;
      }
    });
  }
}
