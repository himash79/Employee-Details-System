import { Employee } from 'src/app/model/Employee';
import { EmployeeService } from 'src/app/services/employee.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from 'src/app/shared/token-storage.service';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit {

  id: string = "";
  employee: Employee = new Employee();
  isLoggedIn = false;
  isUpdated = false;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private empService: EmployeeService,
    private tokenStorage: TokenStorageService
  ) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.id = this.route.snapshot.params['id'];
      this.empService.getEmp(this.id).subscribe(data => {
        this.employee = data;
      });
    }
  }

  updateDetails() {
    return this.empService.updateEmp(this.id, this.employee).subscribe(() => {
      this.employee = new Employee();
      this.gotoList();
    }, error => {
      this.isUpdated = true;      
    });
  }

  gotoList() {
    this.router.navigate(['/emps']);
  }

}
