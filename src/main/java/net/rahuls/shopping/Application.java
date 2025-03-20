package net.rahuls.shopping;

import lombok.extern.slf4j.Slf4j;
import net.rahuls.shopping.cart.ShoppingCart;
import net.rahuls.shopping.core.BeanProvider;

@Slf4j
public class Application {

    public static void main(String[] args) {
        ShoppingCart cart = BeanProvider.shoppingCart();
        log.info("Created new Shopping cart");
        try {
            cart.addProduct("cornflakes", 2);
            cart.addProduct("weetabix", 1);
            cart.display();
        } catch (Exception e) {
            log.error("Shopping Cart Exception: {}", e.getMessage());
        }
    }
}
