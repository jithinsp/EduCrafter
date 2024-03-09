import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { User } from 'src/app/core/interfaces/user.model';
import { AdminService } from 'src/app/core/services/admin/admin.service';
import { JwtService } from 'src/app/core/services/auth/jwt.service';
import { TeacherService } from 'src/app/core/services/teacher/teacher.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit, OnDestroy {
  title: string;
  mode: 'create' | 'edit';
  // role: 'teacher' | 'student'| 'parent';
  role: any;
  students: User[];

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

  sexOptions = [
    { value: 'MALE', viewValue: 'Male' },
    { value: 'FEMALE', viewValue: 'Female' },
    { value: 'OTHER', viewValue: 'Other' }
  ];

  designationOptions = [
    { value: 'ADMIN', viewValue: 'Admin' },
    { value: 'TEACHER', viewValue: 'Teacher' },
    { value: 'HOD', viewValue: 'HOD' }
  ];

  fetchStudents() {
    this.studentsSubscription = this.teacherService.getAllUsers('student').subscribe(
      (data: User[]) => {
        this.students = data;
      },
      error => {
        console.error('Error fetching user data:', error);
      }
    );
  }

  registerForm: FormGroup;
  constructor(
    private service: JwtService,
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar,
    private router: Router,
    private adminService: AdminService,
    private teacherService: TeacherService
  ) { }
  ngOnInit(): void {
    this.fetchStudents();
    this.registerForm = this.fb.group({
      name: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
      confirmPassword: ['', [Validators.required]],
      sex: ['', [Validators.required]],
      dateOfBirth: ['', [Validators.required]],
      phone: [''],
      remarks: [''],
      studentId: [''], //for parent only
      designation: [''] //for teacher/admin only
    }, {
      validator: this.passwordMatchValidator
    })
    // this.getServerData();
    this.paramsSubscription = this.route.queryParams.subscribe((queryParams) => {
      this.role = queryParams['role'] || 'default';
    });
    this.title = (this.mode === 'edit') ? 'Edit' : 'Create';
  }

  prepareDataForBackend() {
    const formData = this.registerForm.value;
    const formattedData = {
      name: formData.name,
      email: formData.email,
      password: formData.password,
      sex: formData.sex,
      phone: formData.phone,
      remarks: formData.remarks,
      designation: formData.designation,
      studentId: formData.studentId,
      dateOfBirth: formData.dateOfBirth
    };
    const field = 'dateOfBirth';

    if (formData[field] instanceof Date) {
      const dateOfBirth = new Date(formData[field]);
      const isoString = new Date(dateOfBirth.getTime() - (dateOfBirth.getTimezoneOffset() * 60000)).toISOString();
      formattedData[field] = isoString;
    }
    return formattedData;
  }





  // public getServerData(){
  //   this.route.params.subscribe((params) => {
  //     const type = params['role'];
  //     console.log(type);

  //     // this.datasource = params['data']; 
  //     const data = history.state.data;
  //     // this.cash = data;
  //   });
  // }

  passwordMatchValidator(formGroup: FormGroup) {
    const password = formGroup.get('password')?.value;
    const confirmPassword = formGroup.get('confirmPassword')?.value;

    if (password != confirmPassword) {
      formGroup.get('confirmPassword')?.setErrors({ passwordMismatch: true });
    } else {
      formGroup.get('confirmPassword')?.setErrors(null);
    }
  }

  submitForm() {
    if (this.mode === 'edit') {
      console.log("Edit: " + this.registerForm.value);

    } else {
      console.log(this.registerForm.value);
      const dataForBackend = this.prepareDataForBackend();
      this.registerSubscription = this.adminService.registerUser(dataForBackend, this.role).subscribe(
        (response) => {
          console.log(response);
          this.resetForm();
          this.showSuccessMessage('User registered successfully');
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
