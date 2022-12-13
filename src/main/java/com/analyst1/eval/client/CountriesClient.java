package com.analyst1.eval.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

public class CountriesClient {
    public static String getCountryNameByCountryCode(String countrycode) {
        Objects.requireNonNull(countrycode);
        try {
            HttpResponse<String> response = HttpClient.newBuilder()
                    .build()
                    .send(HttpRequest.newBuilder()
                            .uri(new URI("http://localhost:8081/static/countries/" + countrycode))
                            .GET()
                            .build(), HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
