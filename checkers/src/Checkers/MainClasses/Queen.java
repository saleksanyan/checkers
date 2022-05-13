package Checkers.MainClasses;
import java.util.ArrayList;
/**
 * The class Queen extends the abstract class Piece. It indicates the Queen piece on the board.
 * @author Syuzanna Aleksanyan
 * @author Mane Adamyan
 * @author Meri Barseghyan
 */
//a few methods were taken from chess

public class Queen extends Piece {

    /**
     * The no arg constructor that by calling the no arg constructor of the parent object(Piece) by default initializes
     * the color of the queen white.
     */
    public Queen() {
        super();
    }

    /**
     * The  constructor that by calling the no arg constructor of the parent object(Piece)  initializes
     * the color of the queen according to the given color.
     * @param color an enum type paremeter that indicates the color of the queen.
     */
    public Queen(Board.PieceColor color) {
        super(color);
    }

    /**
     * An overriden to string method for the class Queen that gives the letter according to its color.
     * @return Returns "Q" if the color of the calling object queen is white and "q" if it is black.
     */
    public String toString() {
        if (this.getPieceColor() == Board.PieceColor.WHITE)
            return "Q";
        else
            return "q";
    }

    /**
     * Calls the metheod reachablePosition for the calling object queen.
     * @param checkers indicates the board of the ongoing game.
     * @param p gives the position of the Queen.
     * @return the method reachablePositions which in turn returns the Array List of all reachable positions for
     * the calling object queen.
     */
    public ArrayList<Position> allDestinations(Board checkers, Position p) {
        return reachablePositions(checkers, p);
    }

    /**
     * Gives the reachable positions of the Queen object from the given position.
     * @param checkers indicates the board of the ongoing game.
     * @param p gives the position of the Queen.
     * @return Returns the Array List of reachable position.
     */
    public static ArrayList<Position> reachablePositions(Board checkers, Position p) {
        //Accessed by Queens, does not have to be public
        ArrayList<Position> positions = new ArrayList<Position>(2);
        positions.addAll(canMove(checkers, p));
        ArrayList<Position> newPositions = new ArrayList<>();
        jumpPositions(positions, p, checkers, newPositions);
        return positions;
    }


    /**
     * generates and returns the positions which are reachable for a move, without taking opponent's piece
     * @param checkers current board
     * @param p current position
     * @return the positions which are reachable for a move, without taking opponent's piece
     */
    public static ArrayList<Position> canMove(Board checkers, Position p) {
        ArrayList<Position> result = new ArrayList<>(0);

        int[] rankOffsets = {1, -1, 1, -1};
        int[] fileOffsets = {1, 1, -1, -1};


        for (int d = 0; d < 4; d++) {
            int i = p.getRank() + rankOffsets[d];
            int j = p.getFile() + fileOffsets[d];
            while (i >= 0 && i < Board.BOARD_RANKS
                    && j >= 0 && j < Board.BOARD_FILES) {
                Position current = Position.generateFromRankAndFile(i, j);

                if (checkers.isEmpty(current))
                    result.add(current);
                else if (checkers.getPieceAt(current).getPieceColor() == checkers.getTurn()) break;
                i += rankOffsets[d];
                j += fileOffsets[d];
            }
        }

        return result;
    }
    /**
     * generates and returns positions that are eatable for the piece
     * @param checkers current board
     * @param p current position
     * @return positions that are eatable for the piece
     */

    public static ArrayList<Position> eatable(Board checkers, Position p) {

        ArrayList<Position> positions = new ArrayList<>(2);
        int[] rank = {1, -1, 1, -1};
        int[] file = {1, 1, -1, -1};
        int rank1, file1;
        Position newPosition;
        for (int i = 0; i < 4; i++) {
            rank1 = p.getRank() + rank[i];
            file1 = p.getFile() + file[i];

            while (rank1 >= 0 && rank1 < 7
                    && file1 >= 0 && file1 < 7) {
                newPosition = new Position(rank1, file1);

                if (!checkers.isEmpty(newPosition)){
                    if(checkers.getPieceAt(newPosition).getPieceColor() != checkers.getTurn()) {
                        positions.add(newPosition);
                    }else{
                        break;
                    }
                }
                rank1 += rank[i];
                file1 += file[i];
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
}