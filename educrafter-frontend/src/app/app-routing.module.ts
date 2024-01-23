import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './feature/common/register/register.component';
import { LoginComponent } from './feature/common/login/login.component';
import { DashboardComponent } from './feature/common/dashboard/dashboard.component';
import { LogoutComponent } from './feature/common/logout/logout.component';
import { AppComponent } from './app.component';
import { UserListComponent } from './feature/common/user-list/user-list.component';
import { UserProfileComponent } from './feature/common/user-profile/user-profile.component';
import { AuthGuard } from './core/guards/AuthGuard';

const routes: Routes = [
  { path: "", redirectTo: "dashboard", pathMatch: 'full'},
  { path: "dashboard", component: DashboardComponent , canActivate: [AuthGuard]},
  { path: "register", component: RegisterComponent},
  { path: "login", component: LoginComponent},
  { path: "logout", component: LogoutComponent},
  { path: "user-list", component: UserListComponent},
  { path: "user-profile", component: UserProfileComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
