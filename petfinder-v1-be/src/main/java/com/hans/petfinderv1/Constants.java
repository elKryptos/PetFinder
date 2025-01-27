package com.hans.petfinderv1;

import lombok.Getter;

@Getter
public enum Constants {

    USER_NOT_FOUND("User Not Found"),
    USE_ANOTHER_EMAIL("Use Another Email"),
    USER_DELETED("User Deleted"),
    MAIL_NOT_FOUND("Mail Not Found"),
    USER_REGISTERED("User Registered"),
    USER_SUCCESSFULLY_REGISTERED("User Successfully Registered"),
    USER_REGISTER_FAILED("User Register Failed");

    private final String message;

    Constants(String message) {
        this.message = message;
    }

}
