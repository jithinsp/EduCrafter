import { HttpClient } from '@angular/common/http';
import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { JwtService } from 'src/app/core/services/auth/jwt.service';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  constructor(
    private service: JwtService,
    private http: HttpClient) { }
    
  message: string;

  ngOnInit() {
    // this.hello();
    this.message = 'Hi ' + this.service.extractUsername();
    this.loadUsers();

  }

  // hello(){
  //   this.service.hello().subscribe(
  //     (response) => {
  //       console.log(response);
  //       // this.message = response.message;
  //       this.message = response;
  //     }
  //   )
  // }

  // constructor() { }

  // ngOnInit(): void {
  //   this.loadUsers();
  // }


  usersList: any[] = [];
  headArray = [
    { 'Head': 'Sr', 'FieldName': 'no' },
    { 'Head': 'User Name', 'FieldName': 'name' },
    { 'Head': 'Email', 'FieldName': 'email' },
    { 'Head': 'Contact', 'FieldName': 'phone' },
    { 'Head': 'Website', 'FieldName': 'website' },
    { 'Head': 'Action', 'FieldName': '' }
  ];

  loadUsers() {
    this.http.get('https://jsonplaceholder.typicode.com/users').subscribe((result: any) => {
      this.usersList = result;
    })
  }

  editUser(item: any) {
    debugger;
  }
  deleteUser(item: any) {
    debugger;
  }


}