import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginRequest } from 'src/app/models/login-request';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:8080';

  constructor(
    private http: HttpClient,
    private router: Router,
  ) { }

  setToken(token: string): void {
    localStorage.setItem('token', token);
    //   localStorage.setItem('user', 'athulya');
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  setId(id: string): void {
    return localStorage.setItem('Id', id)
  }
  getId(): string | null {
    return localStorage.getItem('Id');
  }
  isLoggedIn() {
    return this.getToken() !== null;
  }
  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['login']);
  }
  login(loginRequest: LoginRequest): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/login`, loginRequest);
  }

}
