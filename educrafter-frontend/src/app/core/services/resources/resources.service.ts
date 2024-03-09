import { Injectable } from '@angular/core';
import { BASE_URL } from '../../constants/baseurls.constant';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ResourcesService {

  getFile(item: any): Observable<any> {
    const options = {
      responseType: 'blob' as 'json',
    };
    return this.http.get(BASE_URL + 'academics/resources/files/' + item.filepath, options);
  }

  constructor(private http: HttpClient) {}

  getFiles(): Observable<any[]> {
    return this.http.get<any[]>(BASE_URL + 'academics/resources/listAll');
  }

  uploadFile(formData: FormData): Observable<any> {
    // const headers = new HttpHeaders();
    // headers.append('Content-Type', 'multipart/form-data');
    // const formData = new FormData();
    // formData.append('file', file);
    console.log(formData);
    
    return this.http.post(BASE_URL + 'academics/resources/upload', formData);
  }

  // uploadFile(file: File): Observable<any> {
  //   const headers = new HttpHeaders();
  //   headers.append('Content-Type', 'multipart/form-data');
  //   const formData = new FormData();
  //   formData.append('file', file);
  //   console.log(formData);
    
  //   return this.http.post(BASE_URL + 'academics/resources/upload', formData);
  // }
}
