package net.rahuls.shopping.cart;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
public class CartItem {

    private final String productName;

    private final BigDecimal price;

    private int quantity = 0;

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }
}
