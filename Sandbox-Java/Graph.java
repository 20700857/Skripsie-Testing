import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Graph extends JPanel{
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    ArrayList<Double> values = new ArrayList<Double>();
    int length = 50;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGraph(g);

    }

    public void drawGraph(Graphics g){
        g.setColor(Color.RED);
        int y;
        int y2;

        int x;
        int x2;
        

        for (int i = 0; i < values.size()-1; i++) {
            y = (int)(((float)getHeight()/2) + values.get(i)*20);
            y2 = (int)(((float)getHeight()/2) +values.get(i+1)*20);

            x = (int)(((float)i/(float)length)*getWidth());
            x2 = (int)(((float)(i+1)/(float)length)*getWidth());

            g.drawLine(x, y, x2, y2);

        }

        

    }

    public void updateGraphics(){
        repaint();
    }

    public void update(double pos){
        if (values.size() == length) {
            values.remove(0);
        }          
        values.add(pos);
        

    }

}