import java.awt.Color;
import java.awt.Graphics;

import java.awt.Font;

import javax.swing.JPanel;

/**
 * A panel maintaining a picture of a Do Not Enter sign.
 */
public class GraphicsDraw extends JPanel {

    boolean showCosts;
    boolean showChecks;
    boolean showStates;

    Car car1;
    Map map1;

    private static final long serialVersionUID = 7148504528835036003L;

    public GraphicsDraw(Car car1, Map map1) {

        this.car1 = car1;
        this.map1 = map1;
        showCosts = false;
        showChecks = true;
        showStates = true;
    }

    /**
     * Called by the runtime system whenever the panel needs painting.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGrid(g);

    }

    public void updateGraphics(){
        repaint();
    }

    public void drawGrid(Graphics g) {
        // Drawing of the grid
        Font font = new Font("Serif", Font.PLAIN, 12);
        g.setFont(font);

        for (int i = 0; i < map1.gridMap.length; i++) {
            for (int j = 0; j < map1.gridMap[i].length; j++) {

                switch (map1.gridMap[i][j].state) {
                    case "OOB": // Out of bounds

                        g.setColor(Color.WHITE);

                        break;

                    case "EMPTY": // Unused

                        g.setColor(Color.BLUE);

                        break;
                    case "OBSTACLE": // Obstacle

                        g.setColor(Color.RED);

                        break;
                    case "START": // Start

                        g.setColor(Color.RED);

                        break;
                    case "GOAL": // Goal

                        g.setColor(Color.GREEN);

                        break;
                }

                if (showStates) {
                    drawState(new int[]{i,j}, g);
                }

               

                if (showCosts) {
                    g.drawString(String.valueOf((int) map1.gridMap[i][j].cost),
                        (int) (((float) i / (float) map1.gridMap.length) * getWidth()),
                        (int) (((float) j / (float) map1.gridMap[i].length) * getHeight()));
                }
                
                if (showChecks) {

                    if (map1.gridMap[i][j].checked) {
                        drawCheck(new int[]{i,j}, g);
                    }
                }
                
            }
        }

        if (map1.path != null) {
            g.setColor(Color.PINK);
            for (int k = 0; k < map1.path.size() - 1; k++) {
                g.drawLine((int) (((float) map1.path.get(k)[0] / (float) map1.columns) * getWidth()), (int) (((float) map1.path.get(k)[1] / (float) map1.rows) * getHeight()), (int) (((float) map1.path.get(k+1)[0] / (float) map1.columns) * getWidth()), (int) (((float) map1.path.get(k+1)[1] / (float) map1.rows) * getHeight()));
            }
        }
        drawCar(car1.position, g);

    }

    private void drawCar(float[] pos, Graphics g){
        int[] centre = new int[2];
        int[] dimensions = new int[2];
        dimensions[0] = (int) (getWidth() * 0.5 / map1.columns);
        dimensions[1] = (int) (getHeight() * 0.5 / map1.rows);
        centre[0] = (int) ((pos[0] / (float) map1.gridMap.length) * getWidth()) - dimensions[0]/2;
        centre[1] = (int) (( pos[1] / (float) map1.gridMap.length) * getHeight()) - dimensions[1]/2;

        g.setColor(Color.GREEN);
        g.fillOval(centre[0], centre[1], dimensions[0], dimensions[1]);
    }

    private void drawCheck(int[] pos, Graphics g){

        int[] centre = new int[2];
        int[] dimensions = new int[2];
        dimensions[0] = (int) (getWidth() * 0.2 / map1.columns);
        dimensions[1] = (int) (getHeight() * 0.2 / map1.rows);
        centre[0] = (int) (((float) pos[0] / (float) map1.columns) * getWidth()) - dimensions[0]/2;
        centre[1] = (int) (((float) pos[1] / (float) map1.rows) * getHeight()) - dimensions[1]/2;

        g.setColor(Color.YELLOW);
        g.fillOval(centre[0], centre[1], dimensions[0], dimensions[1]);

    }

    private void drawState(int[] pos, Graphics g){

        int[] centre = new int[2];
        int[] dimensions = new int[2];
        dimensions[0] = (int) (getWidth() * 0.9 / map1.columns);
        dimensions[1] = (int) (getHeight() * 0.9 / map1.rows);
        centre[0] = (int) (((float) pos[0] / (float) map1.columns) * getWidth()) - dimensions[0]/2;
        centre[1] = (int) (((float) pos[1] / (float) map1.columns) * getHeight()) - dimensions[1]/2;

       g.fillRect(centre[0], centre[1], dimensions[0], dimensions[1]);
    }

}