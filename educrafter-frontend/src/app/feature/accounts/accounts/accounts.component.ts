import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { PaymentService } from 'src/app/core/services/payment/payment.service';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent {

  constructor(
    private paymentService: PaymentService,
    private router: Router) { }

  ngOnInit() {
    this.loadAll();
  }

  showCreateButton = false;
  showToggleButton = true;
  showDeleteButton = true;
  title = 'Accounts';


  dataList: any[] = [];
  headArray = [
    { 'Head': 'No', 'FieldName': 'no' },
    { 'Head': 'User Name', 'FieldName': 'username' },
    { 'Head': 'Payment Id', 'FieldName': 'razorpay_payment_id' },
    { 'Head': 'Amount', 'FieldName': 'amount' },
    { 'Head': 'Date', 'FieldName': 'createdDate' },
    // { 'Head': 'Action', 'FieldName': '' }
  ];

  loadAll() {
    this.paymentService.getAll().subscribe((res) => {
      this.dataList = res;
      console.log(res);

    },
      error => {
        console.error('Error fetching user data:', error);
      }
    );
  }

  edit(item: any) {
    // const mode ='edit';
    // console.log(item.id);
    // const id = item.id;
    // this.router.navigate(['new-classes'], { queryParams: { id, mode } });
  }

  delete(item: any) {
    this.loadAll();
    // debugger;
    // this.paymentService.deleteClasses(item).subscribe((res) => {
    //   console.log(res);

    // },
    //   error => {
    //     console.error('Error fetching user data:', error);
    //   }
    // );
  }

  toggle(item: any) {
    this.loadAll();
    // debugger;
    // this.paymentService.deleteClasses(item).subscribe((res) => {
    //   console.log(res);

    // },
    //   error => {
    //     console.error('Error fetching user data:', error);
    //   }
    // );
  }

  create() {
    // this.router.navigateByUrl("new-classes");
    // this.router.navigate(['register'], { queryParams: { role: this.param } });
  }
}