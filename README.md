# FakeComm
A simple, light-weight, "faked" networking system for use in my software robotics simulations. Now you can use it too!

The idea is simple enough for now: you create a <b>PacketTarget<b>, then create <b>FakePacket</b>s with String messages and send them to the targets. The system is designed for modularity, so feel free to implement your own FakePacket classes (right now we only have FakeTCP and FakeUDP), and request a pull if you think they're useful.

You can set the success rate of UDP packets to simulate high drop, and you can set the time taken for a TCP packet to arrive. These help to simulate, at a very high level, networking conditions in non-ideal areas or places - the kind of places you'd like robots to go!
