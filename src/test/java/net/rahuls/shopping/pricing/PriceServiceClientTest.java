package net.rahuls.shopping.pricing;

import net.rahuls.shopping.core.PriceServiceException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class PriceServiceClientTest {

    @Test
    public void shouldReturnPriceForValidProduct() {
        PriceService priceService = new PriceServiceClient();
        BigDecimal price = priceService.getPrice("cornflakes");
        assertTrue(price.compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    public void shouldThrowExceptionForInvalidProduct() {
        PriceService priceService = new PriceServiceClient();
        Exception exception = assertThrows(PriceServiceException.class, () ->
                priceService.getPrice("invalidProduct"));
        assertNotNull(exception.getMessage());
    }
}
