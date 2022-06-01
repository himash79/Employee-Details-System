import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from '../model/Employee';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private empURL = "http://localhost:8090/v1/";

  constructor(
    private http: HttpClient
  ) { }

    getEmployeeDetis(): Observable<any> {
      return this.http.get(`${this.empURL}getEmps`, httpOptions);
    }

    addEmployee(employee: Employee): Observable<any> {
      return this.http.post(`${this.empURL}createEmp`, employee , httpOptions);
    }

    deleteEmp(id: string): Observable<any> {
      return this.http.delete(`${this.empURL}deleteEmp/${id}`,({responseType: 'text'}));
    }

    getEmp(id: string): Observable<any> {
      return this.http.get(`${this.empURL}employee/${id}`, httpOptions);
    }

    updateEmp(id: string, object: any): Observable<any> {
      return this.http.put(`${this.empURL}updateEmployee/${id}`, object, httpOptions);
    }

}

