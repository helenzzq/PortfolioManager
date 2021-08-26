import { Component, NgModule, OnInit } from '@angular/core';
import { DatabaseService } from 'src/app/database/database.service';

// export var lastWeekGraphData = [
//   {
//     "name": "Net Worth",
//     "series": [
//       {
//         "name": "Mon",
//         "value": 55
//       },
//       {
//         "name": "Tues",
//         "value": 68
//       },
//       {
//         "name": "Wed",
//         "value": 72
//       }
//     ]
//   },

//   {
//     "name": "Cash",
//     "series": [
//       {
//         "name": "Mon",
//         "value": 87
//       },
//       {
//         "name": "Tues",
//         "value": 92
//       },
//       {
//         "name": "Wed",
//         "value": 106
//       }
//     ]
//   },

//   {
//     "name": "Market Value",
//     "series": [
//       {
//         "name": "Mon",
//         "value": 113
//       },
//       {
//         "name": "Tues",
//         "value": 128
//       },
//       {
//         "name": "Wed",
//         "value": 132
//       }
//     ]
//   },

//   {
//     "name": "Total Equity",
//     "series": [
//       {
//         "name": "Mon",
//         "value": 45
//       },
//       {
//         "name": "Tues",
//         "value": 22
//       },
//       {
//         "name": "Wed",
//         "value": 83
//       }
//     ]
//   }
// ];

export var yearToDateGraphData = [
  {
    "name": "Net Worth",
    "series": [
      {
        "name": "1990",
        "value": 62000000
      },
      {
        "name": "2010",
        "value": 73000000
      },
      {
        "name": "2011",
        "value": 89400000
      }
    ]
  },

  {
    "name": "Cash",
    "series": [
      {
        "name": "1990",
        "value": 250000000
      },
      {
        "name": "2010",
        "value": 309000000
      },
      {
        "name": "2011",
        "value": 311000000
      }
    ]
  },

  {
    "name": "Market Value",
    "series": [
      {
        "name": "1990",
        "value": 58000000
      },
      {
        "name": "2010",
        "value": 50000020
      },
      {
        "name": "2011",
        "value": 58000000
      }
    ]
  },
  
  {
    "name": "Total Equity",
    "series": [
      {
        "name": "1990",
        "value": 45000000
      },
      {
        "name": "2010",
        "value": 22000000
      },
      {
        "name": "2011",
        "value": 83000000
      }
    ]
  }
];


@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.css']
})
export class ActivityComponent implements OnInit {
  // selectedTimeFrame = 'lastWeek';
  
  // data from database
  lastWeekData: any;
  lastMonthData: any;
  lastQuarterData: any;
  yearToDateData: any;
  // data mapped to work with graph
  lastWeekGraphData: any = [{name: 'Net Worth',series: []},
  {name: 'Cash',series: []},
  {name: 'Market Value',series: []},
  {name: 'Total Equity',series: []}];

  multi: any;
  // view: any = [900, 500];

  // options
  legend: boolean = true;
  showLabels: boolean = true;
  animations: boolean = true;
  xAxis: boolean = true;
  yAxis: boolean = true;
  showYAxisLabel: boolean = true;
  showXAxisLabel: boolean = true;
  xAxisLabel: string = '';
  yAxisLabel: string = 'USD';
  timeline: boolean = false;
  colorScheme = 'vivid';

  constructor(private databaseService: DatabaseService) {
    Object.assign(this, this.multi);
  }

  ngOnInit(): void {
    this.databaseService.getApiData('user/account/net-worth/range=lastWeek')
    .subscribe((incomingData: any) => {
      // this.lastWeekData = incomingData
      // console.log(this.lastWeekData);
      this.lastWeekData = this.mapLastWeekData(incomingData, 'Net Worth');
    });

    this.databaseService.getApiData('user/account/cash/range=lastWeek')
    .subscribe((incomingData: any) => {
      // this.lastWeekData = incomingData
      // console.log(this.lastWeekData);
      this.lastWeekData = this.mapLastWeekData(incomingData, 'Cash');
    });

    this.databaseService.getApiData('user/account/market-value/range=lastWeek')
    .subscribe((incomingData: any) => {
      // this.lastWeekData = incomingData
      // console.log(this.lastWeekData);
      this.lastWeekData = this.mapLastWeekData(incomingData, 'Market Value');
    });

    this.databaseService.getApiData('user/account/total-equity/range=lastWeek')
    .subscribe((incomingData: any) => {
      // this.lastWeekData = incomingData
      // console.log(this.lastWeekData);
      this.lastWeekData = this.mapLastWeekData(incomingData, 'Total Equity');
    });

  //   this.databaseService.getApiData('user')
  //   .subscribe((incomingData: any) => {
  //     this.data = incomingData
  //     console.log(this.data);
  //   });

  // this.databaseService.getApiData('portfolio-manager/net-worth/today')
  //   .subscribe((incomingData: any) => {
  //     this.networthToday = incomingData
  //     console.log(this.networthToday);
  //   });
  }

  onSelect(data: any): void {
    console.log('Item clicked', JSON.parse(JSON.stringify(data)));
  }

  onActivate(data: any): void {
    console.log('Activate', JSON.parse(JSON.stringify(data)));
  }

  onDeactivate(data: any): void {
    console.log('Deactivate', JSON.parse(JSON.stringify(data)));
  }

  array: any[] = [];

  mapLastWeekData(incomingData: any, type: string) {
    this.lastWeekGraphData;
    console.log(incomingData);

    // if () {}

    // this.lastWeekGraphData = incomingData.map((obj:any) => {return {name: type,series: this.array.push(obj)}});
    // this.lastWeekGraphData = incomingData.map((obj:any) => {return this.lastWeekGraphData[indexOfType] {name: type,series: this.array.push(obj)}});
    console.log(this.lastWeekGraphData);
  }

  onSelectTimeFrame(selectedTimeFrame: any) {
    console.log('selected: ' + selectedTimeFrame);
    // let val: string = '';
    switch (selectedTimeFrame) {
      case 'lastMonth':
        // data for this doesn't exist yet
        // this.multi = lastMonthGraphData;
        // this.xAxisLabel = 'day';
        break;
      case 'lastQuarter':
        // data for this doesn't exist yet
        // this.multi = lastQuarterGraphData;
        // this.xAxisLabel = 'day';
        break;
      case 'yearToDate':
        this.multi = yearToDateGraphData;
        this.xAxisLabel = 'year';
        break;
      default:
        this.multi = this.lastWeekGraphData;
        this.xAxisLabel = 'day';
        break;

    }
  }

}
