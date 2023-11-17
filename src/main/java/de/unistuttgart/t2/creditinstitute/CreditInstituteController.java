package de.unistuttgart.t2.creditinstitute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import de.unistuttgart.t2.creditinstitute.domain.PaymentData;
import de.unistuttgart.t2.creditinstitute.exceptions.PaymentFailedException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;

/**
 * Defines the endpoints of the credit institute.
 *
 * @author maumau
 */
@RestController
public final class CreditInstituteController {

    @Autowired
    CreditInstituteService service;

    /**
     * Fake-performs some payment.
     *
     * @param card information usually found on a credit card
     * @throws Exception if anything 'failed'
     */
    @Operation(summary = "Does Payment", description = "Does Payment for provided data", tags = { "..." })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Payment successful"),
        @ApiResponse(responseCode = "500", description = "Payment failed") })
    @PostMapping("/pay")
    public void doPayment(@RequestBody PaymentData card) throws Exception {
        service.doPayment(card);
    }

    /**
     * Update and get the timeout duration.
     * <p>
     * If the parameter cannot be processed, the timeout remains unchanged.
     *
     * @param timeout new timeout duration
     * @return current timeout duration
     */
    @Operation(summary = "Set timeout duration")
    @PostMapping("/timeout")
    public int setTimeout(@RequestBody int timeout) {
        service.setTimeout(timeout);
        return service.getTimeout();
    }

    @Operation(summary = "Get timeout duration")
    @GetMapping("/timeout")
    public int getTimeout() {
        return service.getTimeout();
    }

    /**
     * Updated and get the failure rate.
     * <p>
     * If the parameter cannot be processed, the rate remains unchanged.
     *
     * @param rate new failure rate
     * @return current failure rate
     */
    @Operation(summary = "Set failurerate", description = "Set failurerate in decimals")
    @PostMapping("/failurerate")
    public double setFailurerate(@RequestBody double rate) {
        service.setFailurerate(rate);

        return service.getFailurerate();
    }

    @Operation(summary = "Get failurerate")
    @GetMapping("/failurerate")
    public double getFailurerate() {
        return service.getFailurerate();
    }

    /**
     * Updated and get the timeout rate.
     * <p>
     * If the parameter cannot be processed, the rate remains unchanged.
     *
     * @param rate new timeout rate
     * @return current timeout rate
     */
    @Operation(summary = "Set timeoutrate", description = "Set timeoutrate in decimals")
    @PostMapping("/timeoutrate")
    public double setTimeoutrate(@RequestBody double rate) {
        service.setTimeoutrate(rate);

        return service.getTimeoutrate();
    }

    @Operation(summary = "Get timeoutrate")
    @GetMapping("/timeoutrate")
    public double getTimeoutrate() {
        return service.getTimeoutrate();
    }

    /**
     * Creates the response entity if serving a payment request or setting the timeout/ rates failed.
     *
     * @param exception the exception that was thrown
     * @return a response entity with an exceptional message
     */
    @ExceptionHandler({ PaymentFailedException.class, IllegalArgumentException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handlePaymentFailedException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }
}
