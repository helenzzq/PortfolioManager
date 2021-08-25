// Core Angular modules
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

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

// Angular Material modules
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import { MatSliderModule } from '@angular/material/slider';
import { MatIconModule } from '@angular/material/icon';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    BalancesComponent,
    ActivityComponent,
    IndicesComponent,
    GainersLosersComponent,
    PortfolioComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatSliderModule,
    MatIconModule
  ],
  providers: [DatabaseService],
  bootstrap: [AppComponent]
})
export class AppModule { }