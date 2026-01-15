import model.PassengerRecord;
import processor.DataProcessor;
import util.CSVReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Main class for Airline Passenger Satisfaction Analysis.
 * IT221 - Information Management, Lab Activity 0
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
        System.out.println("\n=================================");
        System.out.println("  Airline Satisfaction Analysis");
        System.out.println("  IT221 - Lab Activity 0");
        System.out.println("=================================\n");
        
        if (!loadDataset()) {
            System.out.println("Failed to load dataset. Exiting.");
            return;
        }
        
        processor = new DataProcessor(records);
        System.out.println("Loaded " + processor.getTotalRecords() + " records.\n");
        
        boolean running = true;
        while (running) {
            showMenu();
            int choice = getIntInput("Choice: ");
            
            if (choice == 1) showOverview();
            else if (choice == 2) showDemographics();
            else if (choice == 3) showFlightStats();
            else if (choice == 4) showServiceRatings();
            else if (choice == 5) showSatisfactionAnalysis();
            else if (choice == 6) showSearchFilter();
            else if (choice == 7) showReport();
            else if (choice == 0) {
                running = false;
                System.out.println("\nGoodbye!");
            }
            else System.out.println("Invalid choice.");
        }
        scanner.close();
    }
    
    private static boolean loadDataset() {
        System.out.print("Loading " + CSV_FILE_PATH + "... ");
        try {
            CSVReader reader = new CSVReader(CSV_FILE_PATH);
            records = reader.readAllRecords();
            System.out.println("Done.");
            return true;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    private static void showMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Dataset Overview");
        System.out.println("2. Demographics");
        System.out.println("3. Flight Statistics");
        System.out.println("4. Service Ratings");
        System.out.println("5. Satisfaction Analysis");
        System.out.println("6. Search & Filter");
        System.out.println("7. Full Report");
        System.out.println("0. Exit");
    }
    
    private static void showOverview() {
        System.out.println("\n--- DATASET OVERVIEW ---");
        System.out.println("Total Records: " + processor.getTotalRecords());
        
        Map<String, Long> sat = processor.getSatisfactionDistribution();
        long satisfied = sat.getOrDefault("satisfied", 0L);
        long total = processor.getTotalRecords();
        
        System.out.println("Satisfied: " + satisfied + " (" + pct(satisfied, total) + ")");
        System.out.println("Dissatisfied: " + (total - satisfied) + " (" + pct(total - satisfied, total) + ")");
        
        pause();
    }
    
    private static void showDemographics() {
        boolean sub = true;
        while (sub) {
            System.out.println("\n--- DEMOGRAPHICS ---");
            System.out.println("1. Gender");
            System.out.println("2. Customer Type");
            System.out.println("3. Age Stats");
            System.out.println("4. Age Groups");
            System.out.println("0. Back");
            
            int c = getIntInput("Choice: ");
            if (c == 1) showDistribution("Gender", processor.getGenderDistribution());
            else if (c == 2) showDistribution("Customer Type", processor.getCustomerTypeDistribution());
            else if (c == 3) showStats("Age", processor.getAgeStatistics());
            else if (c == 4) showDistribution("Age Groups", processor.getAgeGroupDistribution());
            else if (c == 0) sub = false;
        }
    }
    
    private static void showFlightStats() {
        boolean sub = true;
        while (sub) {
            System.out.println("\n--- FLIGHT STATISTICS ---");
            System.out.println("1. Travel Type");
            System.out.println("2. Travel Class");
            System.out.println("3. Distance Stats");
            System.out.println("4. Distance Categories");
            System.out.println("5. Departure Delays");
            System.out.println("6. Arrival Delays");
            System.out.println("0. Back");
            
            int c = getIntInput("Choice: ");
            if (c == 1) showDistribution("Travel Type", processor.getTravelTypeDistribution());
            else if (c == 2) showDistribution("Travel Class", processor.getTravelClassDistribution());
            else if (c == 3) showStats("Distance", processor.getFlightDistanceStatistics());
            else if (c == 4) showDistribution("Distance", processor.getFlightDistanceDistribution());
            else if (c == 5) showStats("Departure Delay", processor.getDepartureDelayStatistics());
            else if (c == 6) showStats("Arrival Delay", processor.getArrivalDelayStatistics());
            else if (c == 0) sub = false;
        }
    }
    
    private static void showServiceRatings() {
        boolean sub = true;
        while (sub) {
            System.out.println("\n--- SERVICE RATINGS ---");
            System.out.println("1. All Ratings");
            System.out.println("2. Top & Bottom");
            System.out.println("0. Back");
            
            int c = getIntInput("Choice: ");
            if (c == 1) {
                System.out.println("\n--- Service Ratings (0-5) ---");
                Map<String, Double> ratings = processor.getAverageServiceRatings();
                for (String s : ratings.keySet()) {
                    System.out.printf("%-20s: %.2f\n", s, ratings.get(s));
                }
                pause();
            }
            else if (c == 2) {
                System.out.println("\n--- Service Ranking ---");
                System.out.println(processor.getServiceRankingSummary());
                pause();
            }
            else if (c == 0) sub = false;
        }
    }
    
    private static void showSatisfactionAnalysis() {
        boolean sub = true;
        while (sub) {
            System.out.println("\n--- SATISFACTION ANALYSIS ---");
            System.out.println("1. Overall");
            System.out.println("2. By Class");
            System.out.println("3. By Customer Type");
            System.out.println("4. By Travel Type");
            System.out.println("5. By Age Group");
            System.out.println("0. Back");
            
            int c = getIntInput("Choice: ");
            if (c == 1) showDistribution("Satisfaction", processor.getSatisfactionDistribution());
            else if (c == 2) showRates("By Class", processor.getSatisfactionRateByClass());
            else if (c == 3) showRates("By Customer Type", processor.getSatisfactionRateByCustomerType());
            else if (c == 4) showRates("By Travel Type", processor.getSatisfactionRateByTravelType());
            else if (c == 5) showRates("By Age Group", processor.getSatisfactionRateByAgeGroup());
            else if (c == 0) sub = false;
        }
    }
    
    private static void showSearchFilter() {
        boolean sub = true;
        while (sub) {
            System.out.println("\n--- SEARCH & FILTER ---");
            System.out.println("1. Search by ID");
            System.out.println("2. Filter by Class");
            System.out.println("3. Filter by Age");
            System.out.println("4. Sample Records");
            System.out.println("0. Back");
            
            int c = getIntInput("Choice: ");
            if (c == 1) searchById();
            else if (c == 2) filterByClass();
            else if (c == 3) filterByAge();
            else if (c == 4) showSample();
            else if (c == 0) sub = false;
        }
    }
    
    private static void searchById() {
        System.out.print("Enter ID (e.g., PAX0000001): ");
        String id = scanner.nextLine().trim();
        PassengerRecord r = processor.searchById(id);
        
        System.out.println("\n--- Search Result ---");
        if (r != null) {
            System.out.println("Found: ID=" + r.getId());
            System.out.println("  Gender: " + r.getGender());
            System.out.println("  Age: " + r.getAge());
            System.out.println("  Type: " + r.getCustomerType());
            System.out.println("  Class: " + r.getTravelClass());
            System.out.println("  Distance: " + r.getFlightDistance() + " mi");
            System.out.println("  Satisfaction: " + r.getSatisfaction());
            System.out.println("  Date: " + r.getDate());
        } else {
            System.out.println("Not found.");
        }
        pause();
    }
    
    private static void filterByClass() {
        System.out.print("Enter class (Business/Eco/Eco Plus): ");
        String cls = scanner.nextLine().trim();
        List<PassengerRecord> filtered = processor.filterByClass(cls);
        
        System.out.println("\n--- Filter Result ---");
        System.out.println("Class: " + cls);
        System.out.println("Records found: " + filtered.size());
        
        if (filtered.size() > 0) {
            int sat = 0;
            for (PassengerRecord r : filtered) {
                if (r.isSatisfied()) sat++;
            }
            System.out.println("Satisfied: " + sat + " (" + pct(sat, filtered.size()) + ")");
        }
        pause();
    }
    
    private static void filterByAge() {
        int min = getIntInput("Min age: ");
        int max = getIntInput("Max age: ");
        List<PassengerRecord> filtered = processor.filterByAgeRange(min, max);
        
        System.out.println("\n--- Filter Result ---");
        System.out.println("Age range: " + min + "-" + max);
        System.out.println("Records found: " + filtered.size());
        
        if (filtered.size() > 0) {
            int sat = 0;
            for (PassengerRecord r : filtered) {
                if (r.isSatisfied()) sat++;
            }
            System.out.println("Satisfied: " + sat + " (" + pct(sat, filtered.size()) + ")");
        }
        pause();
    }
    
    private static void showSample() {
        System.out.println("\n--- Sample Records (First 10) ---");
        System.out.printf("%-12s %-8s %-4s %-10s %-10s %-12s\n", "ID", "Gender", "Age", "Class", "Date", "Satisfied");
        
        int count = 0;
        for (PassengerRecord r : records) {
            if (count >= 10) break;
            System.out.printf("%-12s %-8s %-4d %-10s %-10s %-12s\n",
                r.getId(), r.getGender(), r.getAge(), 
                r.getTravelClass(), r.getDate(), r.getSatisfaction());
            count++;
        }
        pause();
    }
    
    private static void showReport() {
        System.out.println(processor.generateComprehensiveSummary());
        pause();
    }
    
    // Helper methods
    
    private static void showDistribution(String title, Map<String, Long> data) {
        System.out.println("\n--- " + title + " ---");
        long total = 0;
        for (Long v : data.values()) total += v;
        
        for (String k : data.keySet()) {
            long v = data.get(k);
            System.out.printf("%-25s: %6d (%s)\n", k, v, pct(v, total));
        }
        System.out.println("Total: " + total);
        pause();
    }
    
    private static void showStats(String title, Map<String, Double> data) {
        System.out.println("\n--- " + title + " ---");
        for (String k : data.keySet()) {
            double v = data.get(k);
            if (k.contains("Count") || k.contains("Flights") || k.contains("Total")) {
                System.out.printf("%-25s: %.0f\n", k, v);
            } else {
                System.out.printf("%-25s: %.2f\n", k, v);
            }
        }
        pause();
    }
    
    private static void showRates(String title, Map<String, Double> data) {
        System.out.println("\n--- Satisfaction " + title + " ---");
        for (String k : data.keySet()) {
            System.out.printf("%-20s: %.1f%% satisfied\n", k, data.get(k));
        }
        pause();
    }
    
    private static String pct(long val, long total) {
        return String.format("%.1f%%", val * 100.0 / total);
    }
    
    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Enter a number: ");
            scanner.next();
        }
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
    }
    
    private static void pause() {
        System.out.print("\nPress Enter...");
        scanner.nextLine();
    }
}
