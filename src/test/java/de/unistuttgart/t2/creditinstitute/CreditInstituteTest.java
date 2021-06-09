package de.unistuttgart.t2.creditinstitute;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.unistuttgart.t2.creditinstitute.exceptions.PaymentFailedException;

/**
 * Tests for the {@link de.unistuttgart.t2.creditinstitute.CreditInstituteService CreditInstituteService}.
 * 
 * @author maumau
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = TestContext.class)
public class CreditInstituteTest {
    
    @Autowired CreditInstituteService service;
    

    @Test
    public void testFunctionalFailure() {
        service.setFailurerate(1.0);
        assertThrows(PaymentFailedException.class, () -> service.doPayment(null));
    }
    
    @Test
    public void testNoFunctionalFailure() throws Exception {
        service.setFailurerate(0.0);
        service.setTimeoutrate(0.0);
        service.doPayment(null);
    }
    
    @Test
    public void testSetTimeout() {
        service.setTimeout(1);
        assertEquals(1, service.getTimeout());
    }
    
    @Test
    public void testSetTimeout_zero() {
        service.setTimeout(0);
        assertEquals(0, service.getTimeout());
    }
    
    @Test
    public void testSetTimeout_exception() {
        assertThrows(IllegalArgumentException.class, () -> service.setTimeout(-1));
    }
    
    @Test
    public void testSetTimeoutrate() {
        service.setTimeoutrate(1.0);
        assertEquals(1.0, service.getTimeoutrate());
    }
    
    @Test
    public void testSetTimeoutrate_zero() {
        service.setTimeoutrate(0.0);
        assertEquals(0.0, service.getTimeoutrate());
    }
    
    @Test
    public void testSetTimeoutrate_exception() {
        assertThrows(IllegalArgumentException.class, () -> service.setTimeoutrate(-1.0));
    }
    
    @Test
    public void testSetFailurerate() {
        service.setFailurerate(1.0);
        assertEquals(1.0, service.getFailurerate());
    }
    
    @Test
    public void testSetFailurerate_zero() {
        service.setFailurerate(0.0);
        assertEquals(0.0, service.getFailurerate());
    }
    
    @Test
    public void testSetFailurerate_exception() {
        assertThrows(IllegalArgumentException.class, () -> service.setFailurerate(-1.0));
    }
}
