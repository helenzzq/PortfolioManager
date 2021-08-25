import { Component, OnInit } from '@angular/core';
import { Data } from '@angular/router';
import { DatabaseService } from 'src/app/database/database.service'

@Component({
  selector: 'app-gainers-losers',
  templateUrl: './gainers-losers.component.html',
  styleUrls: ['./gainers-losers.component.css']
})
export class GainersLosersComponent implements OnInit {

  gainerData = {}
  loserData = {}
  gainerStr = "gainers"
  loserStr = "losers"

  constructor(private databaseService:DatabaseService) { }

  ngOnInit(): void {
    
  }

  getGainers() {
    this.databaseService.getApiData(this.gainerStr).subscribe(
      (data:any) => {this.gainerData = data},
      (err) => console.log(err)
    )
  }

  getLosers() {
    this.databaseService.getApiData(this.loserStr).subscribe(
      (data:any) => {this.loserData = data},
      (err) => console.log(err)
    )
  }

}
