import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import * as SockJS from 'sockjs-client';
import { USER_SERVICE } from 'src/app/core/constants/baseurls.constant';
import { CustomMessage } from 'src/app/core/interfaces/user.model';
import { JwtService } from 'src/app/core/services/auth/jwt.service';
import { NotificationService } from 'src/app/core/services/notification/notification.service';
import  {Stomp} from '@stomp/stompjs';

@Component({
  selector: 'app-notice',
  templateUrl: './notice.component.html',
  styleUrls: ['./notice.component.css']
})
export class NoticeComponent {

  notifications: CustomMessage[] = [];
  role: string;
  private stompClient = null;

  ngOnInit(){
    this.fetchNotifications(); 
    this.role = this.authService.extractRole();
    console.log(this.role);
    // this.notificationService.getNotification();
    
  }

  constructor(
    private notificationService: NotificationService,
    private authService: JwtService,
    private router: Router,
    ) {}
  // constructor(private webSocketService: WebSocketService) {}

  // ngOnInit(): void {
  //   this.webSocketService.connect();
  //   this.webSocketService.getNotifications().subscribe((notification) => {
  //     this.notifications.push(notification);
  //   });
  // }

  fetchNotifications() {
    this.subscription = this.notificationService.getAllNotifications().subscribe(
      (data) => {
        console.log(data);
        this.notifications = data.sort((a, b) => new Date(b.messageDate).getTime() - new Date(a.messageDate).getTime());
        // this.dataList = files;
        // this.errorMessage = null;
      }
    );
  }

  private subscription: Subscription;

  ngOnDestroy() {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  openCreate(){
      this.router.navigateByUrl("new-notice");
  }
}