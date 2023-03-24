package de.unistuttgart.t2.creditinstitute.exceptions;

/**
 * Indicates that a payment failed.
 *
 * @author maumau
 */
public final class PaymentFailedException extends Exception {

    private static final long serialVersionUID = 1L;

    public PaymentFailedException(String message) {
        super(message);
    }
}
