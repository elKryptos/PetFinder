import { Component } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Location } from "@angular/common";

@Component({
    selector: 'app-signin',
    templateUrl: './signin.component.html',
    standalone: false,
    styleUrl: './signin.component.css'
})
export class SigninComponent{

    registrationForm: FormGroup

    constructor(private fb: FormBuilder, private location: Location) {
        this.registrationForm = this.fb.group({
            firstname: ['', [Validators.required, Validators.minLength(3)]],
            lastname: ['', [Validators.required, Validators.minLength(3)]],
            birthday: ['', Validators.required],
            email: ['', [Validators.required, Validators.email]],
            password: ['', [Validators.required, Validators.minLength(6)]],
            confirmPassword: ['', Validators.required]

        })
    }

    back() {
        this.location.back()
    }



}