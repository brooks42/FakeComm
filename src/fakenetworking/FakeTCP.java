package fakenetworking;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chris
 */
public class FakeTCP extends FakePacket {

    private static final int DEFAULT_TIME_RANDOMIZER = 4500;

    // a Random specifically for the TCP. I mean you shouldn't really be hacking 
    // the randomness built into a fake protocol anyway, but this way many, many
    // packets made all at the same time will still differ in deliver time.
    private static Random random = new Random();

    private final PacketTarget target;

    // this will be the time in milliseconds until the packet is delivered
    private int timeRandomizer = 4500;

    /**
     * Creates a simple FakeTCP packet.
     *
     * @param info the packet's message
     * @param target the target for the packet. Can be null.
     */
    public FakeTCP(String info, PacketTarget target) {
        this(info, System.currentTimeMillis(), target, DEFAULT_TIME_RANDOMIZER);
    }

    /**
     * Creates a FakeTCP packet with the passed information.
     *
     * @param info the message for the packet
     * @param sendTime the time the packet was "sent"
     * @param target the target for the packet
     */
    public FakeTCP(String info, long sendTime, PacketTarget target) {
        this(info, sendTime, target, DEFAULT_TIME_RANDOMIZER);
    }

    /**
     * Creates a FakeTCP packet with the passed information.
     *
     * @param info the message for the packet
     * @param sendTime the time the packet was "sent"
     * @param target the target for the packet
     * @param timeRandomizer a value that is used to generate the amount of time
     * until the packet is actually sent to the target, mimicking (very simply)
     * the affects of TCP' packet drop and data checking.
     */
    public FakeTCP(String info, long sendTime, PacketTarget target, int timeRandomizer) {
        super(info, sendTime);
        this.target = target;

        if (timeRandomizer < 0) {
            throw new IllegalArgumentException("The time randomizer should be > 0");
        }
        this.timeRandomizer = timeRandomizer;
    }

    /**
     * Sends this TCP packet to the current target. If the target is null, this
     * method has no effect.
     */
    public void sendToTarget() {
        if (target != null) {
            // if the percentages work out, send the packet
            if (random.nextInt(100) < this.timeRandomizer) {
                FakeTCP tp = this;
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        target.receivePacket(tp);
                    }
                });
                thread.start();
                // otherwise oh well, it's lost to a hardware" issue
            }
        }
    }
}
