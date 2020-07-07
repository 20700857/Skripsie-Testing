public class BoundsCreator {

    Map map1;

    public BoundsCreator(Map map1){
        this.map1 = map1;
    }

    public void boundsCreate(int[] cornerLeftTop, int[] cornerRightBottom){

        for (int i = cornerLeftTop[0]; i < cornerRightBottom[0]; i++) {
            for (int j = cornerLeftTop[1]; j < cornerRightBottom[1]; j++) {

                map1.gridMap[i][j].state = "OOB";
                
            }
        }
    }
    
}