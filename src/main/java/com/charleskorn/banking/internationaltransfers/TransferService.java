package com.charleskorn.banking.internationaltransfers;

import com.charleskorn.banking.internationaltransfers.persistence.Database;
import com.charleskorn.banking.internationaltransfers.services.ExchangeRateService;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

public class TransferService {
    private final Database database;
    private final ExchangeRateService exchangeRateService;

    public TransferService(Database database, ExchangeRateService exchangeRateService) {
        this.database = database;
        this.exchangeRateService = exchangeRateService;
    }

    public Transfer createTransfer(TransferRequest request) {
        try {
            BigDecimal exchangeRate = this.exchangeRateService.getExchangeRate(request.getFromCurrency(), request.getToCurrency(), request.getTransferDate().toLocalDate());
            return this.database.saveTransfer(request, exchangeRate);
        } catch (IOException | SQLException e) {
            throw new RuntimeException("Could not save transfer.", e);
        }
    }
}
