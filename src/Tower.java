
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Tower extends Piece{
    
    
    public Tower(Board board, boolean white, int x, int y) {
        super(board, white, x, y);
    }


    @Override
    public void moveTo(int tx, int ty)
    {
        if(this.canMove(tx, ty) && board.isEmpty(tx, ty))
        {
            board.removePiece(x, y);
            board.placePiece(tx, ty, this);
        }         
        if(canEat(tx, ty))
        {
            board.removePiece(x, y);
            board.removePiece(tx,ty);
            board.placePiece(tx, ty, this);
           
        }
    }
    @Override
    public boolean canEat(int tx,int ty)
    {   
        if(board.isEmpty(tx, ty))
            return false;

        if(board.getPiece(tx, ty).sameColor(white))
            return false;

        if(tx == x)
            return board.havePiecesBeetweenY(y, ty, x);
        if(ty == y)
            return board.havePiecesBeetweenX(x, tx, y);
        
        
        
        return false;
    }   

    @Override 
    public boolean canMove(int tx, int ty)
    {   
        if(!board.onBounds(tx, ty))
            return false;

        if(!this.board.isEmpty(tx, ty))
            return false;   
        
        
        
        if(ty == y)
        {       
            return board.havePiecesBeetweenX(x, tx, y);
        }
        if(tx == x)
            return board.havePiecesBeetweenY(y, ty, x);
        return false;

        
        
       
    }

    @Override
    public void drawSelf(Graphics2D g)
    {
        super.drawSelf(g);
            g.fillRect(x*60+15+30, y*60+8+30, 30, 45);
        if(white)
            g.setColor(Color.black);
        else 
            g.setColor(Color.white);

        g.setStroke(new BasicStroke(2));
        g.drawRect(x*60+15+30, y*60+8+30, 30, 45);
      
    }
    
}
