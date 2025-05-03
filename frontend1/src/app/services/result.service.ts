import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Result } from '../models/result.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ResultService {

  private baseUrl = 'http://localhost:8082/api/result'

  constructor(private http: HttpClient) { }

  submitResult(result: Result): Observable<any> {
    return this.http.post(`${this.baseUrl}/create`, result);
  }

  getCurrentResult(): Observable<Result> {
    return this.http.get<Result>(`${this.baseUrl}/current`);
  }

  getAllResultsOfUser(uid: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/all/${uid}`);
  }

  public deleteAllResultsOfUser(uid:any){
    return this.http.delete(`${this.baseUrl}/deleteAll/${uid}`);
  }


    // delete single result of user
    public deleteResult(resultid:number){
      return this.http.delete(`${this.baseUrl}/delete/${resultid}`);
    }
  



}
