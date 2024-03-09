import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { User } from 'src/app/core/interfaces/user.model';
import { AcademicsService } from 'src/app/core/services/academics/academics.service';
import { AdminService } from 'src/app/core/services/admin/admin.service';
import { JwtService } from 'src/app/core/services/auth/jwt.service';
import { TeacherService } from 'src/app/core/services/teacher/teacher.service';

@Component({
  selector: 'app-create-class',
  templateUrl: './create-class.component.html',
  styleUrls: ['./create-class.component.css']
})
export class CreateClassComponent {
  title: string;
  mode: 'create' | 'edit';
  editId: string;
  // role: 'teacher' | 'student'| 'parent';
  // role: any;
  teachers: User[];

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


  fetchTeachers() {
    this.studentsSubscription = this.teacherService.getAllUsers('teacher').subscribe(
      (data: User[]) => {
        this.teachers = data;
      },
      error => {
        console.error('Error fetching user data:', error);
      }
    );
  }

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

  validateClassRange(control) {
    const value = control.value;
    const isValid = value >= 1 && value <= 12;
    return isValid ? null : { invalidClassRange: true };
  }

  validateDivisionFormat(control) {
    const value = control.value;
    const isValid = /^[a-zA-Z]+$/.test(value);
    return isValid ? null : { invalidDivisionFormat: true };
  }

  ngOnInit(): void {
    this.fetchTeachers();
    this.registerForm = this.fb.group({
      id:[''],
      classes: ['', [Validators.required, this.validateClassRange]],
      division: ['', [Validators.required, this.validateDivisionFormat]],
      classTeacherId: ['', [Validators.required]],
      remarks: [''],
    })
    this.route.queryParams.subscribe(params => {
      this.editId = params['id'];
      this.mode = params['mode'];;
      if (this.editId) {
        this.academicsService.getClass(this.editId).subscribe(
          (details) => {
            console.log("cls:"+details);
            
            this.registerForm.patchValue({
              id: details.id,
              classes: details.classes,
              division: details.division,
              classTeacherId: details.classTeacherId,
              remarks: details.remarks,
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
      this.registerSubscription = this.academicsService.editClasses(this.registerForm.value).subscribe(
        (response) => {
          console.log(response);
          this.resetForm();
          this.showSuccessMessage('Class updated successfully');
        },
        (error) => {
          this.showErrorMessage(error);
          console.error(error);
        }
      );
    } else {
      console.log(this.registerForm.value);
      // const dataForBackend = this.prepareDataForBackend();
      this.registerSubscription = this.academicsService.createClass(this.registerForm.value).subscribe(
        (response) => {
          console.log(response);
          this.resetForm();
          this.showSuccessMessage('Class added successfully');
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