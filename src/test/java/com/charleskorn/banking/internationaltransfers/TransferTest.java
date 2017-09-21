package com.charleskorn.banking.internationaltransfers;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TransferTest {
    @Test
    public void getConvertedAmount_returnsOriginalAmountConvertedToDestinationCurrency() {
        BigDecimal originalAmount = new BigDecimal("10.00");
        BigDecimal exchangeRate = new BigDecimal("2.50");
        Transfer transfer = new Transfer(UUID.randomUUID(), "AUD", "EUR", OffsetDateTime.now(), originalAmount, exchangeRate);

        assertThat(transfer.getConvertedAmount(), is(new BigDecimal("25.0000")));
    }
}
