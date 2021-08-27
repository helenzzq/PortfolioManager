import { Component, OnInit } from '@angular/core';
import { DatabaseService } from 'src/app/database/database.service'

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  getStr = "portfolio-manager/net-worth/today"
  netWorthToday = 0
  todaysDate = new Date();

  constructor(private databaseService:DatabaseService) { }

  ngOnInit(): void {
    this.getNetWorth()
  }

  getNetWorth() {
    this.databaseService.getApiData(this.getStr).subscribe(
      (data:any) => {
        this.netWorthToday = data.toFixed(2)
      },
      (err) => console.log(err)
    )
  }

}
