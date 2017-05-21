package onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Player;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.LaserGun;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

import java.util.ArrayList;

/**
 * Created by taylor hudson on 2/23/2017.
 */
public class Dijkstra {
    public ArrayList<ArrayList<Node>> AllPaths;
    public ArrayList<Node> Pathway;
    public ArrayList<Node> EndNodes;
    public static Pathfinding.CellState[][] TILELAYOUT;
    static boolean update = false;
    static int LoopBeginIndex;
    static int LoopEndIndex;
    public Dijkstra() {
        TILELAYOUT = Pathfinding.getCellState();
        Pathway = new ArrayList<Node>();
        AllPaths = new ArrayList<ArrayList<Node>>();
        EndNodes = new ArrayList<Node>();
        update = true;
    }
    public void PathGen(int startI, int startJ, ArrayList<Node> AllNodes ){
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

        //System.out.println(startI + " : " + startJ);
        Pathway.clear();
        AllPaths.clear();
        EndNodes.clear();
        boolean allNodesTraversed = false;
        Node start = new Node(startI, startJ);
        for(Node n : AllNodes){
            if(n.equals(start)){
                start = AllNodes.get(AllNodes.indexOf(n));
            }
        }
        start.moveTo();
        Pathway.add(start);
        AllPaths.add(Pathway);
        EndNodes.add(start);
        LoopBeginIndex = 0;
        LoopEndIndex = 0;
       // System.out.println(start);
        while(!allNodesTraversed){

            LoopEndIndex = AllPaths.size();
            for(int i = LoopBeginIndex; i < LoopEndIndex; i++){
                Node ENDNODE = EndNodes.get(i);
                    boolean CHANGE = false;
                    for(int conn = 0; conn < 8; conn++){
                        // This should make the start node connect to all other nodes.
                        if(ENDNODE.getConnectedNodes().get(conn) != null && ENDNODE.getWeight() != 10000){
                            if(ENDNODE.getConnectedNodes().get(conn).isTraversed() && (ENDNODE.TraverseDistance.get(conn).intValue()+ ENDNODE.getHeuristic()) < ENDNODE.getConnectedNodes().get(conn).getWeight()  ){
                                ArrayList<Node> create = updatedPath(AllPaths.get(i), ENDNODE.getConnectedNodes().get(conn), conn);
                                RemoveFromAllPaths(ENDNODE.getConnectedNodes().get(conn));
                                AllPaths.add(create);
                                EndNodes.add(ENDNODE.getConnectedNodes().get(conn));
                                ENDNODE.getConnectedNodes().get(conn).setHeuristic(ENDNODE.getHeuristic() + ENDNODE.TraverseDistance.get(conn));
                                CHANGE = true;
                            }
                            else if(!ENDNODE.getConnectedNodes().get(conn).isTraversed()){
                                AllPaths.add(updatedPath(AllPaths.get(i), ENDNODE.getConnectedNodes().get(conn), conn));
                                EndNodes.add(ENDNODE.getConnectedNodes().get(conn));
                                CHANGE = true;
                            }
                        }

                    }
                    if(CHANGE){
                    AllPaths.remove(i);
                    EndNodes.remove(i);
                    LoopBeginIndex --;
                    LoopEndIndex--;
                    i--;
                }

            }
            LoopBeginIndex = 0;


            allNodesTraversed = true;
            for(int k = 0; allNodesTraversed && k < AllNodes.size(); k++){
                allNodesTraversed = AllNodes.get(k).isTraversed();

            }

        }

    }
    public static void deffixNewNode() {
        if (Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed((Input.Keys.L)) && Gdx.input.isKeyPressed(Input.Keys.G) && Gdx.input.isKeyJustPressed((Input.Keys.NUM_4))) {
            Play.getGui().getBackpack().addToBackpack(new LaserGun(10000));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed((Input.Keys.L)) && Gdx.input.isKeyPressed(Input.Keys.G) && Gdx.input.isKeyJustPressed((Input.Keys.NUM_1))) {
            Player.increaseHealth();
        }
    }

    public static ArrayList<Node> affixNewNode (ArrayList<Node> current, Node newEnd){
        for(int i = 0; i < current.size(); i++)
        {

        }
            return new ArrayList<Node>();
    }
    public ArrayList<Node> updatedPath(ArrayList<Node> current, Node newEnd, int index){
        ArrayList<Node> create = new ArrayList<Node>();
        for(Node n : current){
            create.add(n);
        }
        newEnd.moveTo(current.get(current.size()-1), 7-index);
        create.add(newEnd);
        //print(create,newEnd);
        return create;
    }
    public void RemoveFromAllPaths(Node updated){
        for(int i = 0; i < AllPaths.size(); i++){

            if(AllPaths.get(i).contains(updated)){
                System.out.print(AllPaths.size());
                AllPaths.remove(i);
                EndNodes.remove(i);
                if(i < LoopBeginIndex){
                    LoopBeginIndex --;
                }
                if(i < LoopEndIndex){
                    LoopEndIndex --;
                }
                i--;
                System.out.println(" : " + AllPaths.size() + " after removal");

            }
        }
    }
    public void print(ArrayList<Node> Path, Node END){
        System.out.print(END);
        System.out.println(Path);
    }

    public boolean AllPathContains(Node n){
        for(int i = 0; i < AllPaths.size(); i++){
            if(AllPaths.get(i).contains(n)){
                return true;
            }
        }
        return false;
    }

}
