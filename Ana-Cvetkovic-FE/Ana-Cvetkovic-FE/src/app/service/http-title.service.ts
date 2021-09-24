import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Title } from '../core/models/professor.model';

@Injectable({
  providedIn: 'root'
})
export class HttpTitleService {

  controlerPrefix = 'title';

  constructor(private httpClient: HttpClient) { }

  getAll(){
    return this.httpClient.get<Title[]>(`${environment.baseHttpURL}/${this.controlerPrefix}`)
  }
}
