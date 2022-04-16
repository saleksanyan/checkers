public class Position {
    private int rank;
    private int file;

    public Position() {
        this.rank = this.file = 0;
    }

    public Position(int rank, int file) {
        if (0 <= rank && rank <= 8 && 0 <= file && file <= 8) {
            this.rank = rank;
            this.file = file;
        } else {
            System.out.println("The numbers that you gave are not in the range");
        }

    }

    public Position(Position that) {
        this.rank = that.rank;
        this.file = that.file;
    }

    public void setRank(int rank) {
        if (0 <= rank && rank <= 7) {
            this.rank = rank;
        } else {
            System.out.println("Gave a non-negative number that is less then or equal to 7");
        }

    }

    public void setFile(int file) {
        if (0 <= this.rank && this.rank <= 7) {
            this.file = file;
        } else {
            System.out.println("Gave a non-negative number that is less then or equal to 7");
        }

    }

    public int getRank() {
        return this.rank;
    }

    public int getFile() {
        return this.file;
    }

    public String toString() {
        char letter = (char)(65 + this.file);
        char number = (char)(56 - this.rank);
        String result = String.valueOf(letter + number);
        return result;
    }

    public static Position generateFromString(String position) {
        Position newObject;
        if ('A' <= position.charAt(0) && position.charAt(0) <= 'H' && '1' <= position.charAt(1) && position.charAt(1) <= '8' && position.length() == 2) {
            int file = position.charAt(0) - 65;
            int rank = 56 - position.charAt(1);
            newObject = new Position(rank, file);
        } else {
            newObject = null;
        }

        return newObject;
    }

    public static Position generateFromRankAndFile(int rank, int file) {
        Position object;
        if (0 <= rank && rank <= 7 && file >= 0 && file <= 7) {
            object = new Position(rank, file);
        } else {
            object = null;
        }

        return object;
    }

    public static Position[] appendPositionToArray(Position[] arr, Position p) {
        Position[] finalArr = new Position[arr.length + 1];

        for(int i = 0; i < arr.length; ++i) {
            finalArr[i] = arr[i];
        }

        finalArr[finalArr.length - 1] = p;
        return finalArr;
    }
    public static Position[] appendPositionsToArray(Position[] arr, Position...arg) {

        Position[] result = new Position[arr.length + arg.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i];
        }
        for (int i = 0, j = arr.length; i < arg.length; i++, j++) {
            result[j] = arg[i];
        }
        return result;
    }

    public boolean equalsTo(Position object) {
        return this.rank == object.rank && this.file == object.file;
    }

}
