import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Subject } from '../core';
import { Page } from '../core/models/page.dto';

@Injectable({
  providedIn: 'root'
})
export class HttpSubjectService {

  controlerPrefix = 'subject';

  constructor(private httpClient: HttpClient) {   }

  getAll(){
    return this.httpClient.get<Subject[]>(`${environment.baseHttpURL}/${this.controlerPrefix}`)
  }

  getById(id: number) {
    return this.httpClient.get<Subject>(`${environment.baseHttpURL}/${this.controlerPrefix}/${id}`)
  }

  getByPage(page:number, size: number) {
    return this.httpClient.get<Page<Subject[]>>(`${environment.baseHttpURL}/${this.controlerPrefix}/page?page=${page}&size=${size}`)
  }

  insertSubject(subject: Object): Observable<Object>{
    return this.httpClient.post(`${environment.baseHttpURL}/${this.controlerPrefix}`, subject)
  }

  updateSubject(subject: Subject) {
    return this.httpClient.put<Subject>(`${environment.baseHttpURL}/${this.controlerPrefix}`, subject)
  }

  deleteSubject(subject: Subject) {
    return this.httpClient
    .delete(`${environment.baseHttpURL}/${this.controlerPrefix}/${subject.id}`, {responseType: 'text'})

  }

}
