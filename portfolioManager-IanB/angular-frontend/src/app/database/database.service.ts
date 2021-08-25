import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class DatabaseService {
  
  getApiData(param:String){ 
    return this.http.get(`https://namdevops5.conygre.com/${param}`)
  }
  
  constructor(private http:HttpClient) { }
}
