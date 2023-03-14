public class TestMagicSquare {

    public static void main(String[] args) {
    
        int n = 0; // size of magic square
    
        //Process input from command line
        if (args.length == 1) {
            try {
                n = Integer.parseInt(args[0]);
            
            // catch exception if input is not an integer
            } catch (NumberFormatException e) {
                System.out.println("Input must be a valid integer");
                System.exit(1);
            }

            // catch exception if input is less than 1 or 1
            if ( n <= 1) {
                System.out.println("Enter a number more than 1");
                System.exit(1);
            }

            // catch exception if input is even
            if(n % 2 == 0) { 
                System.out.println("Size of square must be odd");
                System.exit(1);
            }

        // catch exception if no input is given
        } else {
            System.out.println("Usage: java -jar <jarFile> <odd size of square> \nExample: java -jar <jarFile> 9");
            System.exit(1);
        } 
    
        //Create an object of MagicSquare
        MagicSquare ms = new MagicSquare(n);
    
        // Print the results
        System.out.println("Magic Square of size " + n + "x" + n + ":\n\n" + ms + "\nMain diagonal sum: " + ms.diagonalSum() + "\nColumn sum: " + ms.columnSum(0) + "\nRow sum: " + ms.rowSum(n-1) + "\n"); 
    }

}
