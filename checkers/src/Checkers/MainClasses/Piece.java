package Checkers.MainClasses;
//a few methods were taken from chess
/**
 *
 * @author Syuzanna Aleksanyan
 * @author Mane Adamyan
 * @author Meri Barseghyan
 */

import java.util.ArrayList;

public abstract class Piece {
    /**
     * An enum type from the class Board indicating the color of the piece on the board.
     */
    private Board.PieceColor color;

    /**
     * No ard constructor that by default initializes the color of the piece White.
     */
    public Piece(){
        color = Board.PieceColor.WHITE;
    }

    /**
     * Constructor that receives the value of the color and initializes the color
     * of the piece according to that.
     * @param color an enum type parameter for the color of the piece.
     */
    public Piece(Board.PieceColor color){
        this.color = color;
    }

    /**
     * An accessor method for the class Piece.
     * @return the color of the calling abject piece.
     */
    public Board.PieceColor getPieceColor(){
        return color;
    }

    /**
     * An abstract method of the abstract class Piece.
     * @param checkers indicates the board of the ongoing game.
     * @param p gives the position of the Piece.
     * @return The Position ArrayList that indicates all reachable positions of the calling abject.
     */
    public abstract ArrayList<Position> allDestinations(Board checkers, Position p);


}