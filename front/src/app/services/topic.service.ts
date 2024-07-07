import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Topic } from "src/app/interfaces/topic.interface";

@Injectable({
  providedIn: 'root'
})
/**
 * Service for managing topics.
 */
export class TopicService {
  private pathService = 'http://localhost:9000/api/topics';

  constructor(private http: HttpClient) {}

  /**
   * Retrieves all topics.
   * @returns An observable of an array of topics.
   */
  public getTopics(): Observable<Topic[]> {
    return this.http.get<Topic[]>(`${this.pathService}`);
  }

  /**
   * Retrieves subscribed topics.
   * @returns An observable of an array of subscribed topics.
   */
  public getSubscriptions(): Observable<Topic[]> {
    return this.http.get<Topic[]>(`${this.pathService}/subscriptions`);
  }

  /**
   * Subscribes to a topic.
   * @param topicId - The ID of the topic to subscribe to.
   * @returns An observable of void.
   */
  public subscribe(topicId: number): Observable<void> {
    return this.http.post<void>(`${this.pathService}/${topicId}/subscribe`, {});
  }

  /**
   * Unsubscribes from a topic.
   * @param topicId - The ID of the topic to unsubscribe from.
   * @returns An observable of void.
   */
  public unsubscribe(topicId: number): Observable<void> {
    return this.http.delete<void>(`${this.pathService}/${topicId}/subscribe`);
  }
}
