package Checkers.MainClasses;

import java.util.ArrayList;

public abstract class Piece {

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


    public abstract ArrayList<Position> allDestinations(Board checkers, Position p);


}
