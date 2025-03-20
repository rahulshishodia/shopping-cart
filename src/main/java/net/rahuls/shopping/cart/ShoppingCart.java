package net.rahuls.shopping.cart;

import lombok.extern.slf4j.Slf4j;
import net.rahuls.shopping.core.ShoppingCartException;
import net.rahuls.shopping.pricing.PriceService;
import net.rahuls.shopping.tax.TaxCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class ShoppingCart {

    private final Map<String, CartItem> cart = new HashMap<>();
    private final PriceService priceService;
    private final TaxCalculator taxCalculator;

    public ShoppingCart(PriceService priceService, TaxCalculator taxCalculator) {
        this.priceService = Objects.requireNonNull(priceService, "PriceService cannot be null");
        this.taxCalculator = Objects.requireNonNull(taxCalculator, "TaxCalculator cannot be null");
    }

    public void addProduct(String productName, int quantity) {
        if (productName == null || productName.trim().isEmpty()) {
            throw new ShoppingCartException("Product name cannot be null or empty.");
        }
        if (quantity <= 0) {
            throw new ShoppingCartException("Quantity must be greater than 0.");
        }

        BigDecimal price = priceService.getPrice(productName);
        cart.computeIfAbsent(productName, _ -> new CartItem(productName, price))
                .addQuantity(quantity);
    }

    public BigDecimal getSubtotal() {
        return cart.values().stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTax() {
        return taxCalculator.calculateTax(getSubtotal());
    }

    public BigDecimal getTotal() {
        return getSubtotal().add(getTax());
    }

    public void display() {
        for (CartItem item : cart.values()) {
            log.info("{} x {} @ {} each", item.getQuantity(), item.getProductName(), item.getPrice());
        }

        log.info("Subtotal: {}", getSubtotal());
        log.info("Tax: {}", getTax());
        log.info("Total: {}", getTotal());
    }
}
