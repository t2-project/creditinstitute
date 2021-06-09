package de.unistuttgart.t2.creditinstitute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.unistuttgart.t2.creditinstitute.domain.PaymentData;
import de.unistuttgart.t2.creditinstitute.exceptions.PaymentFailedException;

/**
 * Defines the endpoints of the credit institute.
 * 
 * @author maumau
 *
 */
@RestController
public class CreditInstituteController {

    @Autowired
    CreditInstituteService service;

    /**
     * Fakes performs some payment.
     * 
     * @param card informations usually found on a credit card 
     * @throws Exception if anything 'failed'
     */
    @PostMapping("/pay")
    public void doPayment(@RequestBody PaymentData card) throws Exception {
        service.doPayment(card);
    }

    /**
     * Updated and get the timeout duration.
     * 
     * <p> 
     * If the parameter cannot be processed, the timeout remains unchanged.
     * 
     * @param timeout new timeout duration  
     * @return current timeout duration 
     */
    @PostMapping("/timeout")
    public int setTimeout(@RequestBody int timeout) {
        try {
            service.setTimeout(timeout);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return service.getTimeout();
    }
    
    @GetMapping("/timeout")
    public int getTimeout() {
        return service.getTimeout();
    }

    /**
     * Updated and get the failure rate.
     * 
     * <p> 
     * If the parameter cannot be processed, the rate remains unchanged.
     * 
     * @param rate new failure rate
     * @return current failure rate
     */
    @PostMapping("/failurerate")
    public double setFailurerate(@RequestBody double rate) {
        try {
            service.setFailurerate(rate);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return service.getFailurerate();
    }
    
    @GetMapping("/failurerate")
    public double getFailurerate() {
        return service.getFailurerate();
    }

    /**
     * Updated and get the timeout rate.
     * 
     * <p> 
     * If the parameter cannot be processed, the rate remains unchanged.
     * 
     * @param rate new timeout rate
     * @return current timeout rate
     */
    @PostMapping("/timeoutrate")
    public double setTimeoutrate(@RequestBody double rate) {
        try {
            service.setTimeoutrate(rate);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return service.getTimeoutrate();
    }
    
    @GetMapping("/timeoutrate")
    public double getTimeoutrate() {
        return service.getTimeoutrate();
    }
    
    /**
     * Creates the response entity if serving a payment request failed.
     * 
     * @param exception
     * @return a response entity with an exceptional message
     */
    @ExceptionHandler(PaymentFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handlePaymentFailedException(PaymentFailedException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }
    
    /**
     * Creates the response entity if setting the timeout or the rates failed.
     * 
     * @param exception
     * @return a response entity with an exceptional message
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
