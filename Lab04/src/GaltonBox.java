/**
 * GaltonBox.java
 
 * Program idea:
 * Create an array named slots. Each element in slots stores the number
 * of balls in a slot. Each ball falls randomly into a slot via a path.
 * The number of Rs in a path is the position of the slot where the ball falls.
 * For example, for the path LRLRLRR, the ball falls into slots [4] (because
 * there are 4 Rs), and for the path RRLLLLL, the ball falls into slotst2]
 * (because there are 2 Rs).
 */

import java.util.Scanner;
import java.util.InputMismatchException;

public class GaltonBox {
    
    /**
     * The main method prompts the user for input, initializes an array of slots to store the 
     * distribution of balls, calls the onePath() method to calculate the slot position for each ball,
     * and then calls the PrintHistogram() method to print a histogram of the resulting distribution.
     * Finally, the Scanner is closed.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int balls = 0;
        int numberOfSlots = 0;
    
        while (true) {
            try {
                System.out.print("Enter the number of balls: ");
                balls = input.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter integers only.");
                input.nextLine();
            }
        }
    
        while (true) {
            try {
                System.out.print("Enter the number of slots: ");
                numberOfSlots = input.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter integers only.");
                input.nextLine();
            }
        }
    
        int[] slots = new int[numberOfSlots];
    
        System.out.println();
    
        for (int i = 0; i < balls; i++) {
            slots[onePath(numberOfSlots)]++;
        }
    
        PrintHistogram(slots);
    
        input.close();
    }
    
    /**
     * This method takes in the number of slots and calculates the slot position for a single ball
     * using a random path, and also prints out the path. It returns the final slot position of the ball.
     */
    public static int onePath(int numberOfSlots) {
        int position = 0;
        
        for (int i = 0; i < numberOfSlots - 1; i++) {
            if (Math.random() < 0.5) {
                System.out.print("L");
            }
            else {
                System.out.print("R");
                position++;
            }
        }

        System.out.println();

        return position;
    }

    /**
     * This method takes in an array of slots and prints out a histogram of the resulting ball distribution
     * for each slot. It first calculates the maximum slot height (i.e., the number of balls in the tallest slot),
     * and then prints out each row of the histogram from top to bottom. Finally, it prints out a line of dashes
     * to represent the bottom of the histogram.
     */
    public static void PrintHistogram(int[] slots) {
        int maxSlotHeight = max(slots);

        System.out.println();
        
        for (int h = maxSlotHeight; h > 0; h--) { 
            for (int i = 0; i < slots.length; i++) { 
                if (slots[i] < h)
                    System.out.print(" ");
                else
                    System.out.print("O");
            }
            System.out.println();

        }

        for (int i = 0; i < slots.length; i++) { 
            System.out.print("-");
        }
        System.out.println();

    }

    /**
     * This method takes in an array of integers and returns the maximum value in the array.
     */
    public static int max(int[] slots) {
        int max = slots[0];
    
        for (int i = 1; i < slots.length; i++) {
            if (slots[i] > max) {
                max = slots[i];
            }
        }

        return max;
    }
}


