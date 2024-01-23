import { Component, DoCheck, EventEmitter, Output } from '@angular/core';
import { Router } from '@angular/router';
import { JwtService } from 'src/app/core/services/auth/jwt.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements DoCheck {
  title = 'EduCrafter';

  onButtonClick(param: string): void {
    this.jwtService.setParam(param);
    this.router.navigate(['user-list'], { queryParams: { param: param } });
  }

  jwtTok:string | null;
  isLogged: boolean=false;
  isAdmin: boolean=false;
  isTeacher: boolean=false;
  isParent: boolean=false;
  isStudent: boolean=false;
  role: string='';

  constructor(
    private jwtService: JwtService, 
    private router: Router
    ){}

  ngDoCheck(): void {
    this.jwtTok= localStorage.getItem('jwt');
    if(this.jwtTok!=null){
      this.isLogged=true;
      this.role=this.jwtService.extractRole();
      if(this.role === '[ADMIN]'){
        this.isAdmin=true;
        this.isTeacher=false;
        this.isParent=false;
        this.isStudent=false;
      } else if(this.role === '[TEACHER]'){
        this.isAdmin=false;
        this.isTeacher=true;
        this.isParent=false;
        this.isStudent=false;
      } else if(this.role === '[PARENT]'){
        this.isAdmin=false;
        this.isTeacher=false;
        this.isParent=true;
        this.isStudent=false;
      } else if(this.role === '[STUDENT]'){
        this.isAdmin=false;
        this.isTeacher=false;
        this.isParent=false;
        this.isStudent=true;
      }
    } else{
      this.isLogged = false;
    }
  }
}