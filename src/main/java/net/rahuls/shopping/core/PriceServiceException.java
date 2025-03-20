package net.rahuls.shopping.core;

public class PriceServiceException extends RuntimeException {
    public PriceServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PriceServiceException(String message) {
        super(message);
    }
}
