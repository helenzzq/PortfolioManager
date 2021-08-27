import { Component, NgModule, OnInit } from '@angular/core';
import { DatabaseService } from 'src/app/database/database.service';

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
  lastWeekGraphData: any = [{ name: 'Net Worth', series: [] },
  { name: 'Cash', series: [] },
  { name: 'Market Value', series: [] },
  { name: 'Total Equity', series: [] }];
  lastMonthGraphData: any = [{ name: 'Net Worth', series: [] },
  { name: 'Cash', series: [] },
  { name: 'Market Value', series: [] },
  { name: 'Total Equity', series: [] }];
  lastQuarterGraphData: any = [{ name: 'Net Worth', series: [] },
  { name: 'Cash', series: [] },
  { name: 'Market Value', series: [] },
  { name: 'Total Equity', series: [] }];
  yearToDateGraphData: any = [{ name: 'Net Worth', series: [] },
  { name: 'Cash', series: [] },
  { name: 'Market Value', series: [] },
  { name: 'Total Equity', series: [] }];

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
  xAxisLabel: string = 'Day';
  yAxisLabel: string = 'USD';
  timeline: boolean = false;
  colorScheme = 'vivid';

  constructor(private databaseService: DatabaseService) {
    Object.assign(this, this.multi);
  }

  ngOnInit(): void {

      // _____________Last Week__________________

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


      // _____________Last Month__________________

      this.databaseService.getApiData('user/account/net-worth/range=lastMonth')
      .subscribe((incomingData: any) => {
        // this.lastWeekData = incomingData
        // console.log(this.lastWeekData);
        this.lastMonthData = this.mapLastMonthData(incomingData, 'Net Worth');
      });

    this.databaseService.getApiData('user/account/cash/range=lastMonth')
      .subscribe((incomingData: any) => {
        // this.lastWeekData = incomingData
        // console.log(this.lastWeekData);
        this.lastMonthData = this.mapLastMonthData(incomingData, 'Cash');
      });

    this.databaseService.getApiData('user/account/market-value/range=lastMonth')
      .subscribe((incomingData: any) => {
        // this.lastWeekData = incomingData
        // console.log(this.lastWeekData);
        this.lastMonthData = this.mapLastMonthData(incomingData, 'Market Value');
      });

    this.databaseService.getApiData('user/account/total-equity/range=lastMonth')
      .subscribe((incomingData: any) => {
        // this.lastWeekData = incomingData
        // console.log(this.lastWeekData);
        this.lastMonthData = this.mapLastMonthData(incomingData, 'Total Equity');
      });

      // _____________Last Quarter__________________

      this.databaseService.getApiData('user/account/net-worth/range=lastQuarter')
      .subscribe((incomingData: any) => {
        // this.lastWeekData = incomingData
        // console.log(this.lastWeekData);
        this.lastQuarterData = this.mapLastQuarterData(incomingData, 'Net Worth');
      });

    this.databaseService.getApiData('user/account/cash/range=lastQuarter')
      .subscribe((incomingData: any) => {
        // this.lastWeekData = incomingData
        // console.log(this.lastWeekData);
        this.lastQuarterData = this.mapLastQuarterData(incomingData, 'Cash');
      });

    this.databaseService.getApiData('user/account/market-value/range=lastQuarter')
      .subscribe((incomingData: any) => {
        // this.lastWeekData = incomingData
        // console.log(this.lastWeekData);
        this.lastQuarterData = this.mapLastQuarterData(incomingData, 'Market Value');
      });

    this.databaseService.getApiData('user/account/total-equity/range=lastQuarter')
      .subscribe((incomingData: any) => {
        // this.lastWeekData = incomingData
        // console.log(this.lastWeekData);
        this.lastQuarterData = this.mapLastQuarterData(incomingData, 'Total Equity');
      });

            // _____________Year to Date__________________

            this.databaseService.getApiData('user/account/net-worth/range=yearToDate')
            .subscribe((incomingData: any) => {
              // this.lastWeekData = incomingData
              // console.log(this.lastWeekData);
              this.yearToDateData = this.mapYearToDateData(incomingData, 'Net Worth');
            });
      
          this.databaseService.getApiData('user/account/cash/range=yearToDate')
            .subscribe((incomingData: any) => {
              // this.lastWeekData = incomingData
              // console.log(this.lastWeekData);
              this.yearToDateData = this.mapYearToDateData(incomingData, 'Cash');
            });
      
          this.databaseService.getApiData('user/account/market-value/range=yearToDate')
            .subscribe((incomingData: any) => {
              // this.lastWeekData = incomingData
              // console.log(this.lastWeekData);
              this.yearToDateData = this.mapYearToDateData(incomingData, 'Market Value');
            });
      
          this.databaseService.getApiData('user/account/total-equity/range=yearToDate')
            .subscribe((incomingData: any) => {
              // this.lastWeekData = incomingData
              // console.log(this.lastWeekData);
              this.yearToDateData = this.mapYearToDateData(incomingData, 'Total Equity');
            });
  }

  onSelect(data: any): void {
    // console.log('Item clicked', JSON.parse(JSON.stringify(data)));
  }

  onActivate(data: any): void {
    // console.log('Activate', JSON.parse(JSON.stringify(data)));
  }

  onDeactivate(data: any): void {
    // console.log('Deactivate', JSON.parse(JSON.stringify(data)));
  }

  // array: any[] = [];

  mapLastWeekData(incomingData: any, type: string) {
    // this.lastWeekGraphData;
    // console.log(incomingData);

    // if () {}

    switch (type) {
      case 'Net Worth':
        // The ... spread notation removes square brackets from incomingdata
        this.lastWeekGraphData[0].series.push(...incomingData);
        break;
      case 'Cash':
        this.lastWeekGraphData[1].series.push(...incomingData);
        break;
      case 'Market Value':
        this.lastWeekGraphData[2].series.push(...incomingData);
        break;
      case 'Total Equity':
        this.lastWeekGraphData[3].series.push(...incomingData);
        // console.log(JSON.stringify(incomingData));
        // console.log(JSON.stringify(this.lastWeekGraphData));
        // console.log('YTD: ' );
        // console.log(JSON.stringify(yearToDateGraphData));
        break;
      default:
        break;
    }
  }

    mapLastMonthData(incomingData: any, type: string) {
      // this.lastWeekGraphData;
      // console.log(incomingData);
  
      // if () {}
  
      switch (type) {
        case 'Net Worth':
        // The ... spread notation removes square brackets from incomingdata
          this.lastMonthGraphData[0].series.push(...incomingData);
          break;
        case 'Cash':
          this.lastMonthGraphData[1].series.push(...incomingData);
          break;
        case 'Market Value':
          this.lastMonthGraphData[2].series.push(...incomingData);
          break;
        case 'Total Equity':
          this.lastMonthGraphData[3].series.push(...incomingData);
          // console.log(JSON.stringify(incomingData));
          // console.log(JSON.stringify(this.lastWeekGraphData));
          // console.log('YTD: ' );
          // console.log(JSON.stringify(yearToDateGraphData));
          break;
        default:
          break;
      }

    // this.lastWeekGraphData = incomingData.map((obj:any) => {return {name: type,series: this.array.push(obj)}});
    // this.lastWeekGraphData = incomingData.map((obj:any) => {return this.lastWeekGraphData[indexOfType] {name: type,series: this.array.push(obj)}});

  }

  mapLastQuarterData(incomingData: any, type: string) {
    // this.lastWeekGraphData;
    // console.log(incomingData);

    // if () {}

    switch (type) {
      case 'Net Worth':
        // The ... spread notation removes square brackets from incomingdata
        this.lastQuarterGraphData[0].series.push(...incomingData);
        break;
      case 'Cash':
        this.lastQuarterGraphData[1].series.push(...incomingData);
        break;
      case 'Market Value':
        this.lastQuarterGraphData[2].series.push(...incomingData);
        break;
      case 'Total Equity':
        this.lastQuarterGraphData[3].series.push(...incomingData);
        // console.log(JSON.stringify(incomingData));
        // console.log(JSON.stringify(this.lastWeekGraphData));
        // console.log('YTD: ' );
        // console.log(JSON.stringify(yearToDateGraphData));
        break;
      default:
        break;
    }

    
  }

  mapYearToDateData(incomingData: any, type: string) {
    // this.lastWeekGraphData;
    // console.log(incomingData);

    // if () {}

    switch (type) {
      case 'Net Worth':
        // The ... spread notation removes square brackets from incomingdata
        this.yearToDateGraphData[0].series.push(...incomingData);
        break;
      case 'Cash':
        this.yearToDateGraphData[1].series.push(...incomingData);
        break;
      case 'Market Value':
        this.yearToDateGraphData[2].series.push(...incomingData);
        break;
      case 'Total Equity':
        this.yearToDateGraphData[3].series.push(...incomingData);
        // console.log(JSON.stringify(incomingData));
        // console.log(JSON.stringify(this.lastWeekGraphData));
        // console.log('YTD: ' );
        // console.log(JSON.stringify(yearToDateGraphData));
        break;
      default:
        break;
    }

  // this.lastWeekGraphData = incomingData.map((obj:any) => {return {name: type,series: this.array.push(obj)}});
  // this.lastWeekGraphData = incomingData.map((obj:any) => {return this.lastWeekGraphData[indexOfType] {name: type,series: this.array.push(obj)}});

}

  onSelectTimeFrame(selectedTimeFrame: any) {
    console.log('selected: ' + selectedTimeFrame);
    // let val: string = '';
    switch (selectedTimeFrame) {
      case 'lastWeek':
        this.multi = this.lastWeekGraphData;
        // this.xAxisLabel = 'Day';
        break;
      case 'lastMonth':
        this.multi = this.lastMonthGraphData;
        // this.xAxisLabel = 'Day';
        break;
      case 'lastQuarter':
        // data for this doesn't exist yet
        // this.multi = lastQuarterGraphData;
        // this.xAxisLabel = 'day';
        this.multi = this.lastQuarterGraphData;
        // this.xAxisLabel = 'Day';
        break;
      case 'yearToDate':
        this.multi = this.yearToDateGraphData;
        // this.xAxisLabel = 'year';
        // this.multi = this.lastMonthGraphData;
        // this.xAxisLabel = 'Day';
        break;
      default:
        this.multi = this.lastWeekGraphData;
        // this.xAxisLabel = 'Day';
        break;

    }
  }

}
