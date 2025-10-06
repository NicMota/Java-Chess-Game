

public class Board {

    public Piece pieces[][];
    
    public Board(int xsize, int ysize)
    {
        this.pieces = new Piece[xsize][ysize];
        this.initBoard();
    }
    
    public boolean havePiecesBeetweenX(int xo, int xf, int y)
    {  
       int co = xo+1;
       int cf = xf-1;
       
       if(cf<co)
       {
        co = xf+1;
        cf = xo-1;
       }
            
       for(int i = co; i<=cf; i++)
       {
            if(!isEmpty(i, y))
                return false;
       }
       return true;
    }
    
    public boolean havePiecesBeetweenY(int yo, int yf,int x)
    {
       int co = yo+1;
       int cf = yf-1;
       
       if(yf<yo)
       {
            co = yf+1;
            cf = yo-1;
       }
            
       for(int i = co; i<=cf;i++)
       {
            if(!isEmpty(x, i))
                return false;
       }
       return true;
    }
  
    public boolean onBounds(int x,int y)
    {
        return y<8 && y>=0 && x>=0 && x<8;

    }
    public boolean isEmpty(int x,int y)
    {
        return pieces[x][y] == null;
    }
    public Piece getPiece(int x,int y)
    {
        return pieces[x][y];
    }

    public void removePiece(int x,int y)
    {
        pieces[x][y] = null;
    }
    public void placePiece(int x, int y, Piece piece)
    {   
        piece.setX(x);
        piece.setY(y);
        pieces[x][y] = piece;
    }
   
   
    
    private void initBoard()
    {
        for(int i = 0;i<pieces.length;i++)
        {
            pieces[i][1] = new Pawn(this,false,i,1);
            pieces[i][6] = new Pawn(this,true,i,6);
        }
        pieces[0][0] = new Tower(this, false,0,0);
        pieces[7][0] = new Tower(this, false,7,0);
        pieces[0][7] = new Tower(this, true, 0,7);
        pieces[7][7] = new Tower(this, true, 7,7);

        pieces[4][0] = new King(this,false,4,0);
        pieces[4][7] = new King(this,true,4,7);
        this.printBoard();
    }

    public void printBoard()
    {
        int o,i;
        for(i = 0; i<pieces.length;i++)
        {
             for(o=0;o<pieces.length;o++)
             {  
                if(pieces[o][i] instanceof Pawn)
                    System.out.printf("Pau");
                
                else
                    System.out.printf("cu");
             }
             System.out.println("\n");
        }
    }
    
}

        