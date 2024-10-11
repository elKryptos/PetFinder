import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'PetFinder-Frontend';
  isLoggedIn: boolean = false

  constructor(private router: Router) {}

  ngOnInit() {
    this.isLoggedIn = !!sessionStorage.getItem('userToken')
  }
}
