package de.unistuttgart.t2.creditinstitute.exceptions;

import java.io.Serial;

/**
 * Indicates that a payment failed.
 *
 * @author maumau
 */
public final class PaymentFailedException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public PaymentFailedException(String message) {
        super(message);
    }
}
