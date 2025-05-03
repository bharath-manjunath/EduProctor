import { Component, OnInit  } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Category } from '../../models/category.model';
import { CategoryService } from '../../services/category.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-category',
  templateUrl: './update-category.component.html',
  styleUrls: ['./update-category.component.css']
})
export class UpdateCategoryComponent implements OnInit {

  category: Category = {
    cid: 0,
    title: '',
    description: '',
  };

  constructor(
    private route: ActivatedRoute,
    private categoryService: CategoryService,
    public router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.params['cid']; // Fetch the category ID from the route
    if (id) {
      this.categoryService.getCategoryById(id).subscribe(
        (data) => {
          this.category = data;
        },
        (error) => {
          Swal.fire('Error', 'Category not found!', 'error');
          this.router.navigate(['/admin/categories']); // Redirect if the category is not found
        }
      );
    }
  }

  updateCategory(): void {
    if (!this.category.title || !this.category.description) {
      Swal.fire('Warning', 'Title and Description are required!', 'warning');
      return;
    }

    this.categoryService.updateCategory(this.category.cid!, this.category).subscribe(
      (data) => {
        Swal.fire('Success', 'Category updated successfully!', 'success');
        this.router.navigate(['/admin/categories']); // Redirect to the categories list
      },
      (error) => {
        console.error(error);
        Swal.fire('Error', 'Failed to update category!', 'error');
      }
    );
  }
}
