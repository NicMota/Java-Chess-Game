
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;


public class Pawn extends Piece{

    
   
    boolean firstMove = true;

    public Pawn(Board board,boolean white,int x,int y)
    {
        super(board,white,x,y);
    }
    @Override
    public void moveTo(int tx,int ty)
    {   
        if(board.onBounds(tx,ty))
        {
            if(board.isEmpty(tx, ty) && canMove(tx,ty))
            {   
                if(firstMove)
                    firstMove = false;
                board.removePiece(x, y);
                board.placePiece(tx, ty, this);
            }
            if(this.canEat(tx, ty))
            {
                board.removePiece(tx, ty);
                board.removePiece(x, y);
                board.placePiece(tx, ty, this);
            }
        }
           
    }

    @Override
    public boolean canMove(int tx,int ty)
    {       
      if(!board.isEmpty(tx,ty))
        return false;
      if(white)
      { 
        if(firstMove)
            return tx==x && ty == y-1 || ty == y-2;
        else
            return tx==x && ty == y-1;
      }
      
      if(!white)
      {
        if(firstMove)
            return tx==x && ty == y+1 || ty == y+2;
       return tx==x && ty == y+1;
      }
      return false;

    }
    @Override
    public boolean canEat(int tx,int ty)
    {   
        if (board.isEmpty(tx, ty)) return false;
        if (board.getPiece(tx, ty).sameColor(white)) return false;
    
        int dy = white ? -1 : 1;
        return ty == y + dy && (tx == x + 1 || tx == x - 1);
    }
    
   
    @Override
    public void drawSelf(Graphics2D g)
    {
        super.drawSelf(g);
            g.fillRect(x*60+15+30, y*60+15+30, 30, 30);
        if(white)
            g.setColor(Color.black);
        else 
            g.setColor(Color.white);
        g.setStroke(new BasicStroke(2));
        g.drawRect(x*60+15+30, y*60+15+30, 30, 30);
      
    }
}
