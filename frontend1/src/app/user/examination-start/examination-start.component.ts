import { LocationStrategy } from '@angular/common';
import { Component, OnInit  } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Examination } from 'src/app/models/examination.model';
import { Result } from 'src/app/models/result.model';
import { UserQna } from 'src/app/models/userqna.model';
import { AuthService } from 'src/app/services/auth.service';
import { QuestionService } from 'src/app/services/question.service';
import { ExaminationService } from 'src/app/services/examination.service';
import { ResultService } from 'src/app/services/result.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-examination-start',
  templateUrl: './examination-start.component.html',
  styleUrls: ['./examination-start.component.css']
})
export class ExaminationStartComponent {

  examination: any = {};
  questions: any[] = [];
  result: any = {};
  timer: any;
  currentUrl: any;
  userQnas: any[] = [];
  uid!: number;
  isSubmitted: boolean = false; 
  timerValue: number = 600;
  timerInterval: any;

  userDetails: {
    id?: number;
    email: string;
    enabled: boolean;
    firstName: string;
    lastName: string;
    password?: string;
    phone: string;
    userName: string;
    profile: string;
    clientRole: string;
  } = {
    id: undefined,
    email: '',
    enabled: false,
    firstName: '',
    lastName: '',
    password: '',
    phone: '',
    userName: '',
    profile: '',
    clientRole: ''
  };


  constructor(
    private locationStrategy: LocationStrategy,
    private activatedRoute: ActivatedRoute,
    private authService: AuthService,
    private examinationService: ExaminationService,
    private questionService: QuestionService,
    private resultService: ResultService,
    private router: Router
  ) {}

  

  ngOnInit(): void {
    this.preventBack();
    this.examination.eid = this.activatedRoute.snapshot.params['examId'];
    const storedDetails = localStorage.getItem('userDetails');
    if(storedDetails){
      this.userDetails = JSON.parse(storedDetails);
      if(this.userDetails.id){
        this.uid = this.userDetails.id;
      }
    }
    this.loadQuestions();
    this.loadExaminationDetails();
  }

  preventBack() {
    history.pushState(null, "null", location.href);
    this.locationStrategy.onPopState(() => {
      history.pushState(null, "null", location.href);
    });
  }

  loadQuestions() {
    this.questionService.getQuestionsByExamination(this.examination.eid).subscribe(
      (data: any[]) => {
        this.questions = data;
        this.questions.forEach((q) => (q.answer = ''));
        this.timerValue = 600; // Reset timer value
        if (this.timerInterval) {
          clearInterval(this.timerInterval); // Clear existing interval
        }
        this.startTimer();
      },
      (error) => {
        Swal.fire('Error', 'Error in loading Questions', 'error');
      }
    );
  }

  loadExaminationDetails() {
    this.examinationService.getExaminationById(this.examination.eid).subscribe(
      (data: any) => {
        this.examination = data;
      },
      (error) => {
        Swal.fire('Error', 'Failed to load Examination Details', 'error');
      }
    );
  }

  // startTimer() {
  //   this.timer = setInterval(() => {
  //     if (this.timer <= 0) {
  //       this.submitExamination();
  //       clearInterval(this.timer);
  //     } else {
  //       this.timer--;
  //     }
  //   }, 1000);
  // }
  startTimer() {
    this.timerInterval = setInterval(() => {
      if (this.timerValue <= 0) {
        clearInterval(this.timerInterval);
        this.submitExamination();
      } else {
        this.timerValue--;
      }
    }, 1000);
  }

  // getTimerFormat() {
  //   let min = Math.floor(this.timer / 60);
  //   let sec = this.timer - min * 60;
  //   return `${min} min : ${sec} sec`;
  // }

  getTimerFormat() {
    let min = Math.floor(this.timerValue / 60);
    let sec = this.timerValue % 60;
    return `${min} min : ${sec} sec`;
  }

  submitExamination() {
    if (this.isSubmitted) return;
    Swal.fire({
      title: 'Do you want to submit the examination?',
      showCancelButton: true,
      confirmButtonText: 'Submit',
      icon: 'info',
    }).then((result) => {
      if (result.isConfirmed) {
        this.isSubmitted = true;
        this.doSubmitExamination();
      }
    });
  }

  doSubmitExamination() {
    clearInterval(this.timer);
    this.result.client = { id: this.uid };
    this.result.examination = { eid: this.examination.eid };
    this.result.userQnas = this.questions.map((q) => ({
      quesId: q.id,
      answer: q.answer,
    }));

    this.resultService.submitResult(this.result).subscribe(
      (data:any) => {
        Swal.fire('Success !!', 'Examination submitted successfully', 'success');
        this.router.navigate(['/userdashboard/load-result']);
      },
      (error) => {
        Swal.fire('Error !!', 'Failed to submit Examination', 'error');
        this.router.navigate(['/userdashboard/examinationintro/' + this.examination.eid]);
      }
    );
  }

  printPage() {
    window.print();
  }
}
