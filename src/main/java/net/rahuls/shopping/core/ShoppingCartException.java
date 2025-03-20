package net.rahuls.shopping.core;

public class ShoppingCartException extends RuntimeException {
    public ShoppingCartException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShoppingCartException(String message) {
        super(message);
    }
}
