import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Post } from "src/app/interfaces/post.interface";

@Injectable({
    providedIn: 'root'
  })
  export class PostsService {
    private pathService = 'http://localhost:9000/api/posts';
  
    constructor(private http: HttpClient) {}
  
    public getPosts(): Observable<Post[]> {
      return this.http.get<Post[]>(`${this.pathService}`);
    }

    public getPost(postId: number): Observable<Post> {
      return this.http.get<Post>(`${this.pathService}/${postId}`);
    }
  
    public createPost(post: Post): Observable<Post> {
      console.log(post);
      return this.http.post<Post>(`${this.pathService}`, post);
    }
  }