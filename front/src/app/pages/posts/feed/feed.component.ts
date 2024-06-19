import { Component, OnInit } from '@angular/core';
import { SessionService } from 'src/app/services/session.service';
import { PostsService } from '../services/posts.service';
import { Observable } from 'rxjs';
import { Post } from 'src/app/interfaces/post.interface';
import { Router } from '@angular/router';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.scss']
})
export class FeedComponent implements OnInit {

  public posts$: Observable<Post[]> = this.postsService.getPosts();

  

  constructor(
    private sessionService: SessionService,
    private postsService: PostsService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.posts$.subscribe(posts => {
      console.log(posts);
    });
  }

  create() {
    this.router.navigate(['posts/create']);
  }

}
