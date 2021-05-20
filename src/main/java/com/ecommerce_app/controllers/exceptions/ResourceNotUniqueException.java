package com.ecommerce_app.controllers.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ResourceNotUniqueException extends RuntimeException {

    public ResourceNotUniqueException(String message) {
        super(message);
    }
}
