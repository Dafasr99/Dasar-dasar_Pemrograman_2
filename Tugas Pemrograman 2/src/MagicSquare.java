/**
A magic square that is constructed by a the "right and down" algorithm
*/

public class MagicSquare {
    
    private int[][] square;
    private int size;

    public MagicSquare(int s) {

        size = s;
        square = new int[size][size];
        int row = size/2;
        int col = size/2 + 1;
        square[row][col] = 1;

        // fill in the square using the Siamese method
        // loop through each number to be placed in the square
        for (int k=2; k<=size*size; k++) { 
            int nextRow = (row - 1 + size) % size;  
            int nextCol = (col + 1) % size;

            // if the next position is empty, place the number there
            if (square[nextRow][nextCol] == 0) { 
                row = nextRow;
                col = nextCol;

            // otherwise, place the number below the current position
            } else {
                row = (row + 1) % size; 
            }
            square[row][col] = k;
        }
    }

    /**
     Find the sum of the diagonal 
    @return sum: the sum of the diagonal
    */

    public int diagonalSum() {

        int sum = 0;

        // sum the diagonal elements
        // loop through each element in the diagonal
        for (int i=0; i<size; i++) {
            sum += square[i][i];
        }

        // return the sum
        return sum;

    }

    /** 
    Add the numbers in a column of the square.
    @param i the column index
    @return the sum of the column
    */

    public int columnSum(int i) {

        int sum = 0;

        // loop through each element in the column
        for (int j=0; j<size; j++) {
            sum += square[j][i];
        }

        return sum;

    }


    /**
    Add the numbers in a row of the square
    @param i the row index
    @return sum: the sum of the row
    */

    public int rowSum(int i) {

        int sum = 0;
        
        // loop through each element in the row
        for (int j=0; j<size; j++) { 
            sum += square[i][j];
        }

        return sum;

    }

    /**
    Gets a string representation of the contents of this square
    @return a string representation of the square
    */

    public String toString() {

        String r = "";

        // loop through each row and column to create a string representation of the square
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                r += square[i][j] + "\t";
            }
            r += "\n";
        }

        return r;

    }
    
}
