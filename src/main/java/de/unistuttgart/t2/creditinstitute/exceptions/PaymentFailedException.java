package de.unistuttgart.t2.creditinstitute.exceptions;

/**
 * Indicates that a payment failed.
 * 
 * @author maumau
 */
public class PaymentFailedException extends Exception {

    public PaymentFailedException(String message) {
        super(message);
    }
}
