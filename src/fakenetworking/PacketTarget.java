package fakenetworking;

/**
 * A PacketTarget is anything that can be sent TCP/UDP packets.
 *
 * This'll obfuscate away the IP address.
 *
 * @author Chris
 */
public interface PacketTarget {

    /**
     * Called by the FakePacket object when its logic dictates that its target
     * should receive it.
     *
     * @param packet the packet that is received.
     */
    public void receivePacket(FakePacket packet);
}
