package net.rahuls.shopping.tax;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StandardTaxCalculator implements TaxCalculator {
    private static final BigDecimal TAX_RATE = new BigDecimal("0.125");

    @Override
    public BigDecimal calculateTax(BigDecimal subtotal) {
        return subtotal.multiply(TAX_RATE).setScale(2, RoundingMode.HALF_UP);
    }
}
