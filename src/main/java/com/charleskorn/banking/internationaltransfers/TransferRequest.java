package com.charleskorn.banking.internationaltransfers;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class TransferRequest {
    private final String fromCurrency;
    private final String toCurrency;
    private final OffsetDateTime transferDate;
    private final BigDecimal originalAmount;

    public TransferRequest(String fromCurrency, String toCurrency, OffsetDateTime transferDate, BigDecimal originalAmount) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.transferDate = transferDate;
        this.originalAmount = originalAmount;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public OffsetDateTime getTransferDate() {
        return transferDate;
    }

    public BigDecimal getOriginalAmount() {
        return originalAmount;
    }
}
