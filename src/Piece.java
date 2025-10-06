
import java.awt.Color;
import java.awt.Graphics2D;


public abstract class Piece {
    Board board;
    int x,y;
    boolean canMove;
    boolean selected;
    boolean white;
    public Piece(Board board, boolean white,int x, int y)
    {
        this.board = board;
        this.white = white;
        this.x = x;
        this.y = y;
    }
    public void moveTo(int tx,int ty)
    {
        
    }
   
    public void setX(int x)
    {
        this.x = x;
       
    }
    public void setY(int y)
    {
        this.y = y;
    }

    public boolean sameColor(boolean color)
    {
        return white == color;
    }
    public void drawSelf(Graphics2D g){
        if(white)
            g.setColor(Color.white);
        else
            g.setColor(Color.black);
    }
    public Board getBoard() {
        return board;
    }
    public boolean canMove(int tx,int ty)
    {
        return false;
    }
    public boolean canEat(int tx, int ty)
    {
        return false;
    }
    
    public void setBoard(Board board) {
        this.board = board;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public boolean isCanMove() {
        return canMove;
    }
    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public boolean isWhite() {
        return white;
    }
    public void setWhite(boolean white) {
        this.white = white;
    }
}
