// This line of code inports the Scanner class which is used to read the user imput from the console.
import java.util.Scanner;

public class StringFlip {
    
    // This defines a public static method named reverse that takes a string (input) as a parameter and returns a string.
    public static String reverse(String input) {
        // This will create an empty StringBuilder object named reversed which will then be used by the program to build the reversed string more efficently whithin the program.
        StringBuilder reversed = new StringBuilder();
        // This loop will iterate through the characters of the imput string that was given by the user. the iteration will be starting with the last character and end with tthe first.
        for (int i = input.length() - 1; i >= 0; i--) {
            // Each charecter at postion i in the input string, it appends that charecter to the reversed StringBuilder.
            reversed.append(input.charAt(i));
        }
        // The toString() method of StringBuilder is called to convert the built reversed string into a regular string and return it.
        return reversed.toString();
    }

    public static void main(String[] args) {
        // Creates a scanner object to read the input from the standerd imput (console).
        Scanner scan = new Scanner(System.in);

        // The while loop will run indefinitely unti the user explicitly chooses to exit the program.
        while (true) {
        // Asks the user to enter a string that they would like to be Fliped by the program (or to type 'exit' to quit the program).
        System.out.print("Enter a string (or 'exit' to quit): ");
        // Reads the imput value from the console and stores it in originalString varable.
        String originalString = scan.nextLine();

        // Checks if the user enterd "exit" (case-insensative). if true, the loop breaks using break;. 
        if (originalString.equalsIgnoreCase("exit")) {
            break; // Exit the loop if and when the user types "exit" into the terminal.
        }

        String reversedString = reverse(originalString);
        System.out.println("The stringFlip is: " + reversedString);
        }

        // Close the Scanner to stop a resource leak.
        scan.close();
    }
    
}
