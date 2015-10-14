package project;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by James on 10/13/2015.
 */
public class ChunkManager {

    ArrayList<ArrayList<HashMap<String,Player>>> topLeft =  new ArrayList<>();
    ArrayList<ArrayList<HashMap<String,Player>>> topRight =  new ArrayList<>();
    ArrayList<ArrayList<HashMap<String,Player>>> bottomLeft =  new ArrayList<>();
    ArrayList<ArrayList<HashMap<String,Player>>> bottomRight =  new ArrayList<>();

    public ChunkManager()
    {
        topLeft.add(null);
        topRight.add(null);
        bottomLeft.add(null);
        bottomRight.add(null);
    }


    public synchronized void addPlayerToChunk(Player player)
    {
        int[] chunk = player.getChunk();
        int x = Math.abs(chunk[0]);
        int y = Math.abs(chunk[1]);
        if(chunk[0]>0&&chunk[1]>0)
        {
            expand(topRight,chunk);
            topRight.get(x).get(y).put(player.getUsername(),player);
        }
        else if(chunk[0]>0&&chunk[1]<0)
        {
            expand(bottomRight,chunk);
            bottomRight.get(x).get(y).put(player.getUsername(),player);
        }
        else if(chunk[0]<0&&chunk[1]<0)
        {
            expand(bottomLeft,chunk);
            bottomLeft.get(x).get(y).put(player.getUsername(),player);
        }
        else if(chunk[0]<0&&chunk[1]>0)
        {
            expand(topLeft,chunk);
            topLeft.get(x).get(y).put(player.getUsername(),player);
        }
        else
        {
            System.err.println("not a valid chunk to put player into");
        }
        //System.out.println(topRight.get(x).get(y).get(player.getUsername()).getChunk()[1]);
    }

    public void expand(ArrayList<ArrayList<HashMap<String,Player>>> arrayList,int[] chunk)
    {
        int x = Math.abs(chunk[0]);
        int y = Math.abs(chunk[1]);
        if(arrayList.size()-1<x)
        {
            for(int i=arrayList.size();i<x+1;i++)
            {
                arrayList.add(new ArrayList<>());
                if(arrayList.get(i).size()<y)
                {
                    for(int z=arrayList.get(i).size();z<y+1;z++)
                    {
                        arrayList.get(i).add(new HashMap<>());
                    }
                }
            }
        }
        else {
            //arrayList.add(new ArrayList<>());
            if (arrayList.get(x).size() < y) {
                for (int z = arrayList.get(x).size(); z < y + 1; z++) {
                    arrayList.get(x).add(new HashMap<>());
                }
            }
        }
    }

    public synchronized HashMap<String,Player> getHashMap(int[] chunk)
    {
        int x = Math.abs(chunk[0]);
        int y = Math.abs(chunk[1]);
        System.out.println(x+" "+y);
        if(chunk[0]>0&&chunk[1]>0)
        {
            return topRight.get(x).get(y);
        }
        else if(chunk[0]>0&&chunk[1]<0)
        {
            return bottomRight.get(x).get(y);
        }
        else if(chunk[0]<0&&chunk[1]<0)
        {
            return bottomLeft.get(x).get(y);
        }
        else if(chunk[0]<0&&chunk[1]>0)
        {
            return topLeft.get(x).get(y);
        }
        else
        {
            System.err.println("not a valid chunk to put player into");
            return null;
        }
    }



}
