package de.unistuttgart.t2.creditinstitute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/timeout/{timeout}")
    public int setTimeout(@PathVariable String timeout) {
        try {
            service.setTimeout(Integer.valueOf(timeout));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
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
    @GetMapping("/failurerate/{rate}")
    public double setFailurerate(@PathVariable String rate) {
        try {
            service.setFailurerate(Double.valueOf(rate));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
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
    @GetMapping("/timeoutrate/{rate}")
    public double setTimeoutrate(@PathVariable String rate) {
        try {
            service.setTimeoutrate(Double.valueOf(rate));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return service.getTimeoutrate();
    }
}
