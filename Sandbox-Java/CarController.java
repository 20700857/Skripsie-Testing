public class CarController {

    Map map1;
    Car car1;
    double delta;
    double[] dists = new double[3];
    int[] goal = new int[2];

    public CarController(Map map1, Car car1){

        this.map1 = map1;
        this.car1 = car1;
        delta = 0.5;
        goal = map1.path.get(0);

    }

    public void updateController(){
        if (dists[2] <= delta) {
            map1.path.remove(0);
            goal = map1.path.get(0);
        }
    }

    public void dist(){
        dists[0] = Math.abs(goal[0] - car1.position[0] );
        dists[1] = Math.abs(goal[1] - car1.position[1] );
        dists[2] = Math.sqrt(dists[0]*dists[0] + dists[1]*dists[1]);
    }
    
}