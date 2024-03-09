import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './feature/user-management/register/register.component';
import { LoginComponent } from './feature/user-management/login/login.component';
import { DashboardComponent } from './feature/dashboard/dashboard/dashboard.component';
import { LogoutComponent } from './feature/user-management/logout/logout.component';
import { AppComponent } from './app.component';
import { UserListComponent } from './feature/user-management/user-list/user-list.component';
import { UserProfileComponent } from './feature/user-management/user-profile/user-profile.component';
import { AuthGuard } from './core/guards/AuthGuard';
import { AttendanceComponent } from './feature/attendance/attendance/attendance.component';
import { TimetableViewComponent } from './feature/timetable/timetable-view/timetable-view.component';
import { AttendanceViewComponent } from './feature/attendance/attendance-view/attendance-view.component';
import { ClassesComponent } from './feature/classes/classes/classes.component';
import { CreateClassComponent } from './feature/classes/create-class/create-class.component';
import { FeepaymentComponent } from './feature/accounts/feepayment/feepayment.component';
import { AccountsComponent } from './feature/accounts/accounts/accounts.component';
import { ResourcesComponent } from './feature/resources/resources/resources.component';
import { UploadComponent } from './feature/resources/upload/upload.component';
import { SubjectComponent } from './feature/subjects/subject/subject.component';
import { CreateSubjectComponent } from './feature/subjects/create-subject/create-subject.component';
import { PaymentHistoryComponent } from './feature/accounts/payment-history/payment-history.component';
import { NoticeComponent } from './feature/notification/notice/notice.component';
import { NewNoticeComponent } from './feature/notification/new-notice/new-notice.component';
import { ChatComponent } from './feature/notification/chat/chat.component';
import { NewResultComponent } from './feature/results/new-result/new-result.component';
import { NewExamComponent } from './feature/exam/new-exam/new-exam.component';
import { NewSlotComponent } from './feature/slot/new-slot/new-slot.component';
import { NewTimetableComponent } from './feature/timetable/new-timetable/new-timetable.component';
import { ResultsViewComponent } from './feature/results/results-view/results-view.component';
import { ExamViewComponent } from './feature/exam/exam-view/exam-view.component';
import { SlotViewComponent } from './feature/slot/slot-view/slot-view.component';

const routes: Routes = [
  { path: "", redirectTo: "dashboard", pathMatch: 'full'},
  { path: "dashboard", component: DashboardComponent , canActivate: [AuthGuard]},
  { path: "register", component: RegisterComponent},
  { path: "login", component: LoginComponent},
  { path: "logout", component: LogoutComponent},
  { path: "user-list", component: UserListComponent},
  { path: "user-profile", component: UserProfileComponent},
  { path: "teacher-attendance", component: AttendanceComponent},
  { path: "attendance-view", component: AttendanceViewComponent},
  { path: "all-classes", component: ClassesComponent},
  { path: "new-classes", component: CreateClassComponent},
  { path: "fee-payment", component: FeepaymentComponent},
  { path: "accounts", component: AccountsComponent},
  { path: "resources", component: ResourcesComponent},
  { path: "upload", component: UploadComponent},
  { path: "all-subjects", component: SubjectComponent},
  { path: "new-subject", component: CreateSubjectComponent},

  { path: "new-result", component: NewResultComponent},
  { path: "new-exam", component: NewExamComponent},
  { path: "new-slot", component: NewSlotComponent},
  { path: "new-timetable", component: NewTimetableComponent},
  { path: "result-view", component: ResultsViewComponent},
  { path: "exam-view", component: ExamViewComponent},
  { path: "slot-view", component: SlotViewComponent},
  { path: "timetable-view", component: TimetableViewComponent},
  
  { path: "payment-history", component: PaymentHistoryComponent},
  { path: "notice", component: NoticeComponent},
  { path: "new-notice", component: NewNoticeComponent},
  { path: "chat", component: ChatComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
