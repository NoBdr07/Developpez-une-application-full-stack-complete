import { Component, OnDestroy } from '@angular/core';
import { PostsService } from '../../../services/posts.service';
import { CommentsService } from '../../../services/comments.service';
import { FormControl } from '@angular/forms';
import { Subscription } from 'rxjs';

/**
 * Represents the PostComponent which displays a single post and its comments.
 */
@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss'],
})
export class PostComponent implements OnDestroy{

  constructor(
    private postService: PostsService,
    private commentsService: CommentsService,
  ) {}

  /**
   * Represents the subscriptions of the component.
   */
  private subscriptions = new Subscription();

  /**
   * The ID of the post retrieved from the URL.
   */
  public postId = Number(window.location.pathname.split('/')[2]);

  /**
   * The observable representing the post.
   */
  public post$ = this.postService.getPost(this.postId);

  /**
   * The observable representing the comments of the post.
   */
  public comments$ = this.commentsService.getComments(
    this.postId.toString()
  );

  /**
   * The form control for adding a new comment.
   */
  public newComment = new FormControl('');

  /**
   * Adds a new comment to the post.
   */
  addComment() {
    const content = this.newComment.value;
    if (content) {
      const sub = this.commentsService.createComment(this.postId.toString(), content).subscribe({
        next: (comment) => {
          this.comments$ = this.commentsService.getComments(this.postId.toString());
          this.newComment.reset(); 
        },
        error: (err) => {
          console.error('Erreur lors de l\'ajout du commentaire:', err);
        },
      });
      this.subscriptions.add(sub);
    }
  }

  /**
   * Navigates back to the previous page.
   */
  back() {
    window.history.back();
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }
  
}
