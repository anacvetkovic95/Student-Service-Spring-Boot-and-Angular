import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { City } from '../core/models/student.model';

@Injectable({
  providedIn: 'root'
})
export class HttpCityService {

  controlerPrefix = 'city';

  constructor(private httpClient: HttpClient) { }

  getAll(){
    return this.httpClient.get<City[]>(`${environment.baseHttpURL}/${this.controlerPrefix}`)
  }
}
