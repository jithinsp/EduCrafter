import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AcademicsService } from 'src/app/core/services/academics/academics.service';
import { PaymentService } from 'src/app/core/services/payment/payment.service';

@Component({
  selector: 'app-payment-history',
  templateUrl: './payment-history.component.html',
  styleUrls: ['./payment-history.component.css']
})
export class PaymentHistoryComponent {

  constructor(
    private paymentService: PaymentService,
    private router: Router) { }

  ngOnInit() {
    this.loadAll();
  }

  showCreateButton = false;
  showToggleButton = true;
  showDeleteButton = true;
  title = 'Payment History';

  dataList: any[] = [];
  headArray = [
    { 'Head': 'No', 'FieldName': 'no' },
    // { 'Head': 'User Name', 'FieldName': 'username' },
    { 'Head': 'Payment Id', 'FieldName': 'razorpay_payment_id' },
    { 'Head': 'Amount', 'FieldName': 'amount' },
    { 'Head': 'Date', 'FieldName': 'createdDate' },
    // { 'Head': 'Action', 'FieldName': '' }
  ];

  loadAll() {
    this.paymentService.getUser().subscribe((res) => {
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