package project.packet;

import project.Connection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

/**
 * Created by James on 10/12/2015.
 */
public abstract class Packet implements Serializable,PacketInterface{

    private static final long serialVersionUID = 2142633872L;
    protected String username;
    protected int packetID;

    public Packet(String username)
    {
        this.username=username;
        packetID=0;
    }

    public String getUsername()
    {
        return username;
    }

    public int getPacketID()
    {
        return packetID;
    }

    public void send(Packet packet, ArrayList<Connection> connections,DatagramSocket serverSocket) {

        byte[] sendData = serializeManagerPacket(packet);

        for(int i = 0;i<connections.size();i++) {
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, connections.get(i).getIP(), connections.get(i).getPort());
            try {
                serverSocket.send(sendPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("sent");
        }
    }

    public byte[] serializeManagerPacket(Packet mp) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(2048);
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(mp);
            oos.close();
            // get the byte array of the object
            byte[] obj = baos.toByteArray();
            baos.close();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }
}