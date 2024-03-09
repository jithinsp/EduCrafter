import { Component, DoCheck, EventEmitter, Output } from '@angular/core';
import { Router } from '@angular/router';
import * as SockJS from 'sockjs-client';
import { USER_SERVICE } from 'src/app/core/constants/baseurls.constant';
import { JwtService } from 'src/app/core/services/auth/jwt.service';
import  {Stomp} from '@stomp/stompjs';
import { NotificationService } from 'src/app/core/services/notification/notification.service';

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
    private router: Router,
    private notificationService: NotificationService
    ){}

  ngOnInit() {
    // this.connect();
    this.notificationService.connect();
  }

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
  // private stompClient = null;
  // connect() {
  //   const socket = new SockJS(USER_SERVICE+'user/testchat');    
  //   this.stompClient = Stomp.over(socket);
  //   const _this = this;
  //   this.stompClient.connect(
  //     {
  //       headers: this.jwtService.createAuthorizationHeader()
  //     }
  //     , function (frame) {
  //     console.log('Connected to notification: ' + frame);
  //     _this.stompClient.subscribe('/all/messages', function(hello){
  //       console.log(hello.body);
  //       // _this.showMessage(JSON.parse(hello.body));
  //       // _this.showMessage(hello.body);
  //     });
  //  });
  // }
}