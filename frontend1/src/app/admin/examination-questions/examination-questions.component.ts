import { Component, OnInit  } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionService } from 'src/app/services/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-examination-questions',
  templateUrl: './examination-questions.component.html',
  styleUrls: ['./examination-questions.component.css']
})
export class ExaminationQuestionsComponent implements OnInit{

  examination: any = {};
  questions: any[] = [];


  constructor(
    private route: ActivatedRoute,
    private questionService: QuestionService,
  ) {}


  ngOnInit(): void {
    const examId = this.route.snapshot.params['examId'];

    // Fetch examination questions
    this.questionService.getQuestionsByExamination(examId).subscribe(
      (data: any) => {
        if (data.length > 0) {
          this.examination = data[0].examination; // Use examination details from the first question
          this.questions = data;
        } else {
          this.examination.eid = examId;
          Swal.fire('Error!!', 'No questions available for this examination');
        }
      },
      (error) => {
        Swal.fire('Error', 'Error loading questions', 'error');
      }
    );
  }

  deleteQuestion(questionId: number): void {
    
    Swal.fire({
      icon: 'info',
      title: 'Are you sure?',
      confirmButtonText: 'Delete',
      showCancelButton: true,
    }).then((result) => {
      if (result.isConfirmed) {
        
        this.questionService.deleteQuestion(questionId).subscribe({
          next: (response) => {
           
            this.questions = this.questions.filter((q) => q.id !== questionId);
           
            Swal.fire('Success', 'Question deleted successfully', 'success');
          },
          error: (error) => {
            
            Swal.fire('Error', 'Failed to delete question', 'error');
          },
        });
      }
    });
  }



}
