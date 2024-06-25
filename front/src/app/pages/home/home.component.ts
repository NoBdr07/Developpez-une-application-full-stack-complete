import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {

  sessionExpired = false;

  constructor(private router: Router, private route: ActivatedRoute) {}

  login() {
    this.router.navigate(['auth/login']);
  }

  register() {
    this.router.navigate(['auth/register']);
  }

  // Control if the token is expired to show a message
  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      if (params['sessionExpired']) {
        this.sessionExpired = true;
      }
    });
  }
}
