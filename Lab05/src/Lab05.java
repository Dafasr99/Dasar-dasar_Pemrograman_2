import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;

/* 
 * Creates a random list of integers, then repeadtedly print the menu
 * and do what the user asks until they quit. 
*/
public class Lab05 {
    public static void main(String[] args) {

        int[] list = new int[10]; //initial default array
        Scanner scan = new Scanner(System.in);

        printMenu();

        int choice = 0;

        while (true) {
            try {
                String input = scan.nextLine().trim();
                // if user enters an empty string, prompt again
                if (input.isEmpty()) {
                    System.out.print("Enter your choice: ");
                    continue;
                }
                choice = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter an integer.\nInput a number: ");
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter an integer.\nEnter your choice: ");
                scan.nextLine(); // consume the invalid input
                System.out.println();
            } 
        }

        while (choice != 0) {
            int loc;
            switch (choice) {
                case 1 -> {
                    try {
                        System.out.println("How many elements?");
                        int size = scan.nextInt();
                        list = new int[size];
                        randomize(list); // fill the array with random integers
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter an integer.");
                        scan.nextLine(); // consume the invalid input
                    }
                
                }
                case 2 -> selectionSort(list);
                case 3 -> {
                    try {
                        System.out.println("Enter the value to look for: ");
                        int val = scan.nextInt();
                        loc = linearSearch(list, val);
                        if (loc != -1) { // if val is found
                            System.out.println("Found at index " + loc);
                        } else {
                            System.out.println("Not in the list");
                        }
                    } catch (InputMismatchException e) { // if user enters a non-integer
                        System.out.println("Invalid input. Please enter an integer.");
                        scan.nextLine(); // consume the invalid input
                    }
                }
                case 4 -> printList(list);
                default -> System.out.println("Sorry, Invalid choice.");
            }                

            printMenu();

            // get user's choice
            while (true) {
                try {
                    choice = scan.nextInt();
                    break;
                } catch (InputMismatchException e) { // if user enters a non-integer
                    System.out.print("Invalid input. Please enter an integer.\nEnter your choice: ");
                    scan.nextLine(); // consume the invalid input
                }
            }
        }

        System.out.println("Bye!");

        scan.close();
    }

/*
* Print the menu of user's choices
*/

    public static void printMenu() {
        System.out.println("\nMenu   ");
        System.out.println("====");
        System.out.println("0: Quit");
        System.out.println("1: Create a new list of random elements");
        System.out.println("2: Sort the list using selection sort");
        System.out.println("3: Find an element in the list using linear search");
        System.out.println("4: Print the list");
        System.out.print("Enter your choice: ");
    }

    /*
    * Fill the array with random integers between 1 and 1000, inclusive
    */

    public static void randomize(int[] list) {
        // create a Random object
        Random rand = new Random();
        // fill the array with random integers between 1 and 1000, inclusive
        for (int i = 0; i < list.length; i++) {
            // generate a random integer between 1 and 1000, inclusive
            list[i] = rand.nextInt(1000) + 1;
        }
    }

    /*
    * Prints array elements with indices
    */
    public static void printList(int[] list) {
        // print the array elements with indices
        for (int i = 0; i < list.length; i++) { 
            System.out.printf("[%d]: %d\n", i, list[i]); 
        }
    }

    /*
    * Returns the index of the first occurrence of target in the list, -1
    * if target does not appear in the list.
    */
    public static int linearSearch(int[] list, int target) {
        return linearSearchRec(list, target, 0);
    }  

    /*
    * Recursive implementation of the sequential search -- searches
    * for target starting at index lo.
    * Is this method tail-recursive?
    */
    public static int linearSearchRec(int[] list, int target, int lo) {
        if (lo >= list.length) { // target not found
            return -1;
        } else if (list[lo] == target) { // target found
            return lo;
        } else { // keep searching recursively
            return linearSearchRec(list, target, lo + 1);
        }
    }

    /*
    * Sorts the list into ascending order using the selection sort algorithm.
    */
    public static void selectionSort(int[] list) {
        int minIndex;
        for (int i = 0; i < list.length - 1; i++) {
            // find smallest element in list starting at location i
            minIndex = i;
            for (int j = i + 1; j < list.length; j++) { 
                // if list[j] is smaller than current minimum, update minIndex
                if (list[j] < list[minIndex]) {
                    minIndex = j;
                }
            }
            // swap list[i] with smallest element
            int temp = list[i]; // store list[i] in temp
            list[i] = list[minIndex]; // copy smallest element to list[i]
            list[minIndex] = temp; // copy temp to list[minIndex]
        }
    }
}
