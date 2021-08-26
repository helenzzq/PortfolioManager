import { Component, OnInit } from '@angular/core';


// Advanced Pie chart
export var singleAdvanced =  [
  { name: 'Cash', value: 22},
  { name: 'Stocks', value: 33 },
  { name: 'Bonds', value: 44 },
  { name: 'Futures', value: 55 },
  { name: 'ETFs', value: 66 },
];

@Component({
  selector: 'app-advanced-pie-chart',
  templateUrl: './advanced-pie-chart.component.html',
  styleUrls: ['./advanced-pie-chart.component.css']
})
export class AdvancedPieChartComponent implements OnInit {

   // Pie Chart
   singleAdvanced: any;
   // view: any = [];
   gradient: boolean = false;
   showLegend: boolean = false;
   showLabels: boolean = true;
   isDoughnut: boolean = false;
   // legendPosition: string = 'below';
   colorScheme: string = 'vivid';

  constructor() { 
    Object.assign(this, { singleAdvanced });

  }

  ngOnInit(): void {
  }

  
  //  --------------------- Pie Chart ---------------------
  onSelect(data: any): void {
    console.log('Item clicked', JSON.parse(JSON.stringify(data)));
  }

  onActivate(data: any): void {
    console.log('Activate', JSON.parse(JSON.stringify(data)));
  }

  onDeactivate(data: any): void {
    console.log('Deactivate', JSON.parse(JSON.stringify(data)));
  }
  //  --------------------- /Pie Chart ---------------------

}
