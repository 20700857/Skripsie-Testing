import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import java.awt.event.*;  

public class Main {

    static GraphicsDraw graphics;
    static JFrame mainFrame;
    static HashMap<Character, Boolean> inputs = new HashMap<>();
    static Car car1;
    static Map map1;
    static CarController controller;
    static InputHandling input;
    static AStar pathPlanning;

    static Graph graph1;
    static Graph graph2;

    static boolean paused = false;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Started");

        car1 = new Car(5, new int[] { 0, 0 });
        map1 = new Map(new int[] { 25, 25 });
        BoundsCreator bounds = new BoundsCreator(map1);
        bounds.boundsCreate(new int[] { 10, 10 }, new int[] { 15, 15 });
        ObstacleCreator obs = new ObstacleCreator(map1);
        // obs.createObstacles(100);

        graphics = new GraphicsDraw(car1, map1);
        graphics.setBackground(Color.WHITE);
        graphics.setSize(800, 800);

        JPanel graphing = new JPanel();
        graphing.setLayout(new GridLayout(2, 1));
        graphing.setSize(800, 800);

        graph1 = new Graph();
        graph2 = new Graph();
        graphing.add(graph1);
        graphing.add(graph2);

        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(2,2));

        JButton pause = new JButton("Pause");
        pause.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){  
                paused = !paused;  
                if(paused){
                    pause.setText("Play");
                }else{
                    pause.setText("Pause");
                }
        }  
        });
        JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){  
                map1.reset();
                car1.reset();
                pathPlanning.startSearch();
                pathPlanning.populateNodes();
        }  
        });

        controls.add(pause);
        controls.add(reset);
        controls.setSize(graphics.getWidth(), 50);

        mainFrame = new JFrame("Sandbox for skirpsie");

        mainFrame.setLayout(new GridLayout(2,2));
        mainFrame.setSize(800, 800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.add(graphics);
        mainFrame.add(graphing);
        mainFrame.add(controls);
        mainFrame.setFocusable(true);

        mainFrame.setVisible(true);

        pathPlanning = new AStar(map1, new int[] { 0, 0 }, new int[] { 24, 24 }, graphics);
        pathPlanning.startSearch();
        pathPlanning.populateNodes();

        controller = new CarController(map1, car1);

        input = new InputHandling(mainFrame, inputs);

        Timer gameTicks = new Timer();
        TimerTask gameTicksTask = new TimerTask() {
            @Override
            public void run() {
                if(!paused){
                    gameTick();
                }
                

            };
        };
        gameTicks.scheduleAtFixedRate(gameTicksTask, 500, 50);

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
                car1.force(new float[] { 0, -1 });
            }
        }

        if (inputs.get('s') != null) {
            if (inputs.get('s')) {
                car1.force(new float[] { 0, 1 });
            }
        }

        if (inputs.get('a') != null) {
            if (inputs.get('a')) {
                car1.force(new float[] { -1, 0 });
            }
        }

        if (inputs.get('d') != null) {
            if (inputs.get('d')) {
                car1.force(new float[] { 1, 0 });
            }
        }
        // controller.update();
        car1.update();
        controller.pController();
        graph1.update(controller.dists[0]);
        graph2.update(controller.dists[1]);

    }

    private static void graphicsTick() {
        graphics.updateGraphics();
        graph1.updateGraphics();
        graph2.updateGraphics();
    }

}