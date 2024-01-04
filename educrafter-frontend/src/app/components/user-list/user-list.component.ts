import { Component, OnInit, ViewChild } from '@angular/core';
import { Store } from '@ngrx/store';
import { JwtService } from 'src/app/service/jwt.service';
import { loadUser } from 'src/app/state/user.actions';
import { getuserlist } from 'src/app/state/user.selectors';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  users: any[] = []; // Define a property to hold user data
  // filteredUsers: any[] = [];
  // searchTerm: string = '';

  constructor(private jwtService: JwtService, private store: Store) {}

  // ngOnInit() {
  //   this.jwtService.getAllUsers().subscribe(
  //     (data: any) => {
  //       this.users = data; // Assuming your API returns an array of users
  //       console.log(data);
  //     },
  //     error => {
  //       console.error('Error fetching user data:', error);
  //     }
  //   );
  // }
  ngOnInit() {
    this.store.dispatch(loadUser());
    this.store.select(getuserlist).subscribe(item=>{
      this.users = item;
      console.log(this.users);
    });
  }

  onDeleteUser(userId: number) {
    this.jwtService.deleteUser(userId).subscribe(
      () => {
        console.log("user deleted successfully");
        location.reload();
      },
      (error) => {
        console.error('Error deleting user:', error);
      }
    );
  }

  // onSearch(): void {
  //   this.filteredUsers = this.users.filter((user) =>
  //     user.name.toLowerCase().includes(this.searchTerm.toLowerCase())
  //   );
  // }
  
}
