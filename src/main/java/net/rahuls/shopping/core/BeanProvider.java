package net.rahuls.shopping.core;

import net.rahuls.shopping.cart.ShoppingCart;
import net.rahuls.shopping.pricing.PriceService;
import net.rahuls.shopping.pricing.PriceServiceClient;
import net.rahuls.shopping.tax.StandardTaxCalculator;
import net.rahuls.shopping.tax.TaxCalculator;

public class BeanProvider {

    public static PriceService priceService() {
        return new PriceServiceClient();
    }

    public static TaxCalculator taxCalculator() {
        return new StandardTaxCalculator();
    }

    public static ShoppingCart shoppingCart() {
        return new ShoppingCart(priceService(), taxCalculator());
    }
}
