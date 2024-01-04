import { Component } from '@angular/core';
import { JwtService } from 'src/app/service/jwt.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent{
  constructor( private service: JwtService ){}
  message: string;

  ngOnInit(){
    // this.hello();
    this.message ='Hi ' + this.service.extractUsername();
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
}