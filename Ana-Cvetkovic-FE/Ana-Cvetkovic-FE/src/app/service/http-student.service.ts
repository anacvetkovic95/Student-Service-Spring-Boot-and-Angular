import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Page } from '../core/models/page.dto';
import { Student } from '../core/models/student.model';

@Injectable({
  providedIn: 'root'
})
export class HttpStudentService {

  controlerPrefix = 'student';

  constructor(private httpClient: HttpClient) { }

  getAll(){
    return this.httpClient.get<Student[]>(`${environment.baseHttpURL}/${this.controlerPrefix}`)
  }

  getById(id: number) {
    return this.httpClient.get<Student>(`${environment.baseHttpURL}/${this.controlerPrefix}/${id}`)
  }

  getByPage(page:number, size: number) {
    return this.httpClient.get<Page<Student[]>>(`${environment.baseHttpURL}/${this.controlerPrefix}/page?page=${page}&size=${size}`)
  }

  insertStudent(student: Object): Observable<Object>{
    return this.httpClient.post(`${environment.baseHttpURL}/${this.controlerPrefix}`, student)
  }

  updateStudent(student: Student) {
    return this.httpClient.put<Student>(`${environment.baseHttpURL}/${this.controlerPrefix}`, student)
  }

  deleteStudent(student: Student) {
    return this.httpClient
    .delete(`${environment.baseHttpURL}/${this.controlerPrefix}/${student.id}`, {responseType: 'text'})

  }

}
