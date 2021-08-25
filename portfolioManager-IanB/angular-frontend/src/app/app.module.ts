// Core Angular modules
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

// Components within the application
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

// Angular Material modules
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import { MatSliderModule } from '@angular/material/slider';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatSliderModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
