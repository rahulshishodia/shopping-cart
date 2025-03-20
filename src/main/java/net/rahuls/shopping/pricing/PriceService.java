package net.rahuls.shopping.pricing;

import java.math.BigDecimal;

public interface PriceService {
    BigDecimal getPrice(String productName);
}
