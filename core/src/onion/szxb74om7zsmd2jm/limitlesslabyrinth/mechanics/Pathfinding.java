package onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.Array;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Enemy;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Wall;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

import java.util.ArrayList;

import static onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics.Pathfinding.CellState.*;

/**
 * Created by taylor hudson on 2/22/2017.
 */
public class Pathfinding {
    static int HEIGHT;
    static int WIDTH;
    final int SQR = 32;
    private static CellState [][]TILELAYOUT ;
    public static CellState[][] getCellState(){
        return TILELAYOUT;
    }
    private boolean [][] Path;
    private int PlayerX, PlayerY;
    public Dijkstra method;
    TiledMapTileLayer collisionLayer;
    int numNODES;
    public static Array<POINT> malleable;
    public ArrayList<Node> AllNodes;

    public Pathfinding(){
        collisionLayer = (TiledMapTileLayer) Play.getMap().getLayers().get(1);
        HEIGHT = collisionLayer.getHeight();
        WIDTH = collisionLayer.getWidth();
        TILELAYOUT = new CellState[HEIGHT][WIDTH];
        Path = new boolean[HEIGHT][WIDTH];
        malleable = new Array<POINT>();
        AllNodes = new ArrayList<Node>();
        method = new Dijkstra();
        init();
        update();
    }
    public enum CellState{BLOCKED, WALL, ENEMY, PLAYER, START, FINISH, PATH}
    public void init(){

        numNODES = 0; // Setup for Dikstras
        for(int i =  0; i < HEIGHT; i++){
            for(int j = 0; j < WIDTH; j++){
                TILELAYOUT[i][j] = PATH;
                Path[i][j] = true;
                numNODES++;

                if(collisionLayer.getCell(i,j).getTile().getProperties().containsKey("blocked")){
                    TILELAYOUT [i][j] = BLOCKED;
                    Path[i][j] = false;
                    numNODES--;
                }
                else{
                    AllNodes.add(new Node(i,j));
                }
            }
        }
        Dijkstra.TILELAYOUT = TILELAYOUT;
        // Connect Nodes
        for(int i = 0; i < AllNodes.size(); i++) {
            AllNodes.get(i).setConnectedNodes(AllNodes);
        }

    }

    /*
         HEIGHT
            _________________________
            |__|__|__|__|__|__|__|__|
            |__|__|__|__|__|__|__|__|
            |__|__|__|__|__|__|__|__|
      X     |__|__|__|__|__|__|__|__|
            |__|__|__|__|__|__|__|__|
            |__|__|__|__|__|__|__|__|
           0                     WIDTH
                        Y

     */

    public void print(){
        for(int i = HEIGHT-1; i >= 0; i--){
            //System.out.print("[ ");
            for(int j = 0; j < WIDTH; j++ ){
                if(j != 0){
                    System.out.print(", ");
                }
                if(TILELAYOUT[i][j] == PATH){}

                else if(TILELAYOUT[i][j] == BLOCKED){
                   // System.out.print("BL");
                }
                else if(TILELAYOUT[i][j] ==WALL){
                    //System.out.print("WL");
                }
                else if(TILELAYOUT[i][j] == ENEMY){
                    //System.out.print("EN");
                }
                else if(TILELAYOUT[i][j] == PLAYER){
                    //System.out.print("PL");
                }
            }
           // System.out.println(" ]");
        }
    }
    public void getWall(){
        for(Wall e : Play.getWalls()){
            // EVERY WALL
            int xWall = e.getSETX();
            int yWall = e.getSETY();
            /** Directly from Wall
             * (SETX+1 == x1 || x1 == (SETX -1) || SETX == x1)&& (y1 == (SETY -1) || SETY == y1)
             *
              */
            TILELAYOUT[xWall][yWall] = WALL;
            Path[xWall][yWall] = false;
            malleable.add(new POINT(xWall,yWall));
            TILELAYOUT[xWall+1][yWall] = WALL;
            Path[xWall+1][yWall] = false;
            malleable.add(new POINT(xWall+1,yWall));
            TILELAYOUT[xWall-1][yWall] = WALL;
            Path[xWall-1][yWall] = false;
            malleable.add(new POINT(xWall-1,yWall));
            TILELAYOUT[xWall][yWall-1] = WALL;
            Path[xWall][yWall-1] = false;
            malleable.add(new POINT(xWall,yWall-1));
            TILELAYOUT[xWall+1][yWall-1] = WALL;
            Path[xWall+1][yWall-1] = false;
            malleable.add(new POINT(xWall+1,yWall-1));
            TILELAYOUT[xWall-1][yWall-1] = WALL;
            Path[xWall-1][yWall-1] = false;
            malleable.add(new POINT(xWall-1,yWall-1));
        }
    }
    public void getEnemies(){
        for(Enemy p : Play.getEnemies()){
            int Ex = (int)(p.getX()/32);
            int Ey = (int)(p.getY()/32);
            TILELAYOUT[Ex][Ey] = ENEMY;
            malleable.add(new POINT(Ex, Ey));
        }
    }
    public void getPlayer(){
        PlayerX = (int)((Play.getPlayer().getSprite().getX() + Play.getPlayer().getSprite().getWidth()/2)/SQR);
        PlayerY = (int)((Play.getPlayer().getSprite().getY() + Play.getPlayer().getSprite().getHeight()/2)/SQR);
        TILELAYOUT[PlayerX][PlayerY] = PLAYER;
    }
    public void update(){
        //removeMalleable();
//        getWall();
        getEnemies();
        getPlayer();
        for(Node node : AllNodes)
            node.reset();


        method.PathGen(PlayerX, PlayerY, AllNodes);




    }
    public void removeMalleable(){
        TILELAYOUT[PlayerX][PlayerY] = PATH;
        for(POINT p : malleable) {
            TILELAYOUT[p.getI()][p.getJ()] = collisionLayer.getCell(p.getI(), p.getJ()).getTile().getProperties().containsKey("blocked") ? BLOCKED : PATH;
            Path[p.getI()][p.getJ()] = (TILELAYOUT[p.getI()][p.getJ()] == PATH) ? true : false;
            p.remove();
            if(malleable.indexOf(null, true) != -1) {
                malleable.removeIndex(malleable.indexOf(null, true));
            }
        }
    }
    // Make sure update has recently been called
    public void findPath(float currX, float currY, float goalX, float goalY){
        int STARTX = (int)(currX/SQR);
        int STARTY = (int)(currY/SQR);
        int GOALX = (int)(goalX/SQR);
        int GOALY = (int)(goalY/SQR);
        TILELAYOUT[STARTX][STARTY] = START;
        TILELAYOUT[GOALX][GOALY] = FINISH;
        float HCost = 0;
        float GCost = 0;
        PQNode Start = new PQNode(STARTX,STARTY, HCost, GCost);

    }

}

class POINT {
    int i;
    int j;
    public POINT(int i, int j){
        this.i = i;
        this.j = j;
    }
    public int getI() {
        return i;
    }
    public int getJ() {
        return j;
    }
    public boolean equals(POINT pt){ return ((getI() == pt.getI()) && (getJ() == pt.getJ())); }
    public boolean equals(int i, int j){
        return (i == getI()) && (getJ() == j);
    }
    public void remove(){
        Pathfinding.malleable.set(Pathfinding.malleable.indexOf(this, true), null);
        Pathfinding.malleable.removeIndex(Pathfinding.malleable.indexOf(null, true));
    }
}
class PQNode extends POINT implements Comparable{
    float Hcost;
    float Gcost;
    float Fcost;
    POINT Parent;
    public PQNode(int i, int j, float Hcost, float Gcost){
         super(i,j);
         this.Fcost = Hcost + Gcost;
     }
    public float getCost(){
         return Fcost;
     }
    public int compareNODE(PQNode t){
        if(Fcost > t.getCost()){
            return -1;
        }
        else if(Fcost == t.getCost())
            return 0;
        else
            return 1;
    }
    @Override
    public int compareTo(Object o) {
        return compareNODE((PQNode)o);
    }

}