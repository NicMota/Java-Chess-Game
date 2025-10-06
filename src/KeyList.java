
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyList implements KeyListener {

    public boolean right,left,up,down,enter;
    @Override
    public void keyTyped(KeyEvent e) {
    
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key==KeyEvent.VK_RIGHT)
            right = true;
        if(key==KeyEvent.VK_LEFT)
            left = true;
        if(key==KeyEvent.VK_UP)
            up = true;
        if(key==KeyEvent.VK_DOWN)
            down = true;
        if(key==KeyEvent.VK_ENTER)
            enter = true;
     
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
      
    }
    
}
