import { Component, OnInit } from '@angular/core';
import { SessionService } from 'src/app/services/session.service';
import { PostsService } from '../services/posts.service';
import { Observable, map } from 'rxjs';
import { Post } from 'src/app/interfaces/post.interface';
import { Router } from '@angular/router';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.scss']
})
export class FeedComponent implements OnInit {

  public posts$: Observable<Post[]> = this.postsService.getPosts();
  sortOrder = 'asc';

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

  toggleSortOrder() {
    this.sortOrder = this.sortOrder === 'asc' ? 'desc' : 'asc';
    this.sortArticles();
  }

  sortArticles() {
    this.posts$ = this.posts$.pipe(
      map(posts => posts.sort((a, b) => {
        if (this.sortOrder === 'asc') {
          return new Date(a.createdAt).getTime() - new Date(b.createdAt).getTime();
        } else {
          return new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime();
        }
      }))
    );
  }

}
