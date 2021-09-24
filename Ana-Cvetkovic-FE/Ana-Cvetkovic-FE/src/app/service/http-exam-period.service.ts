import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ExamPeriod } from '../core/models/ExamPeriod';

@Injectable({
  providedIn: 'root'
})
export class HttpExamPeriodService {

  controlerPrefix = 'examperiod';

  constructor(private httpClient: HttpClient) {   }

  getAll(){
    return this.httpClient.get<ExamPeriod[]>(`${environment.baseHttpURL}/${this.controlerPrefix}`)
  }

  getById(id: number) {
    return this.httpClient.get<ExamPeriod>(`${environment.baseHttpURL}/${this.controlerPrefix}/${id}`)
  }

  insertExamPeriod(examPeriod: Object): Observable<Object>{
    return this.httpClient.post(`${environment.baseHttpURL}/${this.controlerPrefix}`, examPeriod)
  }

  updateExamPeriod(examPeriod: ExamPeriod) {
    return this.httpClient.put<ExamPeriod>(`${environment.baseHttpURL}/${this.controlerPrefix}`, examPeriod)
  }

  deleteExamPeriod(examPeriod: ExamPeriod) {
    return this.httpClient
    .delete(`${environment.baseHttpURL}/${this.controlerPrefix}/${examPeriod.id}`, {responseType: 'text'})

  }

}
