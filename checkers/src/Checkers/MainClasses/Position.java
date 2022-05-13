package Checkers.MainClasses;
//a few methods were taken from chess


/**
 * A mutable class for storing and representing a position on checkers board.
 * @author Syuzanna Aleksanyan
 * @author Mane Adamyan
 * @author Meri Barseghyan
 */

public class Position {
    // instance var
    private int rank;
    private int file;

    /**
     * a no arg constructor that set default values for instance variables
     */
    public Position() {
        this.rank = this.file = 0;
    }

    /**
     * sets rank and file if they are in the range else prints a informative message
     * @param rank given integer representing the rank
     * @param file given integer representing the file
     */
    public Position(int rank, int file) {
        if (0 <= rank && rank <= 8 && 0 <= file && file <= 8) {
            this.rank = rank;
            this.file = file;
        } else {
            System.out.println("The numbers that you gave are not in the range");
        }

    }

    /**
     * copy-constructor
     * @param that ab object from this class representing Position object
     */
    public Position(Position that) {
        this.rank = that.rank;
        this.file = that.file;
    }

    /**
     * sets the rank of the Position class
     * @param rank integer representing rank
     */

    public void setRank(int rank) {
        if (0 <= rank && rank <= 7) {
            this.rank = rank;
        } else {
            System.out.println("Gave a non-negative number that is less then or equal to 7");
        }

    }
    /**
     * sets the file of the Position class
     * @param file integer representing file
     */
    public void setFile(int file) {
        if (0 <= this.rank && this.rank <= 7) {
            this.file = file;
        } else {
            System.out.println("Gave a non-negative number that is less then or equal to 7");
        }

    }

    /**
     * returns the rank of the class Position
     * @return int, the rank
     */
    public int getRank() {
        return this.rank;
    }

    /**
     * returns the file of the class Position
     * @return int, the file
     */
    public int getFile() {
        return this.file;
    }

    /**
     * calculates and returns the string representation of Position object
     * @return string, the string representation of Position object
     */
    public String toString() {
        return "" + (char)('A' + this.file) + (Board.BOARD_RANKS - this.rank);
    }

    /**
     * calculates and returns a Position object generates from a string
     * @param position String representation of a Position object
     * @return Position object generates from a string
     */
    public static Position generateFromString(String position) {
        Position newObject;
        if ('A' <= position.charAt(0) && position.charAt(0) <= 'H' && '1' <= position.charAt(1) && position.charAt(1) <= '8' && position.length() == 2) {
            int file = position.charAt(0) - 65;
            int rank = 56 - position.charAt(1);
            newObject = new Position(rank, file);
        }else  if ('a' <= position.charAt(0) && position.charAt(0) <= 'h' && '1' <= position.charAt(1) && position.charAt(1) <= '8' && position.length() == 2) {
            int file = position.charAt(0) - 97;
            int rank = 56 - position.charAt(1);
            newObject = new Position(rank, file);
        }else {
            newObject = null;
        }

        return newObject;
    }

    /**
     *calculates and returns a Position object generated from rank and file
     * @param rank int representing y coordinate of the position
     * @param file int representing x coordinate of the position
     * @return Position object generated from rank and file
     */
    public static Position generateFromRankAndFile(int rank, int file) {
        Position object;
        if (0 <= rank && rank <= 7 && file >= 0 && file <= 7) {
            object = new Position(rank, file);
        } else {
            object = null;
        }

        return object;
    }

    /**
     * checkes if given object is equal to our object
     * @param object Position object
     * @return true, if objects are equal, false, if they are not
     */
    public boolean equalsTo(Position object) {
        return this.rank == object.rank && this.file == object.file;
    }

}