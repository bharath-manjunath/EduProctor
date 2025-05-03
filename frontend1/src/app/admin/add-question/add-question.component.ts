import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { QuestionService } from '../../services/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.css']
})
export class AddQuestionComponent implements OnInit {
  examinationId: number = 0;
  questionForm: FormGroup;
  questionTypes = [
    { id: 1, name: 'Quiz' },
    { id: 2, name: 'Fill in the Blank' },
    { id: 3, name: 'Short Answer' },
    { id: 4, name: 'Essay' }
  ];

  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private questionService: QuestionService,
    private router: Router
  ) {
    // Initialize the form group with all fields
    this.questionForm = this.fb.group({
      content: ['', Validators.required],
      typeId: [null, Validators.required],
      option1: [''],
      option2: [''],
      option3: [''],
      option4: [''],
      quizAnswer: [''],
      fillAnswer: [''],
      shortAnswer: [''],
      essayAnswer: [''],
      wordLimit: ['']
    });
  }

  ngOnInit(): void {
    this.examinationId = this.route.snapshot.params['examId'];
    this.questionForm.get('typeId')?.valueChanges.subscribe(() => this.onQuestionTypeChange());
  }

  onQuestionTypeChange(): void {
    const questionType = +this.questionForm.get('typeId')?.value; // Ensure typeId is cast to a number
    console.log('Selected Question Type:', questionType);

    // Reset all dynamic fields
    this.questionForm.patchValue({
      option1: '',
      option2: '',
      option3: '',
      option4: '',
      quizAnswer: '',
      fillAnswer: '',
      shortAnswer: '',
      essayAnswer: '',
      wordLimit: ''
    });

    // Clear existing validators for all fields
    Object.keys(this.questionForm.controls).forEach((key) => {
      this.questionForm.get(key)?.clearValidators();
      this.questionForm.get(key)?.updateValueAndValidity();
    });

    // Dynamically apply validators based on question type
    if (questionType === 1) {
      this.setValidators(['option1', 'option2', 'option3', 'option4', 'quizAnswer'], Validators.required);
    } else if (questionType === 2) {
      this.setValidators(['fillAnswer'], Validators.required);
    } else if (questionType === 3) {
      this.setValidators(['shortAnswer'], Validators.required);
    } else if (questionType === 4) {
      this.setValidators(['essayAnswer', 'wordLimit'], Validators.required);
    }
  }

  setValidators(fields: string[], validator: any): void {
    fields.forEach((field) => {
      const control = this.questionForm.get(field);
      if (control) {
        control.setValidators(validator);
        control.updateValueAndValidity();
        console.log(`Set validator for ${field}`);
      }
    });
  }

  onSubmit(): void {
    if (this.questionForm.valid) {
      const questionData = {
        ...this.questionForm.value,
        examinationId: this.examinationId
      };

      console.log('Final Payload:', questionData);


      this.questionService.addQuestion(questionData).subscribe({
        next: () => {
          Swal.fire({
            title: 'Success!',
            text: 'The question has been added successfully.',
            icon: 'success',
            confirmButtonText: 'Okay'
          }).then(() => {
            this.router.navigate([`/admin/questions/${this.examinationId}`]);
          });
        },
        error: (err) => {
          Swal.fire({
            title: 'Error!',
            text: 'There was an error while adding the question.',
            icon: 'error',
            confirmButtonText: 'Try Again'
          });
          console.error('Error adding question:', err);
        }
      });
    } else {
      Swal.fire({
        title: 'Form Invalid!',
        text: 'Please fill in all required fields.',
        icon: 'warning',
        confirmButtonText: 'Okay'
      });
    }
  }
}
