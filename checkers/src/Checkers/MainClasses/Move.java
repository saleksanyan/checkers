package Checkers.MainClasses;
//a few methods were taken from chess

/**
 * A class for storing and representing a move on a checkers board.
 * @author Syuzanna Aleksanyan
 * @author Mane Adamyan
 * @author Meri Barseghyan
 */

public class Move {

    private Position origin;
    private Position destination;

    /**
     * takes positions for the start point and
     * for the final point gives those values to the instance variables of the class move
     * @param origin the start point
     * @param destination the final point
     */

    public Move(Position origin, Position destination){
        this.origin = origin;
        this.destination = destination;
    }

    /**
     * copy the parameters of the given move object
     * @param that is an object of the class move
     */

    public Move(Move that){
        this.origin.setRank(that.origin.getRank());
        this.origin.setFile(that.origin.getFile());
        this.destination.setRank(that.destination.getRank());
        this.destination.setFile(that.destination.getFile());
    }


    /**
     * gives the origin
     * @return the origin
     */

    public Position getOrigin() {
        return origin;
    }

    /**
     * gives the destination
     * @return the destination
     */


    public Position getDestination() {
        return destination;
    }

    /**
     * takes a position object and gives its value to the move class's origin object
     * @param origin is a position object
     */

    public void setOrigin(Position origin) {
        this.origin = origin;
    }


    /**
     * takes a position object and gives its value to the move class's destination object
     * @param destination is a position object
     */

    public void setDestination(Position destination) {
        this.destination = destination;
    }

    /**
     * turn two position objects into one string
     * @return string
     */

    public String toString() {
        return origin + " " + destination;
    }


}
