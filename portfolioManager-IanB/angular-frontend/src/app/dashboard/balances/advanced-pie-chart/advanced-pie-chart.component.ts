import { Component, OnInit } from '@angular/core';

import { DatabaseService } from 'src/app/database/database.service';

// Advanced Pie chart
// export var singleAdvanced = [
//   { name: 'Cash', value: 22 },
//   { name: 'Stocks', value: 33 },
//   { name: 'Bonds', value: 44 },
//   { name: 'Futures', value: 55 },
//   { name: 'ETFs', value: 66 },
// ];

@Component({
  selector: 'app-advanced-pie-chart',
  templateUrl: './advanced-pie-chart.component.html',
  styleUrls: ['./advanced-pie-chart.component.css']
})
export class AdvancedPieChartComponent implements OnInit {

  // Advanced Pie Chart Options
  singleAdvanced: any;
  gradient: boolean = false;
  showLegend: boolean = false;
  showLabels: boolean = true;
  isDoughnut: boolean = false;
  colorScheme: string = 'vivid';

  constructor(private databaseService: DatabaseService) {
    Object.assign(this, this.singleAdvanced);
  }

  ngOnInit(): void {
    this.databaseService.getApiData('portfolio-manager/investments/portfolio-percentage/userId=1')
      .subscribe((incomingData: any) => {
        this.singleAdvanced = incomingData.map((x: { investmentType: any; percentage: any; }) => {
          return {
            name: x.investmentType,
            value: x.percentage,
          }
        })
      });
  }

}
