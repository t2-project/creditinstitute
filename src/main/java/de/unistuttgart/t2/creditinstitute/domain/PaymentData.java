package de.unistuttgart.t2.creditinstitute.domain;

import com.fasterxml.jackson.annotation.*;

/**
 * All the information usually found on a credit card.
 *
 * @author maumau
 */
public final class PaymentData {

    @JsonProperty("cardNumber")
    private final String cardNumber;
    @JsonProperty("cardOwner")
    private final String cardOwner;
    @JsonProperty("checksum")
    private final String checksum;
    @JsonProperty("total")
    private final double total;

    @JsonCreator
    public PaymentData(final String cardNumber, final String cardOwner, final String checksum, final double total) {
        this.cardNumber = cardNumber;
        this.cardOwner = cardOwner;
        this.checksum = checksum;
        this.total = total;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardOwner() {
        return cardOwner;
    }

    public String getChecksum() {
        return checksum;
    }
}
