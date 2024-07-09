import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/auth/login/login.component';
import { RegisterComponent } from './pages/auth/register/register.component';
import { LayoutComponent } from './layout/layout.component';
import { FeedComponent } from './pages/posts/feed/feed.component';
import { NewPostComponent } from './pages/posts/new-post/new-post.component';
import { MeComponent } from './pages/auth/me/me.component';
import { ListComponent } from './pages/topics/list/list.component';
import { AuthGuard } from './security/auth.guard';
import { PostComponent } from './pages/posts/post/post.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  {
    path: 'auth',
    component: LayoutComponent,
    children: [
      { path: 'login', component: LoginComponent },
      { path: 'register', component: RegisterComponent },
    ],
  },
  {
    path: 'me',
    component : LayoutComponent,
    canActivate: [AuthGuard],
    children: [
      { path: '', component: MeComponent },
    ],
  },
  {
    path: 'posts',
    component: LayoutComponent,
    canActivate: [AuthGuard],
    children: [
      { path: 'feed', component: FeedComponent },
      { path: 'create', component: NewPostComponent },
      { path: ':postId', component: PostComponent }
    ],
  },
  {
    path: 'topics',
    component: LayoutComponent,
    canActivate: [AuthGuard],
    children: [
      { path: '', component: ListComponent },
    ],
  },
  { path: '**', redirectTo: ''},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
