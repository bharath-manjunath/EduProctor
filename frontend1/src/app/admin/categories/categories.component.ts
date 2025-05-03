import { Component, OnInit  } from '@angular/core';
import { Category } from '../../models/category.model';
import { CategoryService } from 'src/app/services/category.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';


@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit{

  categories: Category[] = [];

  constructor(
    private categoryService: CategoryService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadCategories();
  }

  loadCategories() {
    this.categoryService.getAllCategories().subscribe(
      (data: Category[]) => {
        this.categories = data;
      },
      (error) => {
        console.error(error);
        Swal.fire("Error!", "Error in loading categories.", "error");
      }
    );
  }

  deleteCategory(catid: number) {
    Swal.fire({
      icon: 'info',
      title: 'Are you sure?',
      confirmButtonText: 'Delete',
      showCancelButton: true,
    }).then((result) => {
      if (result.isConfirmed) {
        this.categoryService.deleteCategory(catid).subscribe(
          () => {
            this.categories = this.categories.filter((category) => category.cid !== catid);
            Swal.fire('Deleted!', 'Category has been deleted.', 'success');
          },
          (error) => {
            Swal.fire('Error!', 'Failed to delete category.', 'error');
          }
        );
      }
    });
  }

  goToExaminations(catid: number) {
    this.router.navigate(['/admin/examinations', catid]);
  }

  updateCategory(catid: number) {
    this.router.navigate(['/admin/update-category', catid]);
  }
}
