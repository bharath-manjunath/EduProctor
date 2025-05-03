import { Component, OnInit  } from '@angular/core';
// import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Category } from 'src/app/models/category.model';
import { Examination } from 'src/app/models/examination.model';
import { CategoryService } from 'src/app/services/category.service';
import { ExaminationService } from 'src/app/services/examination.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-add-examination',
  templateUrl: './add-examination.component.html',
  styleUrls: ['./add-examination.component.css']
})
export class AddExaminationComponent implements OnInit{

  categories: Category[] = [];
  examination: Examination = {
    title: '',
    description: '',
    active: false,
    examinationDate: '',
    category: { cid: 0, title: '', description: '' },  // Initialize with empty values
    questions: []  // Empty array for questions
  };

  constructor(
    private categoryService: CategoryService,
    private examinationService: ExaminationService,
    // private snackBar: MatSnackBar,
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
  }


  addExamination() {
    if (
      !this.examination.title ||
      !this.examination.description ||
      // !this.examination.examinationDate ||
      !this.examination.category.cid
    ) {
      Swal.fire('Warning', 'All fields are required!', 'warning');
      return;
    }

    this.examinationService.createExamination(this.examination).subscribe(
      (data: any) => {
        this.examination = {
          title: '',
          description: '',
          active: false,
          examinationDate: '',
          category: { cid: 0, title: '', description: '' },
          questions: []
        };  
        Swal.fire('Success!', 'Examination is added successfully', 'success');
        this.router.navigate(['/admin/examinations/', 'null']);
      },
      (error) => {
        Swal.fire('Error!', 'Failed to add Examination', 'error');
      }
    );

  }


}
