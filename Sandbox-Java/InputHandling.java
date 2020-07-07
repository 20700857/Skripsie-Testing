import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.JFrame;

public class InputHandling implements KeyListener{

    JFrame frame;
    HashMap<Character, Boolean> inputs = new HashMap<>(); 

    public InputHandling(JFrame frame, HashMap<Character, Boolean> inputs) {
        this.frame = frame;
        this.inputs = inputs;
        frame.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

        char in = e.getKeyChar();
        if(inputs.get(in) == null){
            inputs.put(in, true);
        }else{
            inputs.replace(in, true);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

        char in = e.getKeyChar();

        if (inputs.get(in) != null) {
            inputs.put(in, false);
        }else{
            inputs.replace(in, false);
        }
    }
    
}