/**
 * Represents the ListComponent class that displays a list of topics.
 */
import { Component, OnDestroy, OnInit } from '@angular/core';
import { TopicService } from '../../../services/topic.service';
import { Observable, Subscription, combineLatest, map } from 'rxjs';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit, OnDestroy {

  /**
   * Represents all the subscriptions of the component.
   */
  private subs = new Subscription();

  /**
   * Represents an observable of topics.
   */
  public topics$ = this.topicService.getTopics();

  /**
   * Represents an observable of subscriptions.
   */
  public subscriptions$ = this.topicService.getSubscriptions();

  /**
   * Represents an observable of topics with subscription information.
   */
  public topicsSubs$: Observable<{ isSubscribed: boolean; topicId: number; name: string; description: string; }[]> | undefined;

  constructor(
    private topicService: TopicService
  ) { }

  /**
   * Merges the topics and subscriptions observables to create an observable of topics with subscription information.
   * The isSubscribed property alow the template to disable the button if the user is already subscribed to the topic.
   */
  mergeTopicsAndSubscriptions(): void {
    this.topicsSubs$ = combineLatest([this.topics$, this.subscriptions$]).pipe(
      map(([topics, subscriptions]) => 
        topics.map(topic => ({
          ...topic,
          isSubscribed: subscriptions.some(subscription => subscription.topicId === topic.topicId)
        }))
      )
    );
  }

  /**
   * Subscribes to a topic.
   * @param topicId - The ID of the topic to subscribe to.
   */
  subscribe(topicId: number): void {
    const sub = this.topicService.subscribe(topicId).subscribe(() => {
      this.mergeTopicsAndSubscriptions();
    });
    this.subs.add(sub);
  }

  ngOnInit(): void {
    this.mergeTopicsAndSubscriptions();
  }

  /**
   * Unsubscribes from all subscriptions when the component is destroyed.
   */
  ngOnDestroy(): void {
    this.subs.unsubscribe();
  }
}
