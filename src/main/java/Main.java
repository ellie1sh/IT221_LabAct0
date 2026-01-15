import model.PassengerRecord;
import processor.DataProcessor;
import util.CSVReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Main Application Class - Airline Passenger Satisfaction
 * 
 * <p>
 * This is the entry point of the program. It:
 * <ol>
 *   <li>Loads the data from the CSV file</li>
 *   <li>Creates a DataProcessor to handle the data</li>
 *   <li>Shows a menu to the user</li>
 *   <li>Runs commands based on user input</li>
 * </ol>
 * </p>
 * 
 * @author IT221 Student
 */
public class Main {
    
    // The path to our data file
    private static final String CSV_FILE_PATH = "data/airline_satisfaction.csv";
    
    // Tools for input and data processing
    private static Scanner scanner = new Scanner(System.in);
    private static DataProcessor processor;
    
    public static void main(String[] args) {
        System.out.println("Welcome to the Airline Satisfaction Data System!");
        System.out.println("Loading data...");
        
        // 1. Load the dataset
        if (!loadDataset()) {
            System.out.println("Error: Could not load the dataset. The program will exit.");
            return; // Exit the program
        }
        
        System.out.println("Data loaded successfully!");
        System.out.println("Total records found: " + processor.getTotalRecords());
        
        // 2. Run the menu loop
        boolean isRunning = true;
        
        while (isRunning) {
            printMenu();
            
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine();
            
            // Process the user's choice
            switch (input) {
                case "1":
                    showGeneralStatistics();
                    break;
                case "2":
                    showDemographics();
                    break;
                case "3":
                    searchPassengerById();
                    break;
                case "4":
                    filterByAge();
                    break;
                case "5":
                    searchByDate();
                    break;
                case "0":
                    System.out.println("Goodbye!");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            
            // Add a small pause so the user can read the output
            if (isRunning) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    /**
     * Tries to load the data from the CSV file.
     * @return true if successful, false if there was an error.
     */
    private static boolean loadDataset() {
        try {
            // Create our CSV reader
            CSVReader reader = new CSVReader(CSV_FILE_PATH);
            
            // Read all records
            List<PassengerRecord> records = reader.readAllRecords();
            
            // Initialize our processor with the records
            processor = new DataProcessor(records);
            
            return true;
            
        } catch (IOException e) {
            System.out.println("File Error: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Prints the main menu options.
     */
    private static void printMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Show General Statistics");
        System.out.println("2. Show Demographics (Gender, Class, etc.)");
        System.out.println("3. Search for a Passenger by ID");
        System.out.println("4. Filter Passengers by Age");
        System.out.println("5. Search by Flight Date");
        System.out.println("0. Exit");
    }
    
    /**
     * Option 1: Shows general stats like average age, delays, satisfaction.
     */
    private static void showGeneralStatistics() {
        System.out.println("\n--- GENERAL STATISTICS ---");
        
        System.out.printf("Average Age: %.1f years\n", processor.getAverageAge());
        System.out.printf("Average Flight Distance: %.1f miles\n", processor.getAverageFlightDistance());
        System.out.printf("Average Departure Delay: %.1f minutes\n", processor.getAverageDepartureDelay());
        System.out.printf("Average Arrival Delay: %.1f minutes\n", processor.getAverageArrivalDelay());
        
        System.out.println("\nSatisfaction:");
        Map<String, Integer> satisfaction = processor.getSatisfactionDistribution();
        System.out.println("  Satisfied: " + satisfaction.getOrDefault("satisfied", 0));
        System.out.println("  Neutral/Dissatisfied: " + satisfaction.getOrDefault("neutral or dissatisfied", 0));
    }
    
    /**
     * Option 2: Shows distributions (counts) for gender, class, etc.
     */
    private static void showDemographics() {
        System.out.println("\n--- DEMOGRAPHICS ---");
        
        System.out.println("Gender Distribution:");
        Map<String, Integer> gender = processor.getGenderDistribution();
        System.out.println("  Male: " + gender.getOrDefault("Male", 0));
        System.out.println("  Female: " + gender.getOrDefault("Female", 0));
        
        System.out.println("\nClass Distribution:");
        Map<String, Integer> classes = processor.getTravelClassDistribution();
        System.out.println("  Business: " + classes.getOrDefault("Business", 0));
        System.out.println("  Eco: " + classes.getOrDefault("Eco", 0));
        System.out.println("  Eco Plus: " + classes.getOrDefault("Eco Plus", 0));
    }
    
    /**
     * Option 3: Asks for ID and shows passenger details.
     */
    private static void searchPassengerById() {
        System.out.print("Enter Passenger ID: ");
        
        try {
            String input = scanner.nextLine();
            int id = Integer.parseInt(input);
            
            PassengerRecord record = processor.searchById(id);
            
            if (record != null) {
                System.out.println("\n--- PASSENGER FOUND ---");
                System.out.println("ID: " + record.getId());
                System.out.println("Gender: " + record.getGender());
                System.out.println("Age: " + record.getAge());
                System.out.println("Class: " + record.getTravelClass());
                System.out.println("Flight Date: " + record.getFlightDate()); // Showing the new column
                System.out.println("Satisfaction: " + record.getSatisfaction());
            } else {
                System.out.println("No passenger found with ID " + id);
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a number.");
        }
    }
    
    /**
     * Option 4: Filters by age range.
     */
    private static void filterByAge() {
        try {
            System.out.print("Enter Minimum Age: ");
            int minAge = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Enter Maximum Age: ");
            int maxAge = Integer.parseInt(scanner.nextLine());
            
            List<PassengerRecord> results = processor.filterByAge(minAge, maxAge);
            
            System.out.println("\nFound " + results.size() + " passengers in age range " + minAge + "-" + maxAge);
            
            // Show first 5 results as preview
            System.out.println("Preview (first 5):");
            for (int i = 0; i < Math.min(5, results.size()); i++) {
                System.out.println(results.get(i));
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numbers.");
        }
    }
    
    /**
     * Option 5: Search by Flight Date.
     */
    private static void searchByDate() {
        System.out.print("Enter Date (MM:DD:YYYY): ");
        String date = scanner.nextLine();
        
        List<PassengerRecord> results = processor.searchByDate(date);
        
        System.out.println("\nFound " + results.size() + " passengers on " + date);
        
        if (!results.isEmpty()) {
            System.out.println("Preview (first 5):");
            for (int i = 0; i < Math.min(5, results.size()); i++) {
                System.out.println(results.get(i));
            }
        }
    }
}
