import { Component, NgModule, OnInit } from '@angular/core';

export var lastWeek = [
  {
    "name": "Net Worth",
    "series": [
      {
        "name": "Mon",
        "value": 55
      },
      {
        "name": "Tues",
        "value": 68
      },
      {
        "name": "Wed",
        "value": 72
      }
    ]
  },

  {
    "name": "Cash",
    "series": [
      {
        "name": "Mon",
        "value": 87
      },
      {
        "name": "Tues",
        "value": 92
      },
      {
        "name": "Wed",
        "value": 106
      }
    ]
  },

  {
    "name": "Market Value",
    "series": [
      {
        "name": "Mon",
        "value": 113
      },
      {
        "name": "Tues",
        "value": 128
      },
      {
        "name": "Wed",
        "value": 132
      }
    ]
  },

  {
    "name": "Total Equity",
    "series": [
      {
        "name": "Mon",
        "value": 45
      },
      {
        "name": "Tues",
        "value": 22
      },
      {
        "name": "Wed",
        "value": 83
      }
    ]
  }
];

export var yearToDate = [
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

  ngOnInit(): void {
  }
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
  timeline: boolean = true;
  colorScheme = 'vivid';

  constructor() {
    Object.assign(this, this.multi);

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

  onSelectTimeFrame(selectedTimeFrame: any) {
    console.log('selected: ' + selectedTimeFrame);
    // let val: string = '';
    switch (selectedTimeFrame) {
      case 'lastMonth':
        // data for this doesn't exist yet
        // this.multi = lastMonth;
        // this.xAxisLabel = 'day';
        break;
      case 'lastQuarter':
        // data for this doesn't exist yet
        // this.multi = lastQuarter;
        // this.xAxisLabel = 'day';
        break;
      case 'yearToDate':
        this.multi = yearToDate;
        this.xAxisLabel = 'year';
        break;
      default:
        this.multi = lastWeek;
        this.xAxisLabel = 'day';
        break;

    }
  }

}
