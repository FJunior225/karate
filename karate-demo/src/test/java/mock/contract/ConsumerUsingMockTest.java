package mock.contract;

import com.intuit.karate.FileUtils;
import com.intuit.karate.netty.FeatureServer;
import java.io.File;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author pthomas3
 */
public class ConsumerUsingMockTest {
    
    private static FeatureServer server;
    private static Consumer consumer;
    
    @BeforeClass
    public static void beforeClass() {
        File file = FileUtils.getFileRelativeTo(ConsumerUsingMockTest.class, "payment-service-mock.feature");
        server = FeatureServer.start(file, 0, false, null);        
        String paymentServiceUrl = "http://localhost:" + server.getPort();
        consumer = new Consumer(paymentServiceUrl);        
    }    
    
    @Test
    public void testPaymentCreate() {
        Payment payment = new Payment();
        payment.setAmount(5.67);
        payment.setDescription("test one");
        payment = consumer.create(payment);
        assertTrue(payment.getId() > 0);
        assertEquals(payment.getAmount(), 5.67, 0);
        assertEquals(payment.getDescription(), "test one");       
    }
    
    @AfterClass
    public static void afterClass() {
        server.stop();
    }    
    
}
