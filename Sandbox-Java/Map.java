import java.util.ArrayList;

public class Map {

    MapNode[][] gridMap;
    int rows;
    int columns;
    ArrayList<int[]> path = new ArrayList<int[]>();


    public Map(int[] size){

        gridMap = new MapNode[size[0]][size[1]];
        rows = size[0];
        columns = size[1];
        instantiateMap();

    }

    private void instantiateMap(){
        for (int i = 0; i < gridMap.length; i++) {
            for (int j = 0; j < gridMap[i].length; j++) {
                MapNode temp = new MapNode(new int[]{i,j}, null, 0, "EMPTY");
                gridMap[i][j] = temp;
            }
        }
    }

    public void changeElement(int[] location, String type){

        gridMap[location[0]][location[1]].state = type;
    }
}