import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from 'src/app/model/Employee';
import { EmployeeService } from 'src/app/services/employee.service';
import { TokenStorageService } from 'src/app/shared/token-storage.service';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {

  employee: Employee = new Employee();
  isLoggedIn = false;
  submittedFail = false;

  constructor(
    private router: Router,
    private empService: EmployeeService,
    private tokenStorage: TokenStorageService
  ) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
    }
  }

  onSubmit() {
    this.empService.addEmployee(this.employee).subscribe(() => {
      this.employee = new Employee();
      this.gotoList();
    }, error => {
      this.submittedFail = true;
    });
  }

  gotoList() {
    this.router.navigate(['/emps']);
  }

}
