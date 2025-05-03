import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { AuthenticationRequest } from '../../models/client.model';
import { FormsModule } from '@angular/forms';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  authRequest: AuthenticationRequest = {
    userName: '',
    password: '',
  };

  constructor(private authService: AuthService, private router: Router) {}

  login(): void {
    this.authService.login(this.authRequest).subscribe(
      (response: any) => {
        
        // alert('Login successful!');
        // Swal.fire('Success', 'Login successful!', 'success');
        this.authService.setRole(response.role);
        this.authService.setUserDetails(response.userDetails);

        if (response.role === 'Admin') {
          this.router.navigate(['/admin']);
        } else if (response.role === 'User') {
          this.router.navigate(['/userdashboard']);
        }

        
        // alert(this.authService.isLoggedIn);
        
      },
      (error: any) => {
        // alert('Login failed: ' + error.message);
        Swal.fire('Error', 'Login failed!', 'error');
      }
    );
  }

  // private redirectBasedOnRole(role: string): void {
  //   if (role === 'Admin') {
  //     this.router.navigate(['/admin-dashboard']);
  //   } else if (role === 'User') {
  //     this.router.navigate(['/user-dashboard']);
  //   }
  // }
}
