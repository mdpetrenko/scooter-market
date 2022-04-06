package com.github.mdpetrenko.market.api.exceptions;

import java.util.Date;

public class AppError {
    private String code;
    private String message;
    private Date date;

    public AppError() {
    }

    public AppError(String code, String message) {
        this.message = message;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
