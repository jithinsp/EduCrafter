import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { CalendarEvent,CalendarView } from 'angular-calendar';
import { Subscription, map } from 'rxjs';
import { User } from 'src/app/core/interfaces/user.model';
import { JwtService } from 'src/app/core/services/auth/jwt.service';
import { TeacherService } from 'src/app/core/services/teacher/teacher.service';

@Component({
  selector: 'app-attendance-view',
  templateUrl: './attendance-view.component.html',
  styleUrls: ['./attendance-view.component.css']
})
export class AttendanceViewComponent implements OnInit,OnDestroy {
  // view: string = 'month'; // or 'week', 'day', etc.
  // viewDate: Date = new Date();
  // events: CalendarEvent[] = [
  //   // Define your events here
  // ];

  // view: CalendarView = CalendarView.Month;

  // CalendarView = CalendarView;



  // // students: User[] = [];
  // userName: string;
  // role: string;
  // attendance: any[] = [];
  // // param:string;
  // displayedColumns: string[] = ['index', 'date', 'attended'];

  // dataSource: MatTableDataSource<any>;
  // @ViewChild(MatPaginator) paginatior !: MatPaginator;
  // @ViewChild(MatSort) sort !: MatSort;
  // Filterchange(data: Event) {
  //   const value = (data.target as HTMLInputElement).value;
  //   this.dataSource.filter = value.trim().toLowerCase();
  // }


 

  // ngOnInit(){
  //   this.fetchAttendance();
  //   this.userName = this.authService.extractUsername();
  //   this.role = this.authService.extractRole();
  //   // this.filterDataForLoggedInStudent();
  //   // this.fetchStudents();
  //   // this.fetchAttendance();
  // }

  // fetchAttendance(){
  //   this.attendanceSubscription = this.teacherService.getAttendanceOfStudent().subscribe((data: any[])=>{
  //     this.attendance=data;
  //     console.log(this.attendance);
  //     this.dataSource = new MatTableDataSource( this.attendance);
  //     // this.dataSource.paginator = this.paginatior;
  //     // this.dataSource.sort = this.sort;
  //   },
  //   error => {
  //     console.error('Error fetching user data:', error);
  //   }
  // );
  // }


   constructor(
    private teacherService: TeacherService,
    private authService: JwtService,
  private router: Router
  ){}


  ngOnInit() {
    this.loadAll();
  }

  showCreateButton = false; 

  dataList: any[] = [];
  headArray = [
    { 'Head': 'No', 'FieldName': 'no' },
    { 'Head': 'Date', 'FieldName': 'date' },
    { 'Head': 'Status', 'FieldName': 'attended' },
    // { 'Head': 'Amount', 'FieldName': 'amount' },
    // { 'Head': 'Date', 'FieldName': 'createdDate' },
    // { 'Head': 'Action', 'FieldName': '' }
  ];

  // loadAll() {
  //   this.attendanceSubscription = this.teacherService.getAttendanceOfStudent().subscribe((res) => {
  //     this.dataList = res;
  //     console.log(res);
  //   }
  //   );
  // }
  loadAll() {
    this.attendanceSubscription = this.teacherService.getAttendanceOfStudent().pipe(
      map((res) => {
        // Transform the 'attended' property to 'Preset' or 'Absent'
        return res.map(item => {
          return { ...item, attended: item.attended ? 'Preset' : 'Absent' };
        });
      })
    ).subscribe(
      (transformedData) => {
        this.dataList = transformedData;
        console.log(transformedData);
      }
    );
  }

  edit(item: any) {
    // const mode ='edit';
    // console.log(item.id);
    // const id = item.id;
    // this.router.navigate(['new-classes'], { queryParams: { id, mode } });
  }

  delete(item: any) {
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
    // this.router.navigateByUrl("new-classes");
    // this.router.navigate(['register'], { queryParams: { role: this.param } });
  }

    //all subscribtions
    // private studentsSubscription: Subscription;
    private attendanceSubscription: Subscription;

  ngOnDestroy() {
    // Unsubscribe from subscriptions to avoid memory leaks
    // if (this.studentsSubscription) {
    //   this.studentsSubscription.unsubscribe();
    // }

    if (this.attendanceSubscription) {
      this.attendanceSubscription.unsubscribe();
    }
  }
  
}
