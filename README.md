# FakeComm
A simple, light-weight, "faked" networking system for use in my software robotics simulations. Now you can use it too!

The idea is simple enough for now: you create a PacketTarget, then create packets with messages and send them to the targets. You can set the success rate of UDP packets to simulate high drop, and you can set the time taken for a TCP packet to arrive. These help simulate, at a very high level, networking conditions in non-ideal areas or places, such as the kind of places you'd like robots to go.
