
package Checkers.MainClasses;
import java.util.ArrayList;

/**
 * A class for representing a pawn on checkers board.
 * @author Syuzanna Aleksanyan
 * @author Mane Adamyan
 * @author Meri Barseghyan
 */
//a few methods were taken from chess

public class Pawn extends Piece{

    /**
     * creates white pawn
     */
    public Pawn() { this(Board.PieceColor.WHITE);}

    /**
     * sets given piece color as a color of the piece
     * @param pieceColor the color of the pawn
     */
    public Pawn(Board.PieceColor pieceColor){super(pieceColor);}

    /**
     * generates and returns the string representation of class Pawn
     * @return String, representation of class Pawn
     */
    public String toString(){
        if(this.getPieceColor().equals(Board.PieceColor.WHITE)){
            return "P";
        }else{
            return "p";
        }
    }

    /**
     * no static methods that returns all reachable positions
     * @param checkers current board
     * @param p current positions
     * @return arrayList, reachable positions
     */


    public ArrayList<Position> allDestinations(Board checkers, Position p) {
        return reachablePositions(checkers, p);
    }


    /**
     * static method returns all reachable positions
     * @param checkers current board
     * @param p current position
     * @return arrayList, all reachable positions
     */
    public static ArrayList<Position> reachablePositions(Board checkers, Position p) {

        ArrayList<Position> positions = new ArrayList<Position>(2);
        positions.addAll(canMove(checkers, p));
        ArrayList<Position> newPositions = new ArrayList<>();
        ArrayList<Position> newPositions1 = new ArrayList<>();
        jumpPositions(positions, p, checkers, newPositions);
        jumpPositions(newPositions1, p, checkers, newPositions);
        return positions;

    }

    /**
     * generates and returns the positions which are reachable for a move, without taking opponent's piece
     * @param checkers current board
     * @param p current position
     * @return the positions which are reachable for a move, without taking opponent's piece
     */
    public static ArrayList<Position> canMove(Board checkers, Position p){
        ArrayList<Position> positions = new ArrayList<>(2);
        int[] rankOfWhite = {-1, -1};
        int[] fileOfWhiteAndBlack = {1, -1};
        int[] rankOfBlack = {1, 1};
        int rank = 0, file;
        Position newPosition;
            for (int i = 0; i < 2; i++) {
                if(checkers.getTurn().equals(Board.PieceColor.WHITE)){
                    rank = p.getRank() + rankOfWhite[i];
                }else if(checkers.getTurn().equals(Board.PieceColor.BLACK)){
                    rank = p.getRank() + rankOfBlack[i];
                }
                file = p.getFile() + fileOfWhiteAndBlack[i];
                if(rank >= 0 && rank <= 7 && file >= 0 && file <= 7) {
                    newPosition = new Position(rank, file);
                    if (checkers.isEmpty(newPosition)) {
                        positions.add(newPosition);
                    }
                }
            }

        return positions;

    }

    /**
     * generates and returns positions that are eatable for the piece
     * @param checkers current board
     * @param p current position
     * @return positions that are eatable for the piece
     */
    public static ArrayList<Position> eatable(Board checkers, Position p){

        ArrayList<Position> positions = new ArrayList<>(2);
        int[] rank = {1, -1, 1, -1};
        int[] file = {1, 1, -1, -1};
        int rank1, file1;
        Position newPosition;
        for (int i = 0; i < 4; i++) {
            rank1 = p.getRank() + rank[i];
            file1 = p.getFile() + file[i];

            if (rank1 >= 0 && rank1 < 7
                    && file1 >= 0 && file1 < 7) {
                newPosition = new Position(rank1, file1);

                if (!checkers.isEmpty(newPosition)){
                    if(checkers.getPieceAt(newPosition).getPieceColor() != checkers.getTurn()) {
                        positions.add(newPosition);
                    }
                }
            }
        }
        return positions;


    }


    /**
     * generates and returns positions that can be performed from the given position with taking opponent's piece
     * @param positions reachable position arrayList
     * @param position current position
     * @param board current board
     * @param newPositions
     */


    public static void jumpPositions(ArrayList<Position> positions, Position position, Board board,
                                     ArrayList<Position> newPositions) {
        ArrayList<Position> eatablePositions = eatable(board, position);

        Position pos;
        for (Position p : eatablePositions) {
            int r = p.getRank() - position.getRank();
            int f = p.getFile() - position.getFile();
            int rank = p.getRank() + (r/(Math. abs(r)));
            int file = p.getFile() + (f/(Math. abs(f)));
            if (rank >= 0 && rank <= 7 && file >= 0 && file <= 7) {
                pos = new Position(rank, file);

                if (!board.isEmpty(pos)) {
                    continue;
                }

                for (Position p1 : newPositions) {
                    if (pos.equalsTo(p1)) {
                        return;
                    }
                }

                newPositions.add(pos);
                positions.add(pos);
                jumpPositions(positions, pos, board, newPositions);
            }
        }
    }

    /**
     *
     * @param checkers current board
     * @param position current position
     * @return String, "Black" if black's pawn should become the queen, "White" if white 's pawn should become the queen
     * and "^_^" if there is no piece that should become a queen
     */

    public static String BecomeQueen(Board checkers, Position position) {
        if(checkers.getTurn().equals(Board.PieceColor.WHITE) && position.getRank() == 0){
            return "White";
        }
        else if(checkers.getTurn().equals(Board.PieceColor.BLACK) && position.getRank() == 7)
            return "Black";
        return "^_^";
    }

}

