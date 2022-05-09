package Checkers.MainClasses;

import java.util.ArrayList;

public class Queen extends Piece {
    public Queen() {
        super();
    }

    public Queen(Board.PieceColor color) {
        super(color);
    }

    public String toString() {
        if (this.getPieceColor() == Board.PieceColor.WHITE)
            return "Q";
        else
            return "q";
    }

    public ArrayList<Position> allDestinations(Board checkers, Position p) {
        return reachablePositions(checkers, p);
    }


    public static ArrayList<Position> reachablePositions(Board checkers, Position p) {
        //Accessed by Queens, does not have to be public
        ArrayList<Position> positions = new ArrayList<Position>(2);
        positions.addAll(canMove(checkers, p));
//        for(int i = 0; i < positions.size(); i++){
//            System.out.println("available position rank" + positions.get(i).getRank());
//            System.out.println("available position file" + positions.get(i).getFile());
//        }
        ArrayList<Position> newPositions = new ArrayList<>();
        jumpPositions(positions, p, checkers, newPositions);
        return positions;
    }

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

                if (!checkers.isEmpty(newPosition) &&
                        checkers.getPieceAt(newPosition).getPieceColor() != checkers.getTurn()) {
                    positions.add(newPosition);
                }
                rank1 += rank[i];
                file1 += file[i];
            }
        }
        return positions;

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
