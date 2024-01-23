import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtService } from '../auth/jwt.service';
import { IStudentResponse, ITeacherResponse } from '../../interfaces/signup.interface';
import { User, UserModel } from '../../interfaces/user.model';

const BASE_URL = ["http://localhost:8060/"]

@Injectable({
  providedIn: 'root'
})
export class TeacherService {
  
  constructor(private http: HttpClient,
    private authService: JwtService) { }

  getAllStudents(): Observable<UserModel> {
    const jwtToken = localStorage.getItem('jwt');
    if (this.authService.extractRole() === '[ADMIN]' || this.authService.extractRole() === '[TEACHER]') {
      return this.http.get<UserModel>(BASE_URL + 'user/admin/allStudents');
    } else {
      return null;
    } 
  }

  getAllUsers(param:string): Observable<User[]> {
    const jwtToken = localStorage.getItem('jwt');
    const role = this.authService.extractRole();
    if(param === 'admin' && role === '[ADMIN]'){
      return this.http.get<User[]>(BASE_URL + 'user/admin/allAdmins');
    } else if(param === 'teacher' && role === '[ADMIN]'){
      return this.http.get<User[]>(BASE_URL + 'user/admin/allTeachers');
    } else if (param === 'student' && (role === '[ADMIN]' || role === '[TEACHER]')){
      return this.http.get<User[]>(BASE_URL + 'user/teacher/allStudents');
    } else if (param === 'parent' && (role === '[ADMIN]' || role === '[TEACHER]')){
      return this.http.get<User[]>(BASE_URL + 'user/teacher/allParents');
    } else {
      return null;
    } 
  }
}
