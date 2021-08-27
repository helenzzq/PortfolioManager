// Core Angular modules
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

// Components within the application
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DatabaseService } from './database/database.service';
import { DashboardComponent } from './dashboard/dashboard.component';
import { BalancesComponent } from './dashboard/balances/balances.component';
import { ActivityComponent } from './dashboard/activity/activity.component';
import { IndicesComponent } from './dashboard/indices/indices.component';
import { GainersLosersComponent } from './dashboard/gainers-losers/gainers-losers.component';
import { PortfolioComponent } from './dashboard/portfolio/portfolio.component';
import { AdvancedPieChartComponent } from './dashboard/balances/advanced-pie-chart/advanced-pie-chart.component';

// Angular Material modules
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import {MatTableModule} from '@angular/material/table';
import {MatSelectModule} from '@angular/material/select';
import {MatTabsModule} from '@angular/material/tabs';
import {MatCardModule} from '@angular/material/card';

// External libraries
import { NgxChartsModule } from '@swimlane/ngx-charts';


@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    BalancesComponent,
    ActivityComponent,
    IndicesComponent,
    GainersLosersComponent,
    PortfolioComponent,
    AdvancedPieChartComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,
    HttpClientModule,
    MatTableModule,
    NgxChartsModule,
    MatSelectModule,
    MatTabsModule,
    MatCardModule
  ],
  providers: [DatabaseService],
  bootstrap: [AppComponent]
})
export class AppModule { }
