import { Inject } from "@angular/core";

@Inject({
    provide: 'root'
})
export class AuthService {

    constructor() { }
}