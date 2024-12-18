import { Component, OnDestroy } from '@angular/core';
import { Validators } from '@angular/forms';
import { FormBuilder } from '@angular/forms';
import { PostsService } from 'src/app/services/posts.service';
import { Post } from 'src/app/interfaces/post.interface';
import { Router } from '@angular/router';
import { TopicService } from 'src/app/services/topic.service';
import { Subscription } from 'rxjs';

/**
 * Component for creating a new post.
 */
@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.scss']
})
export class NewPostComponent implements OnDestroy {
  /**
   * Represents the subscriptions of the component.
   */
  private subscription = new Subscription();

  /**
   * Observable that emits the list of topics.
   */
  topics$ = this.topicService.getTopics();

  /**
   * Form group for the new post.
   */
  public form = this.fb.group({
    topic: ['', Validators.required],
    title: ['', [Validators.required, Validators.minLength(5)]],
    content: ['', [Validators.required, Validators.minLength(50)]],
  });


  constructor(
    private postsService: PostsService,
    private fb: FormBuilder,
    private router: Router,
    private topicService: TopicService,
  ) { }

  /**
   * Submits the new post form and creates a new post.
   */
  public submit(): void {
    const post = this.form.value as unknown as Post;
    const sub = this.postsService.createPost(post).subscribe(
      () => {
        this.router.navigate(['/posts/feed']);
      }
    );
    this.subscription.add(sub);
  }
  /**
   * 
   * Method to update content 
   */

  onContentChange(event: any): void {
    const editorContent = event.target.innerHTML; // Récupère le contenu de Trix
    this.form.patchValue({ content: editorContent }); // Mets à jour le champ "content" dans le formulaire
  }
  

  /**
   * Navigates back to the previous page.
   */
  back() {
    window.history.back();
  }

  /**
   * Unsubscribes from the subscriptions.
   */
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
