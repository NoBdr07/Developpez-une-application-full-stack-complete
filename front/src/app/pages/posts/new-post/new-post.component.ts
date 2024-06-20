import { Component, OnInit } from '@angular/core';
import { Validators } from '@angular/forms';
import { FormBuilder } from '@angular/forms';
import { PostsService } from 'src/app/pages/posts/services/posts.service';
import { Post } from 'src/app/interfaces/post.interface';
import { User } from 'src/app/interfaces/user.interface';
import { Topic } from 'src/app/interfaces/topic.interface';
import { Router } from '@angular/router';
import { SessionService } from 'src/app/services/session.service';
import { TopicService } from 'src/app/pages/topics/services/topic.service';

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.scss']
})
export class NewPostComponent {

  topics$ = this.topicService.getTopics();

  public form = this.fb.group({
    topic: ['', Validators.required],
    title: ['', [Validators.required, Validators.minLength(5)]],
    content: ['', [Validators.required, Validators.minLength(50)]],
  });

  constructor(
    private postsService: PostsService,
    private fb: FormBuilder,
    private router: Router,
    private sessionService: SessionService,
    private topicService: TopicService
  ) { }

  public submit(): void {
    const post = this.form.value as unknown as Post;
    this.postsService.createPost(post).subscribe(
      () => {
        this.router.navigate(['/posts/feed']);
      }
    );
  }

  back() {
    window.history.back();
  }


}
