import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Examination } from 'src/app/models/examination.model'

@Injectable({
  providedIn: 'root'
})
export class ExaminationService {
  private baseUrl = 'http://localhost:8082/api/examinations';
  constructor(private httpClient: HttpClient) { }

  public createExamination(examination: Examination): Observable<Examination> {
    return this.httpClient.post<Examination>(this.baseUrl, examination);
  }

  public getAllExaminations(): Observable<any> {
    return this.httpClient.get(this.baseUrl);
  }


  // Get a single examination by ID
  public getExaminationById(examinationId: number): Observable<any> {
    return this.httpClient.get(`${this.baseUrl}/${examinationId}`);
  }


  public getExaminationsByCategory(cid: number): Observable<any> {
    return this.httpClient.get(`${this.baseUrl}/category/${cid}`);
  }


  public getAllActiveExaminations(): Observable<any> {
    return this.httpClient.get(`${this.baseUrl}/active`);
  }


  public getActiveExaminationsByCategory(categoryId: number): Observable<any> {
    return this.httpClient.get(`${this.baseUrl}/category/${categoryId}/active`);
  }

  public deleteExamination(examinationId: number): Observable<any> {
    return this.httpClient.delete(`${this.baseUrl}/${examinationId}`);
  }

  public updateExamination(examinationId: number,examination:Examination): Observable<Examination>{
    return this.httpClient.put<Examination>(`${this.baseUrl}/${examinationId}`, examination);  
  }
}
