/**
 * Represents the MeComponent class that handles the user profile page.
 */
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { FormBuilder } from '@angular/forms';
import { User } from 'src/app/interfaces/user.interface';
import { SessionService } from 'src/app/services/session.service';
import { TopicService } from '../../services/topic.service';
import { UpdateRequest } from '../auth/interfaces/updateRequest.interface';

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss']
})
export class MeComponent implements OnInit {

  /**
   * Represents the subscriptions of the user.
   */
  subscriptions$ = this.topicService.getSubscriptions();

  /**
   * Represents the flag indicating if the save operation was successful.
   */
  public saveSuccess: boolean = false;

  /**
   * Represents the form for updating user information.
   */
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

  /**
   * Submits the form and updates the user information.
   */
  public submit(): void {
    const updateRequest = this.form.value as UpdateRequest;
    this.authService.update(updateRequest).subscribe(() => {
      this.saveSuccess = true;
      setTimeout(() => this.saveSuccess = false, 3000);
    });  
  }

  /**
   * Logs out the user and navigates to the home page.
   */
  public logOut(): void {
    this.sessionService.logOut();
    this.router.navigate(['']);  
  }

  /**
   * Unsubscribes the user from a topic.
   * @param topicId - The ID of the topic to unsubscribe from.
   */
  public unsubscribe(topicId: number): void {
    console.log(topicId);
    this.topicService.unsubscribe(topicId).subscribe(
      () => this.subscriptions$ = this.topicService.getSubscriptions()
    );
  }

  /**
   * Get the user info to fulfill the form.
   */
  ngOnInit(): void {    
    this.authService.me().subscribe((user: User) => {
      this.form.patchValue({
        username: user.username,
        email: user.email
      })
    });   
  }
}
