package com.charleskorn.banking.internationaltransfers.services;

public class ExchangeRateServiceException extends RuntimeException {
    public ExchangeRateServiceException(String message) {
        super(message);
    }
}
