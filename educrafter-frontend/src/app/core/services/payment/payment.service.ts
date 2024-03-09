import { Injectable } from '@angular/core';
import { JwtService } from '../auth/jwt.service';
import { HttpClient } from '@angular/common/http';
import { BASE_URL } from '../../constants/baseurls.constant';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(private http: HttpClient,
    private authService: JwtService) { }

    createTransaction(amount: number): Observable<any> {
      return this.http.get(BASE_URL + 'finance/payment/pay/'+amount);
    }

    getAll(): Observable<any> {
      return this.http.get(BASE_URL + 'finance/payment/getAll');
    }

    getUser(): Observable<any> {
      const username = this.authService.extractUsername();
      return this.http.get(BASE_URL + 'finance/payment/get/'+ username);
    }

    pay(username: string, resp: any,data:any): Observable<any> {
      const mergedObject = {
        ...resp,
        ...data,
        username: username
      };
      return this.http.post(BASE_URL + 'finance/payment/pay',mergedObject);
    }
}
