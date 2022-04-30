
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



    public ArrayList<Position> allDestinations(Board chess, Position p) {
        return Pawn.reachablePositions(chess, p);
    }



    public static ArrayList<Position> reachablePositions(Board checkers, Position p){
        if(!checkers.isEmpty(p)){
            int[] rankOfWhite = {-1, -1};
            int[] fileOfWhiteAndBlack = {1, -1};
            int[] rankOfBlack = {1, 1};

            ArrayList <Position> positions = new ArrayList<>(2);
            if (checkers.getTurn().equals(Board.PieceColor.WHITE)){
                Position newPosition;
                Position newPosition2;
                Position positionAfterNewPosition;
                Position positionAfterNewPosition2;
                for (int i = 0; i < 2; i++) {
                    int rank = p.getRank() + rankOfWhite[i];
                    int file = p.getFile() + fileOfWhiteAndBlack[i];
                    int rank2 = p.getRank() + rankOfBlack[i];
                    int file2 = p.getFile() + fileOfWhiteAndBlack[i];
                    newPosition = new Position(rank, file);
                    newPosition2 = new Position(rank2, file2);
                    positionAfterNewPosition = new Position(rank + rankOfWhite[i], file + fileOfWhiteAndBlack[i]);

                    if(checkers.isEmpty(newPosition)){
                        positions.add(newPosition);
                    }else{
                        while(checkers.getPieceAt(newPosition) != null
                                && (checkers.getPieceAt(newPosition).getPieceColor()
                                != (checkers.getPieceAt(p)).getPieceColor())){
                            int rankOfNewP = positionAfterNewPosition.getRank() + rankOfWhite[i];
                            int fileOfNewP = positionAfterNewPosition.getFile() + fileOfWhiteAndBlack[i];
                            int rank1 = newPosition.getRank() + (rankOfWhite[i] * 2);
                            int file1 = newPosition.getFile() + (fileOfWhiteAndBlack[i] * 2);
                            int rank3 = newPosition2.getRank() + (rankOfBlack[i] * 2);
                            int file3 = newPosition2.getFile() + (fileOfWhiteAndBlack[i] * 2);
                            if(rankOfNewP > 7 || fileOfNewP > 7 || rankOfNewP < 0 || fileOfNewP < 0){
                                break;
                            }
                            if(rank1 > 7 || file1 > 7 || rank1 < 0 || file1 < 0){
                                break;
                            }
                            if(rank3 > 7 || file3 > 7 || rank3 < 0 || file3 < 0){
                                break;
                            }
                            newPosition = new Position(rankOfNewP, fileOfNewP);
                            positionAfterNewPosition = new Position(rank1, file1);
                            if(checkers.isEmpty(positionAfterNewPosition))
                               positions.add(positionAfterNewPosition);
                            else
                                break;

                        }
                    }

                }
            }
            if (checkers.getTurn().equals(Board.PieceColor.BLACK)){
                Position newPosition;
                Position positionAfterNewPosition;
                for (int i = 0; i < 2; i++) {
                    int rank = p.getRank() + rankOfBlack[i];
                    int file = p.getFile() + fileOfWhiteAndBlack[i];
                    newPosition = new Position(rank, file);
                    positionAfterNewPosition = new Position(rank + rankOfBlack[i], file + fileOfWhiteAndBlack[i]);
                    if(checkers.isEmpty(newPosition)){
                        positions.add(newPosition);
                    }else{
                        while(checkers.getPieceAt(newPosition) != null
                                && (checkers.getPieceAt(newPosition).getPieceColor()
                                != (checkers.getPieceAt(p)).getPieceColor())){
                            System.out.println(checkers.getPieceAt(newPosition) != null
                                    && (checkers.getPieceAt(newPosition) == (checkers.getPieceAt(p))));
                            int rankOfNewP = positionAfterNewPosition.getRank() + rankOfBlack[i];
                            int fileOfNewP = positionAfterNewPosition.getFile() + fileOfWhiteAndBlack[i];
                            int rank1 = newPosition.getRank() + (rankOfWhite[i] * 2);
                            int file1 = newPosition.getFile() + (fileOfWhiteAndBlack[i] * 2);
                            if(rankOfNewP > 7 || fileOfNewP > 7 || rankOfNewP < 0 || fileOfNewP < 0){
                                break;
                            }
                            if(rank1 > 7 || file1 > 7 || rank1 < 0 || file1 < 0){
                                break;
                            }
                            newPosition = new Position(rankOfNewP, fileOfNewP);
                            positionAfterNewPosition = new Position(rank1, file1);
                            if(checkers.isEmpty(positionAfterNewPosition))
                                positions.add(positionAfterNewPosition);
                            else
                                break;

                        }
                    }

                }
            }
            return positions;
        }
        return null;
    }




}
