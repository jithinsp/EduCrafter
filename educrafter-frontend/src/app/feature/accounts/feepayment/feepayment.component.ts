import { Component } from '@angular/core';
import { Subscription } from 'rxjs';
import { COURSE_FEE } from 'src/app/core/constants/fee.constant';
import { JwtService } from 'src/app/core/services/auth/jwt.service';
import { PaymentService } from 'src/app/core/services/payment/payment.service';

declare const Razorpay:any;

@Component({
  selector: 'app-feepayment',
  templateUrl: './feepayment.component.html',
  styleUrls: ['./feepayment.component.css']
})
export class FeepaymentComponent {
  role:string;

  constructor(private paymentService: PaymentService,
    private jwtService: JwtService) {}

  private paymentSubscription: Subscription;
  ngOnDestroy() {
    // Unsubscribe from subscriptions to avoid memory leaks
    if (this.paymentSubscription) {
      this.paymentSubscription.unsubscribe();
    }
  }

  ngOnInit(){
    this.role = this.jwtService.extractRole();
  }

  payFee() {
    this.paymentSubscription = this.paymentService.createTransaction(COURSE_FEE).subscribe(
      (data: any) => {
        console.log(data);
        this.openTransactionModal(data);
      },
      error => {
        console.error('Error fetching user data:', error);
      }
    );
  }

  openTransactionModal(response:any){
    let options = {
      order_id: response.order_id,
      key: response.key,
      amount: response.amount,
      currency: response.currency,
      name: 'EduCrafter',
      description: 'Fee payment',
      image:'src/assets/E.png',
      handler: (res:any) =>{
        if(res !=null && res.razorpay_payment_id!=null){
          this.processResponse(res,response);
        } else {
          alert("Payment failed...")
        }
      },
      prefill: {
        name: 'EduCrafter',
        email: '',
        contact: '1231231234'
      },
      notes: {
        address: 'fee'
      },
      theme:{
        color: '#FF4411'
      }
    };
    let razorpayObject = new Razorpay(options);
    // razorpayObject.open();
    this.openPayment(
      options,
      () => console.log('Payment successful!'),
      (error: any) => console.error(error)
    );
  
  }

  processResponse(resp:any,response:any){  
    const username = this.jwtService.extractUsername();
    console.log(resp);  
    this.paymentService.pay(username,resp,response).subscribe(
      (data: any) => {
        console.log(data);
      },
      error => {
        console.error('Error fetching user data:', error);
      }
    );
  }

  openPayment(options: any, successCallback: () => void, errorCallback: (error: any) => void) {
    const razorpayObject = new Razorpay(options);
  
    razorpayObject.open({
      modal: {
        ondismiss: () => errorCallback({ description: 'Payment cancelled by user.' }),
      },
      external: {
        wallets: ['netbanking', 'card'],
      },
      handler: (response: any) => {
        // Handle successful payment
        successCallback();
      },
    });
  }
}

