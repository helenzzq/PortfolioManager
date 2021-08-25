import { Component, OnInit } from '@angular/core';
import { DatabaseService } from 'src/app/database/database.service';

@Component({
  selector: 'app-balances',
  templateUrl: './balances.component.html',
  styleUrls: ['./balances.component.css']
})
export class BalancesComponent implements OnInit {
  data: Object = {};
  constructor(private databaseService: DatabaseService) { }

  ngOnInit(): void {
    this.databaseService.getApiData('user')
      .subscribe((incomingData: any) => {
        this.data = incomingData
        console.log(this.data);
      });
  }



}
