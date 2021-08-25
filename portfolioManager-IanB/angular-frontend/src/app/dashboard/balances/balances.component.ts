import { Component, OnInit } from '@angular/core';
import { DatabaseService } from 'src/app/database/database.service';

// currency table
export interface currencyTableRow {
  currency: string;
  cash: number;
  marketValue: number;
  totalEquity: number;
}
const CURRENCY_TABLE_DATA: currencyTableRow[] = [
  { currency: 'CAD only', cash: 5555, marketValue: 5555, totalEquity: 5555 },
  { currency: 'USD only', cash: 7777, marketValue: 7777, totalEquity: 7777 },
  { currency: 'Combined in CAD', cash: 9999, marketValue: 9999, totalEquity: 9999 }
];

@Component({
  selector: 'app-balances',
  templateUrl: './balances.component.html',
  styleUrls: ['./balances.component.css']
})

export class BalancesComponent implements OnInit {

  // data from database
  data: Object = {};
  networthToday?: number;

  // currency table
  displayedColumns: string[] = ['currency', 'cash', 'marketValue', 'totalEquity'];
  dataSource = CURRENCY_TABLE_DATA;

  today = new Date()

  constructor(private databaseService: DatabaseService) { }

  ngOnInit(): void {
    this.databaseService.getApiData('user')
      .subscribe((incomingData: any) => {
        this.data = incomingData
        console.log(this.data);
      });

    this.databaseService.getApiData('portfoliomanager/networth/today')
      .subscribe((incomingData: any) => {
        this.networthToday = incomingData
        console.log(this.networthToday);
      });
  }



}
