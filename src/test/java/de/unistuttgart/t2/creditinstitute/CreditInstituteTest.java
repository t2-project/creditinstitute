package de.unistuttgart.t2.creditinstitute;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * i don't know how to test for delay, but let's at least call every op once.
 * 
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
    public void testFuctionalFailure() {
        service.setFailurerate(1.0);
        assertThrows(Exception.class, () -> service.doPayment(null));
    }

    @Test
    public void testTimeoutFailure() throws Exception {
        service.setFailurerate(0.0);
        service.setTimeoutrate(1.0);
        service.doPayment(null);
    }
}
