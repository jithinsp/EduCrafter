import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { User } from 'src/app/core/interfaces/user.model';
import { AcademicsService } from 'src/app/core/services/academics/academics.service';
import { NotificationService } from 'src/app/core/services/notification/notification.service';
import { TeacherService } from 'src/app/core/services/teacher/teacher.service';

@Component({
  selector: 'app-new-notice',
  templateUrl: './new-notice.component.html',
  styleUrls: ['./new-notice.component.css']
})
export class NewNoticeComponent {
  title: string;
  mode: 'create' | 'edit';
  editId: string;
  // role: 'teacher' | 'student'| 'parent';
  // role: any;
  // teachers: User[];

  //all subscribtions
  private subscription: Subscription;

  

  ngOnDestroy() {
    // Unsubscribe from subscriptions to avoid memory leaks
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  registerForm: FormGroup;
  constructor(
    // private service: JwtService,
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar,
    private notificationService: NotificationService,
    private router: Router,
    // private academicsService: AcademicsService,
    // private teacherService: TeacherService
  ) { }

  ngOnInit(): void {

    this.registerForm = this.fb.group({
      id:[''],
      message: [''],
      description: [''],
    })
    this.route.queryParams.subscribe(params => {
      this.editId = params['id'];
      this.mode = params['mode'];;
      if (this.editId) {
        // this.academicsService.getClass(this.editId).subscribe(
        //   (details) => {
        //     console.log("cls:"+details);
            
        //     this.registerForm.patchValue({
        //       id: details.id,
        //     });
        //   },
        //   (error) => {
        //     console.error(error);
        //   }
        // );
      }
    });
    this.title = (this.mode === 'edit') ? 'Edit' : 'Create';
  }

  submitForm() {
    if (this.mode === 'edit') {

    } else {
      console.log(this.registerForm.value);
      // const dataForBackend = this.prepareDataForBackend();
      this.subscription = this.notificationService.publishNotifications(this.registerForm.value).subscribe(
        (response) => {
          console.log(response);
          this.resetForm();
          this.notificationService.sendMessage(response.message);
          this.showSuccessMessage('Notice added successfully');
          this.router.navigateByUrl("notice");
        });
    }
  }

  showErrorMessage(error: any) {
    let errorMessage = 'An error occurred';

    if (error && error.error) {
      // Use the specific error message from the server response if available
      errorMessage = error.error;
    } else if (error && error.message) {
      // Use the error message from the error object if available
      errorMessage = error.message;
    }
    this.showSuccessMessage(errorMessage);
  }

  showSuccessMessage(message: string) {
    this.snackBar.open(message, 'Close', {
      duration: 3000 // (in milliseconds)
    });
  }

  resetForm() {
    this.registerForm.reset();
    Object.keys(this.registerForm.controls).forEach(key => {
      this.registerForm.get(key).setErrors(null);
    });
    this.registerForm.markAsUntouched();
  }

}