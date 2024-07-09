import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Comment } from "src/app/interfaces/comment.interface";

@Injectable({
  providedIn: 'root'
})
/**
 * Service for managing comments related operations.
 */
export class CommentsService {
  private pathService = 'http://localhost:9000/api/posts/:postId/comments';

  constructor(private http: HttpClient) {}

  /**
   * Retrieves the comments for a specific post.
   * @param postId The ID of the post.
   * @returns An Observable that emits an array of Comment objects.
   */
  public getComments(postId: string): Observable<Comment[]> {
    const url = this.pathService.replace(':postId', postId);
    return this.http.get<Comment[]>(url);
  }

  /**
   * Creates a new comment for a specific post.
   * @param postId The ID of the post.
   * @param content The content of the comment.
   * @returns An Observable that emits the created Comment object.
   */
  public createComment(postId: string, content: string): Observable<Comment> {
    let comment: Comment = { content: content } as Comment;
    const url = this.pathService.replace(':postId', postId);
    return this.http.post<Comment>(url, comment);
  }
}
