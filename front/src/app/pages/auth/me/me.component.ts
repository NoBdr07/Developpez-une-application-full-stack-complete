import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/pages/auth/services/auth.service';
import { FormBuilder, Validators } from '@angular/forms';
import { LoginRequest } from 'src/app/pages/auth/interfaces/loginRequest.interface';
import { AuthSuccess } from 'src/app/pages/auth/interfaces/authSucess.interface';
import { User } from 'src/app/interfaces/user.interface';
import { SessionService } from 'src/app/services/session.service';
import { TopicService } from '../../topics/services/topic.service';
import { UpdateRequest } from '../interfaces/updateRequest.interface';


@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss']
})
export class MeComponent implements OnInit {

  subscriptions$ = this.topicService.getSubscriptions();

  public saveSuccess: boolean = false;

  public form = this.fb.group({
    username: [''],
    email: ['']
  });

  constructor(private authService: AuthService,
    private fb: FormBuilder,
    private router: Router,
    private sessionService: SessionService,
    private topicService: TopicService
  ) { }

  public submit(): void {
    const updateRequest = this.form.value as UpdateRequest;
    this.authService.update(updateRequest).subscribe(() => {
      this.saveSuccess = true;
      setTimeout(() => this.saveSuccess = false, 3000);
    });  
  }

  public logOut(): void {
    this.sessionService.logOut();
    this.router.navigate(['']);  
  }

  public unsubscribe(topicId: number): void {
    console.log(topicId);
    this.topicService.unsubscribe(topicId).subscribe(
      () => this.subscriptions$ = this.topicService.getSubscriptions()
    );
  }

  ngOnInit(): void {    
    this.authService.me().subscribe((user: User) => {
      this.form.patchValue({
        username: user.username,
        email: user.email
      })
    });
    //verification des subscription recuperees
    this.subscriptions$.subscribe(
      (topics) => {
        console.log(topics);
      }
    );
    
  }

}
