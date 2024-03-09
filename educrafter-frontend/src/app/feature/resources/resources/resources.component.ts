import { HttpErrorResponse } from '@angular/common/http';
import { Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JwtService } from 'src/app/core/services/auth/jwt.service';
import { ResourcesService } from 'src/app/core/services/resources/resources.service';
import { saveAs } from 'file-saver';
import { DomSanitizer } from '@angular/platform-browser';
import { MatDialog } from '@angular/material/dialog';
import { VideoplayerComponent } from 'src/app/shared/reusableComponents/videoplayer/videoplayer.component';

@Component({
  selector: 'app-resources',
  templateUrl: './resources.component.html',
  styleUrls: ['./resources.component.css']
})
export class ResourcesComponent implements OnInit {

  // files: string[] = [];
  selectedFile: File | null = null;
  errorMessage: string | null = null;
  role: string = '';
  showVideo: boolean = false;

  constructor(private resourcesService: ResourcesService,
    private authService: JwtService,
    private router: Router,
    public dialog: MatDialog,
    private sanitizer: DomSanitizer) { }

  ngOnInit() {
    this.fetchFiles();
    this.role = this.authService.extractRole();
  }

  fetchFiles() {
    this.subscription = this.resourcesService.getFiles().subscribe(
      (files) => {
        console.log(files);

        // this.files = files;
        // this.dataList = files;
        this.errorMessage = null;
        this.dataList = files.map(file => {
          return {
            id: file.id,
            createdDate: file.createdDate,
            lastModifiedDate: file.lastModifiedDate,
            isEnabled: file.isEnabled,
            isDeleted: file.isDeleted,
            filepath: file.filepath,
            classes: file.classes ? file.classes.classes : null,
            subject: file.subject ? file.subject.name : null
          };
        });
        console.log(this.dataList);
      }
      // ,(error: HttpErrorResponse) => {
      //   this.errorMessage = error.status === 404 ? 'Files not found' : 'Error fetching files';
      // }
    );
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  // uploadFile() {
  //   if (this.selectedFile) {
  //     this.resourcesService.uploadFile(this.selectedFile).subscribe(
  //       () => {
  //         this.fetchFiles();
  //       }
  //     );
  //   }
  // }

  showCreateButton = (this.authService.extractRole() === '[TEACHER]') ? true : false;
  showEditButton = false;
  showDeleteButton = (this.authService.extractRole() === '[TEACHER]') ? true : false;
  showViewButton = true;
  showToggleButton = (this.authService.extractRole() === '[TEACHER]') ? true : false;
  title = 'Resources';


  dataList: any[] = [];
  headArray = [
    { 'Head': 'No', 'FieldName': 'no' },
    { 'Head': 'File Name', 'FieldName': 'filepath' },
    { 'Head': 'Subject', 'FieldName': 'subject' },
    { 'Head': 'Class', 'FieldName': 'classes' },
    // { 'Head': 'Date', 'FieldName': 'createdDate' },
    { 'Head': 'Action', 'FieldName': '' }
  ];



  edit(item: any) {
    // const mode ='edit';
    // console.log(item.id);
    // const id = item.id;
    // this.router.navigate(['new-classes'], { queryParams: { id, mode } });
  }

  delete(item: any) {
    this.fetchFiles();
    // debugger;
    // this.paymentService.deleteClasses(item).subscribe((res) => {
    //   console.log(res);

    // },
    //   error => {
    //     console.error('Error fetching user data:', error);
    //   }
    // );
  }


  toggle(item: any) {
    this.fetchFiles();
    // debugger;
    // this.paymentService.deleteClasses(item).subscribe((res) => {
    //   console.log(res);

    // },
    //   error => {
    //     console.error('Error fetching user data:', error);
    //   }
    // );
  }

  create() {
    this.router.navigateByUrl("upload");
    // this.router.navigate(['register'], { queryParams: { role: this.param } });
  }


  @ViewChild('videoPlayer') videoPlayer: ElementRef;

  view(item: any): void {
    this.subscriptionV = this.resourcesService.getFile(item).subscribe(
      (data: Blob) => {
        // Save the file using file-saver library
        // saveAs(data, item.filepath);

        // Play the video
        this.playVideo(data);
      }
    );
  }

  close(){
    this.showVideo=false;
  }

  playVideo(item: any): void {
    this.showVideo=true;
    // const dialogRef = this.dialog.open(VideoplayerComponent);
    const videoUrl = URL.createObjectURL(new Blob([item], { type: 'video/mp4' }));
    this.videoPlayer.nativeElement.src = videoUrl;
    this.videoPlayer.nativeElement.play();
  }

  //   this.selectedFile = files;
  // //   console.log(files);
  // //   this.errorMessage = null;
  // // }
  // const blob = new Blob([files], { type: 'application/octet-stream' });

  //   // Create a link element and trigger a download
  //   const link = document.createElement('a');
  //   link.href = window.URL.createObjectURL(blob);
  //   link.download = 'filename.ext';
  //   link.click();

  //   // Clean up the object URL to avoid memory leaks
  //   window.URL.revokeObjectURL(link.href);
  // });
  // }
  // this.router.navigateByUrl("new-classes");
  // this.router.navigate(['register'], { queryParams: { role: this.param } });

  private subscription: Subscription;
  private subscriptionV: Subscription;

  ngOnDestroy() {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
    if (this.subscriptionV) {
      this.subscriptionV.unsubscribe();
    }
  }
}
