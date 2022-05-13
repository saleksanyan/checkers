package Checkers;

import Checkers.GUI.CheckersUI;
import Checkers.GameBoard.GameBody;

import javax.swing.*;

public class Main {

    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        CheckersUI object = new CheckersUI();
        object.setVisible(true);

//        GameBody gameBoard = new GameBody();
//        gameBoard.play();
    }
}
