import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit{

  userDetails: {
    id?: number;
    email: string;
    enabled: boolean;
    firstName: string;
    lastName: string;
    password?: string;
    phone: string;
    userName: string;
    profile: string;
    clientRole: string;
  } = {
    id: undefined,
    email: '',
    enabled: false,
    firstName: '',
    lastName: '',
    password: '',
    phone: '',
    userName: '',
    profile: '',
    clientRole: ''
  };

  constructor(){};

  ngOnInit(): void {
      const storedDetails = localStorage.getItem('userDetails');

      if(storedDetails){
        this.userDetails = JSON.parse(storedDetails);
      }else{
        alert(console.error('No userDetails found in local storage'));
      }
  }

}
