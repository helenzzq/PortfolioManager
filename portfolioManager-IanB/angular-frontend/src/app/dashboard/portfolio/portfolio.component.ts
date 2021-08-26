import { Component} from '@angular/core';

export interface portfolioTableRow {
  ticker: string;
  marketPrice: number;
  percentRetained: number;
  grouped: string;
}

 // portfolio table
 const PORTFOLIO_TABLE_DATA: portfolioTableRow[] = [
  { ticker: 'ABCD', marketPrice: 112.70, percentRetained: 3.54, grouped: 'a'},
  { ticker: 'ALOHA', marketPrice: 80.96, percentRetained: 4.06, grouped: 'a'},
  { ticker: 'LOTR', marketPrice: 48.33, percentRetained: -1.23, grouped: 'a'}
];

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})

export class PortfolioComponent {
  constructor() {}
  displayedPortfolioColumns: string[] = ['ticker', 'marketPrice', 'percentRetained'];
  groupedColumns: string[] = ['grouped'];
  portfolioDataSource = PORTFOLIO_TABLE_DATA;
}
