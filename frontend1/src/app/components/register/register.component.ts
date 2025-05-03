import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { Client } from '../../models/client.model';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  client: Client = {
    email: '',
    enabled: true,
    firstName: '',
    lastName: '',
    password: '',
    phone: '',
    userName: '',
    profile: '',
    roleName: 'USER', // Default role
  };

  constructor(private authService: AuthService, private router: Router) {}

  register(): void {
    this.authService.register(this.client).subscribe(
      (response: any) => {
        // Swal.fire('Success', 'Registration successful!', 'success');
        alert('Registration successful!');
        this.router.navigate(['/login']);
      },
      (error: any) => {
        
        // Swal.fire('Error', 'Registration failed!' + 'error.message', 'error');
        alert('Registration failed: ' + error.message);
         
      }
    );
  }
}
