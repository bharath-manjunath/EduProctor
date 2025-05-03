import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {


  private baseUrl = 'http://localhost:8082/api/questions';

  constructor(private httpClient: HttpClient) { }

  public getQuestionsByExamination(examId: number): Observable<any> {
    return this.httpClient.get(`${this.baseUrl}/examination/${examId}`);
  }

  public deleteQuestion(questionId: number): Observable<any> {
    return this.httpClient.delete(`${this.baseUrl}/${questionId}`, { responseType: 'text' });
  }

  public addQuestion(questionData: any): Observable<any> {
    return this.httpClient.post(this.baseUrl, questionData);
  }
}
