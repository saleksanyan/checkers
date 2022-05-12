package Checkers.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * A mutable class for creating buttons for the GUI
 * @author Syuzanna Aleksanyan
 * @author Mane Adamyan
 * @author Meri Barseghyan
 */

public class BoardSquare extends JButton {
    private final String LIGHT_COLOR = "White";
    private final String DARK_COLOR = "Black";

    private int xCoordinate;
    private int yCoordinate;
    private String color;


    /**
     * a constructor that sets coordinates and the color of board square
     * @param flag checks the color
     * @param xCoordinate int representation for xCoordinate
     * @param yCoordinate int representation for yCoordinate
     */
    public BoardSquare(boolean flag, int xCoordinate, int yCoordinate ){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        if(flag){
            color = LIGHT_COLOR;
            setBackground(Color.GRAY);
        }else{
            color = DARK_COLOR;
            setBackground(Color.WHITE);
        }

    }

    /**
     * creates and returns an array of coordinates
     * @return the coordinates of the board square
     */
    public int[] getCoordinates(){
        return new int[]{this.xCoordinate, this.yCoordinate};
    }

    /**
     * checks and puts the piece icon on the right square
     * @param letter string representation of the piece
     */
    public void setPiece(String letter) {
        ImageIcon icon;
        switch(letter){
            case "Q":
                icon = new ImageIcon("figures/WhiteQ.png");
                setIcon(icon);
                break;
            case "P":
                icon = new ImageIcon("figures/WhiteP.png");
                setIcon(icon);
                break;


            case "q":
                icon = new ImageIcon("figures/BlackQ.png");
                setIcon(icon);
                break;
            case "p":

                icon = new ImageIcon("figures/BlackP.png");
                setIcon(icon);
                break;
        }
    }

    /**
     * sets the piece icon as null
     */
    public void setPiece(){
        setIcon(null);
    }

    /**
     * if the given boolean is true the method will highlight the square
     * @param b checkes if the square should be highlighted
     */
    public void setHighlight(boolean b){
        if(b){
            this.setBackground(Color.RED);
        }else{
            if(this.color.equals("White")) this.setBackground(Color.GRAY);
            else this.setBackground(Color.WHITE);
        }
    }

}


