import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Category } from 'src/app/models/category.model';
import { Examination } from 'src/app/models/examination.model';
import { CategoryService } from 'src/app/services/category.service';
import { ExaminationService } from 'src/app/services/examination.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-update-examination',
  templateUrl: './update-examination.component.html',
  styleUrls: ['./update-examination.component.css']
})
export class UpdateExaminationComponent implements OnInit{

  categories: Category[] = [];
  examination: Examination = {
    title: '',
    description: '',
    active: false,
    examinationDate: '',
    category: { cid: 0, title: '', description: '' },
    questions: []
  };

  constructor(
    private categoryService: CategoryService,
    private examinationService: ExaminationService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Load categories
    this.categoryService.getAllCategories().subscribe(
      (data: any) => {
        this.categories = data;
      },
      (error) => {
        Swal.fire('Error!', 'Error in Loading Category List', 'error');
      }
    );

    // Load the examination details using the examination ID from route
    const examId = this.route.snapshot.params['examId'];
    this.examinationService.getExaminationById(examId).subscribe(
      (data: Examination) => {
        this.examination = data;
      },
      (error) => {
        Swal.fire('Error!', 'Error in Loading Examination Details', 'error');
      }
    );
  }

  updateExamination() {
    if (
      !this.examination.title ||
      !this.examination.description ||
      // !this.examination.examinationDate ||
      !this.examination.category.cid
    ) {
      Swal.fire('Warning', 'All fields are required!', 'warning');
      return;
    }

    this.examinationService.updateExamination(this.route.snapshot.params['examId'],this.examination).subscribe(
      (data: any) => {
        Swal.fire('Success!', 'Examination updated successfully', 'success');
        this.router.navigate(['/admin/examinations', 'null']);
      },
      (error) => {
        Swal.fire('Error!', 'Failed to update Examination', 'error');
      }
    );
  }

}
