public class MapNode {
    
    MapNode prior;
    double cost;
    int[] pos;
    String state;
    boolean checked;
    boolean dead;

    public MapNode(int[] pos, MapNode prior, float cost, String state){
        this.pos = pos;
        this.prior = prior;
        this.cost = cost;
        this.state = state;
        checked = false;
        dead = false;
    }
}