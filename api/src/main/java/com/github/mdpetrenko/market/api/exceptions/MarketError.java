package com.github.mdpetrenko.market.api.exceptions;

import java.util.Date;

public class MarketError {
    private String message;
    private Date date;

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

    public MarketError() {
    }

    public MarketError(String message) {
        this.message = message;
        this.date = new Date();
    }

}
