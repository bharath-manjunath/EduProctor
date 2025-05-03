import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ExaminationService } from 'src/app/services/examination.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-examination-intro',
  templateUrl: './examination-intro.component.html',
  styleUrls: ['./examination-intro.component.css']
})
export class ExaminationIntroComponent implements OnInit{

  examId: number | null = null;
  examination: any = {};

  constructor(
    private activatedRoute: ActivatedRoute,
    private examinationService: ExaminationService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const examinationId = this.activatedRoute.snapshot.params['examId'];
    this.examId = examinationId;
    this.loadExamination(examinationId);
  }

  private loadExamination(examinationId: number): void {
    this.examinationService.getExaminationById(examinationId).subscribe(
      (data: any) => {
        this.examination = data;
      },
      (error) => {
        console.error('Error loading examination:', error);
        Swal.fire('Error', 'Failed to load examination data', 'error');
      }
    );
  }

  startExamination(): void {
    Swal.fire({
      title: 'Do you want to start the examination?',
      showCancelButton: true,
      confirmButtonText: 'Start',
      icon: 'info',
    }).then((result) => {
      if (result.isConfirmed) {
        this.router.navigate(['/examination-start', this.examination.eid]);
      }
    });
  }
}
