import model.PassengerRecord;
import processor.DataProcessor;
import util.CSVReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

/**
 * Main Application Class - Airline Passenger Satisfaction Data Processing
 * 
 * IT221 - Information Management
 * Module 1: Information Management, Data Processing and Databases
 * 
 * This program processes an airline passenger satisfaction dataset
 * containing over 25,000 records with various data types:
 * - String: Gender, Customer Type, Travel Type, Class, Satisfaction
 * - Integer: ID, Age, Flight Distance, Service Ratings (0-5 scale)
 * - Decimal: Departure Delay, Arrival Delay (in minutes)
 * 
 * @author IT221 Student
 * @version 1.0
 */
public class Main {
    
    private static final String CSV_FILE_PATH = "data/airline_satisfaction.csv";
    private static Scanner scanner = new Scanner(System.in);
    private static DataProcessor processor;
    private static List<PassengerRecord> records;
    
    public static void main(String[] args) {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║    AIRLINE PASSENGER SATISFACTION DATA PROCESSING SYSTEM   ║");
        System.out.println("║                   IT221 - Lab Activity 0                   ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        // Load the dataset
        if (!loadDataset()) {
            System.out.println("Failed to load dataset. Exiting...");
            return;
        }
        
        // Initialize processor
        processor = new DataProcessor(records);
        
        System.out.println("\n✓ Dataset loaded successfully!");
        System.out.println("  Total records: " + processor.getTotalRecords());
        
        // Run the main menu loop
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    displayDatasetOverview();
                    break;
                case 2:
                    displayDemographicsMenu();
                    break;
                case 3:
                    displayFlightStatisticsMenu();
                    break;
                case 4:
                    displayServiceRatingsMenu();
                    break;
                case 5:
                    displaySatisfactionAnalysisMenu();
                    break;
                case 6:
                    displaySearchAndFilterMenu();
                    break;
                case 7:
                    displayComprehensiveReport();
                    break;
                case 0:
                    running = false;
                    System.out.println("\n╔════════════════════════════════════════╗");
                    System.out.println("║  Thank you for using the system!       ║");
                    System.out.println("║  Goodbye!                              ║");
                    System.out.println("╚════════════════════════════════════════╝");
                    break;
                default:
                    System.out.println("\n⚠ Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    /**
     * Load the dataset from CSV file
     */
    private static boolean loadDataset() {
        System.out.println("Loading dataset from: " + CSV_FILE_PATH);
        System.out.print("Please wait");
        
        try {
            CSVReader reader = new CSVReader(CSV_FILE_PATH);
            records = reader.readAllRecords();
            System.out.println("... Done!");
            return true;
        } catch (IOException e) {
            System.out.println("\n✗ Error loading dataset: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Display the main menu
     */
    private static void displayMainMenu() {
        System.out.println("\n┌──────────────────────────────────────────┐");
        System.out.println("│              MAIN MENU                   │");
        System.out.println("├──────────────────────────────────────────┤");
        System.out.println("│  1. Dataset Overview                     │");
        System.out.println("│  2. Demographics Analysis                │");
        System.out.println("│  3. Flight Statistics                    │");
        System.out.println("│  4. Service Ratings Analysis             │");
        System.out.println("│  5. Satisfaction Analysis                │");
        System.out.println("│  6. Search & Filter Records              │");
        System.out.println("│  7. Generate Comprehensive Report        │");
        System.out.println("│  0. Exit                                 │");
        System.out.println("└──────────────────────────────────────────┘");
    }
    
    /**
     * Display dataset overview
     */
    private static void displayDatasetOverview() {
        System.out.println("\n════════════════════ DATASET OVERVIEW ════════════════════");
        System.out.println("  Total Records: " + String.format("%,d", processor.getTotalRecords()));
        System.out.println("\n  Data Columns:");
        System.out.println("  ├─ Identification: ID");
        System.out.println("  ├─ Demographics: Gender, Customer Type, Age");
        System.out.println("  ├─ Travel Info: Type of Travel, Class, Flight Distance");
        System.out.println("  ├─ Service Ratings (0-5): 14 different services");
        System.out.println("  ├─ Delays: Departure Delay, Arrival Delay");
        System.out.println("  └─ Outcome: Satisfaction");
        
        System.out.println("\n  Data Types Used:");
        System.out.println("  ├─ String: Gender, Customer Type, Travel Type, Class, Satisfaction");
        System.out.println("  ├─ Integer: ID, Age, Distance, Service Ratings");
        System.out.println("  └─ Decimal (Double): Delay times in minutes");
        
        System.out.println("\n  Quick Statistics:");
        Map<String, Long> satisfaction = processor.getSatisfactionDistribution();
        long satisfied = satisfaction.getOrDefault("satisfied", 0L);
        long total = processor.getTotalRecords();
        System.out.printf("  ├─ Satisfied: %,d (%.1f%%)\n", satisfied, (satisfied * 100.0) / total);
        System.out.printf("  └─ Dissatisfied: %,d (%.1f%%)\n", total - satisfied, ((total - satisfied) * 100.0) / total);
        
        pressEnterToContinue();
    }
    
    /**
     * Display demographics sub-menu
     */
    private static void displayDemographicsMenu() {
        boolean inSubmenu = true;
        while (inSubmenu) {
            System.out.println("\n┌──────────────────────────────────────────┐");
            System.out.println("│         DEMOGRAPHICS ANALYSIS            │");
            System.out.println("├──────────────────────────────────────────┤");
            System.out.println("│  1. Gender Distribution                  │");
            System.out.println("│  2. Customer Type Distribution           │");
            System.out.println("│  3. Age Statistics                       │");
            System.out.println("│  4. Age Group Distribution               │");
            System.out.println("│  0. Back to Main Menu                    │");
            System.out.println("└──────────────────────────────────────────┘");
            
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    displayDistribution("GENDER DISTRIBUTION", processor.getGenderDistribution());
                    break;
                case 2:
                    displayDistribution("CUSTOMER TYPE DISTRIBUTION", processor.getCustomerTypeDistribution());
                    break;
                case 3:
                    displayStatistics("AGE STATISTICS", processor.getAgeStatistics());
                    break;
                case 4:
                    displayDistribution("AGE GROUP DISTRIBUTION", processor.getAgeGroupDistribution());
                    break;
                case 0:
                    inSubmenu = false;
                    break;
                default:
                    System.out.println("\n⚠ Invalid choice. Please try again.");
            }
        }
    }
    
    /**
     * Display flight statistics sub-menu
     */
    private static void displayFlightStatisticsMenu() {
        boolean inSubmenu = true;
        while (inSubmenu) {
            System.out.println("\n┌──────────────────────────────────────────┐");
            System.out.println("│           FLIGHT STATISTICS              │");
            System.out.println("├──────────────────────────────────────────┤");
            System.out.println("│  1. Travel Type Distribution             │");
            System.out.println("│  2. Travel Class Distribution            │");
            System.out.println("│  3. Flight Distance Statistics           │");
            System.out.println("│  4. Flight Distance Categories           │");
            System.out.println("│  5. Departure Delay Statistics           │");
            System.out.println("│  6. Arrival Delay Statistics             │");
            System.out.println("│  0. Back to Main Menu                    │");
            System.out.println("└──────────────────────────────────────────┘");
            
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    displayDistribution("TRAVEL TYPE DISTRIBUTION", processor.getTravelTypeDistribution());
                    break;
                case 2:
                    displayDistribution("TRAVEL CLASS DISTRIBUTION", processor.getTravelClassDistribution());
                    break;
                case 3:
                    displayStatistics("FLIGHT DISTANCE STATISTICS", processor.getFlightDistanceStatistics());
                    break;
                case 4:
                    displayDistribution("FLIGHT DISTANCE CATEGORIES", processor.getFlightDistanceDistribution());
                    break;
                case 5:
                    displayStatistics("DEPARTURE DELAY STATISTICS", processor.getDepartureDelayStatistics());
                    break;
                case 6:
                    displayStatistics("ARRIVAL DELAY STATISTICS", processor.getArrivalDelayStatistics());
                    break;
                case 0:
                    inSubmenu = false;
                    break;
                default:
                    System.out.println("\n⚠ Invalid choice. Please try again.");
            }
        }
    }
    
    /**
     * Display service ratings sub-menu
     */
    private static void displayServiceRatingsMenu() {
        boolean inSubmenu = true;
        while (inSubmenu) {
            System.out.println("\n┌──────────────────────────────────────────┐");
            System.out.println("│         SERVICE RATINGS ANALYSIS         │");
            System.out.println("├──────────────────────────────────────────┤");
            System.out.println("│  1. All Service Average Ratings          │");
            System.out.println("│  2. Top & Bottom Rated Services          │");
            System.out.println("│  0. Back to Main Menu                    │");
            System.out.println("└──────────────────────────────────────────┘");
            
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    displayServiceRatings();
                    break;
                case 2:
                    System.out.println("\n════════════ SERVICE RANKING SUMMARY ════════════");
                    System.out.println(processor.getServiceRankingSummary());
                    pressEnterToContinue();
                    break;
                case 0:
                    inSubmenu = false;
                    break;
                default:
                    System.out.println("\n⚠ Invalid choice. Please try again.");
            }
        }
    }
    
    /**
     * Display satisfaction analysis sub-menu
     */
    private static void displaySatisfactionAnalysisMenu() {
        boolean inSubmenu = true;
        while (inSubmenu) {
            System.out.println("\n┌──────────────────────────────────────────┐");
            System.out.println("│         SATISFACTION ANALYSIS            │");
            System.out.println("├──────────────────────────────────────────┤");
            System.out.println("│  1. Overall Satisfaction Distribution    │");
            System.out.println("│  2. Satisfaction by Travel Class         │");
            System.out.println("│  3. Satisfaction by Customer Type        │");
            System.out.println("│  4. Satisfaction by Travel Type          │");
            System.out.println("│  5. Satisfaction by Age Group            │");
            System.out.println("│  0. Back to Main Menu                    │");
            System.out.println("└──────────────────────────────────────────┘");
            
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    displayDistribution("SATISFACTION DISTRIBUTION", processor.getSatisfactionDistribution());
                    break;
                case 2:
                    displaySatisfactionRates("SATISFACTION BY TRAVEL CLASS", processor.getSatisfactionRateByClass());
                    break;
                case 3:
                    displaySatisfactionRates("SATISFACTION BY CUSTOMER TYPE", processor.getSatisfactionRateByCustomerType());
                    break;
                case 4:
                    displaySatisfactionRates("SATISFACTION BY TRAVEL TYPE", processor.getSatisfactionRateByTravelType());
                    break;
                case 5:
                    displaySatisfactionRates("SATISFACTION BY AGE GROUP", processor.getSatisfactionRateByAgeGroup());
                    break;
                case 0:
                    inSubmenu = false;
                    break;
                default:
                    System.out.println("\n⚠ Invalid choice. Please try again.");
            }
        }
    }
    
    /**
     * Display search and filter sub-menu
     */
    private static void displaySearchAndFilterMenu() {
        boolean inSubmenu = true;
        while (inSubmenu) {
            System.out.println("\n┌──────────────────────────────────────────┐");
            System.out.println("│           SEARCH & FILTER                │");
            System.out.println("├──────────────────────────────────────────┤");
            System.out.println("│  1. Search by Passenger ID               │");
            System.out.println("│  2. Filter by Travel Class               │");
            System.out.println("│  3. Filter by Age Range                  │");
            System.out.println("│  4. View Sample Records (First 10)       │");
            System.out.println("│  0. Back to Main Menu                    │");
            System.out.println("└──────────────────────────────────────────┘");
            
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    searchById();
                    break;
                case 2:
                    filterByClass();
                    break;
                case 3:
                    filterByAgeRange();
                    break;
                case 4:
                    displaySampleRecords();
                    break;
                case 0:
                    inSubmenu = false;
                    break;
                default:
                    System.out.println("\n⚠ Invalid choice. Please try again.");
            }
        }
    }
    
    /**
     * Search for a passenger by ID
     */
    private static void searchById() {
        int id = getIntInput("Enter Passenger ID to search: ");
        Optional<PassengerRecord> result = processor.searchById(id);
        
        System.out.println("\n════════════ SEARCH RESULT ════════════");
        if (result.isPresent()) {
            PassengerRecord r = result.get();
            System.out.println("  ✓ Passenger Found!");
            System.out.println("  ─────────────────────────────");
            System.out.printf("  ID: %d\n", r.getId());
            System.out.printf("  Gender: %s\n", r.getGender());
            System.out.printf("  Age: %d\n", r.getAge());
            System.out.printf("  Customer Type: %s\n", r.getCustomerType());
            System.out.printf("  Type of Travel: %s\n", r.getTypeOfTravel());
            System.out.printf("  Travel Class: %s\n", r.getTravelClass());
            System.out.printf("  Flight Distance: %,d miles\n", r.getFlightDistance());
            System.out.printf("  Departure Delay: %.1f min\n", r.getDepartureDelayInMinutes());
            System.out.printf("  Arrival Delay: %.1f min\n", r.getArrivalDelayInMinutes());
            System.out.printf("  Average Service Rating: %.2f/5.00\n", r.getAverageServiceRating());
            System.out.printf("  Satisfaction: %s\n", r.getSatisfaction());
        } else {
            System.out.println("  ✗ No passenger found with ID: " + id);
        }
        
        pressEnterToContinue();
    }
    
    /**
     * Filter records by travel class
     */
    private static void filterByClass() {
        System.out.println("\nAvailable Classes: Business, Eco, Eco Plus");
        System.out.print("Enter travel class to filter: ");
        String travelClass = scanner.nextLine().trim();
        
        List<PassengerRecord> filtered = processor.filterByClass(travelClass);
        
        System.out.println("\n════════════ FILTER RESULTS ════════════");
        System.out.printf("  Records in '%s' class: %,d\n", travelClass, filtered.size());
        
        if (!filtered.isEmpty()) {
            long satisfied = filtered.stream().filter(PassengerRecord::isSatisfied).count();
            System.out.printf("  Satisfied: %,d (%.1f%%)\n", satisfied, (satisfied * 100.0) / filtered.size());
            System.out.printf("  Dissatisfied: %,d (%.1f%%)\n", 
                    filtered.size() - satisfied, ((filtered.size() - satisfied) * 100.0) / filtered.size());
            
            double avgAge = filtered.stream().mapToInt(PassengerRecord::getAge).average().orElse(0);
            System.out.printf("  Average Age: %.1f years\n", avgAge);
            
            double avgDistance = filtered.stream().mapToInt(PassengerRecord::getFlightDistance).average().orElse(0);
            System.out.printf("  Average Flight Distance: %.1f miles\n", avgDistance);
        }
        
        pressEnterToContinue();
    }
    
    /**
     * Filter records by age range
     */
    private static void filterByAgeRange() {
        int minAge = getIntInput("Enter minimum age: ");
        int maxAge = getIntInput("Enter maximum age: ");
        
        List<PassengerRecord> filtered = processor.filterByAgeRange(minAge, maxAge);
        
        System.out.println("\n════════════ FILTER RESULTS ════════════");
        System.out.printf("  Passengers aged %d-%d: %,d\n", minAge, maxAge, filtered.size());
        
        if (!filtered.isEmpty()) {
            long satisfied = filtered.stream().filter(PassengerRecord::isSatisfied).count();
            System.out.printf("  Satisfied: %,d (%.1f%%)\n", satisfied, (satisfied * 100.0) / filtered.size());
            
            // Class distribution
            System.out.println("\n  Class Distribution:");
            filtered.stream()
                    .collect(java.util.stream.Collectors.groupingBy(
                            PassengerRecord::getTravelClass, 
                            java.util.stream.Collectors.counting()))
                    .forEach((k, v) -> System.out.printf("    - %s: %,d\n", k, v));
        }
        
        pressEnterToContinue();
    }
    
    /**
     * Display sample records
     */
    private static void displaySampleRecords() {
        System.out.println("\n════════════ SAMPLE RECORDS (First 10) ════════════");
        System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.printf("%-8s %-8s %-5s %-18s %-18s %-10s %-12s %-12s\n",
                "ID", "Gender", "Age", "Customer Type", "Travel Type", "Class", "Distance", "Satisfaction");
        System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────");
        
        records.stream().limit(10).forEach(r -> 
            System.out.printf("%-8d %-8s %-5d %-18s %-18s %-10s %-12d %-12s\n",
                    r.getId(), r.getGender(), r.getAge(), 
                    truncate(r.getCustomerType(), 16),
                    truncate(r.getTypeOfTravel(), 16),
                    r.getTravelClass(), r.getFlightDistance(), r.getSatisfaction())
        );
        
        System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────");
        pressEnterToContinue();
    }
    
    /**
     * Display comprehensive report
     */
    private static void displayComprehensiveReport() {
        System.out.println(processor.generateComprehensiveSummary());
        pressEnterToContinue();
    }
    
    // ==================== HELPER METHODS ====================
    
    /**
     * Display a distribution map
     */
    private static void displayDistribution(String title, Map<String, Long> distribution) {
        System.out.println("\n════════════ " + title + " ════════════");
        long total = distribution.values().stream().mapToLong(Long::longValue).sum();
        
        distribution.forEach((key, value) -> {
            double percentage = (value * 100.0) / total;
            int barLength = (int) (percentage / 2);
            String bar = "█".repeat(Math.max(1, barLength));
            System.out.printf("  %-25s: %,7d (%5.1f%%) %s\n", key, value, percentage, bar);
        });
        
        System.out.printf("\n  Total: %,d\n", total);
        pressEnterToContinue();
    }
    
    /**
     * Display statistics map
     */
    private static void displayStatistics(String title, Map<String, Double> stats) {
        System.out.println("\n════════════ " + title + " ════════════");
        
        stats.forEach((key, value) -> {
            if (key.contains("Count") || key.contains("Flights") || key.contains("Total")) {
                System.out.printf("  %-25s: %,.0f\n", key, value);
            } else {
                System.out.printf("  %-25s: %,.2f\n", key, value);
            }
        });
        
        pressEnterToContinue();
    }
    
    /**
     * Display service ratings
     */
    private static void displayServiceRatings() {
        System.out.println("\n════════════ ALL SERVICE RATINGS (Average out of 5.00) ════════════");
        
        Map<String, Double> ratings = processor.getAverageServiceRatings();
        
        ratings.forEach((service, rating) -> {
            int filledStars = (int) Math.round(rating);
            String stars = "★".repeat(filledStars) + "☆".repeat(5 - filledStars);
            System.out.printf("  %-35s: %.2f %s\n", service, rating, stars);
        });
        
        double overall = ratings.values().stream().mapToDouble(Double::doubleValue).average().orElse(0);
        System.out.println("\n  ─────────────────────────────────────────────");
        System.out.printf("  %-35s: %.2f\n", "OVERALL AVERAGE", overall);
        
        pressEnterToContinue();
    }
    
    /**
     * Display satisfaction rates
     */
    private static void displaySatisfactionRates(String title, Map<String, Double> rates) {
        System.out.println("\n════════════ " + title + " ════════════");
        
        rates.forEach((category, rate) -> {
            int barLength = (int) (rate / 2);
            String bar = "█".repeat(Math.max(1, barLength));
            System.out.printf("  %-20s: %5.1f%% satisfied %s\n", category, rate, bar);
        });
        
        pressEnterToContinue();
    }
    
    /**
     * Get integer input from user
     */
    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return value;
    }
    
    /**
     * Truncate string to specified length
     */
    private static String truncate(String str, int length) {
        if (str.length() <= length) {
            return str;
        }
        return str.substring(0, length - 2) + "..";
    }
    
    /**
     * Wait for user to press Enter
     */
    private static void pressEnterToContinue() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
}
