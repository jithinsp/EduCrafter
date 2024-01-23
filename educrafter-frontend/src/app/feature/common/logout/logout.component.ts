import { Component, OnInit } from '@angular/core';
import { JwtService } from 'src/app/core/services/auth/jwt.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private jwtService: JwtService,
    private router: Router){}

  ngOnInit(): void {
      this.jwtService.logout();
      this.router.navigateByUrl("/login");
  }
}
