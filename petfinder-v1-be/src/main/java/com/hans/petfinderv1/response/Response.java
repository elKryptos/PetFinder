package com.hans.petfinderv1.response;

import lombok.*;

@Getter
@Setter
public class Response <T>{
    private String message;
    private T data;

    public Response(String message) {
        this.message = message;
    }

    public Response(T data) {
        this.data = data;
    }

    public Response(String message, T data) {
        this.message = message;
        this.data = data;
    }

}
