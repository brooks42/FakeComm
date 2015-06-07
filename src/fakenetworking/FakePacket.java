package fakenetworking;

/**
 * The base FakePacket class is used to send FakeUDP or FakeTCP messages to
 * PacketTargets.
 *
 * @author Chris
 */
public class FakePacket {

    // the data in the packet. For simplicity purposes this will act as the 
    // entire message - in the case of TCP, there's no packets sending back and 
    // forth, there's just a randomized time increase.
    private final String message;

    // the time this packet was sent. Helps to determine timeouts in the FakeNet
    // system, etc
    private final long sentmillis;

    /**
     * Creates a FakePacket with the passed String message.
     *
     * @param message
     */
    public FakePacket(String message, long sentmillis) {
        this.message = message;
        this.sentmillis = sentmillis;
    }

    /**
     * @return the message for this packet.
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the time in milliseconds that this message was sent.
     *
     * @discussion note that the time this returns doesn't actually have to
     * reflect system time in any way
     */
    public long getSentMillis() {
        return sentmillis;
    }
}
