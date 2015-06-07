package fakenetworking;

import java.util.Random;

/**
 * Transmission protocol. Lacks the extra transmission lag of TCP, but the
 * packets can be completely dropped instead.
 *
 * By default the
 *
 * @author Chris
 */
public class FakeUDP extends FakePacket {

    private static int DEFAULT_SUCCESS_PROB = 95;

    // a Random specifically for the UDP. I mean you shouldn't really be hacking 
    // the randomness built into a fake protocol anyway, but this way many, many
    // packets made all at the same time will still differ in delivery chance
    private static Random random = new Random();

    private final PacketTarget target;
    private int successProb = 95;

    /**
     * Creates a simple FakeUDP packet.
     *
     * @param info the message for the packet
     * @param target the target for the packet
     */
    public FakeUDP(String info, PacketTarget target) {
        this(info, System.currentTimeMillis(), target, DEFAULT_SUCCESS_PROB);
    }

    /**
     * Creates a FakeUDP packet with the passed info:
     *
     * @param info the message for the packet
     * @param sendTime the time the packet was "sent"
     * @param target the target for the packet
     */
    public FakeUDP(String info, long sendTime, PacketTarget target) {
        this(info, sendTime, target, DEFAULT_SUCCESS_PROB);
    }

    /**
     * Creates a FakeUDP packet with the passed info:
     *
     * @param info the message for the packet
     * @param sendTime the time the packet was "sent"
     * @param target the target for the packet
     * @param successProb the chance that the packet will be delivered
     * successfully
     */
    public FakeUDP(String info, long sendTime, PacketTarget target, int successProb) {
        super(info, sendTime);
        this.target = target;

        if (successProb < 0 || successProb > 100) {
            throw new IllegalArgumentException("The success probability should be 0 < x < 100");
        }
        this.successProb = successProb;
    }

    /**
     * Sends this UDP packet to the current target. If the target is null, this
     * method has no effect and the packet is lost.
     */
    public void sendToTarget() {
        if (target != null) {
            // if the percentages work out, send the packet
            if (random.nextInt(100) < this.successProb) {
                target.receivePacket(this);
            }
            // otherwise oh well, it's lost!
        }
    }
}
