import { Component, OnInit} from '@angular/core';
import { AdminService } from 'src/app/core/services/admin/admin.service';
import { JwtService } from 'src/app/core/services/auth/jwt.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  users: any;

  constructor(
    private jwtService: JwtService,
    private adminService: AdminService) {}

  ngOnInit() {
    this.adminService.getAdminProfile().subscribe(
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