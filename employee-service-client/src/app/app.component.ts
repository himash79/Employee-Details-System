import { Router } from '@angular/router';
import { Component } from '@angular/core';
import { TokenStorageService } from './shared/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'employee-service-client';

  isLoggedIn = false;
  userName = null;

  constructor(
    private tokenStorageService: TokenStorageService,
  ) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();  
    if (this.isLoggedIn) {
      this.isLoggedIn = true;
      const user = this.tokenStorageService.getUser();
      this.userName = user.userName;
    }
  }

  signOut(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }

}
