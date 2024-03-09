import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable, timer } from 'rxjs';
import { switchMap, takeUntil } from 'rxjs/operators';
import jwt_decode from 'jwt-decode';
import { ILogin, ILoginResponse } from '../../interfaces/login.interface';
import { IStudentRegister } from '../../interfaces/signup.interface';
import { BASE_URL } from '../../constants/baseurls.constant';

@Injectable({
  providedIn: 'root'
})
export class JwtService {

  constructor(private http: HttpClient) { 
  }

  login(loginRequest: ILogin): Observable<ILoginResponse> {
    console.log("Logging in");
    this.initLogoutTimer();
    return this.http.post<ILoginResponse>(BASE_URL + 'auth/login', loginRequest);
  }

  logout(): void {
    localStorage.removeItem('jwt');
    this.http.post(BASE_URL + 'logout', {});
  }

  private initLogoutTimer(): void {
    const logoutTime = 12 * 60 * 60 * 1000; // 12 hours
    timer(0, 1000) // Check every second
      .pipe(
        takeUntil(this.http.post(BASE_URL + 'logout', {})), // Stop timer when logout is triggered
        switchMap(() => timer(logoutTime))
      )
      .subscribe(() => {
        this.logout(); // Trigger automatic logout
      });
  }

  extractRole(): string | null {
    const jwtTok = localStorage.getItem('jwt');
    if (jwtTok) {
      // Decode the JWT
      const decodedToken: any = jwt_decode(jwtTok);
      // Access the role from the decoded JWT payload
      const role = decodedToken.role;
      // Now you can use the 'role' variable for access control or display purposes.
      // console.log('User Role:', role);
      return role;
    } else {
      console.log('cannot extract role');
      return null;
    }
  }
  extractUsername(): string | null {
    const jwtTok = localStorage.getItem('jwt');
    if (jwtTok) {
      const decodedToken: any = jwt_decode(jwtTok);
      const user = decodedToken.sub;
      // console.log('User: ', user);
      return user;
    } else {
      console.log('cannot extract username');
      return null;
    }
  }

  public createAuthorizationHeader() {
    const jwtToken = localStorage.getItem('jwt');
    if (jwtToken) {
      console.log("JWT token found in local storage", jwtToken);
      return new HttpHeaders().set(
        "Authorization", "Bearer " + jwtToken
      )
    } else {
      console.log("JWT token not found");
    }
    console.log('cannot create authorization header');

    return null;
  }

  // register(signRequest: IStudentRegister): Observable<any> {
  //   return this.http.post(BASE_URL + 'auth/signup', signRequest);
  // }

  // deleteUser(userId: number): Observable<any> {
  //   return this.http.delete(BASE_URL + 'auth/delete/' + userId);
  // }

  // hello(): Observable<any> {
  //   const jwtToken = localStorage.getItem('jwt');
  //   if (this.extractRole() === '[ADMIN]') {
  //     console.log("admin side");
  //     return this.http.get(BASE_URL + 'admin/hi '
  //       // , {
  //       //   headers: this.createAuthorizationHeader()
  //       // }
  //     );
  //   } else {
  //     console.log("user side");
  //     return this.http.get(BASE_URL + 'test/'+ this.extractUsername());
  //   }
  // }

  // getAllUsers(): Observable<any> {
  //   const jwtToken = localStorage.getItem('jwt');
  //   if (this.extractRole() === '[ADMIN]') {
  //     return this.http.get(BASE_URL + 'auth/users');
  //   } else {
  //     return null;
  //   }
  // }

  // getUserProfile(): Observable<any> {
  //   return this.http.get(BASE_URL + 'auth/profile');
  // }

  private paramSubject = new BehaviorSubject<string>(''); 

  setParam(param: string): void {
    this.paramSubject.next(param);
  }

  getParam(): Observable<string> {
    return this.paramSubject.asObservable();
  }
}