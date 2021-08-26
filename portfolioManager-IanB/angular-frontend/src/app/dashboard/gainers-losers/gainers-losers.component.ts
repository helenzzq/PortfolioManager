import { Component, OnInit } from '@angular/core';
import { Data } from '@angular/router';
import { DatabaseService } from 'src/app/database/database.service'

@Component({
  selector: 'app-gainers-losers',
  templateUrl: './gainers-losers.component.html',
  styleUrls: ['./gainers-losers.component.css']
})
export class GainersLosersComponent implements OnInit {

  gainerStr = "portfolio-manager/gainers"
  loserStr = "portfolio-manager/losers"
  gainerNames = ["loading...", "loading...", "loading...", "loading...", "loading..."]
  loserNames = ["loading...", "loading...", "loading...", "loading...", "loading..."]


  constructor(private databaseService:DatabaseService) { }

  ngOnInit(): void {
    this.getGainers();
    this.getLosers();
  }

  getGainers() {
    this.databaseService.getApiData(this.gainerStr).subscribe(
      (data:any) => {
        this.gainerNames = [data[0], data[1], data[2], data[3], data[4]]
      },
      (err) => console.log(err)
    )
  }

  getLosers() {
    this.databaseService.getApiData(this.loserStr).subscribe(
      (data:any) => {
        this.loserNames = [data[0], data[1], data[2], data[3], data[4]]
      },
      (err) => console.log(err)
    )
  }

}
