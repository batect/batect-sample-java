package com.charleskorn.banking.internationaltransfers;

import okhttp3.*;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.*;

public class JourneyTest {
    private static final String BASE_URL = "http://international-transfers-service:6001";
    private static final MediaType JSON = MediaType.parse("application/json");

    private static final String FROM_CURRENCY = "AUD";
    private static final String TO_CURRENCY = "EUR";
    private static final String TRANSFER_DATE = "2017-02-13T13:00:00Z";
    private static final BigDecimal ORIGINAL_AMOUNT = new BigDecimal("20.5");
    private static final BigDecimal EXPECTED_EXCHANGE_RATE = new BigDecimal("0.50");

    @Test
    public void journeyTest() throws IOException {
        OkHttpClient client = new OkHttpClient();

        assertNoTransfers(client);
        createTransfer(client);
        assertNewlyCreatedTransferInListOfTransfers(client);
    }

    private void assertNoTransfers(OkHttpClient client) throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "/transfers")
                .build();

        try (Response response = client.newCall(request).execute()) {
            assertThat(response.code(), is(200));
            assertThat(response.body().contentType(), is(JSON));
            assertThat(response.body().string(), hasJsonPath("$", hasSize(0)));
        }
    }

    private void createTransfer(OkHttpClient client) throws IOException {
        String transfer = String.format("{'fromCurrency': '%s', 'toCurrency': '%s', 'transferDate': '%s', 'originalAmount': %s}",
                FROM_CURRENCY, TO_CURRENCY, TRANSFER_DATE, ORIGINAL_AMOUNT);

        Request request = new Request.Builder()
                .url(BASE_URL + "/transfers")
                .post(RequestBody.create(JSON, transfer))
                .build();

        try (Response response = client.newCall(request).execute()) {
            assertThat(response.code(), is(201));
            assertThat(response.body().contentType(), is(JSON));

            String body = response.body().string();
            assertThat(body, hasJsonPath("$.fromCurrency", is(FROM_CURRENCY)));
            assertThat(body, hasJsonPath("$.toCurrency", is(TO_CURRENCY)));
            assertThat(body, hasJsonPath("$.transferDate", is(TRANSFER_DATE)));
            assertThat(body, hasJsonPath("$.originalAmount", is(ORIGINAL_AMOUNT.doubleValue())));
            assertThat(body, hasJsonPath("$.exchangeRate", is(EXPECTED_EXCHANGE_RATE.doubleValue())));
        }
    }

    private void assertNewlyCreatedTransferInListOfTransfers(OkHttpClient client) throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "/transfers")
                .build();

        try (Response response = client.newCall(request).execute()) {
            assertThat(response.code(), is(200));
            assertThat(response.body().contentType(), is(JSON));

            String body = response.body().string();
            assertThat(body, hasJsonPath("$", hasSize(1)));
            assertThat(body, hasJsonPath("$[0].fromCurrency", is(FROM_CURRENCY)));
            assertThat(body, hasJsonPath("$[0].toCurrency", is(TO_CURRENCY)));
            assertThat(body, hasJsonPath("$[0].transferDate", is(TRANSFER_DATE)));
            assertThat(body, hasJsonPath("$[0].originalAmount", is(ORIGINAL_AMOUNT.doubleValue())));
            assertThat(body, hasJsonPath("$[0].exchangeRate", is(EXPECTED_EXCHANGE_RATE.doubleValue())));
        }
    }
}
