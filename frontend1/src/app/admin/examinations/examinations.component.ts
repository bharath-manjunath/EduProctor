import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Examination } from 'src/app/models/examination.model';
import { ExaminationService } from 'src/app/services/examination.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-examinations',
  templateUrl: './examinations.component.html',
  styleUrls: ['./examinations.component.css']
})
export class ExaminationsComponent implements OnInit{

  examinations: any[] = [];
  categoryId: number | null = null;
  constructor(
    private activatedRoute: ActivatedRoute,
    private examinationService: ExaminationService,
    private router: Router
  ) {}


  // ngOnInit(): void {
  //   this.activatedRoute.params.subscribe((params) => {
  //     this.categoryId = params['categoryId'];

  //     if (this.categoryId === 0) {
  //       this.examinationService.getAllExaminations().subscribe(
  //         (data: any) => {
  //           this.examinations = data;
  //         },
  //         (error) => {
  //           console.log(error);
  //           Swal.fire("Error!", "Error loading data", error);
  //         }
  //       );
  //     } else {
  //       this.examinationService.getExaminationsByCategory(this.categoryId).subscribe(
  //         (data: any) => {
  //           this.examinations = data;
  //         },
  //         (error) => {
  //           console.log(error);
  //           Swal.fire("Error!", "Error loading data", error);
  //         }
  //       );
  //     }
  //   });
  // }


  ngOnInit(): void {
    // Get categoryId from route parameters with a default value of null
    this.categoryId = this.activatedRoute.snapshot.params['categoryId'] 
      ? Number(this.activatedRoute.snapshot.params['categoryId']) 
      : null;

    if (this.categoryId) {
      this.loadExaminationsByCategory(this.categoryId);
    } else {
      this.loadAllExaminations();
    }
  }

  private loadAllExaminations(): void {
    this.examinationService.getAllExaminations().subscribe(
      (data: any) => {
        this.examinations = data;
      },
      (error) => {
        console.error(error);
        Swal.fire("Error!", "Error loading all examinations", 'error');
      }
    );
  }


  private loadExaminationsByCategory(categoryId: number): void {
    this.examinationService.getExaminationsByCategory(categoryId).subscribe(
      (data: any) => {
        this.examinations = data;
      },
      (error) => {
        console.error(error);
        Swal.fire("Error!", "Error loading examinations by category", 'error');
      }
    );
  }


  deleteExamination(examId: number): void {
    Swal.fire({
      icon: 'info',
      title: 'Are you sure?',
      confirmButtonText: 'Delete',
      showCancelButton: true,
    }).then((result) => {
      if (result.isConfirmed) {
        this.examinationService.deleteExamination(examId).subscribe({
          next: (data: any) => {
            this.examinations = this.examinations.filter((exam) => exam.eid !== examId);
            Swal.fire('Success!!', 'Examination deleted successfully', 'success');
          },
          error: (error: any) => {
            Swal.fire('Error!!', 'Failed to delete examination', 'error');
          }
      });
      }
    });
  }

}
