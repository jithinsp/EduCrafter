import { Component, OnInit} from '@angular/core';
import { JwtService } from 'src/app/service/jwt.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  users: any;

  constructor(private jwtService: JwtService) {}

  ngOnInit() {
    this.jwtService.getUserProfile().subscribe(
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