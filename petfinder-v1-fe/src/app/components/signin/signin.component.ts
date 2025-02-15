import { Component } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";

@Component({
    selector: 'app-signin',
    templateUrl: './signin.component.html',
    standalone: false,
    styleUrl: './signin.component.css'
})
export class SigninComponent{

    registrationForm: FormGroup

    constructor(private fb: FormBuilder) {
        this.registrationForm = this.fb.group({
            username: ['', [Validators.required, Validators.minLength(3)]],
            email: ['', [Validators.required, Validators.email]],
            password: ['', [Validators.required, Validators.minLength(6)]],
            confirmPassword: ['', [Validators.required]]
        })
    }

    

}