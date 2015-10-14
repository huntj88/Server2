package project.packet;

import project.ChunkManager;
import project.ConnectionMap;

import java.net.DatagramSocket;

/**
 * Created by James on 10/13/2015.
 */
public class TextPacket extends AreaPacket {

    private String text;
    private static final long serialVersionUID = 2142633872L;

    public TextPacket(String username,String text) {
        super(username);
        this.text=text;
        packetID=1;
    }

    public String getText()
    {
        return text;
    }

    @Override
    public void doPacket(ChunkManager chunkManager, ConnectionMap connections,DatagramSocket serverSocket) {
        super.doPacket(chunkManager,connections,serverSocket);
    }
}
