
import java.awt.*;
import javax.swing.*;

public class GameWindow extends JPanel implements Runnable
{
    Game game;
    Thread GameThread;
    final int WIDTH = 540;
    final int HEIGHT = 540;
    KeyList keyList = new KeyList();
    final int TILE_SIZE = 60;
    String gameInfo;
    public GameWindow()
    {   
        game = new Game();
        this.addKeyListener(keyList);
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setFocusable(true);
        this.setBackground(Color.magenta);
    }
    public void init()
    {
        this.GameThread = new Thread(this);
        this.GameThread.start();
    }
    public void run()
    {
        double drawInterval = 1000000000/60;
        double delta = 0;
        long lastTime =  System.nanoTime();
        long currentTime;

        while(GameThread!=null)
        {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime)/drawInterval;
            lastTime = currentTime;

            if(delta>=1){
                update();
                repaint();
                delta--;
            }
        }
    }
    public void update()
    {       
        game.update();
        gameInfo = String.format("Check: %s, black: %d, white: %d turno: %s",game.check? "sim" : "nao",game.blackPieces.size(),game.whitePieces.size(),game.turn%2==0?"brancas":"pretas");
        if(keyList.right)
            game.addS(1,0);
        if(keyList.left)
            game.addS(-1,0);
        if(keyList.up)
            game.addS(0,-1);
        if(keyList.down)
            game.addS(0,1);
        if(keyList.enter){
           select();
            
        }
          
        

        keyList.right = false;
        keyList.left = false;
        keyList.up = false;
        keyList.down= false;
        keyList.enter = false;
    }
    public void select()
    {
         if(game.selectedPiece!=null)
            {
                game.movePiece(game.sx, game.sy);
                game.selectedPiece = null;
                game.board.printBoard();
            }else
            {
                game.select();
                System.out.printf("enter");
            }
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2  = (Graphics2D) g;
        drawBoard(g2);
        drawSelector(g2);
        drawInfo(g2);
    }   
    public void drawInfo(Graphics2D g)
    {   
        g.setStroke(new BasicStroke(4));
        g.setColor(Color.black);
        g.setFont(new Font("Consolas", Font.BOLD, 15));
        g.drawString(gameInfo, 12, 20 );
    }
    public void drawSelector(Graphics2D g)
    {   
        
        Color selectorColor = game.selectedPiece !=null? Color.green : Color.red;
        g.setColor(selectorColor);
        g.drawRect(game.sx * 60+30, game.sy * 60+30, 60, 60);
    }
    public void drawBoard(Graphics2D g)
    {   
        boolean white = false;
        Color tileColor;
        for(int r = 0; r<game.board.pieces.length;r++)
        {
            for(int c = 0;c<game.board.pieces.length;c++)
            {   
                white = !white;
                if(white)
                    tileColor = Color.white;
                else
                    tileColor = Color.gray;
                g.setColor(tileColor);
                g.fillRect(r*60+ 30,c*60+ 30,60 ,60 );
                if(!game.board.isEmpty(r, c))
                    game.board.pieces[r][c].drawSelf(g);
            }
            white=!white;
        }
        g.setStroke(new BasicStroke(2));
        g.setColor(Color.black);
        g.drawRect(30, 30, 480, 480);
    }
 
}