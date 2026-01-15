import model.PassengerRecord;
import processor.DataProcessor;
import util.CSVReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Main - The main entry point for the Airline Satisfaction Analysis program.
 * 
 * <p>This program reads airline passenger satisfaction data from a CSV file
 * and provides various analysis options through a menu-based interface.</p>
 * 
 * <h2>IT221 - Information Management</h2>
 * <h3>Module 1: Data Processing and Databases</h3>
 * 
 * <h2>Features:</h2>
 * <ul>
 *   <li>Load and process CSV data</li>
 *   <li>View dataset overview and statistics</li>
 *   <li>Analyze demographics (gender, age)</li>
 *   <li>Analyze flight information</li>
 *   <li>View service ratings</li>
 *   <li>Analyze satisfaction by different categories</li>
 *   <li>Search and filter records</li>
 * </ul>
 * 
 * <h2>Data Types Used:</h2>
 * <ul>
 *   <li><b>String</b> - Text data (Gender, Class, Satisfaction, Date)</li>
 *   <li><b>int</b> - Whole numbers (Age, ID, Ratings)</li>
 *   <li><b>double</b> - Decimal numbers (Delays)</li>
 * </ul>
 * 
 * @author IT221 Student
 * @version 1.0
 */
public class Main {
    
    /**
     * Path to the CSV data file.
     */
    private static final String CSV_FILE_PATH = "data/airline_satisfaction.csv";
    
    /**
     * Scanner for reading user input.
     */
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Data processor for analyzing the records.
     */
    private static DataProcessor processor;
    
    /**
     * List of all passenger records loaded from CSV.
     */
    private static List<PassengerRecord> records;
    
    /**
     * Main method - the starting point of the program.
     * 
     * <p>This method:</p>
     * <ol>
     *   <li>Displays a welcome message</li>
     *   <li>Loads the dataset from CSV</li>
     *   <li>Shows a menu and handles user choices</li>
     *   <li>Exits when the user chooses to quit</li>
     * </ol>
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Show welcome message
        System.out.println("\n========================================");
        System.out.println("  AIRLINE PASSENGER SATISFACTION");
        System.out.println("  DATA PROCESSING SYSTEM");
        System.out.println("  IT221 - Lab Activity 0");
        System.out.println("========================================\n");
        
        // Try to load the dataset
        boolean loaded = loadDataset();
        if (!loaded) {
            System.out.println("Failed to load dataset. Exiting...");
            return;
        }
        
        // Create the data processor
        processor = new DataProcessor(records);
        
        // Show success message
        System.out.println("\nDataset loaded successfully!");
        System.out.println("Total records: " + processor.getTotalRecords());
        
        // Main menu loop
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");
            
            // Handle the user's choice
            if (choice == 1) {
                displayDatasetOverview();
            } else if (choice == 2) {
                displayDemographicsMenu();
            } else if (choice == 3) {
                displayFlightStatisticsMenu();
            } else if (choice == 4) {
                displayServiceRatingsMenu();
            } else if (choice == 5) {
                displaySatisfactionAnalysisMenu();
            } else if (choice == 6) {
                displaySearchAndFilterMenu();
            } else if (choice == 7) {
                displayComprehensiveReport();
            } else if (choice == 0) {
                running = false;
                System.out.println("\n========================================");
                System.out.println("  Thank you for using the system!");
                System.out.println("  Goodbye!");
                System.out.println("========================================");
            } else {
                System.out.println("\nInvalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    /**
     * Loads the dataset from the CSV file.
     * 
     * <p>Uses the CSVReader class to read and parse the file.</p>
     * 
     * @return true if loading succeeded, false otherwise
     */
    private static boolean loadDataset() {
        System.out.println("Loading dataset from: " + CSV_FILE_PATH);
        System.out.print("Please wait...");
        
        try {
            CSVReader reader = new CSVReader(CSV_FILE_PATH);
            records = reader.readAllRecords();
            System.out.println(" Done!");
            return true;
        } catch (IOException e) {
            System.out.println("\nError loading dataset: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Displays the main menu options.
     */
    private static void displayMainMenu() {
        System.out.println("\n----------------------------------------");
        System.out.println("              MAIN MENU");
        System.out.println("----------------------------------------");
        System.out.println("  1. Dataset Overview");
        System.out.println("  2. Demographics Analysis");
        System.out.println("  3. Flight Statistics");
        System.out.println("  4. Service Ratings Analysis");
        System.out.println("  5. Satisfaction Analysis");
        System.out.println("  6. Search & Filter Records");
        System.out.println("  7. Generate Comprehensive Report");
        System.out.println("  0. Exit");
        System.out.println("----------------------------------------");
    }
    
    /**
     * Shows an overview of the dataset.
     */
    private static void displayDatasetOverview() {
        System.out.println("\n========== DATASET OVERVIEW ==========");
        System.out.println("  Total Records: " + processor.getTotalRecords());
        
        System.out.println("\n  Data Columns:");
        System.out.println("  - ID (integer)");
        System.out.println("  - Gender (string)");
        System.out.println("  - Customer Type (string)");
        System.out.println("  - Age (integer)");
        System.out.println("  - Type of Travel (string)");
        System.out.println("  - Class (string)");
        System.out.println("  - Flight Distance (integer)");
        System.out.println("  - Service Ratings (integers, 0-5)");
        System.out.println("  - Delays (decimal/double)");
        System.out.println("  - Satisfaction (string)");
        System.out.println("  - Date (string, month:date:year)");
        
        // Quick satisfaction stats
        Map<String, Long> satisfaction = processor.getSatisfactionDistribution();
        long satisfied = 0;
        if (satisfaction.containsKey("satisfied")) {
            satisfied = satisfaction.get("satisfied");
        }
        long total = processor.getTotalRecords();
        
        System.out.println("\n  Quick Statistics:");
        System.out.println("  - Satisfied: " + satisfied + " (" + 
                          String.format("%.1f", satisfied * 100.0 / total) + "%)");
        System.out.println("  - Dissatisfied: " + (total - satisfied) + " (" + 
                          String.format("%.1f", (total - satisfied) * 100.0 / total) + "%)");
        
        pressEnterToContinue();
    }
    
    /**
     * Shows the demographics sub-menu.
     */
    private static void displayDemographicsMenu() {
        boolean inSubmenu = true;
        while (inSubmenu) {
            System.out.println("\n--- DEMOGRAPHICS ANALYSIS ---");
            System.out.println("  1. Gender Distribution");
            System.out.println("  2. Customer Type Distribution");
            System.out.println("  3. Age Statistics");
            System.out.println("  4. Age Group Distribution");
            System.out.println("  0. Back to Main Menu");
            
            int choice = getIntInput("Enter your choice: ");
            
            if (choice == 1) {
                displayDistribution("GENDER DISTRIBUTION", processor.getGenderDistribution());
            } else if (choice == 2) {
                displayDistribution("CUSTOMER TYPE DISTRIBUTION", processor.getCustomerTypeDistribution());
            } else if (choice == 3) {
                displayStatistics("AGE STATISTICS", processor.getAgeStatistics());
            } else if (choice == 4) {
                displayDistribution("AGE GROUP DISTRIBUTION", processor.getAgeGroupDistribution());
            } else if (choice == 0) {
                inSubmenu = false;
            } else {
                System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }
    
    /**
     * Shows the flight statistics sub-menu.
     */
    private static void displayFlightStatisticsMenu() {
        boolean inSubmenu = true;
        while (inSubmenu) {
            System.out.println("\n--- FLIGHT STATISTICS ---");
            System.out.println("  1. Travel Type Distribution");
            System.out.println("  2. Travel Class Distribution");
            System.out.println("  3. Flight Distance Statistics");
            System.out.println("  4. Flight Distance Categories");
            System.out.println("  5. Departure Delay Statistics");
            System.out.println("  6. Arrival Delay Statistics");
            System.out.println("  0. Back to Main Menu");
            
            int choice = getIntInput("Enter your choice: ");
            
            if (choice == 1) {
                displayDistribution("TRAVEL TYPE DISTRIBUTION", processor.getTravelTypeDistribution());
            } else if (choice == 2) {
                displayDistribution("TRAVEL CLASS DISTRIBUTION", processor.getTravelClassDistribution());
            } else if (choice == 3) {
                displayStatistics("FLIGHT DISTANCE STATISTICS", processor.getFlightDistanceStatistics());
            } else if (choice == 4) {
                displayDistribution("FLIGHT DISTANCE CATEGORIES", processor.getFlightDistanceDistribution());
            } else if (choice == 5) {
                displayStatistics("DEPARTURE DELAY STATISTICS", processor.getDepartureDelayStatistics());
            } else if (choice == 6) {
                displayStatistics("ARRIVAL DELAY STATISTICS", processor.getArrivalDelayStatistics());
            } else if (choice == 0) {
                inSubmenu = false;
            } else {
                System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }
    
    /**
     * Shows the service ratings sub-menu.
     */
    private static void displayServiceRatingsMenu() {
        boolean inSubmenu = true;
        while (inSubmenu) {
            System.out.println("\n--- SERVICE RATINGS ANALYSIS ---");
            System.out.println("  1. All Service Average Ratings");
            System.out.println("  2. Top & Bottom Rated Services");
            System.out.println("  0. Back to Main Menu");
            
            int choice = getIntInput("Enter your choice: ");
            
            if (choice == 1) {
                displayServiceRatings();
            } else if (choice == 2) {
                System.out.println("\n===== SERVICE RANKING SUMMARY =====");
                System.out.println(processor.getServiceRankingSummary());
                pressEnterToContinue();
            } else if (choice == 0) {
                inSubmenu = false;
            } else {
                System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }
    
    /**
     * Shows the satisfaction analysis sub-menu.
     */
    private static void displaySatisfactionAnalysisMenu() {
        boolean inSubmenu = true;
        while (inSubmenu) {
            System.out.println("\n--- SATISFACTION ANALYSIS ---");
            System.out.println("  1. Overall Satisfaction Distribution");
            System.out.println("  2. Satisfaction by Travel Class");
            System.out.println("  3. Satisfaction by Customer Type");
            System.out.println("  4. Satisfaction by Travel Type");
            System.out.println("  5. Satisfaction by Age Group");
            System.out.println("  0. Back to Main Menu");
            
            int choice = getIntInput("Enter your choice: ");
            
            if (choice == 1) {
                displayDistribution("SATISFACTION DISTRIBUTION", processor.getSatisfactionDistribution());
            } else if (choice == 2) {
                displaySatisfactionRates("SATISFACTION BY TRAVEL CLASS", processor.getSatisfactionRateByClass());
            } else if (choice == 3) {
                displaySatisfactionRates("SATISFACTION BY CUSTOMER TYPE", processor.getSatisfactionRateByCustomerType());
            } else if (choice == 4) {
                displaySatisfactionRates("SATISFACTION BY TRAVEL TYPE", processor.getSatisfactionRateByTravelType());
            } else if (choice == 5) {
                displaySatisfactionRates("SATISFACTION BY AGE GROUP", processor.getSatisfactionRateByAgeGroup());
            } else if (choice == 0) {
                inSubmenu = false;
            } else {
                System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }
    
    /**
     * Shows the search and filter sub-menu.
     */
    private static void displaySearchAndFilterMenu() {
        boolean inSubmenu = true;
        while (inSubmenu) {
            System.out.println("\n--- SEARCH & FILTER ---");
            System.out.println("  1. Search by Passenger ID");
            System.out.println("  2. Filter by Travel Class");
            System.out.println("  3. Filter by Age Range");
            System.out.println("  4. View Sample Records (First 10)");
            System.out.println("  0. Back to Main Menu");
            
            int choice = getIntInput("Enter your choice: ");
            
            if (choice == 1) {
                searchById();
            } else if (choice == 2) {
                filterByClass();
            } else if (choice == 3) {
                filterByAgeRange();
            } else if (choice == 4) {
                displaySampleRecords();
            } else if (choice == 0) {
                inSubmenu = false;
            } else {
                System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }
    
    /**
     * Searches for a passenger by ID.
     */
    private static void searchById() {
        int id = getIntInput("Enter Passenger ID to search: ");
        PassengerRecord result = processor.searchById(id);
        
        System.out.println("\n===== SEARCH RESULT =====");
        if (result != null) {
            System.out.println("  Passenger Found!");
            System.out.println("  ----------------------");
            System.out.println("  ID: " + result.getId());
            System.out.println("  Gender: " + result.getGender());
            System.out.println("  Age: " + result.getAge());
            System.out.println("  Customer Type: " + result.getCustomerType());
            System.out.println("  Travel Type: " + result.getTypeOfTravel());
            System.out.println("  Travel Class: " + result.getTravelClass());
            System.out.println("  Flight Distance: " + result.getFlightDistance() + " miles");
            System.out.println("  Departure Delay: " + result.getDepartureDelayInMinutes() + " min");
            System.out.println("  Arrival Delay: " + result.getArrivalDelayInMinutes() + " min");
            System.out.println("  Avg Service Rating: " + 
                              String.format("%.2f", result.getAverageServiceRating()) + "/5.00");
            System.out.println("  Satisfaction: " + result.getSatisfaction());
            System.out.println("  Date: " + result.getDate());
        } else {
            System.out.println("  No passenger found with ID: " + id);
        }
        
        pressEnterToContinue();
    }
    
    /**
     * Filters records by travel class.
     */
    private static void filterByClass() {
        System.out.println("\nAvailable Classes: Business, Eco, Eco Plus");
        System.out.print("Enter travel class to filter: ");
        String travelClass = scanner.nextLine().trim();
        
        List<PassengerRecord> filtered = processor.filterByClass(travelClass);
        
        System.out.println("\n===== FILTER RESULTS =====");
        System.out.println("  Records in '" + travelClass + "' class: " + filtered.size());
        
        if (filtered.size() > 0) {
            // Count satisfied passengers
            int satisfied = 0;
            int totalAge = 0;
            int totalDistance = 0;
            
            for (PassengerRecord r : filtered) {
                if (r.isSatisfied()) {
                    satisfied++;
                }
                totalAge += r.getAge();
                totalDistance += r.getFlightDistance();
            }
            
            double satPercent = (satisfied * 100.0) / filtered.size();
            double avgAge = (double) totalAge / filtered.size();
            double avgDistance = (double) totalDistance / filtered.size();
            
            System.out.println("  Satisfied: " + satisfied + " (" + 
                              String.format("%.1f", satPercent) + "%)");
            System.out.println("  Dissatisfied: " + (filtered.size() - satisfied) + " (" + 
                              String.format("%.1f", 100 - satPercent) + "%)");
            System.out.println("  Average Age: " + String.format("%.1f", avgAge) + " years");
            System.out.println("  Average Flight Distance: " + String.format("%.1f", avgDistance) + " miles");
        }
        
        pressEnterToContinue();
    }
    
    /**
     * Filters records by age range.
     */
    private static void filterByAgeRange() {
        int minAge = getIntInput("Enter minimum age: ");
        int maxAge = getIntInput("Enter maximum age: ");
        
        List<PassengerRecord> filtered = processor.filterByAgeRange(minAge, maxAge);
        
        System.out.println("\n===== FILTER RESULTS =====");
        System.out.println("  Passengers aged " + minAge + "-" + maxAge + ": " + filtered.size());
        
        if (filtered.size() > 0) {
            // Count satisfied
            int satisfied = 0;
            for (PassengerRecord r : filtered) {
                if (r.isSatisfied()) {
                    satisfied++;
                }
            }
            
            System.out.println("  Satisfied: " + satisfied + " (" + 
                              String.format("%.1f", satisfied * 100.0 / filtered.size()) + "%)");
            
            // Count by class
            System.out.println("\n  Class Distribution:");
            Map<String, Integer> classCounts = new java.util.HashMap<>();
            for (PassengerRecord r : filtered) {
                String cls = r.getTravelClass();
                if (classCounts.containsKey(cls)) {
                    classCounts.put(cls, classCounts.get(cls) + 1);
                } else {
                    classCounts.put(cls, 1);
                }
            }
            for (String cls : classCounts.keySet()) {
                System.out.println("    - " + cls + ": " + classCounts.get(cls));
            }
        }
        
        pressEnterToContinue();
    }
    
    /**
     * Displays a sample of records.
     */
    private static void displaySampleRecords() {
        System.out.println("\n===== SAMPLE RECORDS (First 10) =====");
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf("%-8s %-8s %-5s %-12s %-10s %-12s %-12s\n",
                "ID", "Gender", "Age", "Class", "Distance", "Satisfied", "Date");
        System.out.println("------------------------------------------------------------------------------");
        
        // Show first 10 records
        int count = 0;
        for (PassengerRecord r : records) {
            if (count >= 10) break;
            
            System.out.printf("%-8d %-8s %-5d %-12s %-10d %-12s %-12s\n",
                    r.getId(), 
                    r.getGender(), 
                    r.getAge(),
                    r.getTravelClass(), 
                    r.getFlightDistance(), 
                    r.getSatisfaction(),
                    r.getDate());
            count++;
        }
        
        System.out.println("------------------------------------------------------------------------------");
        pressEnterToContinue();
    }
    
    /**
     * Displays the comprehensive report.
     */
    private static void displayComprehensiveReport() {
        System.out.println(processor.generateComprehensiveSummary());
        pressEnterToContinue();
    }
    
    // ================================================================
    // HELPER METHODS
    // ================================================================
    
    /**
     * Displays a distribution (counts by category).
     * 
     * @param title the title to display
     * @param distribution the data to display
     */
    private static void displayDistribution(String title, Map<String, Long> distribution) {
        System.out.println("\n===== " + title + " =====");
        
        // Calculate total
        long total = 0;
        for (Long value : distribution.values()) {
            total += value;
        }
        
        // Display each category
        for (String key : distribution.keySet()) {
            long value = distribution.get(key);
            double percentage = (value * 100.0) / total;
            System.out.printf("  %-25s: %7d (%5.1f%%)\n", key, value, percentage);
        }
        
        System.out.println("\n  Total: " + total);
        pressEnterToContinue();
    }
    
    /**
     * Displays statistics (numeric values).
     * 
     * @param title the title to display
     * @param stats the statistics to display
     */
    private static void displayStatistics(String title, Map<String, Double> stats) {
        System.out.println("\n===== " + title + " =====");
        
        for (String key : stats.keySet()) {
            double value = stats.get(key);
            
            // Format differently based on the type of stat
            if (key.contains("Count") || key.contains("Flights") || key.contains("Total")) {
                System.out.printf("  %-25s: %.0f\n", key, value);
            } else {
                System.out.printf("  %-25s: %.2f\n", key, value);
            }
        }
        
        pressEnterToContinue();
    }
    
    /**
     * Displays all service ratings.
     */
    private static void displayServiceRatings() {
        System.out.println("\n===== ALL SERVICE RATINGS (Average out of 5.00) =====");
        
        Map<String, Double> ratings = processor.getAverageServiceRatings();
        
        for (String service : ratings.keySet()) {
            double rating = ratings.get(service);
            System.out.printf("  %-35s: %.2f\n", service, rating);
        }
        
        // Calculate overall average
        double total = 0;
        for (Double rating : ratings.values()) {
            total += rating;
        }
        double overall = total / ratings.size();
        
        System.out.println("\n  -----------------------------------");
        System.out.printf("  %-35s: %.2f\n", "OVERALL AVERAGE", overall);
        
        pressEnterToContinue();
    }
    
    /**
     * Displays satisfaction rates by category.
     * 
     * @param title the title to display
     * @param rates the satisfaction rates
     */
    private static void displaySatisfactionRates(String title, Map<String, Double> rates) {
        System.out.println("\n===== " + title + " =====");
        
        for (String category : rates.keySet()) {
            double rate = rates.get(category);
            System.out.printf("  %-20s: %5.1f%% satisfied\n", category, rate);
        }
        
        pressEnterToContinue();
    }
    
    /**
     * Gets an integer input from the user.
     * 
     * <p>Keeps asking until a valid number is entered.</p>
     * 
     * @param prompt the message to display
     * @return the integer entered by the user
     */
    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        
        // Keep trying until we get a valid number
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();  // Discard invalid input
        }
        
        int value = scanner.nextInt();
        scanner.nextLine();  // Clear the newline character
        return value;
    }
    
    /**
     * Waits for the user to press Enter.
     */
    private static void pressEnterToContinue() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
}
