package com.github.mdpetrenko.market.api.exceptions;

import java.util.Date;

public class AppError {
    private String message;
    private Date date;
    private int statusCode;

    public AppError() {
    }

    public AppError(int statusCode, String message) {
        this.message = message;
        this.statusCode = statusCode;
        this.date = new Date();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

}
