import { Component, OnInit } from '@angular/core';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  isLogged: boolean | undefined ;


  constructor(
    private sessionService: SessionService
  ) { }

  ngOnInit(): void {
    this.sessionService.$isLogged().subscribe((isLogged: boolean) => {
      this.isLogged = isLogged;
    });
  }

}
