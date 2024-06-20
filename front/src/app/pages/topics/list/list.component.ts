import { Component, OnInit } from '@angular/core';
import { TopicService } from '../services/topic.service';
import { Observable, combineLatest, map } from 'rxjs';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {

  public topics$ = this.topicService.getTopics();

  public subscriptions$ = this.topicService.getSubscriptions();

  public topicsSubs$: Observable<{ isSubscribed: boolean; topicId: number; name: string; description: string; }[]> | undefined;

  constructor(
    private topicService: TopicService
  ) { }

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

  subscribe(topicId: number): void {
    this.topicService.subscribe(topicId).subscribe(() => {
      this.mergeTopicsAndSubscriptions();
    });
  }

  ngOnInit(): void {
    this.mergeTopicsAndSubscriptions();
  }

}
