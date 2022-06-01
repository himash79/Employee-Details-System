import { TokenStorageService } from 'src/app/shared/token-storage.service';
import { AuthService } from './../../shared/auth.service';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Employee } from 'src/app/model/Employee';
import { EmployeeService } from 'src/app/services/employee.service';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent implements OnInit {

  employees!: Observable<Employee[]>;
  isLoggedIn = false;


  constructor(
    private empService: EmployeeService,
    private router: Router,
    private tokenStorage: TokenStorageService
  ) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.getAllEmployees();
    }
  }

  getAllEmployees() {
    return this.employees  = this.empService.getEmployeeDetis();
  }

  deleteEmployee(emp_ID: string) {
    return this.empService.deleteEmp(emp_ID).subscribe(() => {
      this.refreshPage();
    }, error => {      
      console.log(error);      
    });
  }

  updateEmployee(emp_ID: string) {
    this.router.navigate(['updateEmp', emp_ID]);
  }

  employeeDetails(emp_ID: string) {
    this.router.navigate(['empDetails', emp_ID]);
  }

  refreshPage() {
    window.location.reload();
  }


}
