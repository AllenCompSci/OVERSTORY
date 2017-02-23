package onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

/**
 * Created by taylor hudson on 2/23/2017.
 */
public class Dijkstra {

    static Pathfinding.CellState[][] TILELAYOUT;
    static boolean update = false;

    public static void set() {
        TILELAYOUT = Pathfinding.getCellState();
        update = true;
    }
    public static void PathGen(){
        /*
        int same;
        int count = same = 0;
        for(int i = 0; i < Pathfinding.HEIGHT; i++){
            for(int j = 0; j < Pathfinding.WIDTH; j++){
                count ++;
                if(TILELAYOUT[i][j] == Pathfinding.getCellState()[i][j]){
                    same ++;
                }
            }
        }
        System.out.println("COUNT : " + count + " SAME : " + same);
        */ // Test to make sure that when Pathfinding TileLayout changes it also changes in Dijstra's





    }
}
