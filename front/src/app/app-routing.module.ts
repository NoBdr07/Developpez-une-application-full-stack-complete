import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/auth/login/login.component';
import { RegisterComponent } from './pages/auth/register/register.component';
import { LayoutComponent } from './pages/auth/layout/layout.component';
import { FeedComponent } from './pages/posts/feed/feed.component';
import { NewPostComponent } from './pages/posts/new-post/new-post.component';
import { MeComponent } from './pages/auth/me/me.component';

// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes = [
  { path: '', component: HomeComponent },
  {
    path: 'auth',
    component: LayoutComponent,
    children: [
      { path: 'login', component: LoginComponent },
      { path: 'register', component: RegisterComponent },
      { path : 'me', component: MeComponent}
    ],
  },
  {
    path: 'posts',
    component: LayoutComponent,
    children: [
      { path: 'feed', component: FeedComponent },
      { path: 'create', component: NewPostComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
