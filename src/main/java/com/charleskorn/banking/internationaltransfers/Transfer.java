package com.charleskorn.banking.internationaltransfers;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class Transfer {
    private final UUID id;
    private final String fromCurrency;
    private final String toCurrency;
    private final OffsetDateTime transferDate;
    private final BigDecimal originalAmount;
    private final BigDecimal exchangeRate;

    public Transfer(UUID id, String fromCurrency, String toCurrency, OffsetDateTime transferDate, BigDecimal originalAmount, BigDecimal exchangeRate) {
        this.id = id;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.transferDate = transferDate;
        this.originalAmount = originalAmount;
        this.exchangeRate = exchangeRate;
    }

    public UUID getId() {
        return id;
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

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public BigDecimal getConvertedAmount() {
        return this.originalAmount.multiply(this.exchangeRate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transfer transfer = (Transfer) o;

        if (!id.equals(transfer.id)) return false;
        if (!fromCurrency.equals(transfer.fromCurrency)) return false;
        if (!toCurrency.equals(transfer.toCurrency)) return false;
        if (!transferDate.equals(transfer.transferDate)) return false;
        if (!originalAmount.equals(transfer.originalAmount)) return false;
        return exchangeRate.equals(transfer.exchangeRate);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + fromCurrency.hashCode();
        result = 31 * result + toCurrency.hashCode();
        result = 31 * result + transferDate.hashCode();
        result = 31 * result + originalAmount.hashCode();
        result = 31 * result + exchangeRate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "id=" + id +
                ", fromCurrency='" + fromCurrency + '\'' +
                ", toCurrency='" + toCurrency + '\'' +
                ", transferDate=" + transferDate +
                ", originalAmount=" + originalAmount +
                ", exchangeRate=" + exchangeRate +
                '}';
    }
}
