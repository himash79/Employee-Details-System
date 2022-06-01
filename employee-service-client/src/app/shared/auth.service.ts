import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const authURL = "http://localhost:8090/";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient
  ) { }
    login(userName:string,password:string): Observable<any>{
      return this.http.post(authURL + 'authenticate', {userName, password}, httpOptions);
      }

}
