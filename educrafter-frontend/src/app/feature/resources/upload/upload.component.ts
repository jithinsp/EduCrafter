import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AcademicsService } from 'src/app/core/services/academics/academics.service';
import { AdminService } from 'src/app/core/services/admin/admin.service';
import { JwtService } from 'src/app/core/services/auth/jwt.service';
import { ResourcesService } from 'src/app/core/services/resources/resources.service';
import { TeacherService } from 'src/app/core/services/teacher/teacher.service';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent {
  files: string[] = [];
  selectedFile: File | null = null;
  errorMessage: string | null = null;
  title: string;
  mode: 'create' | 'edit';
  editId: string;
  registerForm: FormGroup;
  classes: any[];
  subjects: any[];

    //all subscribtions
    private classesSubscription: Subscription;
    private subjectsSubscription: Subscription;
    private registerSubscription: Subscription;
    
  
    ngOnDestroy() {
      // Unsubscribe from subscriptions to avoid memory leaks
      if (this.classesSubscription) {
        this.classesSubscription.unsubscribe();
      }
      if (this.subjectsSubscription) {
        this.subjectsSubscription.unsubscribe();
      }
      if (this.registerSubscription) {
        this.registerSubscription.unsubscribe();
      }
    }

  constructor(
    private resourcesService: ResourcesService,
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar,
    private authService: JwtService,
    private adminrService: AdminService,
    private academicsService: AcademicsService,
    private router: Router) { }

  fetchClasses(){
    this.classesSubscription = this.academicsService.getAllClasses().subscribe(
      (res) => {
        this.classes = res.filter(item => !item.isDeleted);
      }
    );
  }

  fetchSubjects(){
    this.classesSubscription = this.academicsService.getAllSubjects().subscribe(
      (res) => {
        this.subjects = res.filter(item => !item.isDeleted);
      }
    );
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    this.registerForm.patchValue( this.selectedFile );
  }



  ngOnInit(): void {
    this.fetchClasses();
    this.fetchSubjects();
    this.registerForm = this.fb.group({
      id:[''],
      classes: [''],
      subject: [''],
      file: [null],
      // remarks: [''],
    })
    this.route.queryParams.subscribe(params => {
      this.editId = params['id'];
      this.mode = params['mode'];;
      if (this.editId) {
        this.resourcesService.getFile(this.editId).subscribe(
          (details) => {
            console.log("cls:"+details);
            
            this.registerForm.patchValue({
              id: details.id,
              classes: details.classes,
              subject: details.subject,
              file: details.file,
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

  submitForm() {
    if (this.mode === 'edit') {
      console.log(this.registerForm.value);
      // this.registerSubscription = this.academicsService.editClasses(this.registerForm.value).subscribe(
      //   (response) => {
      //     console.log(response);
      //     this.resetForm();
      //     this.showSuccessMessage('Class updated successfully');
      //   },
      //   (error) => {
      //     this.showErrorMessage(error);
      //     console.error(error);
      //   }
      // );
    } else {
      // const dataForBackend = this.prepareDataForBackend();
      const formData = new FormData();
      formData.append('file', this.selectedFile);
      formData.append('classes', this.registerForm.value.classes);
      formData.append('subject', this.registerForm.value.subject);

      // this.registerForm.get('file').setValue(formData);
      console.log(this.registerForm.value);
      this.registerSubscription = this.resourcesService.uploadFile(formData).subscribe(
        (response) => {
          console.log(response);
          this.resetForm();
          this.showSuccessMessage('Resource uploaded successfully');
        },
        (error) => {
          this.showErrorMessage(error);
          console.error(error);
        }
      );
    }
  }

  // uploadFile() {
  //   if (this.selectedFile) {
  //     this.resourcesService.uploadFile(this.selectedFile).subscribe(
  //       () => {
  //         this.router.navigateByUrl("resources");
  //         // this.fetchFiles();
  //       }
  //     );
  //   }
  // }

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
