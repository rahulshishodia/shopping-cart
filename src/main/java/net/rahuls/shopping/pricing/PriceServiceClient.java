package net.rahuls.shopping.pricing;

import net.rahuls.shopping.core.PriceServiceException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static java.time.temporal.ChronoUnit.SECONDS;

public class PriceServiceClient implements PriceService {
    private static final String BASE_URL = "https://equalexperts.github.io/backend-take-home-test-data/";
    // Cache to store prices for already requested products.
    private final Map<String, BigDecimal> priceCache = new HashMap<>();

    @Override
    public BigDecimal getPrice(String productName) {
        if (priceCache.containsKey(productName)) {
            return priceCache.get(productName);
        }
        BigDecimal price = fetchPriceFromAPI(productName);
        priceCache.put(productName, price);
        return price;
    }

    private BigDecimal fetchPriceFromAPI(String productName) {

        try (HttpClient client = HttpClient.newBuilder().build()) {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(BASE_URL + productName + ".json"))
                    .GET()
                    .timeout(Duration.of(10, SECONDS))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();

            if (statusCode != 200) {
                throw new PriceServiceException("Failed to fetch price for " + productName + ": HTTP " + statusCode);
            }

            JSONObject json = new JSONObject(response.body());
            return json.getBigDecimal("price").setScale(2, RoundingMode.HALF_UP);
        } catch (IOException | URISyntaxException | InterruptedException e) {
            throw new PriceServiceException("Error fetching price for " + productName, e);
        }
    }
}