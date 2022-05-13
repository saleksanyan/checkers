package Checkers.MainClasses;
import java.util.ArrayList;
//a few methods were taken from chess

/**
 * A mutable class for creating the board of the game.
 * @author Syuzanna Aleksanyan
 * @author Mane Adamyan
 * @author Meri Barseghyan
 */


public class Board {


    public enum PieceColor {WHITE, BLACK}
    public static final int BOARD_RANKS = 8;

    public static final int BOARD_FILES = 8;
    // an instance variable for the board and pieces on it
    private Piece[][] board;
    // an instance variable counter for the current number of moves in the game
    private int numberOfMoves;

    /**
     * a no arg constructor that prints the initial board
     */
    public Board() {

        this("p.p.p.p..p.p.p.pp.p.p.p..................P.P.P.PP.P.P.P..P.P.P.P.");
    }

    /**
     *a constructor that takes a string as a parameter and construct a board from it
     * @param pieceName string, representation of the board
     */
    public Board(String pieceName){
        this.board = new Piece[BOARD_RANKS][BOARD_FILES];
        for (int i = 0, l = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; l++, j++) {
                switch (pieceName.charAt(l)) {
                    case 'P'-> {
                        board[i][j] = new Pawn(PieceColor.WHITE);
                    }
                    case 'Q'-> {
                        board[i][j] = new Queen(PieceColor.WHITE);
                    }
                    case 'p' -> {
                        board[i][j] = new Pawn(PieceColor.BLACK);
                    }
                    case 'q' -> {
                        board[i][j] = new Queen(PieceColor.BLACK);
                    }

                    case '.' -> board[i][j] = null;


                }

            }

        }
    }

    /**
     *increments the value of NumberOfMoves
     */
    public void incNumberOfMoves(){
        this.numberOfMoves++;
    }

    /**
     *calculates and returns the turn of player
     * @return PieceColor, the color of the player
     */
    public PieceColor getTurn() {
        if (this.numberOfMoves % 2 == 0)
            return PieceColor.WHITE;
        else
            return PieceColor.BLACK;
    }



    /**
     *checks and returns if the given position is empty
     * @param p Position object, which we want to check
     * @return if the given position is empty
     */
    public boolean isEmpty(Position p) {
        return this.board[p.getRank()][p.getFile()] == null;
    }

    /**
     *gets the peace at the given position
     * @param p Position object, which piece we want to get
     * @return a piece of the given position
     */

    public  Piece getPieceAt(Position p) {
        return this.board[p.getRank()][p.getFile()];
    }

    /**
     *finds and returns all destinations of the given position
     * @param origin position, which we want to get destinations for
     * @return arrayList of reachable positions
     */

    public ArrayList<Position> reachableFrom(Position origin) {
        if (origin == null || this.isEmpty(origin))
            return null;
        return this.getPieceAt(origin).allDestinations(this, origin);
    }

    /**
     *checkes and performs the given move
     * @param m Moe object, which we want to perform
     * @return true if the move is performed, false if it is not
     */

    public boolean performMove(Move m) {
        Position o = m.getOrigin();
        Position d = m.getDestination();

        if(o == null || d == null) return false;
        if(this.getPieceAt(o) == null) return false;
        if (this.getPieceAt(o).getPieceColor() != this.getTurn())
            return false;

        ArrayList<Position> reachable = this.reachableFrom(o);
        if((Math.abs(o.getRank() - d.getRank()) > 2 || Math.abs(o.getFile() - d.getFile()) > 2)
                && (!this.getPieceAt(o).toString().equals("Q") && !this.getPieceAt(o).toString().equals("q"))) {
            return false;
        }
        for (int i = 0; i < reachable.size(); i++)
            if (d.getRank() == reachable.get(i).getRank()
                    && d.getFile() == reachable.get(i).getFile()) {

                if(Pawn.BecomeQueen(this, d).equals("White")){
                    this.board[o.getRank()][o.getFile()] = new Queen(PieceColor.WHITE);
                }
                else if(Pawn.BecomeQueen(this, d).equals("Black")) {
                    this.board[o.getRank()][o.getFile()] = new Queen(PieceColor.BLACK);
                }
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
                return true;
            }

        return false;
    }

    public Piece[][] getBoard() {
        Piece[][] board = new Piece[this.board.length][this.board[0].length];

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                board[i][j] = this.board[i][j]; // IMPLEMENT CLONE
            }
        }
        return board;
    }
}
