/**
 * Represents the FeedComponent class that handles the feed functionality.
 */
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
export class FeedComponent {

  /**
   * Represents an Observable of an array of Post objects.
   */
  public posts$: Observable<Post[]> = this.postsService.getPosts();

  /**
   * Represents the sort order of the posts.
   */
  sortOrder = 'asc';

  /**
   * Represents a flag indicating whether the posts are sorted in ascending order.
   */
  isSortedAsc = true;

  constructor(
    private sessionService: SessionService,
    private postsService: PostsService,
    private router: Router
  ) { }

  /**
   * Navigates to the create post page.
   */
  create() {
    this.router.navigate(['posts/create']);
  }

  /**
   * Toggles the sort order between ascending and descending.
   */
  toggleSortOrder() {
    this.sortOrder = this.sortOrder === 'asc' ? 'desc' : 'asc';
    this.sortArticles();
  }

  /**
   * Sorts the articles based on the current sort order.
   */
  sortArticles() {
    this.posts$ = this.posts$.pipe(
      map(posts => posts.sort((a, b) => {
        if (this.sortOrder === 'asc') {
          this.isSortedAsc = true;
          return new Date(a.createdAt).getTime() - new Date(b.createdAt).getTime();
        } else {
          this.isSortedAsc = false;
          return new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime();
        }
      }))
    );
  }
}
