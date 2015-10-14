package project.packet;

import project.ChunkManager;
import project.ConnectionMap;

import java.net.DatagramSocket;

/**
 * Created by James on 10/14/2015.
 */
public class MovementPacket extends AreaPacket{

    private static final long serialVersionUID = 2142633872L;

    private int newX,newY;

    public MovementPacket(String username,int newX,int newY) {
        super(username);
        this.newX=newX;
        this.newY=newY;
    }

    @Override
    public void doPacket(ChunkManager chunkManager, ConnectionMap connections, DatagramSocket serverSocket) {
        super.doPacket(chunkManager,connections,serverSocket);
    }
}
