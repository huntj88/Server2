package project.packet;

import project.ChunkManager;
import project.ConnectionMap;

import java.net.DatagramSocket;

/**
 * Created by James on 10/13/2015.
 */
interface PacketInterface  {
    public void doPacket(ChunkManager chunkManager, ConnectionMap connections,DatagramSocket serverSocket);
}
