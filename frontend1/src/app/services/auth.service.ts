import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { Client, AuthenticationRequest, AuthenticationResponse } from '../models/client.model';
import { BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators'; 


@Injectable({
  providedIn: 'root',
})


export class AuthService {

  // private isLoggedInSubject = new BehaviorSubject<boolean>(this.isLoggedIn());
  // // private isLoggedInSubject = new BehaviorSubject<boolean>(!!localStorage.getItem('token'));
  // // isLoggedIn$ = this.isLoggedInSubject.asObservable();

  // isLoggedInObservable = this.isLoggedInSubject.asObservable();

  private isLoggedInSubject = new BehaviorSubject<boolean>(this.isLoggedInFromStorage());
  isLoggedInObservable = this.isLoggedInSubject.asObservable();

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }


  private baseUrl = 'http://localhost:8082/api';

  constructor(private http: HttpClient, private router: Router) {}

  private isLoggedInFromStorage(): boolean {
    return !!localStorage.getItem('token');
  }

  register(client: Client): Observable<Client> {
    return this.http.post<Client>(`${this.baseUrl}/clients/create`, client);
  }

  login(authRequest: AuthenticationRequest): Observable<AuthenticationResponse> {
    return this.http.post<AuthenticationResponse>(`${this.baseUrl}/auth/login`, authRequest).pipe(
      tap((response:any) => {
        localStorage.setItem('token', response.token);
        localStorage.setItem('role', response.role);
        localStorage.setItem('userDetails', JSON.stringify(response.userDetails));
        this.isLoggedInSubject.next(true); // Notify login state change

        // this.redirectBasedOnRole(response.role, this.router);
      })
    );
  }

  getRole(): string | null {
    return localStorage.getItem('role');
  }

  setRole(role: string): void {
    localStorage.setItem('role', role);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  getUserDetails(): Client | null {
    const userDetails = localStorage.getItem('userDetails');
    return userDetails ? JSON.parse(userDetails) : null;
  }

  setUserDetails(user: Client): void {
    localStorage.setItem('userDetails', JSON.stringify(user));
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    localStorage.removeItem('userDetails');
    this.isLoggedInSubject.next(false); 
    // this.router.navigate(['/login']);
  }

  

  // redirectBasedOnRole(role: string, router: Router): void {
  //   if (role === 'Admin') {
  //     console.log(role+" logged in");
  //     router.navigate(['/user-dashboard']);
  //   } else if (role === 'User') {
  //     router.navigate(['/user-dashboard']);
  //   }
  // }
}
