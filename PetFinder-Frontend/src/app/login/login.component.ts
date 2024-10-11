import { Component } from '@angular/core';
import { ApiService } from '../api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  email!: string
  password!: string
  error!: string

  constructor(private service: ApiService, private router: Router) {}

  onLogin(){
    const userDto = { email: this.email, password: this.password }

    this.service.login(userDto).subscribe({
      next: (next) => {
        sessionStorage.setItem('userToken', next.token)
        sessionStorage.setItem('email', next.email)
        this.router.navigateByUrl('/home')
      },
      error: (errorResponse) => {
        if (errorResponse.status === 401) {
          this.error = errorResponse.error.msg;
        } else {
          this.error = 'An unexpected error occurred. Please try again.'
        }
      }
    })
  }
}
