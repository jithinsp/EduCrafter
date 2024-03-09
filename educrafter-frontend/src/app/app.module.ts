import { NgModule, isDevMode } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './feature/user-management/register/register.component';
import { LoginComponent } from './feature/user-management/login/login.component';
import { DashboardComponent } from './feature/dashboard/dashboard/dashboard.component';
import { LogoutComponent } from './feature/user-management/logout/logout.component';
import { NavbarComponent } from './feature/navbars/navbar/navbar.component';
import { UserListComponent } from './feature/user-management/user-list/user-list.component';
import { UserProfileComponent } from './feature/user-management/user-profile/user-profile.component';
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
import { AttendanceComponent } from './feature/attendance/attendance/attendance.component';
import { TimetableViewComponent } from './feature/timetable/timetable-view/timetable-view.component';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { AttendanceViewComponent } from './feature/attendance/attendance-view/attendance-view.component';
import { MytableComponent } from './shared/reusableComponents/mytable/mytable.component';
import { ClassesComponent } from './feature/classes/classes/classes.component';
import { CreateClassComponent } from './feature/classes/create-class/create-class.component';
import { FeepaymentComponent } from './feature/accounts/feepayment/feepayment.component';
import { AccountsComponent } from './feature/accounts/accounts/accounts.component';
import { ResourcesComponent } from './feature/resources/resources/resources.component';
import { UploadComponent } from './feature/resources/upload/upload.component';
import { ErrorInterceptorService } from './core/interceptor/error-interceptor.service';
import { VideoplayerComponent } from './shared/reusableComponents/videoplayer/videoplayer.component';
import { SubjectComponent } from './feature/subjects/subject/subject.component';
import { CreateSubjectComponent } from './feature/subjects/create-subject/create-subject.component';
import { PaymentHistoryComponent } from './feature/accounts/payment-history/payment-history.component';
import { NoticeComponent } from './feature/notification/notice/notice.component';
import { NewNoticeComponent } from './feature/notification/new-notice/new-notice.component';
import { ServiceWorkerModule } from '@angular/service-worker';
import { ChatComponent } from './feature/notification/chat/chat.component';
import { ResultsViewComponent } from './feature/results/results-view/results-view.component';
import { NewResultComponent } from './feature/results/new-result/new-result.component';
import { ExamViewComponent } from './feature/exam/exam-view/exam-view.component';
import { NewExamComponent } from './feature/exam/new-exam/new-exam.component';
import { NewSlotComponent } from './feature/slot/new-slot/new-slot.component';
import { SlotViewComponent } from './feature/slot/slot-view/slot-view.component';
import { NewTimetableComponent } from './feature/timetable/new-timetable/new-timetable.component';
// import { NgxStompjsModule } from '@stomp/ng2-stompjs';
// import { MatFileInputModule } from 'mat-file-input';
// import { ContextMenuComponent } from './path-to-context-menu.component';


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
    AttendanceComponent,
    TimetableViewComponent,
    AttendanceViewComponent,
    MytableComponent,
    ClassesComponent,
    CreateClassComponent,
    FeepaymentComponent,
    AccountsComponent,
    ResourcesComponent,
    UploadComponent,
    VideoplayerComponent,
    SubjectComponent,
    CreateSubjectComponent,
    PaymentHistoryComponent,
    NoticeComponent,
    NewNoticeComponent,
    ChatComponent,
    ResultsViewComponent,
    NewResultComponent,
    ExamViewComponent,
    NewExamComponent,
    NewSlotComponent,
    SlotViewComponent,
    NewTimetableComponent,
    // ContextMenuComponent
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
    // MatFileInputModule,
    BrowserAnimationsModule,
    // NgxStompjsModule.forRoot(),
    StoreModule.forRoot({user:UserReducer}),
    EffectsModule.forRoot([UserEffects]),
    StoreDevtoolsModule.instrument({maxAge:50, logOnly: !isDevMode()}),
    CalendarModule.forRoot({ provide: DateAdapter, useFactory: adapterFactory }),
    ServiceWorkerModule.register('ngsw-worker.js', {
      enabled: !isDevMode(),
      // Register the ServiceWorker as soon as the application is stable
      // or after 30 seconds (whichever comes first).
      registrationStrategy: 'registerWhenStable:30000'
    })
  ],
  providers: [
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorInterceptorService,
      multi: true,
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
