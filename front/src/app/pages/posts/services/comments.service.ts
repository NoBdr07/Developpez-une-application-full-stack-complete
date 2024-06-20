import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Comment } from "src/app/interfaces/comment.interface";
import { Post } from "src/app/interfaces/post.interface";

@Injectable({
    providedIn: 'root'
  })
  export class CommentsService {
    private pathService = 'http://localhost:9000/api/posts/:postId/comments';
  
    constructor(private http: HttpClient) {}
  
    public getComments(postId: string): Observable<Comment[]> {
      const url = this.pathService.replace(':postId', postId);
      return this.http.get<Comment[]>(url);
    }
  
    public createComment(postId: string, content: string): Observable<Comment> {
      let comment: Comment = { content: content } as Comment;
      const url = this.pathService.replace(':postId', postId);
      console.log(comment);
      return this.http.post<Comment>(url, comment);
    }
  }
