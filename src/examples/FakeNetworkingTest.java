package examples;

import com.sun.istack.internal.logging.Logger;
import fakenetworking.FakePacket;
import fakenetworking.FakeUDP;
import fakenetworking.PacketTarget;
import java.util.logging.Level;

/**
 *
 * @author Chris
 */
public class FakeNetworkingTest {

    private static final int UDP_TEST_NUM = 300;

    int successfulUDPPackets = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // test the networking stuff here

        FakeNetworkingTest mk1 = new FakeNetworkingTest();
        mk1.runPacketTest();
        mk1.printResults();
    }

    /**
     *
     */
    public void printResults() {
        Logger.getLogger(FakeNetworkingTest.class).log(Level.INFO, "Successful UDP packets: ({0} / {1})", new Object[]{successfulUDPPackets, UDP_TEST_NUM});
        Logger.getLogger(FakeNetworkingTest.class).log(Level.INFO, "This concludes our test.");
    }

    /**
     *
     */
    public void runPacketTest() {
        TestExecutor ex = new TestExecutor() {
            @Override
            public void execute() {
                successfulUDPPackets++;
            }
        };
        TestPacketTarget target = new TestPacketTarget(ex);

        // test UDP
        for (int i = 0; i < UDP_TEST_NUM; i++) {
            FakeUDP udp = new FakeUDP("" + i, target);
            udp.sendToTarget();
        }
    }

    // do a little bit of dependency injection or whatever here, so we can make
    // this really easy to grok
    class TestExecutor {

        public void execute() {
        }
    }

    // test PacketTarget, basically just calls its internal executor when it
    // gets a packet
    class TestPacketTarget implements PacketTarget {

        TestExecutor ex;

        public TestPacketTarget(TestExecutor ex) {
            this.ex = ex;
        }

        @Override
        public void receivePacket(FakePacket packet) {
            ex.execute();
        }
    }
}
