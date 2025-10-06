
import javax.swing.JFrame;

public class Main
 {
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        GameWindow gameWindow = new GameWindow();
        gameWindow.init();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gameWindow);
        frame.pack();
        frame.setVisible(true);
    }
 }
  