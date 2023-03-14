import java.util.Scanner;

public class Circle {
    public static void main(String[] args) {

        final double PI = Math.PI; //3.141592653589793

        while (true) { //create a loop to keep the program running until the user enters a valid input

            try{ //used to handle the situation when the user enters an invalid input

            Scanner input = new Scanner(System.in);
            System.out.print("Enter the radius: "); //prompt the user to enter the radius
            double radius = input.nextDouble();
            double circumference = 2 * PI * radius; //calculate the circumference
            System.out.println("The circumference of a circle with radius " + radius + " is " + circumference); //display the result
            double area = PI * radius * radius; //calculate the area
            System.out.println("The area of a circle with radius " + radius + " is " + area); //display the result

            System.out.println("------------------------------------"); 
            
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the radius: "); //prompt the user to enter the radius
            double radiusSecond = scanner.nextDouble();
            double circumferenceSecond = 2 * PI * radiusSecond; //calculate the circumference
            System.out.println("The circumference of a circle with radius " + radiusSecond + " is " + circumferenceSecond); //display the result
            double areaSecond = PI * radiusSecond * radiusSecond; //calculate the area
            System.out.println("The area of a circle with radius " + radiusSecond + " is " + areaSecond); //display the result

            input.close(); //close the scanner
            scanner.close(); //close the scanner

            break; //break the loop

            } catch(Exception e) { //catch the exception
                System.out.println("Invalid input. Please enter a number.");

            }  
        }
    }     
}
