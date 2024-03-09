import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { User } from 'src/app/core/interfaces/user.model';
import { AcademicsService } from 'src/app/core/services/academics/academics.service';
import { TeacherService } from 'src/app/core/services/teacher/teacher.service';

@Component({
  selector: 'app-create-subject',
  templateUrl: './create-subject.component.html',
  styleUrls: ['./create-subject.component.css']
})
export class CreateSubjectComponent {
  title: string;
  mode: 'create' | 'edit';
  editId: string;
  // role: 'teacher' | 'student'| 'parent';
  // role: any;
  // teachers: User[];

  //all subscribtions
  private studentsSubscription: Subscription;
  private paramsSubscription: Subscription;
  private registerSubscription: Subscription;


  ngOnDestroy() {
    // Unsubscribe from subscriptions to avoid memory leaks
    if (this.studentsSubscription) {
      this.studentsSubscription.unsubscribe();
    }
    if (this.paramsSubscription) {
      this.paramsSubscription.unsubscribe();
    }
    if (this.registerSubscription) {
      this.registerSubscription.unsubscribe();
    }
  }


  // fetchTeachers() {
  //   this.studentsSubscription = this.teacherService.getAllUsers('teacher').subscribe(
  //     (data: User[]) => {
  //       this.teachers = data;
  //     },
  //     error => {
  //       console.error('Error fetching user data:', error);
  //     }
  //   );
  // }

  registerForm: FormGroup;
  constructor(
    // private service: JwtService,
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar,
    // private router: Router,
    private academicsService: AcademicsService,
    private teacherService: TeacherService
  ) { }

  ngOnInit(): void {
    // this.fetchTeachers();
    this.registerForm = this.fb.group({
      id: [''],
      name: ['', [Validators.required]],
      description: [''],
      // classTeacherId: ['', [Validators.required]],
      // remarks: [''],
    })
    this.route.queryParams.subscribe(params => {
      this.editId = params['id'];
      this.mode = params['mode'];;
      if (this.editId) {
        this.academicsService.getSubject(this.editId).subscribe(
          (details) => {
            console.log("cls:" + details);

            this.registerForm.patchValue({
              id: details.id,
              name: details.name,
              description: details.description,
              // classTeacherId: details.classTeacherId,
              // remarks: details.remarks,
            });
          },
          (error) => {
            console.error(error);
          }
        );
      }
    });
    this.title = (this.mode === 'edit') ? 'Edit' : 'Create';
  }

  // prepareDataForBackend() {
  //   const formData = this.registerForm.value;
  //   const formattedData = {
  //     name: formData.name,
  //     email: formData.email,
  //     password: formData.password,
  //     sex: formData.sex,
  //     phone: formData.phone,
  //     remarks: formData.remarks,
  //     designation: formData.designation,
  //     studentId: formData.studentId,
  //     dateOfBirth: formData.dateOfBirth
  //   };
  //   const field = 'dateOfBirth';

  //   if (formData[field] instanceof Date) {
  //     const dateOfBirth = new Date(formData[field]);
  //     const isoString = new Date(dateOfBirth.getTime() - (dateOfBirth.getTimezoneOffset() * 60000)).toISOString();
  //     formattedData[field] = isoString;
  //   }
  //   return formattedData;
  // }


  submitForm() {
    if (this.mode === 'edit') {
      console.log(this.registerForm.value);
      this.registerSubscription = this.academicsService.editSubjects(this.registerForm.value).subscribe(
        (response) => {
          console.log(response);
          this.resetForm();
          this.showSuccessMessage('Subject updated successfully');
        },
        (error) => {
          this.showErrorMessage(error);
          console.error(error);
        }
      );
    } else {
      console.log(this.registerForm.value);
      // const dataForBackend = this.prepareDataForBackend();
      this.registerSubscription = this.academicsService.createSubjects(this.registerForm.value).subscribe(
        (response) => {
          console.log(response);
          this.resetForm();
          this.showSuccessMessage('Subject added successfully');
        },
        (error) => {
          this.showErrorMessage(error);
          console.error(error);
        }
      );
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