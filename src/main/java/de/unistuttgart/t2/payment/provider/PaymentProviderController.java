package de.unistuttgart.t2.payment.provider;

import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentProviderController {
	
	int timeout = 1000;
	
	@PostMapping("/pay")
	public void addReservation(@RequestBody PaymentData card) throws Exception {
		if (card.getChecksum().equals("good"))
			return;
		if (card.getChecksum().equals("bad")) 
			throw new Exception("forced failure");	
		if (card.getChecksum().equals("timeout")) { 
			Thread.sleep(timeout);
			return;
		}
		if (new Random().nextInt(10) == 0) {
			throw new Exception("random failure");
		}
		Thread.sleep(new Random().nextInt(timeout/2));
	}
	
	@GetMapping("/timeout/{timeout}")
	public int setTimeout(@PathVariable String timeout) {
		int tmp = Integer.valueOf(timeout);
		if (tmp > 0) {
			this.timeout = tmp;
		}
		return this.timeout;
	}
}
