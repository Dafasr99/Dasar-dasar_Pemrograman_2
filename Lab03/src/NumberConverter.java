import javax.swing.JOptionPane;

public class NumberConverter {
    public static void main(String[] args) throws Exception {

        String inputString, resultString;
        
        boolean valid = false; 
        boolean keepGoing = true; 
        long decLong = 0; //input from user, can be a very big number

        // Keep asking for input until user enters QUIT
        while (keepGoing) {
            inputString = JOptionPane.showInputDialog(null, "Please enter a positive decimal integer <= " + Integer.MAX_VALUE + " or QUIT to quit:", "Number Converter", JOptionPane.PLAIN_MESSAGE);

            // If user enters QUIT, exit the program
            if (inputString == null || inputString.equalsIgnoreCase("QUIT")) {
                keepGoing = false;
                break;
            }
            
            // Check if input is a valid positive decimal integer
            try {
                decLong = Long.parseLong(inputString);
                if (decLong < 0) {
                    throw new NumberFormatException();
                }
                valid = true;
            // If input is not a valid positive decimal integer, show error message
            } catch (NumberFormatException e) {
                valid = false;
                JOptionPane.showMessageDialog(null, "Please enter a positive decimal integer or QUIT to quit.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
            
            // If input is a valid positive decimal integer, convert it to hex
            if (valid) {
                int decInt = (int) decLong;

                resultString = "The hex number for decimal number " + decLong + " is:\n" + int2hex(decInt);

                JOptionPane.showMessageDialog(null, resultString, "Number Converter", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    public static String int2hex(long decLong) {
        // Check if input is a valid positive decimal integer
        if (decLong <= 0 || decLong > Integer.MAX_VALUE) {
            return "Wrong input, Please enter a positive decimal integer or QUIT to quit.";
        }

        // Convert decimal to hex
        String hexStr = "";
        int remainder;

        int decInt = (int) decLong;

        // Keep dividing the decimal number by 16 until it is 0
        while (decInt > 0) {
            remainder = decInt % 16; 
            hexStr = switch (remainder) {
                case 10 -> "A" + hexStr;
                case 11 -> "B" + hexStr;
                case 12 -> "C" + hexStr;
                case 13 -> "D" + hexStr;
                case 14 -> "E" + hexStr;
                case 15 -> "F" + hexStr;
                default -> remainder + hexStr;
            };
            // Divide the decimal number by 16
            decInt /= 16;
        }

        return hexStr;
    }

}
