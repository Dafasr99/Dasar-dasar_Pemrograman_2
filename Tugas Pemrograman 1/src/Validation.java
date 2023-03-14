import javax.swing.JOptionPane;

public class Validation {
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog(null, "Enter a credit card / debit card as a long integer, \nQUIT to end:");

        // Keep reading input until the input is QUIT
        while (input != null && !(input.equalsIgnoreCase("QUIT") || input.equalsIgnoreCase("Quit") ||input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("END") ||input.equalsIgnoreCase("End") || input.equalsIgnoreCase("end"))) {

            // Check if the input is a number
            try {
                long number = Long.parseLong(input);
                if (isValid(number)) {
                    JOptionPane.showMessageDialog(null, "The number " + number + " is valid.");
                } else {
                    JOptionPane.showMessageDialog(null, "The number " + number + "  is invalid.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input.");
            }

            input = JOptionPane.showInputDialog(null, "Enter a credit card / debit card as a long integer, \nQUIT to end:");

        }
        JOptionPane.showMessageDialog(null, "The program has finished.");
    }
    
    /** Return true if the card number is valid */ 
    public static boolean isValid(long number) { 
        int sum = sumOfDoubleEvenPlace(number) + sumOfOddPlace(number); 
        // Check if the sum is divisible by 10 and if the prefix matches the card number
        if (sum % 10 == 0 && (prefixMatched(number, 4) || prefixMatched(number, 5) || prefixMatched(number, 6) || prefixMatched(number, 37))) { 
            return true;
        }
        return false; 
    }
    
    /** Get the result from Step 2 */
    public static int sumOfDoubleEvenPlace(long number) {
        int sum = 0;
        String num = Long.toString(number); 
        // Loop through the number from the rightmost digit to the leftmost digit
        for (int i = num.length() - 2; i >= 0; i -= 2) { // i -= 2 because we want to skip the odd digits
            sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2); // Multiply the digit by 2 and add the result to sum
        }
        return sum;
    }

    /** Return this number if it is a single digit, otherwise,
    * return the sum of the two digits */
    public static int getDigit(int number) {
    // If the number is a single digit, return the number
    if (number <= 9) { 
        return number;
    }
    // If the number is not a single digit, return the sum of the two digits
    return number / 10 + number % 10; 
    }

    /** Return sum of odd-place digits in number */
    public static int sumOfOddPlace(long number) {
        int sum = 0;
        String num = Long.toString(number);
        // Loop through the number from the rightmost digit to the leftmost digit
        for (int i = num.length() - 1; i >= 0; i -= 2) { // i -= 2 because we want to skip the even digits
            sum += Integer.parseInt(num.charAt(i) + ""); // Add the digit to sum
        }
        return sum;
    }

    /** Return true if the number d is a prefix for number */
    public static boolean prefixMatched(long number, int d) {
        // Check if the first k digits of number match d
        return getPrefix(number, getSize(d)) == d;
    }

    /** Return the number of digits in d */
    public static int getSize(long d) { 
        // Return the length of the number
        return Long.toString(d).length();
    }

    /** Return the first k number of digits from number. If the
     * number of digits in number is less than k, return number. */
    public static long getPrefix(long number, int k) {
        // Check if the number of digits in number is less than k
        if (getSize(number) > k) { // If the number of digits in number is greater than k
            String num = Long.toString(number); 
            return Long.parseLong(num.substring(0, k)); // Return the first k digits of number
        }
        return number;
    }
}
