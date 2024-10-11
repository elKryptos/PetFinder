import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LostPetsComponent } from './lost-pets/lost-pets.component';
import { FindPetsComponent } from './find-pets/find-pets.component';
import { AdoptComponent } from './adopt/adopt.component';
import { VolunteeringComponent } from './volunteering/volunteering.component';
import { HelpPageComponent } from './help-page/help-page.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LogoutComponent } from './logout/logout.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LostPetsComponent,
    FindPetsComponent,
    AdoptComponent,
    VolunteeringComponent,
    HelpPageComponent,
    LoginComponent,
    RegistrationComponent,
    LogoutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
