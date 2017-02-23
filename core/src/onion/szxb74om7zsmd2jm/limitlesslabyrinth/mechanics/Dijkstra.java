package onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.ArrayList;

/**
 * Created by taylor hudson on 2/23/2017.
 */
public class Dijkstra {
    static public ArrayList<ArrayList<Node>> AllPaths;
    static public ArrayList<Node> Pathway;
    static public ArrayList<Node> EndNodes;
    static Pathfinding.CellState[][] TILELAYOUT;
    static boolean update = false;
    static int LoopBeginIndex;
    static int LoopEndIndex;
    public static void set() {
        TILELAYOUT = Pathfinding.getCellState();
        Pathway = new ArrayList<Node>();
        AllPaths = new ArrayList<ArrayList<Node>>();
        EndNodes = new ArrayList<Node>();
        update = true;
    }
    public static void PathGen(int startI, int startJ){
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


        Pathway.clear();
        AllPaths.clear();

        boolean allNodesTraversed = false;
        Node start = new Node();
        for(Node n : Pathfinding.AllNodes){
            if(n.equals(startI, startJ)){
                start = Pathfinding.AllNodes.get(Pathfinding.AllNodes.indexOf(n));
            }
        }
        start.moveTo();
        Pathway.add(start);
        AllPaths.add(Pathway);
        EndNodes.add(start);
        LoopBeginIndex = 0;
        LoopEndIndex = 0;
        while(!allNodesTraversed){

            LoopEndIndex = AllPaths.size();
            for(int i = LoopBeginIndex; i < LoopEndIndex; i++){
                Node ENDNODE = AllPaths.get(i).get(AllPaths.get(i).size()-1);
                    for(int conn = 0; conn < 8; conn++){
                        if(ENDNODE.ConnectedNodes.get(conn) != null && ENDNODE.getWeight() != 10000){
                            if(ENDNODE.ConnectedNodes.get(conn).isTraversed() && (ENDNODE.TraverseDistance.get(conn).intValue()+ ENDNODE.getHeuristic()) < ENDNODE.ConnectedNodes.get(conn).getWeight()  ){
                                ArrayList<Node> create = updatedPath(AllPaths.get(i), ENDNODE.ConnectedNodes.get(conn));
                                RemoveFromAllPaths(ENDNODE.ConnectedNodes.get(conn));
                                AllPaths.add(create);
                                EndNodes.add(ENDNODE.ConnectedNodes.get(conn));
                                ENDNODE.ConnectedNodes.get(conn).setHeuristic(ENDNODE.getHeuristic() + ENDNODE.TraverseDistance.get(conn));
                            }
                            else if(!ENDNODE.ConnectedNodes.get(conn).isTraversed()){
                                AllPaths.add(updatedPath(AllPaths.get(i), ENDNODE.ConnectedNodes.get(conn)));
                                EndNodes.add(ENDNODE.ConnectedNodes.get(conn));
                            }
                        }
                    }

            }
            LoopBeginIndex = LoopEndIndex;

            System.out.println(AllPaths.size());
            allNodesTraversed = true;
            for(int k = 0; allNodesTraversed && k < Pathfinding.AllNodes.size(); k++){
                allNodesTraversed = Pathfinding.AllNodes.get(k).isTraversed();

            }

        }

    }

    public static ArrayList<Node> updatedPath(ArrayList<Node> current, Node newEnd){
        ArrayList<Node> create = new ArrayList<Node>();
        for(Node n : current){
            create.add(n);
        }
        newEnd.setTraversed(true);
        create.add(newEnd);

        return create;
    }
    public static void RemoveFromAllPaths(Node updated){
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
    public static void print(ArrayList<Node> Path, Node END){
        System.out.print(END);
        System.out.println(Path);
    }

    public static boolean AllPathContains(Node n){
        for(int i = 0; i < AllPaths.size(); i++){
            if(AllPaths.get(i).contains(n)){
                return true;
            }
        }
        return false;
    }

}
