import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ResultService } from 'src/app/services/result.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-load-all-results',
  templateUrl: './load-all-results.component.html',
  styleUrls: ['./load-all-results.component.css']
})
export class LoadAllResultsComponent implements OnInit{
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

  results: any[] = [];
  public uid!: number;

  constructor(
    private resultService: ResultService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const storedDetails = localStorage.getItem('userDetails');
    if(storedDetails){
      this.userDetails = JSON.parse(storedDetails);
      if(this.userDetails.id){
        this.uid = this.userDetails.id;
      }
    }

    this.resultService.getAllResultsOfUser(this.uid).subscribe(
      (data: any) => {
        this.results = data;
        console.log("Fetched results: ", this.results);
      },
      error => {
        console.error("Error fetching results: ", error);
      }
    );
  }

  deleteResult(resultId: number): void {
    Swal.fire({
      icon: 'info',
      title: "Are you sure?",
      confirmButtonText: 'Delete',
      showCancelButton: true,
    }).then((result) => {
      if (result.isConfirmed) {
        this.resultService.deleteResult(resultId).subscribe(
          () => {
            this.results = this.results.filter(result => result.resultId !== resultId);
            Swal.fire('Success!', 'Result deleted successfully', 'success');
          },
          error => {
            Swal.fire('Error!', 'Failed to delete result', 'error');
          }
        );
      }
    });
  }
}
