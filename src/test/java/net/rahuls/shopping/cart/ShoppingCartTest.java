package net.rahuls.shopping.cart;


import net.rahuls.shopping.core.ShoppingCartException;
import net.rahuls.shopping.pricing.PriceService;
import net.rahuls.shopping.pricing.PriceServiceClient;
import net.rahuls.shopping.tax.StandardTaxCalculator;
import net.rahuls.shopping.tax.TaxCalculator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {

    private final PriceService mockPriceService = new PriceServiceClient();
    private final TaxCalculator taxCalculator = new StandardTaxCalculator();

    @Test
    public void shouldCalculateCartTotalsCorrectly() {
        ShoppingCart cart = new ShoppingCart(mockPriceService, taxCalculator);
        cart.addProduct("cornflakes", 2);
        cart.addProduct("weetabix", 1);

        assertEquals(new BigDecimal("15.02"), cart.getSubtotal(), "Subtotal should match expected value");
        assertEquals(new BigDecimal("1.88"), cart.getTax(), "Tax should match expected value");
        assertEquals(new BigDecimal("16.90"), cart.getTotal(), "Total should match expected value");
    }

    @Test
    public void shouldThrowExceptionForInvalidProductName() {
        ShoppingCart cart = new ShoppingCart(mockPriceService, taxCalculator);
        Exception exception = assertThrows(ShoppingCartException.class, () ->
                cart.addProduct("  ", 1));
        assertTrue(exception.getMessage().contains("Product name cannot be null or empty"));
    }

    @Test
    public void shouldThrowExceptionForInvalidQuantity() {
        ShoppingCart cart = new ShoppingCart(mockPriceService, taxCalculator);
        Exception exception = assertThrows(ShoppingCartException.class, () ->
                cart.addProduct("cornflakes", 0));
        assertTrue(exception.getMessage().contains("Quantity must be greater than 0"));
    }
}
