import { Component, OnInit } from '@angular/core';
import { DatabaseService } from 'src/app/database/database.service';

export interface currencyTableRow {
  currency: string;
  cash: number;
  marketValue: number;
  totalEquity: number;
}

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
  usersName: string = '';
  exchangeRate: number = 0;

  // Currency Table
  displayedCurrencyColumns: string[] = ['currency', 'cash', 'marketValue', 'totalEquity'];
  currencyDataSource: any;

  // Investment Table
  displayedInvestmentTypeColumns: string[] = ['investmentType', 'percentage', 'marketValue'];
  investmentTypeDataSource: any;
  percentageTotal: number = 0;
  marketValueTotal: number = 0;


  // Pie Chart Options
  single: any;
  gradient: boolean = false;
  showLegend: boolean = false;
  showLabels: boolean = true;
  isDoughnut: boolean = false;
  colorScheme: string = 'vivid';

  constructor(private databaseService: DatabaseService) {
    Object.assign(this, this.single);
  }

  ngOnInit(): void {
    this.databaseService.getApiData('user')
      .subscribe((incomingData: any) => {
        this.usersName = incomingData[0].name;
      });

    this.databaseService.getApiData('portfolio-manager/exchange-rate/USD-CAD')
      .subscribe((incomingData: any) => {
        this.exchangeRate = incomingData
      });

    // Currency Table
    this.databaseService.getApiData('user/account/balance/userid=1')
      .subscribe((incomingData: any) => {
        let CURRENCY_TABLE_DATA: currencyTableRow[] = incomingData;
        this.currencyDataSource = CURRENCY_TABLE_DATA;
      });

    // Investment Table
    this.databaseService.getApiData('portfolio-manager/investments/portfolio-percentage/userId=1')
      .subscribe((incomingData: any) => {
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

  // for making the data visible on the pie chart
  labelFormattingFn(name:any) {
    let self: any = this; // this "this" refers to the chart component
    let data = self.series.filter((x:any) => x.name == name);

    if(data.length > 0) {
      return `${data[0].name}:${data[0].value}%`;
    } else {
      return name;
    }
  }

}
