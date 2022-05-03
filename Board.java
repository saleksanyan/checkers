package Checkers.MainClasses;

import java.util.ArrayList;

public class Board {


    public enum PieceColor {WHITE, BLACK}
    public static final int BOARD_RANKS = 8;

    public static final int BOARD_FILES = 8;
    // an instance variable for the board and pieces on it
    private Piece[][] board;
    // an instance variable counter for the current number of moves in the game
    private int numberOfMoves;

    public Board() {
        this( "p.p.p.p..p.p.p.pk.p...P..k.P.p....k........P.P.PP.P...P..P.P.P.P", PieceColor.WHITE);
        //"p.p.p.p..p.p.p.pp.p.p.p..................P.P.P.PP.P.P.P..P.P.P.P."
    }

    public Board(String pieceName, PieceColor color){
        this.board = new Piece[BOARD_RANKS][BOARD_FILES];
        for (int i = 0, l = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; l++, j++) {
                switch (pieceName.charAt(l)) {
                    case 'P'-> {
                        board[i][j] = new Pawn(PieceColor.WHITE);
                    }
                    case 'K'-> {
                        board[i][j] = new King(PieceColor.WHITE);
                    }
                    case 'p' -> {
                        board[i][j] = new Pawn(PieceColor.BLACK);
                    }
                    case 'k' -> {
                        board[i][j] = new King(PieceColor.BLACK);
                    }

                    case '.' -> board[i][j] = null;


                }

            }

        }
    }



    public Piece[][] getBoard() {
        //returns deep copy
        Piece[][] board = new Piece[BOARD_RANKS][BOARD_FILES];
        for (int i = 0; i < BOARD_RANKS; i++)
            for (int j = 0; j < BOARD_FILES; j++)
                board[i][j] = this.board[i][j];
        return board;
    }


    public PieceColor getTurn() {
        if (this.numberOfMoves % 2 == 0)
            return PieceColor.WHITE;
        else
            return PieceColor.BLACK;
    }


    public boolean isGameOver() {
        return false;
    }



    public boolean isEmpty(Position p) {
        return this.board[p.getRank()][p.getFile()] == null;
    }



    public  Piece getPieceAt(Position p) {
        return this.board[p.getRank()][p.getFile()];
    }


    public ArrayList<Position> reachableFrom(Position origin) {
        if (origin == null || this.isEmpty(origin))
            return null;
        return this.getPieceAt(origin).allDestinations(this, origin);
    }


//   public boolean performMove(Move m) {
//        Position origin = m.getOrigin();
//        Position destination = m.getDestination();
//
//       ArrayList<Position> reachable = this.reachableFrom(origin);
//       if (this.getPieceAt(origin).getPieceColor() != this.getTurn())
//           return false;
//       for (Position position : reachable){
//            if (destination.getRank() == position.getRank()
//                    && destination.getFile() == position.getFile()) {
//                this.board[destination.getRank()][destination.getFile()] =
//                        this.board[origin.getRank()][origin.getFile()];
//                this.board[origin.getRank()][origin.getFile()] = null;
//                if(this.board[(origin.getRank() + destination.getRank()) / 2]
//                        [(origin.getFile() + destination.getFile()) / 2] !=
//                        this.board[destination.getRank()][destination.getFile()]){
//                this.board[(origin.getRank() + destination.getRank()) / 2]
//                        [(origin.getFile() + destination.getFile()) / 2] = null;
//                }
//                this.numberOfMoves++;
//                return true;
//            }
//        }return false;
//    }

    public boolean performMove(Move m) {
        Position o = m.getOrigin();
        Position d = m.getDestination();

        if (this.getPieceAt(o).getPieceColor() != this.getTurn())
            return false;

        Position[] reachable = this.reachableFrom(o).toArray(new Position[0]);

        for (int i = 0; i < reachable.length; i++)
            if (d.getRank() == reachable[i].getRank()
                    && d.getFile() == reachable[i].getFile()) {
//                 int rank = o.getRank() + (d.getRank() - o.getRank())/2;
//                 int file = o.getFile() + (d.getFile() - o.getFile())/2;
//                 System.out.println(rank);
//                 System.out.println(file);
                this.board[d.getRank()][d.getFile()] = this.board[o.getRank()][o.getFile()];
                this.board[o.getRank()][o.getFile()] = null;
                for (int j = 1; j < Math.abs(o.getRank() - d.getRank()); j++) {
                    if (o.getRank() > d.getRank() && d.getFile() > o.getFile()) {
                        this.board[d.getRank() + j][d.getFile() - j] = null;
                    }else if(o.getRank() > d.getRank() && d.getFile() < o.getFile()){
                        this.board[d.getRank() + j][d.getFile() + j] = null;
                    }else if(o.getRank() < d.getRank() && d.getFile() < o.getFile()){
                        this.board[o.getRank() + j][o.getFile() - j] = null;
                    }else if(o.getRank() < d.getRank() && d.getFile() > o.getFile()){
                        this.board[o.getRank() + j][o.getFile() + j] = null;
                    }
                }
                this.numberOfMoves++;
                return true;
            }
        return false;
    }



}
