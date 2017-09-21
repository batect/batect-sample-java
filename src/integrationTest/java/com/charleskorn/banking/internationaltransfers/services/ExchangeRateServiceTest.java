package com.charleskorn.banking.internationaltransfers.services;

import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ExchangeRateServiceTest {
    @Test
    public void getExchangeRate_retrievesExchangeRateFromService() throws IOException {
        ExchangeRateService service = new ExchangeRateService();

        BigDecimal rate = service.getExchangeRate("AUD", "EUR", LocalDate.of(2017, 2, 13));

        assertThat(rate, is(new BigDecimal("0.50")));
    }
}
