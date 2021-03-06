package demo;

import com.intuit.karate.junit4.Karate;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import test.ServerStart;

/**
 *
 * @author pthomas3
 */
@RunWith(Karate.class)
public abstract class TestBase {
    
    private static ServerStart server;
    
    @BeforeClass
    public static int beforeClass() throws Exception {
        if (server == null) { // keep spring boot side alive for all tests including package 'mock'
            server = new ServerStart();
            server.start(new String[]{"--server.port=0"}, false);
        }
        System.setProperty("demo.server.port", server.getPort() + "");
        return server.getPort();
    }
    
    @AfterClass
    public static void afterClass() {
        server.stop();
    }
    
}
