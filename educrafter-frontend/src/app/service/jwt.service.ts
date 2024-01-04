import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import jwt_decode from 'jwt-decode';
import { User } from '../model/User';

const BASE_URL = ["http://localhost:8060/"]
@Injectable({
  providedIn: 'root'
})
export class JwtService {

  constructor(private http: HttpClient) { }

  register(signRequest: any): Observable<any> {
    return this.http.post(BASE_URL + 'auth/signup', signRequest);
  }

  login(loginRequest: any): Observable<any> {
    console.log("hi");
    
    return this.http.post(BASE_URL + 'auth/login', loginRequest);
  }

  logout(): void {
    localStorage.removeItem('jwt');
    this.http.post(BASE_URL + 'logout', {});
  }

  deleteUser(userId: number): Observable<any> {
    return this.http.delete(BASE_URL + 'auth/delete/' + userId);
  }

  extractRole(): string | null {
    const jwtTok = localStorage.getItem('jwt');
    // Check if the token is present
    if (jwtTok) {
      // Decode the JWT
      const decodedToken: any = jwt_decode(jwtTok);
      // Access the role from the decoded JWT payload
      const role = decodedToken.role;
      // Now you can use the 'role' variable for access control or display purposes.
      console.log('User Role:', role);
      return role;
    } else {
      return null;
    }
  }
  extractUsername(): string | null {
    const jwtTok = localStorage.getItem('jwt');
    if (jwtTok) {
      const decodedToken: any = jwt_decode(jwtTok);
      const user = decodedToken.sub;
      console.log('User: ', user);
      return user;
    } else {
      return null;
    }
  }


  hello(): Observable<any> {
    const jwtToken = localStorage.getItem('jwt');
    if (this.extractRole() === '[ADMIN]') {
      console.log("admin side");
      return this.http.get(BASE_URL + 'admin/hi '
        // , {
        //   headers: this.createAuthorizationHeader()
        // }
      );
    } else {
      console.log("user side");
      return this.http.get(BASE_URL + 'test/'+ this.extractUsername());
    }
  }

  getAllUsers(): Observable<any> {
    const jwtToken = localStorage.getItem('jwt');
    if (this.extractRole() === '[ADMIN]') {
      return this.http.get(BASE_URL + 'auth/users');
    } else {
      return null;
    }
  }

  getUserProfile(): Observable<any> {
    return this.http.get(BASE_URL + 'auth/profile');
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
    return null;
  }
}