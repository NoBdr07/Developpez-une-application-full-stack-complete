import { Component, OnInit } from '@angular/core';
import { PostsService } from '../services/posts.service';
import { CommentsService } from '../services/comments.service';
import { FormControl } from '@angular/forms';
import { SessionService } from 'src/app/services/session.service';

/**
 * Represents the PostComponent which displays a single post and its comments.
 */
@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss'],
})
export class PostComponent {

  /**
   * The ID of the post retrieved from the URL.
   */
  public postId = window.location.pathname.split('/')[2];

  /**
   * The numeric value of the post ID.
   */
  public postIdNumber = Number(this.postId);

  /**
   * The observable representing the post.
   */
  public post$ = this.postService.getPost(this.postIdNumber);

  /**
   * The observable representing the comments of the post.
   */
  public comments$ = this.commentsService.getComments(
    this.postIdNumber.toString()
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
      this.commentsService.createComment(this.postIdNumber.toString(), content).subscribe({
        next: (comment) => {
          this.comments$ = this.commentsService.getComments(this.postIdNumber.toString());
          this.newComment.reset(); 
        },
        error: (err) => {
          console.error('Erreur lors de l\'ajout du commentaire:', err);
        },
      });
    }
  }

  /**
   * Navigates back to the previous page.
   */
  back() {
    window.history.back();
  }

  constructor(
    private postService: PostsService,
    private commentsService: CommentsService,
    private sessionService: SessionService,
  ) {}
}
