package com.cooperativa.assembleia.web.exception;

import lombok.Getter;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Getter
public class ErrorDetails {

    private final Date date;
    private final String message;
    private final List<String> details;

    public ErrorDetails(String message, List<String> details) {
        this.date = new Date();
        this.message = message;
        this.details = details;
    }

    public ErrorDetails(String message) {
        this(message, Collections.emptyList());
    }

}
