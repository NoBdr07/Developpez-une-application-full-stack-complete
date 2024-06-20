import { Component, OnInit } from '@angular/core';
import { PostsService } from '../services/posts.service';
import { CommentsService } from '../services/comments.service';
import { FormControl } from '@angular/forms';
import { Comment } from 'src/app/interfaces/comment.interface';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss'],
})
export class PostComponent implements OnInit {

  //recuperation du post Id present dans l'url http://localhost:9000/api/posts/{postId}
  public postId = window.location.pathname.split('/')[2];
  // transformation de postId en nombre
  public postIdNumber = Number(this.postId);

  public post$ = this.postService.getPost(this.postIdNumber);

  public comments$ = this.commentsService.getComments(
    this.postIdNumber.toString()
  );

  public newComment = new FormControl('');

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

  back() {
    window.history.back();
  }

  constructor(
    private postService: PostsService,
    private commentsService: CommentsService,
    private sessionService: SessionService,

  ) {}

  ngOnInit(): void {}
}
