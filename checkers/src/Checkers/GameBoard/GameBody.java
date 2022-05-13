package Checkers.GameBoard;
import Checkers.MainClasses.*;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//a few methods were taken from chess

/**
 * A class for representing the game.
 * @author Syuzanna Aleksanyan
 * @author Mane Adamyan
 * @author Meri Barseghyan
 */

public class GameBody {

    private Board game;

    /**
     * a method that represent the whole game
     */
    public void play(){
        Scanner sc = new Scanner(System.in);
        String inputLine;

        game = new Board();

        print();

        while (true) {
            if (game.getTurn() == Board.PieceColor.WHITE)
                System.out.println("White's move: ");
            else
                System.out.println("Black's move: ");

            inputLine = sc.nextLine();
            ArrayList<String> input = new ArrayList<>(List.of(inputLine.split(" ")));

            Position p1, p2;

            if (input.size() >= 1) {
                p1 = Position.generateFromString(input.get(0));
                ArrayList<Position> positions = game.reachableFrom(p1);
                int count = 0;
                if (p1 == null || game.getPieceAt(p1) == null) {
                    System.out.println("Invalid position. Please try again.");
                    continue;
                }
                for (String s : input) {
                    for (Position position : positions) {
                        if (Position.generateFromString(s).toString().equals(position.toString())) {
                            count++;
                            break;
                        }
                    }
                }
                if (count != input.size() - 1) {
                    System.out.println("Invalid move quantity. Please try again.");
                    continue;
                }

                if (game.getPieceAt(p1).getPieceColor() != game.getTurn()) {
                    System.out.println("That piece belongs to the opponent.");
                    continue;
                }
                if (input.size() == 1) {
                    print(p1);
                } else if (input.size() > 1) {

                    for(int i=1; i<input.size(); i++) {
                        p1 = Position.generateFromString(input.get(i-1));
                        p2 = Position.generateFromString(input.get(i));

                        if (p1 == null || p2 == null) {
                            System.out.println(p1 + " " + p2 + " move is invalid. Please try again.");
                            break;
                        }

                        Move m = new Move(p1, p2);
                        if (!game.performMove(m)) {
                            System.out.println("Invalid move. Please try again.");
                            break;
                        }
                    }
                    game.incNumberOfMoves();
                    print();
                    if(end(game) == 1){
                        System.out.println("White won! (*≧▽≦)");
                        playMusic();
                        return;
                    }else if(end(game) == -1){
                        System.out.println("Black won! (*≧▽≦)");
                        playMusic();
                        return;
                    }
                }
            }
        }
    }

    /**
     * prints the board with highlighted origin and reachable positions
     * @param origin Position object, current position
     */
    public void print(Position origin) {
        ArrayList<Position> reachableSquares = game.reachableFrom(origin);

        for (int i = 0; i < Board.BOARD_RANKS; i++) {
            System.out.print((Board.BOARD_RANKS - i) + " ");

            for (int j = 0; j < Board.BOARD_FILES; j++) {
                boolean isHighlighted = false;

                if (origin != null &&
                        origin.getRank() == i && origin.getFile() == j
                )
                    isHighlighted = true;

                for (int k = 0; reachableSquares != null &&
                        k < reachableSquares.size(); k++)
                    if (reachableSquares.get(k).getRank() == i &&
                            reachableSquares.get(k).getFile() == j)
                    {
                        isHighlighted = true;
                        break;
                    }

                if (isHighlighted)
                    System.out.print("\u001b[31m");

                System.out.print("[");

                Position current = Position.generateFromRankAndFile(i, j);
                if (game.isEmpty(current))
                    System.out.print(" ");
                else
                    System.out.print(game.getPieceAt(current));

                System.out.print("]");

                if (isHighlighted)
                    System.out.print("\u001b[0m");
            }
            System.out.println();
        }
        System.out.println("   A  B  C  D  E  F  G  H ");
        System.out.println();
    }

    /**
     * prints the standard board
     */
    public void print() {
        print(null);
    }

    /**
     * plays an audio file
     */
    public static void playMusic(){
        File file = new File("music.wav");
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * checks if the game is over and if blacks or whites won the game
     * @param board current board
     * @return int, 0 if the game is not over, 1 if white won, -1 if black won
     */
    public static int end(Board board){
        int blackCount = 0;
        int whiteCount = 0;
        Position p;
        for (int i = 0; i < Board.BOARD_RANKS; i++) {
            for (int j = 0; j < Board.BOARD_FILES; j++) {
                p = new Position(i,j);
                if(board.isEmpty(p)){
                    continue;
                }
                if(board.getPieceAt(p).getPieceColor() == Board.PieceColor.WHITE){
                    whiteCount++;
                }else if(board.getPieceAt(p).getPieceColor() == Board.PieceColor.BLACK){
                    blackCount++;
                }
            }
        }
        if(whiteCount == 0){
            return -1;
        }
        else if(blackCount == 0){
            return 1;
        }
        return 0;
    }

}
