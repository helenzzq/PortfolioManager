import { Component, OnInit } from '@angular/core';
import { DatabaseService } from 'src/app/database/database.service';

// currency table
export interface currencyTableRow {
  currency: string;
  cash: number;
  marketValue: number;
  totalEquity: number;
}

// investmentType table
export interface investmentTypeTableRow {
  investmentType: string;
  percentage: number;
  marketValue: number;
}

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
  CURRENCY_TABLE_DATA: currencyTableRow[] = [
    { currency: 'CAD only', cash: 5555, marketValue: 5555, totalEquity: 5555 },
    { currency: 'USD only', cash: 7777, marketValue: 7777, totalEquity: 7777 },
    { currency: 'Combined in CAD', cash: 9999, marketValue: 9999, totalEquity: 9999 }
  ];
  displayedCurrencyColumns: string[] = ['currency', 'cash', 'marketValue', 'totalEquity'];
  currencyDataSource = this.CURRENCY_TABLE_DATA;

  // investmentType table
  INVESTMENT_TYPE_TABLE_DATA: investmentTypeTableRow[] = [
    { investmentType: 'Cash', percentage: 2, marketValue: 22 },
    { investmentType: 'Stocks', percentage: 3, marketValue: 33 },
    { investmentType: 'Bonds', percentage: 4, marketValue: 44 },
    { investmentType: 'Futures', percentage: 5, marketValue: 55 },
    { investmentType: 'ETFs', percentage: 6, marketValue: 66 },
  ];
  displayedInvestmentTypeColumns: string[] = ['investmentType', 'percentage', 'marketValue'];
  investmentTypeDataSource = this.INVESTMENT_TYPE_TABLE_DATA;

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

  calculatePercentageTotal() {
    return this.INVESTMENT_TYPE_TABLE_DATA.map(t => t.percentage).reduce((acc, value) => acc + value, 0);
  }

  calculateMarketValueTotal() {
    return this.INVESTMENT_TYPE_TABLE_DATA.map(t => t.marketValue).reduce((acc, value) => acc + value, 0);
  }


}
