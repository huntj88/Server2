package project;

import project.packet.Packet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by James on 10/12/2015.
 */
public class IncomingPackets implements Runnable {

    private boolean running = true;
    DatagramSocket serverSocket=null;
    ConnectionMap connections;
    ChunkManager chunkManager;
    //PacketList packets;

    public IncomingPackets(ConnectionMap connections,DatagramSocket serverSocket,ChunkManager chunkManager)
    {
        this.connections=connections;
        this.serverSocket=serverSocket;
        this.chunkManager=chunkManager;
        new Thread(this).start();
    }

    public void stopThread()
    {
        running=false;
        serverSocket.close();
    }

    @Override
    public void run(){
        byte[] receiveData = new byte[1024];

        while(running) {
            DatagramPacket recvPacket = new DatagramPacket(receiveData, receiveData.length);
            try{
                serverSocket.receive(recvPacket);
            }catch(IOException e){
                System.err.println("IOException in UdpReceiver.receive");
            }

            new Thread(new PacketThread(serverSocket,recvPacket,receiveData,connections,chunkManager)).start();
        }
    }
}
