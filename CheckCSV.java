import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * This programs checks if the user input matches any of the food/items in the provided CSV-File.
 */
public class CheckCSV {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){

            System.out.println();
            System.out.println("Enter your ingredients: ");
            String input = scanner.nextLine().toLowerCase().trim(); // Ensures the proccess is case-insensitive & consistent.
            String foodCsv = "food.csv";
            String splitCsv = ",";
            String line;
            boolean isValid = false;

            // Try-Catch block to ensure that BufferReader is properly closed
            try (BufferedReader br = new BufferedReader(new FileReader(foodCsv))){
                while (((line = br.readLine())) != null && !isValid) {

                    // Convert entire line to lowercase.
                    line = line.toLowerCase(); 
                    String[] values = line.split(splitCsv);
                    // Only reads the first two values per line: Category & Description.
                    if ( values.length >= 2 && 
                            (values[0].trim().equals("\"" + input + "\"") || values[1].trim().equals("\"" + input + "\""))) { // Considering Quotes 
                        isValid = true; // If the input is found, break.
                        break;
                    }
                }
            // FileReadingError
            } catch (IOException e) {
                System.out.println(); // Readability
                System.out.println("File reading error: " + e.getMessage());
            }
            // Print result based on whether the input was found in the CSV.
            if (isValid) {
                System.out.println();
                System.out.println("The user input is valid.");
            } else {
                System.out.println();
                System.out.println("The user input is invalid.");
            }
        } catch(Exception e) {
            System.out.println();
            System.out.println("Unexpected Error: " + e.getMessage() );
           
        }
    }
}