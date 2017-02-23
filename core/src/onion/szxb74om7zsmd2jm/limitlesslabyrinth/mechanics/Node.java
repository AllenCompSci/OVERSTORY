package onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics;

import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Enemy;

import java.util.ArrayList;

/**
 * Created by taylor hudson on 2/23/2017.
 */
public class Node {

    // Private Instance Variables
    POINT point;
    public static ArrayList<Node> ConnectedNodes;
    public static ArrayList<Integer> TraverseDistance;
    int Weight;
    int Heuristic;
    boolean Traversed;

    public Node(int i, int j){
        point = new POINT(i, j);
    }
    public Node(){

    }
    public boolean equals(int i, int j){
        return point.equals(i, j);
    }
    public boolean equals(POINT t){
        return point.equals(t);
    }
    public boolean equals(Node t){
        return point.equals(t.getPoint());
    }
    private POINT getPoint(){
        return point;
    }
    private int getI(){
        return point.getI();
    }
    private int getJ(){
        return point.getJ();
    }
    public int getWeight(){
        return Weight;
    }
    public int getHeuristic(){
        return Heuristic;
    }
    public void setHeuristic(Integer value){
        Heuristic = value.intValue() + Weight;
    }
    public boolean isTraversed(){
        return Traversed;
    }
    public void setTraversed(boolean t){Traversed = t;}
    public void moveTo(){ Traversed = true; }
    public void setConnectedNodes(ArrayList<Node> AllNodes){
        ConnectedNodes = new ArrayList<Node>();
        TraverseDistance = new ArrayList<Integer>();
        Weight = 1;
        Traversed = false;
        Heuristic = 0;
        for(int k = 0; k < 9; k++) {
            int i = getI();
            int j = getJ();
            switch(k/3){
                case 0:
                    i+=1;
                    break;
                case 2:
                    i-=1;
                    break;
            }
            switch(k%3){
                case 0:
                    j-=1;
                    break;
                case 2:
                    j+=1;
                    break;
            }

            if(k!= 4) {
                for (Node node : AllNodes) {
                    if(node.equals(i,j)){
                        ConnectedNodes.add(node);
                        if(k%2 == 1){
                            TraverseDistance.add(10);
                        }
                        else{
                            TraverseDistance.add(25);
                        }
                    }
                }

                if ( (k < 4 && ConnectedNodes.size() != (k + 1)) || (k > 4 && ConnectedNodes.size() != k)){
                    ConnectedNodes.add(null);
                    TraverseDistance.add(null);
                }
            }
        }

    }
    public void reset(){
        Weight = 1;
        if(Pathfinding.getCellState()[getI()][getJ()] == Pathfinding.CellState.WALL)
            isWall();
        Heuristic = 0;
        Traversed = false;
    }
    public void isWall(){
        Weight = 10000;
    }
    public static Enemy.DIRECTION moveState(Node Moveto){
        int index = ConnectedNodes.indexOf(Moveto);
        switch(index){
            case 0:
                return Enemy.DIRECTION.NORTHWEST;
            case 1:
                return Enemy.DIRECTION.NORTH;
            case 2:
                return Enemy.DIRECTION.NORTHEAST;
            case 3:
                return Enemy.DIRECTION.WEST;
            case 4:
                return Enemy.DIRECTION.EAST;
            case 5:
                return Enemy.DIRECTION.SOUTHWEST;
            case 6:
                return Enemy.DIRECTION.SOUTH;
        }
        return Enemy.DIRECTION.SOUTHEAST;
    }
    public String toString(){
        return "(" + getI() + ", " + getJ() + ")";
    }
}
