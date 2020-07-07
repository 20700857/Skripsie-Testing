import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.lang.ModuleLayer.Controller;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ResourceBundle.Control;

public class Main {

    static GraphicsDraw graphics;
    static JFrame frame;
    static HashMap<Character, Boolean> inputs = new HashMap<>();
    static Car car1;
    static Map map1;
    static CarController controller;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Started");

        car1 = new Car(5, new int[] { 0, 0 });
        map1 = new Map(new int[] { 50, 50 });
        BoundsCreator bounds = new BoundsCreator(map1);
        bounds.boundsCreate(new int[] { 10, 10 }, new int[] { 40, 40 });
        ObstacleCreator obs = new ObstacleCreator(map1);
        // obs.createObstacles(50);

        graphics = new GraphicsDraw(car1, map1);
        graphics.setBackground(Color.WHITE);
        frame = new JFrame("Sandbox for skirpsie");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(graphics, BorderLayout.CENTER);
        frame.setVisible(true);

        AStar pathPlanning = new AStar(map1, new int[] { 0, 0 }, new int[] { 49, 49 }, graphics);
        pathPlanning.startSearch();
        pathPlanning.populateNodes();

        controller = new CarController(map1, car1);

        inputs.put('p', false);
        InputHandling input = new InputHandling(frame, inputs);

        Timer gameTicks = new Timer();
        TimerTask gameTicksTask = new TimerTask() {
            @Override
            public void run() {
                gameTick();

            };
        };
        gameTicks.scheduleAtFixedRate(gameTicksTask, 500, 10);

        Timer graphicsTicks = new Timer();
        TimerTask graphicsTicksTask = new TimerTask() {
            @Override
            public void run() {
                graphicsTick();

            };
        };
        graphicsTicks.scheduleAtFixedRate(graphicsTicksTask, 500, 10);

    }

    private static void gameTick() {
        if (inputs.get('w') != null) {
            if (inputs.get('w')) {
                car1.force(new float[]{0,-1}); 
            }
        }

        if (inputs.get('s') != null) {
            if (inputs.get('s')) {
                car1.force(new float[]{0,1});  
            }
        }

        if (inputs.get('a') != null) {
            if (inputs.get('a')) {
                car1.force(new float[]{-1,0});  
            }
        }

        if (inputs.get('d') != null) {
            if (inputs.get('d')) {
                car1.force(new float[]{1,0}); 
            }
        }
        //controller.update();
        car1.update();
        controller.pController();

    }

    private static void graphicsTick() {
        graphics.updateGraphics();
    }

}