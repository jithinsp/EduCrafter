import { Injectable } from '@angular/core';
import { JwtService } from '../auth/jwt.service';
import { HttpClient } from '@angular/common/http';

const BASE_URL = ["http://localhost:8060/"]

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(private http: HttpClient,
    private authService: JwtService) { }
}
