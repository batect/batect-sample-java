package com.charleskorn.banking.internationaltransfers.services;

import com.google.gson.Gson;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ExchangeRateService {
    private final String serviceUrl = "http://exchange-rate-service:6000";

    public BigDecimal getExchangeRate(String fromCurrency, String toCurrency, LocalDate effectiveDate) throws IOException {
        OkHttpClient client = new OkHttpClient();

        HttpUrl url = HttpUrl.parse(serviceUrl).newBuilder()
                .addPathSegment("exchangerate")
                .addPathSegment(fromCurrency)
                .addPathSegment(toCurrency)
                .addPathSegment(String.valueOf(effectiveDate.getYear()))
                .addPathSegment(String.valueOf(effectiveDate.getMonthValue()))
                .addPathSegment(String.valueOf(effectiveDate.getDayOfMonth()))
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new ExchangeRateServiceException(String.format("Exchange rate service call %s %s failed with HTTP %s: %s", request.method(), url, response.code(), response.message()));
            }

            Gson gson = new Gson();
            ExchangeRate exchangeRate = gson.fromJson(response.body().charStream(), ExchangeRate.class);

            return exchangeRate.getRate();
        }
    }

    private class ExchangeRate {
        private final BigDecimal rate;

        public ExchangeRate(BigDecimal rate) {
            this.rate = rate;
        }

        public BigDecimal getRate() {
            return rate;
        }
    }
}
