package project;

import project.packet.Packet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by James on 10/13/2015.
 */
public class PacketThread implements Runnable {

    private DatagramSocket serverSocket;
    private DatagramPacket recvPacket;
    private byte[] receiveData;
    private ConnectionMap connections;
    private ChunkManager chunkManager;
    Packet packet;
    int temp = 0;

    public PacketThread(DatagramSocket serverSocket,DatagramPacket recvPacket,byte[] receiveData,ConnectionMap connections,ChunkManager chunkManager)
    {
        this.serverSocket=serverSocket;
        this.recvPacket=recvPacket;
        this.receiveData=receiveData;
        this.connections = connections;
        this.chunkManager=chunkManager;
    }


    public Packet receive()
    {

        //receive a packet
        int receivedBytes = 0;
        receivedBytes = recvPacket.getLength();

        byte[] myObject = new byte[receivedBytes];

        for(int i = 0; i < receivedBytes; i++)
        {
            myObject[i] = receiveData[i];
        }

        Packet obj = deserializeManagerPacket(receiveData);

        if(obj.getPacketID()==3)
        {
            System.out.println("new connection " + obj.getUsername());

            Player player =  new Player(0,0,obj.getUsername());
            chunkManager.addPlayerToChunk(player);
            connections.add(obj.getUsername(),new Connection(recvPacket.getAddress(),recvPacket.getPort(),obj.getUsername(),player.getChunk()));
        }

        if(obj.getPacketID()==2)
        {
            connections.remove(obj.getUsername());
            System.out.println(obj.getUsername()+" connection removed");
        }

        return obj;
    }


    public Packet deserializeManagerPacket(byte[] data)
    {
        try
        {
            ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(data));
            Packet obj = (Packet) iStream.readObject();
            iStream.close();
            return obj;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public void run() {
        packet = receive();
        packet.doPacket(chunkManager,connections,serverSocket);

    }
}
