import { NgModule, isDevMode } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './feature/common/register/register.component';
import { LoginComponent } from './feature/common/login/login.component';
import { DashboardComponent } from './feature/common/dashboard/dashboard.component';
import { LogoutComponent } from './feature/common/logout/logout.component';
import { NavbarComponent } from './feature/common/navbar/navbar.component';
import { UserListComponent } from './feature/common/user-list/user-list.component';
import { UserProfileComponent } from './feature/common/user-profile/user-profile.component';
import { StoreModule } from '@ngrx/store';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatRadioModule } from '@angular/material/radio';
import { MatDialogModule } from '@angular/material/dialog';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { UserReducer } from './store/uesr.reducer';
import { EffectsModule } from '@ngrx/effects';
import { UserEffects } from './store/user.effects';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import {MatNativeDateModule} from '@angular/material/core';
import { AuthGuard } from './core/guards/AuthGuard';
import { DeleteConfirmationComponent } from './shared/reusableComponents/delete-confirmation/delete-confirmation.component';
import { AuthInterceptor } from './core/interceptor/auth-interceptor.interceptor';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatMenuModule} from '@angular/material/menu';


@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    DashboardComponent,
    LogoutComponent,
    NavbarComponent,
    UserListComponent,
    UserProfileComponent,
    DeleteConfirmationComponent,
  ],
  imports: [
    BrowserModule,
    MatSnackBarModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatCardModule, 
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatCheckboxModule,
    MatRadioModule,
    MatDialogModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatExpansionModule,
    MatMenuModule,
    BrowserAnimationsModule,
    StoreModule.forRoot({user:UserReducer}),
    EffectsModule.forRoot([UserEffects]),
    StoreDevtoolsModule.instrument({maxAge:50, logOnly: !isDevMode()})
  ],
  providers: [
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
