import java.util.LinkedList;

public class Game {

    Board board;

    LinkedList<Piece> whitePieces;
    LinkedList<Piece> blackPieces;

    int turn = 0;
    int sx = 0;
    int sy = 0;
    Piece selectedPiece = null;
    boolean check = false;

    King whiteKing;
    King blackKing;
    
    public Game()
    {
        board = new Board(8, 8);

        whitePieces = new LinkedList<>();
        blackPieces = new LinkedList<>();
        
        initPieces();

        
    }
    private void initPieces()
    {
        for(int i =0;i<8;i++)
        {
            for(Piece piece : board.pieces[i])
            {   
                if(piece!=null){  
                    if(piece.isWhite())
                    {   
                        if(piece instanceof King king)
                            whiteKing = king;
                        
                        whitePieces.add(piece);
                    }

                    else if(!piece.isWhite())
                    {
                        if(piece instanceof King king)
                            blackKing = king;

                        blackPieces.add(piece);
                    }
                }
            }
        }
      
    }
    

    // public boolean isThreat(boolean white,int tx,int ty)
    // {   
    //     if(white)
    //         for(Piece piece : whitePieces)
    //         {
    //             if(piece.canEat(tx, ty))
    //             {
    //                 return true;
    //             }
    //         }
    //     else
    //         for(Piece piece:blackPieces)
    //         {
    //             if(piece.canEat(tx, ty))
    //             {
    //                 return true;
    //             }
    //         }
    //     return false;
    // }

    public boolean isCheck(boolean white)
    {   
        this.check = false;
        if(!white)
            whitePieces.forEach(piece->{
                if(piece.canEat(this.blackKing.x,this.blackKing.y))
                {
                    this.check = true;
                }
            });
        else
            blackPieces.forEach(piece->{
                if(piece.canEat(this.whiteKing.x,this.whiteKing.y))
                {
                    this.check = true;
                }
            });

        return this.check;
        
    }

    public void update()
    {   
        
        
    }

    
    public void addS(int x,int y)
    {
        if(this.sx + x>=board.pieces.length || this.sx + x < 0)
            return;
        if(this.sy + y>=board.pieces.length || this.sy + y < 0)
            return;
        this.sx+=x;
        this.sy+=y;
        System.out.printf("\nx:%d,y:%d\n",this.sx,this.sy);
    }
    public void select()
    {
        if(!board.isEmpty(sx,sy)){
            boolean white = board.getPiece(sx,sy).isWhite();
            if(white){
                if(turn%2==0 || turn == 0 )
                    this.selectedPiece = board.getPiece(sx,sy);
            }else{
                 if(turn%2!=0 && turn!=0)
                    this.selectedPiece = board.getPiece(sx,sy);
            }
    
            System.out.printf("selecionou");
        }
    }
    
    public void movePiece(int tx,int ty)
    {   
        int pre_x = selectedPiece.x;
        int pre_y = selectedPiece.y;


        if(tx == selectedPiece.getX() && ty == selectedPiece.getY())
            return;
        if(!selectedPiece.canMove(tx,ty) && !selectedPiece.canEat(tx,ty))
            return;


        
      
        if(this.selectedPiece.isWhite())
        {
            if(this.turn%2==0){
                this.selectedPiece.moveTo(tx,ty);
            }
        }
        else{
            if(this.turn%2!=0){
                this.selectedPiece.moveTo(tx,ty);
            }
        }
        if(isCheck(selectedPiece.white)){

            System.out.printf("\n\n !! Movimento Inválido, você esta em cheque !! \n\n");
            this.selectedPiece.moveTo(pre_x,pre_y);
            return;
        }   
        this.turn+=1;
        isCheck(!selectedPiece.white);
    }


}
