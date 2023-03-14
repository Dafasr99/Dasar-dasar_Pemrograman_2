import javax.swing.JOptionPane;

public class CountChars {
    public static void main(String[] args) {
        String phrase;

        phrase = JOptionPane.showInputDialog(null, "Enter a sentence or phrase, quit to end:", "Character Counter", JOptionPane.PLAIN_MESSAGE);

        while (phrase != null && !(phrase.equalsIgnoreCase("quit"))) {
            int length = phrase.length();

            // Check if the input is empty
            if (phrase.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter input, please.", "Information", JOptionPane.WARNING_MESSAGE);
            } else {
                // Initialize the counters
                int countSpace = 0;
                int countA = 0, countE = 0, countI = 0, countO = 0, countU = 0;
                int countConsonants = 0;
                int countOther = 0;

                // Check the characters
                for (int i = 0; i < length; i++) {
                    char ch = phrase.charAt(i);
                    switch (ch) {
                        case ' ' -> countSpace++;
                        case 'a', 'A' -> countA++;
                        case 'e', 'E' -> countE++;
                        case 'i', 'I' -> countI++;
                        case 'o', 'O' -> countO++;
                        case 'u', 'U' -> countU++;
                        default -> {
                            if (Character.isLetter(ch)) {
                                countConsonants++;
                            } else {
                                countOther++;
                            }
                        }
                    }
                }
            
                // Print the results
                JOptionPane.showMessageDialog(null, "Space: " + countSpace + "\nA: " + countA + "\nE: " + countE + "\nI: " + countI + "\nO: " + countO + "\nU: " + countU + "\nConsonants: " + countConsonants + "\nOthers: " + countOther + "\nThe input was: \n" + phrase, "Totals", JOptionPane.PLAIN_MESSAGE);
            }
            // Ask for input again
            phrase = JOptionPane.showInputDialog(null, "Enter a sentence or phrase, quit to end:\n", "Character Counter", JOptionPane.PLAIN_MESSAGE);
        }
        // Exit the program
        JOptionPane.showMessageDialog(null, "Goodbye!", "Information", JOptionPane.INFORMATION_MESSAGE);
    }

}

