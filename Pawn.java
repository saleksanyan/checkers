
package Checkers.MainClasses;
import java.util.ArrayList;

public class Pawn extends Piece{


    public Pawn() { this(Board.PieceColor.WHITE);}


    public Pawn(Board.PieceColor pieceColor){super(pieceColor);}



    public String toString(){
        if(this.getPieceColor().equals(Board.PieceColor.WHITE)){
            return "P";
        }else{
            return "p";
        }
    }



    public ArrayList<Position> allDestinations(Board checkers, Position p) {
        return reachablePositions(checkers, p);
    }



    public static ArrayList<Position> reachablePositions(Board checkers, Position p) {
//        ArrayList<Position> positions = new ArrayList<>(2);
//        positions.addAll(canMove(checkers, p));
//        jumpPositions(positions, checkers, p);
//        return positions;
        ArrayList<Position> positions = new ArrayList<Position>(2);
        positions.addAll(canMove(checkers, p));
        ArrayList<Position> newPositions = new ArrayList<>();
        //jumpPositions(positions, p, checkers);
        jumpPositions(positions, p, checkers, newPositions);
        return positions;
    }

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

    public static ArrayList<Position> eatable(Board checkers, Position p){
//
//        ArrayList<Position> positions = new ArrayList<>(2);
//        int[] rank = {1, -1, 1, -1};
//        int[] file = {1, 1, -1, -1};
//        int rank1, file1;
//        Position newPosition;
//        for (int i = 0; i < 4; i++) {
//            rank1 = p.getRank() + rank[i];
//            file1 = p.getFile() + file[i];
//            if(rank1 >= 0 && rank1 <= 7 && file1 >= 0 && file1 <= 7) {
//                newPosition = new Position(rank1, file1);
//
//                if (!checkers.isEmpty(newPosition)){
//                    if(checkers.getPieceAt(newPosition).getPieceColor() != checkers.getTurn()) {
//                        positions.add(newPosition);
//                    }
//                }
//            }
//        }

//        return positions;

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

//    public static void jumpPositions(ArrayList<Position> positions, Position position, Board checkers){
//        ArrayList<Position> eatablePositions = eatable(checkers, position);
//        Position pos;
//
//        for (Position p : eatablePositions) {
//            int r = p.getRank() - position.getRank();
//            int f = p.getFile() - position.getFile();
//            int rank = p.getRank() + (r/(Math. abs(r)));
//            int file = p.getFile() + (f/(Math. abs(f)));
////            int rank = p.getRank() + (p.getRank() - position.getRank());
////            int file = p.getFile() + (p.getFile() - position.getFile());
//            if(rank >= 0 && rank <= 7 && file >= 0 && file <= 7) {
//                pos = new Position(rank, file);
//                boolean exists = false;
//                for(Position p1: positions){
//                    if(pos.equalsTo(p1)){
//                        exists = true;
//                        break;
//                    }
//                }
//                if(exists)
//                    continue;
//
//                if (!checkers.isEmpty(pos)) {
//                    return;
//                }
//                positions.add(pos);
//                jumpPositions(positions, pos, checkers);
//            }
//        }
//    }
//
//
//
    public static String BecomeQueen(Board checkers, Position position) {
        if(checkers.getTurn().equals(Board.PieceColor.WHITE) && position.getRank() == 0){
            return "White";
        }
        else if(checkers.getTurn().equals(Board.PieceColor.BLACK) && position.getRank() == 7)
            return "Black";
        return "^_^";
    }
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

