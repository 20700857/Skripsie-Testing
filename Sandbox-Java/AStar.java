import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;

public class AStar {

    float weighting = 1;

    static GraphicsDraw graphics;

    // Creating an empty HashMap 
    HashMap<String, int[]> directions = new HashMap<String, int[]>();

    Map map1;
    int[] goal;

    boolean pathFound;

    Deque<MapNode> openNodes = new ArrayDeque<MapNode>();

    MapNode currentNode;
    String temp;

    public AStar(Map map1, int[] start, int[] goal, GraphicsDraw graphics){

        this.goal = goal;
        this.map1 = map1;
        this.graphics = graphics;
        pathFound = false;

        // initialise start and goal
        map1.gridMap[start[0]][start[1]].state = "START";
        map1.gridMap[goal[0]][goal[1]].state = "GOAL";

        // Add start node to queue
        openNodes.add(map1.gridMap[start[0]][start[1]]);

        // instantiate direction hashmap
        directions.put("UP",new int[]{0,-1});
        directions.put("RIGHT", new int[]{1,0});
        directions.put("DOWN",new int[]{0,1});
        directions.put("LEFT", new int[]{-1,0});

    }

    public void startSearch(){

        currentNode = openNodes.getLast();
    }

    public void populateNodes() throws InterruptedException {


        while (pathFound == false) {
            graphics.updateGraphics();
            iterateNode();

        }

        ArrayList<int[]> path = new ArrayList<int[]>();

        while (currentNode != null) {
            path.add(currentNode.pos);
            currentNode = currentNode.prior;
        }

        map1.path = path;

    }

    public void iterateNode(){

        temp = checkDirection(currentNode);
            
            if(temp != null){
                map1.gridMap[currentNode.pos[0] + directions.get(temp)[0]][currentNode.pos[1] + directions.get(temp)[1]].cost = currentNode.cost + heuristic(new int[]{currentNode.pos[0] + directions.get(temp)[0], currentNode.pos[1] + directions.get(temp)[1] }, goal);
                map1.gridMap[currentNode.pos[0] + directions.get(temp)[0]][currentNode.pos[1] + directions.get(temp)[1]].prior = currentNode;
                map1.gridMap[currentNode.pos[0] + directions.get(temp)[0]][currentNode.pos[1] + directions.get(temp)[1]].checked = true;
                openNodes.add(map1.gridMap[currentNode.pos[0] + directions.get(temp)[0]][currentNode.pos[1] + directions.get(temp)[1]]);
                currentNode = openNodes.getLast();
            }else{
                openNodes.pop();
                currentNode.dead = true;
                currentNode = currentNode.prior;
            }
                      
            if(currentNode.pos[0] == goal[0] && currentNode.pos[1] == goal[1]){
                System.out.println("Found path");
                pathFound = true;
            }
    }

    private String checkDirection(MapNode origin){

        double minVal = Double.MAX_VALUE;
        String minDirection = "UP";
        double temp;

        for(String direction: directions.keySet()) {

            temp = heuristic(new int[]{origin.pos[0] + directions.get(direction)[0], origin.pos[1] + directions.get(direction)[1] }, goal);
            if (temp < minVal) {
                minVal = temp;
                minDirection = direction;          
            }

        }
        if (minVal == Double.MAX_VALUE) {
            return null;
        }

        return minDirection;

    }

    private double heuristic(int[] start, int[] end){

        if (start[0] > map1.columns-1) {
            return Double.MAX_VALUE;
        }
        if (start[0] < 0) {
            return Double.MAX_VALUE;
        }
        if (start[1] > map1.rows-1) {
            return Double.MAX_VALUE;
        }
        if (start[1] < 0) {
            return Double.MAX_VALUE;
        }
        if (map1.gridMap[start[0]][start[1]].state == "OOB"){
            return Double.MAX_VALUE;
        }
        if (map1.gridMap[start[0]][start[1]].state == "OBSTACLE"){
            return Double.MAX_VALUE;
        }
        if (map1.gridMap[start[0]][start[1]].state == "START"){
            return Double.MAX_VALUE;
        }
        if (map1.gridMap[start[0]][start[1]].checked){
            return Double.MAX_VALUE;
        }

        int distX = Math.abs(start[0] - end[0]);
        int distY = Math.abs(start[1] - end[1]);

        return Math.sqrt(Math.pow(distX,2) + Math.pow(distY,2)) + weighting*Math.max(distY, distX);

    }


    
}