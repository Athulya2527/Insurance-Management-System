import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { LoginRequest } from 'src/app/models/login-request';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm = new FormGroup({
    role: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(6)]),
  });

  showPassword: boolean = false;
  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    sessionStorage.clear();
    localStorage.clear();
   }

   onSubmit() {
    console.log('Log in action');
    if (this.loginForm.valid) {
      const { email, password, role } = this.loginForm.value;
      if (email && password && role) {
        const loginRequest: LoginRequest = {
          email: email,
          password: password,
          role: role
        };
        this.authService.login(loginRequest).subscribe(
          (response) => {
            this.authService.setId(response.id);
            if (response) {
              this.authService.setToken(response.token);
              switch (loginRequest.role) {
                case 'agent':
                  this.router.navigate(['agent/profile']);
                  break;
                case 'customer':
                  this.router.navigate(['customer/profile']);
                  break;
                case 'company':
                  this.router.navigate(['company/profile']);
                  break;
                default:
                  Swal.fire('Error', 'Please select a valid role', 'error');
                  break;
              }
              Swal.fire('Success', 'Login Successful', 'success');
              this.loginForm.reset();
            }
          },
          (error) => {
            console.log(error.error.alert);
            console.log(error);
            Swal.fire('Error', error.error.alert, 'error');
          }
        );
      }
    }
  }
  

  passwordVisibility() {
    this.showPassword = !this.showPassword;
  }
}
