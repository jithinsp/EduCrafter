import { Injectable } from '@angular/core';
import { JwtService } from '../auth/jwt.service';
import { HttpClient } from '@angular/common/http';
import { BASE_URL } from '../../constants/baseurls.constant';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(private http: HttpClient,
    private authService: JwtService) { }
}
