package project;

/**
 * Created by James on 10/13/2015.
 */
public class Player {
    private int x,y;
    private String username;

    public Player(int x, int y, String username)
    {
        this.x=x;
        this.y=y;
        this.username=username;
    }

    public int[] getChunk()
    {
        int chunkX=1;
        int chunkY=1;

        if(x!=0)
            chunkX = x/(64*32)+Math.abs(x)/x;
        if(y!=0)
            chunkY = y/(64*32)+Math.abs(y)/y;

        return new int[]{chunkX,chunkY};
    }

    public String getUsername()
    {
        return username;
    }
}
