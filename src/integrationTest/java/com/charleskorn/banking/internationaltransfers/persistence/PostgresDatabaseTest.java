package com.charleskorn.banking.internationaltransfers.persistence;

import com.charleskorn.banking.internationaltransfers.Transfer;
import com.charleskorn.banking.internationaltransfers.TransferRequest;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertThat;

public class PostgresDatabaseTest {
    @Test
    public void database_savesAndRetrievesTransfers() throws SQLException {
        PostgresDatabase database = new PostgresDatabase();

        List<Transfer> initialTransfers = database.getAllTransfers();
        assertThat(initialTransfers, is(empty()));

        OffsetDateTime transfer1Time = OffsetDateTime.of(2017, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
        OffsetDateTime transfer2Time = OffsetDateTime.of(2017, 6, 7, 8, 9, 10, 0, ZoneOffset.UTC);
        Transfer transfer1 = database.saveTransfer(new TransferRequest("AUD", "EUR", transfer1Time, new BigDecimal("12.50")), new BigDecimal("0.5"));
        Transfer transfer2 = database.saveTransfer(new TransferRequest("EUR", "USD", transfer2Time, new BigDecimal("100.30")), new BigDecimal("1.1"));

        List<Transfer> transfersAfterSaving = database.getAllTransfers();
        assertThat(transfersAfterSaving, containsInAnyOrder(transfer1, transfer2));
    }
}
