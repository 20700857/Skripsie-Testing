public class ObstacleCreator {
    
    Map map1;

    public ObstacleCreator(Map map1){
        
        this.map1 = map1;

    }

    public void createObstacles(int numObstacles){

        int[] pos = new int[2];

        for (int i = 0; i < numObstacles; i++) {
            pos[0] = (int)(Math.random()*map1.columns); 
            pos[1] = (int)(Math.random()*map1.rows); 

            map1.gridMap[pos[0]][pos[1]].state = "OBSTACLE";
            
        }
    }
}