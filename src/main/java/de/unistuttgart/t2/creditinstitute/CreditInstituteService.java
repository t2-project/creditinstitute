package de.unistuttgart.t2.creditinstitute;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * Probabilities for failure and timeouts, disregarding all network delay, are as follows: 
 * 
 * assuming the payment calls the provide with a timeout of t_max, then the
 * probability of a timeout is:
 * 
 * p(timeout) = ((1 - failurerate) * timeoutrate) + p(random timeout)
 * 
 * with: p(random timeout) = (1 - failurerate) * (1 - timeoutrate) * p (X > * t_max)
 * 
 * with: p(X > t_max) = ((timeout/2 - t_max) / timeout) iff timeout/2 >= t_max
 * or: p(X > t_max) = 0 otherwise
 * 
 * the probability of a functional failure (HTTP response with status code 500
 * Internal Server Error) is equal to failurate.
 * 
 * both rates and the timeout can be adjusted via the respective http endpoints.
 * 
 * @author maumau
 *
 */
public class CreditInstituteService {
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	

	private int timeout = 1000; // in ms
	
	private double failurerate = 0.1; // percent
	private double timeoutrate = 0.1; // percent
	

	public void doPayment(PaymentData data) throws Exception {
		if (new Random().nextDouble() < failurerate) {
			throw new Exception("functional failure");
		}
		if (new Random().nextDouble() < timeoutrate) {
			Thread.sleep(timeout);
		} else {
			// random timeout
			Thread.sleep(new Random().nextInt(timeout/2));
		}
	}
	
	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		if (timeout < 0) {
			throw new IllegalArgumentException();
		}
		this.timeout = timeout;
	}

	public double getFailurerate() {
		return failurerate;
	}

	public void setFailurerate(double failurerate) {
		if (failurerate < 0) {
			throw new IllegalArgumentException();
		}
		this.failurerate = failurerate;
	}

	public double getTimeoutrate() {
		return timeoutrate;
	}

	public void setTimeoutrate(double timeoutrate) {
		if (timeoutrate < 0) {
			throw new IllegalArgumentException();
		}
		this.timeoutrate = timeoutrate;
	}
}
