import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Page } from '../core/models/page.dto';
import { Professor } from '../core/models/professor.model';

@Injectable({
  providedIn: 'root'
})
export class HttpProfessorService {

  controlerPrefix = 'professor';

  constructor(private httpClient: HttpClient) { }

  getAll(){
    return this.httpClient.get<Professor[]>(`${environment.baseHttpURL}/${this.controlerPrefix}`)
  }

  getById(id: number) {
    return this.httpClient.get<Professor>(`${environment.baseHttpURL}/${this.controlerPrefix}/${id}`)
  }

  getByPage(page:number, size: number) {
    return this.httpClient.get<Page<Professor[]>>(`${environment.baseHttpURL}/${this.controlerPrefix}/page?page=${page}&size=${size}`)
  }

  insertProfessor(professor: Object): Observable<Object>{
    return this.httpClient.post(`${environment.baseHttpURL}/${this.controlerPrefix}`, professor)
  }

  updateProfessor(professor: Professor) {
    return this.httpClient.put<Professor>(`${environment.baseHttpURL}/${this.controlerPrefix}`, professor)
  }

  deleteProfessor(professor: Professor) {
    return this.httpClient
    .delete(`${environment.baseHttpURL}/${this.controlerPrefix}/${professor.id}`, {responseType: 'text'})

  }

}
