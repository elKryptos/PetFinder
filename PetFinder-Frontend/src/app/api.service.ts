import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  login(userDto: any): Observable<any> {
    return this.http.post<any>("http://localhost:8080/v1/user/login", userDto)
  }
}
