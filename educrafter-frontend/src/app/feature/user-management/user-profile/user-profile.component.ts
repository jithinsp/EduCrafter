import { Component, OnDestroy, OnInit} from '@angular/core';
import { Subscription } from 'rxjs';
import { AdminService } from 'src/app/core/services/admin/admin.service';
import { JwtService } from 'src/app/core/services/auth/jwt.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit,OnDestroy {
  users: any;

  constructor(
    private jwtService: JwtService,
    private adminService: AdminService) {}

    //all subscribtions
    private profileSubscription: Subscription;
    
    ngOnDestroy() {
      // Unsubscribe from subscriptions to avoid memory leaks
      if (this.profileSubscription) {
        this.profileSubscription.unsubscribe();
      }
    }

  ngOnInit() {
    this.profileSubscription = this.adminService.getAdminProfile().subscribe(
      (data: any) => {
        this.users = data;
        console.log(data);
      },
      error => {
        console.error('Error fetching user data:', error);
      }
    );
  }
}