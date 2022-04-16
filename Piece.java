public class Piece {

    private Board.PieceColor color;

    public Piece(){
        color = Board.PieceColor.WHITE;
    }

    public Piece(Board.PieceColor color){
        this.color = color;
    }
    public Board.PieceColor getPieceColor(){
        return color;
    }


    public Position[] allDestinations(Board chess, Position p){
        return null;
    }


}
