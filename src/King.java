
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class King extends Piece {


    public King(Board board, boolean white, int x, int y) {
        super(board, white, x, y);
    }
    
    
    @Override
    public void moveTo(int tx, int ty)
    {
        //verificar se em board(tx,ty) é uma casa ameaçada 
        if(this.canMove(tx,ty))
        {
            board.removePiece(x, y);
            board.placePiece(tx, ty, this);
        }
        if(this.canEat(tx, ty))
        {

            board.removePiece(x, y);
            board.removePiece(tx, ty);
            board.placePiece(tx, ty, this);
        }   
    }
    
    @Override
    public boolean canMove(int tx, int ty)
    {
        //verificar se em board(tx,ty) é uma casa ameaçada 

        if(!board.onBounds(tx, ty))
            return false;
       
        if(!board.isEmpty(tx, ty))
            return false;
        
        
        
        return true;
    }
    
    @Override
    public boolean canEat(int tx, int ty)
    {   
         if(!(Math.abs(tx - x) <= 1 && Math.abs(ty - y)<=1))
            return false;
        if(!board.isEmpty(tx, ty))
        {
            if(!board.getPiece(tx, ty).sameColor(this.white))
            {
                return true;
            }
        }
        return false;
    }


    @Override
    public void drawSelf(Graphics2D g)
    {
        super.drawSelf(g);
        g.fillRect(x*60+15+30, y*60+18+30, 30, 35);
        g.setColor(Color.yellow);
        g.fillRect(x*60+8+30,y*60+8 +30, 45, 10);
        if(white)
            g.setColor(Color.black);
        else 
            g.setColor(Color.lightGray);

        
        g.setStroke(new BasicStroke(2));

        g.drawRect(x*60+15+30, y*60+18+30, 30, 35);
        g.drawRect(x*60+8+30,y*60+8+30 , 45, 10);
    }
}
