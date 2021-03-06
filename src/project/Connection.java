package project; /**
 * Created by James on 10/12/2015.
 */
import java.net.InetAddress;

public class Connection {

    private	int port;
    private InetAddress ip;
    private String username;
    private int chunk[];

    public Connection(InetAddress ip,int port,String username,int[] chunk)
    {
        this.ip=ip;
        this.port=port;
        this.username=username;
        this.chunk=chunk;
    }

    public InetAddress getIP()
    {
        return ip;
    }

    public int getPort()
    {
        return port;
    }

    public String getUsername()
    {
        return username;
    }

    public int[] getChunk()
    {
        return chunk;
    }
}
