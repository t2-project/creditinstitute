package de.unistuttgart.t2.creditinstitute;

import java.util.Random;

import de.unistuttgart.t2.creditinstitute.domain.PaymentData;
import de.unistuttgart.t2.creditinstitute.exceptions.PaymentFailedException;

/**
 * Fakes the payment.
 * <p>
 * Probabilities for failure and timeouts, disregarding all network delay, are as follows:
 * <p>
 * assuming the payment calls the provide with a timeout of t_max, then the probability of a timeout is:
 * <p>
 * p(timeout) = ((1 - failurerate) * timeoutrate) + p(random timeout)
 * <p>
 * with: p(random timeout) = (1 - failurerate) * (1 - timeoutrate) * p (X > * t_max)
 * <p>
 * with: p(X > t_max) = ((timeout/2 - t_max) / timeout) iff timeout/2 >= t_max or: p(X > t_max) = 0 otherwise
 * <p>
 * the probability of a functional failure (HTTP response with status code 500 Internal Server Error) is equal to
 * failurate.
 * <p>
 * Both rates and the timeout duration can be adjusted via the respective http endpoints.
 * 
 * @author maumau
 */
public class CreditInstituteService {

    private int timeout = 1000; // in ms

    private double failurerate = 0.1; // decimals
    private double timeoutrate = 0.1; // decimals

    /**
     * Fake executes some payment. Depending on {@link CreditInstituteService#failurerate failurerate},
     * {@link CreditInstituteService#timeoutrate timeoutrate} and {@link CreditInstituteService#timeout timeout} this
     * operation either throws an exception or delays up to {@link CreditInstituteService#timeoutrate timeoutrate}
     * milliseconds.
     * 
     * @param data information usually found on a credit card
     * @throws Exception if anything 'failed'
     */
    public void doPayment(PaymentData data) throws Exception {
        if (new Random().nextDouble() < failurerate) {
            throw new PaymentFailedException("credit institute functional failure");
        }
        if (new Random().nextDouble() < timeoutrate) {
            Thread.sleep(timeout);
        } else if (timeout > 0) {
            // random timeout
            Thread.sleep(new Random().nextInt(timeout / 2));
        }
    }

    /**
     * Update the timeout duration.
     * <p>
     * The new value must not be negative. If it is, the current value remains unchanged.
     * 
     * @param timeout duration of timeout in ms
     */
    public void setTimeout(int timeout) {
        if (timeout < 0) {
            throw new IllegalArgumentException("timeout must be positive");
        }
        this.timeout = timeout;
    }

    /**
     * Update the failure rate.
     * <p>
     * The new value must not be negative. If it is, the current value remains unchanged.
     * 
     * @param failurerate probability for failures as decimal
     */
    public void setFailurerate(double failurerate) {
        if (failurerate < 0) {
            throw new IllegalArgumentException("failurerate must be positive");
        }
        this.failurerate = failurerate;
    }

    /**
     * Update the timeout rate.
     * <p>
     * The new value must not be negative. If it is, the current value remains unchanged.
     * 
     * @param timeoutrate probability for timeouts as decimal
     */
    public void setTimeoutrate(double timeoutrate) {
        if (timeoutrate < 0) {
            throw new IllegalArgumentException("timeoutrate must be positive");
        }
        this.timeoutrate = timeoutrate;
    }

    public int getTimeout() {
        return timeout;
    }

    public double getFailurerate() {
        return failurerate;
    }

    public double getTimeoutrate() {
        return timeoutrate;
    }
}
