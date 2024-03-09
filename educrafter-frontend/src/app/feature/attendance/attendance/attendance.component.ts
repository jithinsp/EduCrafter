import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { User } from 'src/app/core/interfaces/user.model';
import { TeacherService } from 'src/app/core/services/teacher/teacher.service';

@Component({
  selector: 'app-attendance',
  templateUrl: './attendance.component.html',
  styleUrls: ['./attendance.component.css']
})
export class AttendanceComponent {

  students: User[] = [];
  attendance: any[] = [];
  updatedAttendance: any[] = [];

  param:string;

  //columns of table
  displayedColumns: string[] = ['index', 'name', 'date', 'attendanceStatus', 'attendance'];

  //table datasource
  dataSource: MatTableDataSource<User>;
  
  //all subscribtions
  private studentsSubscription: Subscription;
  private attendanceSubscription: Subscription;
  private tickSubscription: Subscription;
  

  @ViewChild(MatPaginator) paginatior !: MatPaginator;
  @ViewChild(MatSort) sort !: MatSort;

  Filterchange(data: Event) {
    const value = (data.target as HTMLInputElement).value;
    this.dataSource.filter = value.trim().toLowerCase();
  }

  constructor(
    private teacherService: TeacherService
  ){}

  ngOnInit(){
    this.fetchData();
    // this.fetchStudents();
    // this.fetchAttendance();
  }

  // fetchAttendance(){
  //   this.teacherService.getAttendance().subscribe((data: any[])=>{
  //     this.attendance=data;
  //     console.log(this.attendance);
  //     // this.dataSource = new MatTableDataSource(this.students);
  //     // this.dataSource.paginator = this.paginatior;
  //     // this.dataSource.sort = this.sort;
  //   },
  //   error => {
  //     console.error('Error fetching user data:', error);
  //   }
  // );
  // }

  // fetchStudents(){
  //   this.teacherService.getAttendanceOfStudents().subscribe((data: User[])=>{
  //     this.students=data;
  //     console.log(this.students);
  //     this.dataSource = new MatTableDataSource(this.students);
  //     this.dataSource.paginator = this.paginatior;
  //     this.dataSource.sort = this.sort;
  //   },
  //   error => {
  //     console.error('Error fetching user data:', error);
  //   }
  // );
  // }

  // onCheckboxChange(user: any) {
  //   console.log(user);
    
  //   const userId:string = user.id;
  //   // const userId:string = user.studentInfo.id;

  //   // Assuming user.isSelected is a boolean indicating the checkbox state
  //   const isPresent:boolean = user.isPresent;

  //   this.tickSubscription = this.teacherService.markAttendance(userId, isPresent).subscribe(
  //     (response) => {
  //       console.log('Backend response:', response);
  //       // Handle success if needed
  //     },
  //     (error) => {
  //       console.error('Error updating user selection:', error);
  //     }
  //   );
  // }

  onCheckboxChange(user: any) {
    console.log(user);
  
    const userId: string = user.id;
    const isPresent: boolean = user.isPresent;
  
    // Update the array with the changed data
    const updatedUser = { userId: userId, isPresent: isPresent };
    const existingIndex = this.updatedAttendance.findIndex(item => item.userId === userId);
  
    if (existingIndex !== -1) {
      this.updatedAttendance[existingIndex] = updatedUser;
    } else {
      this.updatedAttendance.push(updatedUser);
    }
  }

  onSubmit() {
    console.log(this.updatedAttendance);
    
    if (this.updatedAttendance.length > 0) {
      this.teacherService.markAttendance(this.updatedAttendance).subscribe(
        (response) => {
          console.log('Backend response:', response);
          this.fetchData();
        },
        (error) => {
          console.error('Error updating user selections:', error);
        }
      );
    }
  }
  
  fetchData() {
    this.studentsSubscription = this.teacherService.getAttendanceOfStudents().subscribe(
      (students: User[]) => {
        this.students = students;
        this.attendanceSubscription = this.teacherService.getAttendance().subscribe(
          (attendance: any[]) => {
            this.attendance = attendance;
            this.combineDataAndInitializeTable();
            console.log(attendance);
          },
          error => {
            console.error('Error fetching attendance data:', error);
          }
        );
        console.log(students);
      },
      error => {
        console.error('Error fetching student data:', error);
      }
    );
  }
  combineDataAndInitializeTable() {
    const combinedData = this.students.map(student => {
      const studentAttendance = this.attendance.filter(item => item.student.id === student.id) || [];
  
      const hasDataForDate = (date: Date) => studentAttendance.some(attendance => this.isSameDate(attendance.date, date));
  
      const studentWithHasData = {
        ...student,
        attendances: studentAttendance,
        hasData: (date: Date) => hasDataForDate(date)
      };
  
      return studentWithHasData;
    });
  
    console.log("Combi: ");
    console.log(combinedData);
  
    // Initialize MatTableDataSource with combinedData
    this.dataSource = new MatTableDataSource(combinedData);
    // this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  isSameDate(date1: Date, date2: Date): boolean {
    return date1.getFullYear() === date2.getFullYear() &&
      date1.getMonth() === date2.getMonth() &&
      date1.getDate() === date2.getDate();
  }

  dateClassCallback = (date: Date) => {
    // Check if any student has data for the given date
    const hasDataForDate = this.dataSource.data.some(student =>
      this.attendance.some(attendance => this.isSameDate(attendance.date, date) && attendance.attended)
    );
  
    return hasDataForDate ? 'has-data' : '';
  };
  
  // combineDataAndInitializeTable() {
  //   // Combine students and attendance based on user id
  //   const combinedData = this.students.map(student => {
  //     const attendanceData = this.attendance.find(item => item.studentInfo.id === student.id) || {};

      
  //     return { ...student, ...attendanceData };

  //   });

  //   console.log("Combi: ");
  //   console.log(combinedData);
    
  //   // Initialize MatTableDataSource with combinedData
  //   this.dataSource = new MatTableDataSource(combinedData);
  //   // this.dataSource.paginator = this.paginator;
  //   this.dataSource.sort = this.sort;
  // }

  ngOnDestroy() {
    // Unsubscribe from subscriptions to avoid memory leaks
    if (this.studentsSubscription) {
      this.studentsSubscription.unsubscribe();
    }

    if (this.attendanceSubscription) {
      this.attendanceSubscription.unsubscribe();
    }

    if (this.tickSubscription) {
      this.tickSubscription.unsubscribe();
    }
  }
}
