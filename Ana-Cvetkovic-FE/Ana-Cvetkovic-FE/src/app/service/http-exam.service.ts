import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Exam } from '../core/models/Exam';

@Injectable({
  providedIn: 'root'
})
export class HttpExamService {

  controlerPrefix = 'exam';

  constructor(private httpClient: HttpClient) {   }

  getAll(){
    return this.httpClient.get<Exam[]>(`${environment.baseHttpURL}/${this.controlerPrefix}`)
  }

  getById(id: number) {
    return this.httpClient.get<Exam>(`${environment.baseHttpURL}/${this.controlerPrefix}/${id}`)
  }

  insertExam(exam: Object): Observable<Object>{
    return this.httpClient.post(`${environment.baseHttpURL}/${this.controlerPrefix}`, exam)
  }

  updateExam(exam: Exam) {
    return this.httpClient.put<Exam>(`${environment.baseHttpURL}/${this.controlerPrefix}`, exam)
  }

  deleteExam(exam: Exam) {
    return this.httpClient
    .delete(`${environment.baseHttpURL}/${this.controlerPrefix}/${exam.id}`, {responseType: 'text'})

  }

}
