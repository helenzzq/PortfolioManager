import { Component, OnInit } from '@angular/core';

import { DatabaseService } from 'src/app/database/database.service';

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
        this.singleAdvanced = incomingData.map((x: { investmentType: any; marketValue: any; }) => {
          return {
            name: x.investmentType,
            value: x.marketValue,
          }
        })
      });
  }

    // for adding a currency value to the numbers
    // (it currently works for the biggest number, not the smaller ones)
    valueFormattingFn(value:any) {
      return value + ' USD';
    }

}