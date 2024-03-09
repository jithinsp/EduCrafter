import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { Subscription } from 'rxjs';
import { User } from 'src/app/core/interfaces/user.model';
import { AdminService } from 'src/app/core/services/admin/admin.service';
import { JwtService } from 'src/app/core/services/auth/jwt.service';
import { PaginatorIntlService } from 'src/app/core/services/paginator/paginator-intl.service';
import { TeacherService } from 'src/app/core/services/teacher/teacher.service';
import { loadUser } from 'src/app/store/user.actions';
import { getuserlist } from 'src/app/store/user.selectors';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css'],
  providers: [{ provide: MatPaginatorIntl, useClass: PaginatorIntlService }]
})
export class UserListComponent implements OnInit,OnDestroy {

  openCreate() {
    //   this.router.navigateByUrl("register"); }
    this.router.navigate(['register'], { queryParams: { role: this.param } });
  }

  users: User[] = [];
  param: string;
  displayedColumns: string[] = ['index', 'name', 'email', 'role', 'actions'];

  dataSource: any;
  @ViewChild(MatPaginator) paginatior !: MatPaginator;
  @ViewChild(MatSort) sort !: MatSort;
  Filterchange(data: Event) {
    const value = (data.target as HTMLInputElement).value;
    this.dataSource.filter = value;
  }

    //all subscribtions
    private paramsSubscription: Subscription;
    private querySubscription: Subscription;
    private userSubscription: Subscription;
    private deleteSubscription: Subscription;
    
  
    ngOnDestroy() {
      // Unsubscribe from subscriptions to avoid memory leaks
      if (this.paramsSubscription) {
        this.paramsSubscription.unsubscribe();
      }
      if (this.querySubscription) {
        this.querySubscription.unsubscribe();
      }
      if (this.userSubscription) {
        this.userSubscription.unsubscribe();
      }
      if (this.deleteSubscription) {
        this.deleteSubscription.unsubscribe();
      }
    }

  constructor(
    private jwtService: JwtService,
    private store: Store,
    private route: ActivatedRoute,
    private adminService: AdminService,
    private teacherService: TeacherService,
    private router: Router) { }

  ngOnInit() {
    this.paramsSubscription = this.jwtService.getParam().subscribe((param: string) => {
      this.loadUsers(param);
    });
    this.querySubscription = this.route.queryParams.subscribe((queryParams) => {
      this.param = queryParams['param'] || 'default';
    });
  }

  loadUsers(param: string): void {
    this.userSubscription = this.teacherService.getAllUsers(param).subscribe(
      (data: User[]) => {
        this.users = data;
        console.log(data);
        this.dataSource = new MatTableDataSource(this.users);
        this.dataSource.paginator = this.paginatior;
        this.dataSource.sort = this.sort;
      },
      error => {
        console.error('Error fetching user data:', error);
      }
    );
  }
  // ngOnInit() {
  //   this.store.dispatch(loadUser());
  //   this.store.select(getuserlist).subscribe(item=>{
  //     this.users = item;
  //     console.log(this.users);
  //   });
  // }

  onDeleteUser(userId: number) {
    this.deleteSubscription = this.adminService.deleteUser(userId).subscribe(
      () => {
        console.log("user deleted successfully");
        location.reload();
      },
      (error) => {
        console.error('Error deleting user:', error);
      }
    );
  }

}
