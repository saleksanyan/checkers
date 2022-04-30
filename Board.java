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
        this("p.p.p.p..p.p.p.pp.p.p.p..................P.P.P.PP.P.P.P..P.P.P.P.", PieceColor.WHITE);
    }

    public Board(String pieceName, PieceColor color){
        this.board = new Piece[BOARD_RANKS][BOARD_FILES];
        for (int i = 0, l = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; l++, j++) {
                switch (pieceName.charAt(l)) {
                    case 'P', 'Q' -> {
                        board[i][j] = new Pawn(PieceColor.WHITE);
                    }
                    case 'p', 'q' -> {
                        board[i][j] = new Pawn(PieceColor.BLACK);
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



    public Piece getPieceAt(Position p) {
        return this.board[p.getRank()][p.getFile()];
    }


    public ArrayList<Position> reachableFrom(Position origin) {
        if (origin == null || this.isEmpty(origin))
            return null;
        return this.getPieceAt(origin).allDestinations(this, origin);
    }


   public boolean performMove(Move m) {
        Position origin = m.getOrigin();
        Position destination = m.getDestination();

       ArrayList<Position> reachable = this.reachableFrom(origin);

        for (Position position : reachable)
            if (destination.getRank() == position.getRank()
                    && destination.getFile() == position.getFile()) {
                this.board[destination.getRank()][destination.getFile()] =
                        this.board[origin.getRank()][origin.getFile()];
                this.board[origin.getRank()][origin.getFile()] = null;
                this.numberOfMoves++;
                return true;
            }

        return false;
    }

}
