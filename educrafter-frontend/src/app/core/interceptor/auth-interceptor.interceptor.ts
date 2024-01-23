import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const jwtToken = localStorage.getItem('jwt');

    if (jwtToken) {
      const modifiedReq = req.clone({
        setHeaders: {
          Authorization: `Bearer ${jwtToken}`,
        },
      });
      return next.handle(modifiedReq);
    } else {
      console.log('JWT token not found');
      return next.handle(req);
    }
  }
}
