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

// Pie chart
// export var single = [
//   { name: 'Cash', value: 20 },
//   { name: 'Stocks', value: 15 },
//   { name: 'Bonds', value: 25 },
//   { name: 'Futures', value: 20 },
//   { name: 'ETFs', value: 20 },
// ];


@Component({
  selector: 'app-balances',
  templateUrl: './balances.component.html',
  styleUrls: ['./balances.component.css']
})

export class BalancesComponent implements OnInit {
  today = new Date();

  // data from database
  usersName: any;
  exchangeRate: number = 0;

  // currency table
  // CURRENCY_TABLE_DATA: currencyTableRow[] = [
  //   { currency: 'CAD only', cash: 5555, marketValue: 5555, totalEquity: 5555 },
  //   { currency: 'USD only', cash: 7777, marketValue: 7777, totalEquity: 7777 },
  //   { currency: 'Combined in CAD', cash: 9999, marketValue: 9999, totalEquity: 9999 }
  // ];
  displayedCurrencyColumns: string[] = ['currency', 'cash', 'marketValue', 'totalEquity'];
  // CURRENCY_TABLE_DATA: currencyTableRow[];
  // currencyDataSource = this.CURRENCY_TABLE_DATA;
  currencyDataSource: any;

  // investmentType table
  // INVESTMENT_TYPE_TABLE_DATA: investmentTypeTableRow[] = [
  //   { investmentType: 'Stocks', percentage: 15, marketValue: 33 },
  //   { investmentType: 'Bonds', percentage: 25, marketValue: 44 },
  //   { investmentType: 'Futures', percentage: 20, marketValue: 55 },
  //   { investmentType: 'ETFs', percentage: 20, marketValue: 66 },
  // ];
  displayedInvestmentTypeColumns: string[] = ['investmentType', 'percentage', 'marketValue'];
  // investmentTypeDataSource = this.INVESTMENT_TYPE_TABLE_DATA;
  investmentTypeDataSource: any;
percentageTotal: number = 0;
marketValueTotal: number = 0;


  // Pie Chart
  single: any;
  // singleAdvanced: any;
  // view: any = [];
  gradient: boolean = false;
  showLegend: boolean = false;
  showLabels: boolean = true;
  isDoughnut: boolean = false;
  // legendPosition: string = 'below';
  colorScheme: string = 'vivid';
  // public formatFn = this.valueFormatting.bind(this);
  // valueFormatting(data:any) {
  //   return '$' + data;
  // }
  constructor(private databaseService: DatabaseService) {
    Object.assign(this, this.single );
  }

  ngOnInit(): void {
    this.databaseService.getApiData('user')
      .subscribe((incomingData: any) => {
        this.usersName = incomingData[0].name;
        // console.log('user ' + JSON.stringify(this.data[0].name));
      });

    this.databaseService.getApiData('portfolio-manager/exchange-rate/USD-CAD')
      .subscribe((incomingData: any) => {
        this.exchangeRate = incomingData
        console.log(this.exchangeRate);
      });

    // currency table
    this.databaseService.getApiData('user/account/balance/userid=1')
      .subscribe((incomingData: any) => {
        // this.exchangeRate = incomingData
        // console.log(this.networthToday);
        let CURRENCY_TABLE_DATA: currencyTableRow[] = incomingData;
        this.currencyDataSource = CURRENCY_TABLE_DATA;
      });

    // investment table
    this.databaseService.getApiData('portfolio-manager/investments/portfolio-percentage/userId=1')
      .subscribe((incomingData: any) => {
        console.log(incomingData);
        let INVESTMENT_TYPE_TABLE_DATA: investmentTypeTableRow[] = incomingData;
        this.investmentTypeDataSource = INVESTMENT_TYPE_TABLE_DATA;
        this.percentageTotal = INVESTMENT_TYPE_TABLE_DATA.map(t => t.percentage).reduce((acc, value) => acc + value, 0);
        this.marketValueTotal = INVESTMENT_TYPE_TABLE_DATA.map(t => t.marketValue).reduce((acc, value) => acc + value, 0);

        // pie chart
        this.mapToPieChart(incomingData);
      });
  }

  mapToPieChart(incomingData: any) {
    this.single = incomingData.map((x: { investmentType: any; percentage: any; }) => {
      return {
        name: x.investmentType,
        value: x.percentage, 
      }
    })
  }

  //  --------------------- investmentType Table ---------------------



  // calculatePercentageTotal() {
  //   return this.INVESTMENT_TYPE_TABLE_DATA.map(t => t.percentage).reduce((acc, value) => acc + value, 0);
  // }

  // calculateMarketValueTotal() {
  //   return this.INVESTMENT_TYPE_TABLE_DATA.map(t => t.marketValue).reduce((acc, value) => acc + value, 0);
  // }
  //  --------------------- /investmentType Table ---------------------

}
