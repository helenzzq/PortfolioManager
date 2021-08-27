import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DatabaseService {
  
  getApiData(param:String){ 
    return this.http.get(environment.apiURL + `${param}`);

  }
  
  constructor(private http:HttpClient) { }
}
