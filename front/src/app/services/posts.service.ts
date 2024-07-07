import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Post } from "src/app/interfaces/post.interface";

/**
 * Service for managing posts.
 */
@Injectable({
  providedIn: 'root'
})
export class PostsService {
  private pathService = 'http://localhost:9000/api/posts';

  constructor(private http: HttpClient) {}

  /**
   * Retrieves all posts.
   * @returns An observable of an array of posts.
   */
  public getPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(`${this.pathService}`);
  }

  /**
   * Retrieves a specific post by its ID.
   * @param postId - The ID of the post to retrieve.
   * @returns An observable of the requested post.
   */
  public getPost(postId: number): Observable<Post> {
    return this.http.get<Post>(`${this.pathService}/${postId}`);
  }

  /**
   * Creates a new post.
   * @param post - The post object to create.
   * @returns An observable of the created post.
   */
  public createPost(post: Post): Observable<Post> {
    console.log(post);
    return this.http.post<Post>(`${this.pathService}`, post);
  }
}
