import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from 'src/app/model/Employee';
import { EmployeeService } from 'src/app/services/employee.service';
import { TokenStorageService } from 'src/app/shared/token-storage.service';

@Component({
  selector: 'app-view-employee',
  templateUrl: './view-employee.component.html',
  styleUrls: ['./view-employee.component.css']
})
export class ViewEmployeeComponent implements OnInit {

  id: string = "";
  employee: Employee = new Employee();
  isLoggedIn = false;

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
  list() {
    this.router.navigate(['/home']);
  }
}
