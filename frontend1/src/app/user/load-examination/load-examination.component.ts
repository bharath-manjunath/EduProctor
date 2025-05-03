import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ExaminationService } from 'src/app/services/examination.service';
import { Examination } from 'src/app/models/examination.model';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-load-examination',
  templateUrl: './load-examination.component.html',
  styleUrls: ['./load-examination.component.css']
})
export class LoadExaminationComponent implements OnInit{

  categoryId: number | null = null;
  examinations: any[] = [];

  constructor(
    private activatedRoute: ActivatedRoute,
    private examinationService: ExaminationService
  ) {}

  // ngOnInit(): void {

  //   this.categoryId = this.activatedRoute.snapshot.params['catid'] 
  //     ? Number(this.activatedRoute.snapshot.params['catid']) 
  //     : null;

      

  //     if (this.categoryId && this.categoryId > 0) {
        
  //       this.loadExaminationsByCategory(this.categoryId);
        
  //     } else {
  //       this.loadAllExaminations();  
  //     }

  // }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      this.categoryId = params['catid'] ? Number(params['catid']) : null;
  
      if (this.categoryId && this.categoryId > 0) {
        this.loadExaminationsByCategory(this.categoryId);
      } else {
        this.loadAllExaminations();
      }
    });
  }

  private loadAllExaminations(): void {
    this.examinationService.getAllActiveExaminations().subscribe(
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
    this.examinationService.getActiveExaminationsByCategory(categoryId).subscribe(
      (data: any) => {
        this.examinations = data;
      },
      (error) => {
        console.error(error);
        Swal.fire("Error!", "Error loading examinations by category", 'error');
      }
    );
  }
}
