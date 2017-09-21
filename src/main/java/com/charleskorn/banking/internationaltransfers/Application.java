package com.charleskorn.banking.internationaltransfers;

import com.charleskorn.banking.internationaltransfers.persistence.Database;
import com.charleskorn.banking.internationaltransfers.persistence.PostgresDatabase;
import com.charleskorn.banking.internationaltransfers.services.ExchangeRateService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dongliu.gson.GsonJava8TypeAdapterFactory;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import static spark.Spark.*;

public class Application {
    public static void main(String[] args) {
        new Application().run();
    }

    private final Database database;
    private final ExchangeRateService exchangeRateService;
    private final TransferService transferService;
    private final Gson gson;

    public Application() {
        this.database = new PostgresDatabase();
        this.exchangeRateService = new ExchangeRateService();
        this.transferService = new TransferService(database, exchangeRateService);
        this.gson = new GsonBuilder().registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory()).create();
    }

    private void run() {
        port(6001);

        get("/", (req, res) -> "Welcome to the international transfers service!");
        get("/ping", (req, res) -> "pong");
        get("/transfers", this::handleGetAllTransfers);
        post("/transfers", this::handleCreateTransfer);
    }

    private String handleGetAllTransfers(Request request, Response response) throws SQLException {
        return json(response, database.getAllTransfers());
    }

    private String handleCreateTransfer(Request request, Response response) throws IOException {
        TransferRequest transferRequest = fromJsonRequestBody(request, TransferRequest.class);
        Transfer transfer = this.transferService.createTransfer(transferRequest);

        response.status(201);
        return json(response, transfer);
    }

    private String json(Response response, Object value) {
        response.type("application/json");
        return gson.toJson(value);
    }

    private <T> T fromJsonRequestBody(Request request, Class<T> classOfT) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(request.raw().getInputStream())) {
            return gson.fromJson(reader, classOfT);
        }
    }
}
