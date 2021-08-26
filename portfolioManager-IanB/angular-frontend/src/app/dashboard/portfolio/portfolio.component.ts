import { Component, OnInit} from '@angular/core';
import { DatabaseService } from 'src/app/database/database.service'


export interface portfolioTableRow {
  ticker: string;
  type: string;
  marketPrice: number;
  percentRetained: number;
  grouped: string;
}

 // portfolio table
 const PORTFOLIO_TABLE_DATA: portfolioTableRow[] = [
  { ticker: 'ABCD', type:"stock", marketPrice: 112.70, percentRetained: 3.54, grouped: 'a'},
  { ticker: 'ALOHA', type:"stock", marketPrice: 80.96, percentRetained: 4.06, grouped: 'a'},
  { ticker: 'LOTR', type:"stock", marketPrice: 48.33, percentRetained: -1.23, grouped: 'a'}
];

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})

export class PortfolioComponent implements OnInit{

  constructor(private databaseService:DatabaseService) {}

  getStr = "portfolio-manager/investments/userId=1"

  investmentList = [{ ticker:"", currency:"", quantity:1.0, costPerShare:"", marketPrice:1.0, marketValue:1.0, profitNLoss:1.0, percentRetained:1.0, percentInPort:1.0, portfolioId:1, type: "", grouped:""}]
  
  etfList = [{ ticker:"", currency:"", quantity:1.0, costPerShare:"", marketPrice:1.0, marketValue:1.0, profitNLoss:1.0, percentRetained:1.0, percentInPort:1.0, portfolioId:1, type: "", grouped:""}]

  futureList = [{ ticker:"", currency:"", quantity:1.0, costPerShare:"", marketPrice:1.0, marketValue:1.0, profitNLoss:1.0, percentRetained:1.0, percentInPort:1.0, portfolioId:1, type: "", grouped:""}]

  bondList = [{ ticker:"", currency:"", quantity:1.0, costPerShare:"", marketPrice:1.0, marketValue:1.0, profitNLoss:1.0, percentRetained:1.0, percentInPort:1.0, portfolioId:1, type: "", grouped:""}]

  stockList = [{ ticker:"", currency:"", quantity:1.0, costPerShare:"", marketPrice:1.0, marketValue:1.0, profitNLoss:1.0, percentRetained:1.0, percentInPort:1.0, portfolioId:1, type: "", grouped:""}]

  tableData = [{ ticker:"", type: "", marketPrice:1.0, percentRetained:1.0, grouped:""}]

  
  ngOnInit(): void {
    this.getInvestments();
  }

  getInvestments() {
    this.databaseService.getApiData(this.getStr).subscribe(
      (data:any) => {

        // Retrieving data
        this.etfList = data['ETF']
        this.futureList = data['Future']
        this.bondList = data['Bond']
        this.stockList = data['Stock']

        // Add innvestment type properties
        this.etfList = this.etfList.map(x => {
          x.type = "etf"
          x.grouped = 'a'
          return x
        })
        this.futureList = this.futureList.map(x => {
          x.type = "future"
          x.grouped = 'a'
          return x
        })
        this.bondList = this.bondList.map(x => {
          x.type = "bond"
          x.grouped = 'a'
          return x
        })
        this.stockList = this.stockList.map(x => {
          x.type = "stock"
          x.grouped = 'a'
          return x
        })

        // Format data for table
        this.investmentList = this.etfList.concat(this.futureList, this.bondList, this.stockList)
        this.tableData = this.investmentList.map(x => {
          return {
            ticker: x.ticker,
            type: x.type, 
            marketPrice: x.marketPrice, 
            percentRetained: x.percentRetained,
            grouped: x.grouped
          }
        })
        this.portfolioDataSource = this.tableData
      },
      (err) => console.log(err)
    )
  }

  displayedPortfolioColumns: string[] = ['ticker', 'type','marketPrice', 'percentRetained'];
  groupedColumns: string[] = ['grouped'];
  portfolioDataSource = PORTFOLIO_TABLE_DATA;
}
