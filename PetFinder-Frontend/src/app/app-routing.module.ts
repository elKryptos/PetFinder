import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LostPetsComponent } from './lost-pets/lost-pets.component';
import { FindPetsComponent } from './find-pets/find-pets.component';
import { AdoptComponent } from './adopt/adopt.component';
import { VolunteeringComponent } from './volunteering/volunteering.component';
import { HelpPageComponent } from './help-page/help-page.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { LogoutComponent } from './logout/logout.component';

const routes: Routes = [
  { path: "home", component: HomeComponent },
  { path: "lost-pets", component: LostPetsComponent },
  { path: "find-pets", component: FindPetsComponent },
  { path: "adopt", component: AdoptComponent },
  { path: "volunteering" , component: VolunteeringComponent },
  { path: "help-page", component: HelpPageComponent },
  { path: "login", component: LoginComponent },
  { path: "logout", component: LogoutComponent },
  { path: "registration", component: RegistrationComponent },
  { path: "**", redirectTo: "/home", pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
