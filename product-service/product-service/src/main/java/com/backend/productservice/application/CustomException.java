package com.backend.productservice.application;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomException extends Exception {

    private String code;
    private String description;

    public CustomException() {
        super();
    }

    public CustomException(String message, String code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

}
