package de.unistuttgart.t2.creditinstitute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditInstituteController {

    @Autowired
    CreditInstituteService service;

    @PostMapping("/pay")
    public void doPayment(@RequestBody PaymentData card) throws Exception {
        service.doPayment(card);
    }

    @GetMapping("/timeout/{timeout}")
    public int setTimeout(@PathVariable String timeout) {
        try {
            service.setTimeout(Integer.valueOf(timeout));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return service.getTimeout();
    }

    @GetMapping("/failurerate/{rate}")
    public double setFailurerate(@PathVariable String rate) {
        try {
            service.setFailurerate(Double.valueOf(rate));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return service.getFailurerate();
    }

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
