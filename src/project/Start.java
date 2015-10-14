package project;


import javax.swing.*;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by James on 10/12/2015.
 */
public class Start{

    public static final int WIDTH = 500;
    public static final int HEIGHT = 300;

    public static void main(String[] cows)
    {
        ConnectionMap connections = new ConnectionMap();
        //PacketList packets = new PacketList();
        DatagramSocket serverSocket = null;
        try {
            serverSocket = new DatagramSocket(9876);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        ChunkManager chunkManager = new ChunkManager();



        IncomingPackets incomingPackets = new IncomingPackets(connections,serverSocket,chunkManager);
        //OutgoingPackets outgoingPackets = new OutgoingPackets(connections,packets,serverSocket);

    }
}
