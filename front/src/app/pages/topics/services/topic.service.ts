import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Topic } from "src/app/interfaces/topic.interface";

@Injectable({
    providedIn: 'root'
  })
  export class TopicService {
    private pathService = 'http://localhost:9000/api/topics';
  
    constructor(private http: HttpClient) {}
  
    public getTopics(): Observable<Topic[]> {
      return this.http.get<Topic[]>(`${this.pathService}`);
    }

    public getSubscriptions(): Observable<Topic[]> {
      return this.http.get<Topic[]>(`${this.pathService}/subscriptions`);
    }

    public subscribe(topicId: number): Observable<void> {
      return this.http.post<void>(`${this.pathService}/${topicId}/subscribe`, {});
    }

    public unsubscribe(topicId: number): Observable<void> {
      return this.http.delete<void>(`${this.pathService}/${topicId}/subscribe`);
    }

  
  }