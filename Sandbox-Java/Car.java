public class Car {
    float[] position = new float[2];
    float[] velocity = new float[2];
    float[] acceleration = new float[2];
    float[] force = new float[2];
    float mass;

    public Car(float mass, int[] position){
        this.mass = mass;
        this.position[0] = position[0];
        this.position[1] = position[1];
        force[0] = 0;
        force[1] = 0;
    }

    public void update(){

        acceleration[0] = force[0]/mass;
        acceleration[1] = force[1]/mass;

        velocity[0] += acceleration[0];
        velocity[1] += acceleration[1];

        position[0] += velocity[0];
        position[1] += velocity[1];
        force[0] = 0;
        force[1] = 0;

    }

    public void Force(float[] force){

        this.force = force;
    }

}