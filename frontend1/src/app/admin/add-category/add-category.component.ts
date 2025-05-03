import { Component, OnInit } from '@angular/core';
// import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Category } from '../../models/category.model';
import { CategoryService } from '../../services/category.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
export class AddCategoryComponent implements OnInit{
  category: Category = {
    title: '',
    description: '',
  };

  constructor(
    private categoryService: CategoryService,
    // private snackBar: MatSnackBar,
    private router: Router
  ) {}

  ngOnInit(): void {}

  addCategory(): void {
    if (!this.category.title || !this.category.description) {
      Swal.fire('Warning', 'Title and Description are required!', 'warning');
      // this.snackBar.open('Title and Description are required!', 'OK', {
      //   duration: 3000,
      // });
      return;
    }

    this.categoryService.addCategory(this.category).subscribe(
      (data) => {
        Swal.fire('Success', 'Category added successfully!', 'success');
        // alert("Category added successfully")
        this.router.navigate(['/admin/categories']);
      },
      (error) => {
        // console.error(error);
        Swal.fire('Error', 'Failed to add category!', 'error');
      }
    );
  }
  

}

// export class AddCategoryComponent{}