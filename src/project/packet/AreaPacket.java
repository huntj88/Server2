package project.packet;

import project.ChunkManager;
import project.Connection;
import project.ConnectionMap;
import project.Player;

import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by James on 10/14/2015.
 */
public abstract class AreaPacket extends Packet {

    private static final long serialVersionUID = 2142633872L;

    public AreaPacket(String username) {
        super(username);
    }

    @Override
    public void doPacket(ChunkManager chunkManager, ConnectionMap connections,DatagramSocket serverSocket) {
        ArrayList<Connection> connectionsToSend = new ArrayList<>();
        int[] chunk = connections.get(getUsername()).getChunk();
        HashMap<String, Player> map = chunkManager.getHashMap(chunk);

        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            //System.out.println(pair.getKey() + " = " + pair.getValue());
            connectionsToSend.add(connections.get(pair.getKey()+""));
            //Connection temp = (Connection)pair.getValue();
            //System.out.println(temp.getIP()+" "+temp.getPort());
        }
        send(this, connectionsToSend, serverSocket);
    }
}
