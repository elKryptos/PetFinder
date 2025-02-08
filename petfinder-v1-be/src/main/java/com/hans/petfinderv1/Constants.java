package com.hans.petfinderv1;

import lombok.Getter;

@Getter
public enum Constants {

    //User
    USER_NOT_FOUND("User Not Found"),
    USE_ANOTHER_EMAIL("Use Another Email"),
    USER_DELETED("User Deleted"),
    MAIL_NOT_FOUND("Mail Not Found"),
    USER_REGISTERED("User Registered"),
    USER_SUCCESSFULLY_REGISTERED("User Successfully Registered"),
    USER_REGISTER_FAILED("User Register Failed"),
    USER_INFO_MISSING("User Info Missing"),

    //Animal
    ANIMAL_NOT_FOUND("Animal Not Found"),
    ANIMAL_REGISTERED("Animal Registered"),
    ANIMAL_INFO_MISSING("Animal Info Missing"),
    ANIMAL_REGISTER_FAILED("Animal Register Failed"),
    ANIMAL_DELETED("Animal Deleted"),

    //Token
    ERROR_REMOVING_EXPIRED_TOKEN("Error Removing Expired Token"),
    TOKEN_IS_EXPIRED("Token Is Expired"),
    EXPIRED_TOKEN("Expired Tokens Removed"),
    TOKEN_NOT_VALID("Token not valid or missing"),
    LOGOUT_SUCCESSFULLY("Logout Successful"),
    TOKEN_IS_BLACKLISTED("Token is Blacklisted"),;

    private final String message;

    Constants(String message) {
        this.message = message;
    }

}
