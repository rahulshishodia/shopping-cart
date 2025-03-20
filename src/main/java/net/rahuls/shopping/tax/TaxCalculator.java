package net.rahuls.shopping.tax;

import java.math.BigDecimal;

public interface TaxCalculator {
    BigDecimal calculateTax(BigDecimal subtotal);
}
