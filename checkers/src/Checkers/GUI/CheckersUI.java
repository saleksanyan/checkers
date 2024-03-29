package Checkers.GUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Checkers.MainClasses.*;
import java.util.ArrayList;
import Checkers.GameBoard.GameBody;

/**
 * The class CheckersUI extends JFrame. It is for the interface of checkers.
 * @author Syuzanna Aleksanyan
 * @author Mane Adamyan
 * @author Meri Barseghyan
 */
//a few methods were taken from chess

public class CheckersUI extends JFrame implements ActionListener {
    /**
     * A JPanel that shows the board of the ongoing game.
     */
    private JPanel boardPanel = new JPanel();

    /**
     * A JPanel window that is for the whole page  and which contains the JPanel boardPanel.
     */
    private JPanel windowPanel = new JPanel();

    /**
     * An array of buttons for thepieces on the board.
     */
    private BoardSquare[][] buttons = new BoardSquare[8][8];

    /**
     * The board that indicates the ongoing game.
     */
    private Board game;

    /**
     * The JButton for indicating the start of the game.
     */
    private JButton play = new JButton();

    /**
     * The JButton for indicating the end of the game.
     */
    private JButton end = new JButton();

    /**
     * No arg constructor for the class CheckersUI. It gives properties to buttons and panels of the class and
     * arranges the piece on the board.
     */
    ArrayList<Position> reachablePosition = new ArrayList<Position>();
    ArrayList<Position> list = new ArrayList<Position>(2);


    private boolean gameOver = false;

    public CheckersUI() {
        super("Checkers");
        game = new Board();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        windowPanel.setSize(900, 900);
        windowPanel.setLayout(new FlowLayout());
        windowPanel.setBackground(Color.DARK_GRAY);


        play.setBackground(Color.PINK);
        play.setForeground(Color.DARK_GRAY);
        play.setText("Let's play");
        play.setPreferredSize( new Dimension(500,100));
        play.addActionListener(this);
        play.setFont(new Font("Arial", Font.BOLD, 50));

        end.setBackground(Color.DARK_GRAY);
        end.setForeground(Color.WHITE);
        end.setText("End");
        end.setVisible(false);
        end.setPreferredSize( new Dimension(500,100));
        end.setFont(new Font("Arial", Font.BOLD, 50));

        boardPanel.setLayout(new GridLayout(8, 8));
        boardPanel.setPreferredSize( new Dimension(800,800));
        boardPanel.setVisible(false);



        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    buttons[i][j] = new BoardSquare(true, i, j);
                } else {
                    buttons[i][j] = new BoardSquare(false, i, j);
                }


                buttons[i][j].setPreferredSize(new Dimension(100,100));
                buttons[i][j].addActionListener(this);
                boardPanel.add(buttons[i][j]);

                Piece piece = game.getPieceAt(Position.generateFromRankAndFile(i, j));
                if (piece != null) {
                    String s = piece.toString();
                    buttons[i][j].setPiece(s);
                }

            }
        }

        windowPanel.add(end);
        windowPanel.add(play);
        windowPanel.add(boardPanel, BorderLayout.CENTER);
        add(windowPanel, BorderLayout.CENTER);


    }

    /**
     * A method that performs an action for each button.
     * @param e indicates the button on which the action should be performed.
     */

    public void actionPerformed (ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if(button.getText().equals("Let's play")){
            button.setVisible(false);
            boardPanel.setVisible(true);
        }

        else{
            BoardSquare boardSquare = (BoardSquare) button;
            boardClicked(boardSquare.getCoordinates());
        }
    }

    /**
     * A method from the buttons on the board. It highlights the reachable position for the piece on that button
     * and performs move(if possible) for two consecutive clicked buttons.
     * @param coordinates an array of int that contains the coordinates for the given button.
     */


    private void boardClicked(int[] coordinates) {

        Position position = Position.generateFromRankAndFile(coordinates[0], coordinates[1]);

        ArrayList<Position> highlights = null;
        if (position != null)
            highlights = game.reachableFrom(position);


        for (int i = 0; i < Board.BOARD_RANKS; i++) {

            for (int j = 0; j < Board.BOARD_FILES; j++) {
                boolean isHighlighted = false;


                for (int k = 0; highlights != null && k < highlights.size(); k++)
                    if (highlights.get(k).getRank() == i
                            && highlights.get(k).getFile() == j) {
                        isHighlighted = true;
                        break;
                    }


                if (game.isEmpty(position) || !game.getTurn().equals(game.getPieceAt(position).getPieceColor())) {
                    buttons[i][j].setHighlight(false);
                } else {
                    buttons[i][j].setHighlight(isHighlighted);
                }

            }
        }


        if (list.size() == 0 && position != null && game.getPieceAt(position) != null
                && game.getPieceAt(position).getPieceColor().equals(game.getTurn())) {
            reachablePosition = game.reachableFrom(position);
            list.add(position);
        }


        if (reachablePosition != null && position != null &&  list.indexOf(position) != 0) {
            for (int i = 0; i < reachablePosition.size(); i++) {
                System.out.println("Reachable positions are");
                System.out.println(reachablePosition.get(i).getRank() + " "
                        + reachablePosition.get(i).getFile());
                if (position.equalsTo(reachablePosition.get(i))) {

                    list.add(position);
                    System.out.println("Position that is equal" + position.getRank() + " " + position.getFile());
                }
            }

            if (list.size() != 2 && list.size() != 3) {
                list.clear();
            }
        }


        if (list.size() == 2 || list.size() == 3) {

            Move m = new Move(list.get(0), list.get(1));

            if(game.performMove(m))
            {
                Piece[][] board = game.getBoard();

                for(int i = 0; i < board.length; i++){
                    for(int j = 0; j < board[i].length; j++){
                        if(board[i][j] == null) {
                            buttons[i][j].setPiece();
                        }
                        else{
                            buttons[i][j].setPiece(board[i][j].toString());
                        }
                    }
                }
            }


            game.incNumberOfMoves();
            list.clear();
        }

        if(GameBody.end(game) == 1){
            end.setText("White won! (*≧▽≦)");
            end.setVisible(true);
            gameOver = true;
        }else if(GameBody.end(game) == -1){
            end.setText("Black won! (*≧▽≦)");
            end.setVisible(true);
            gameOver = true;
        }
        if(gameOver){
            GameBody.playMusic();
            return;
        }
    }

}