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
        printHeader();
        
        if (!loadDataset()) {
            System.out.println("  Failed to load dataset. Exiting.");
            return;
        }
        
        processor = new DataProcessor(records);
        System.out.println("  Loaded " + processor.getTotalRecords() + " records.\n");
        
        boolean running = true;
        while (running) {
            showMenu();
            int choice = getIntInput("  Enter choice: ");
            
            if (choice == 1) showOverview();
            else if (choice == 2) showDemographics();
            else if (choice == 3) showFlightStats();
            else if (choice == 4) showServiceRatings();
            else if (choice == 5) showSatisfactionAnalysis();
            else if (choice == 6) showSearchFilter();
            else if (choice == 7) showReport();
            else if (choice == 0) {
                running = false;
                printBox("Goodbye! Thank you for using the system.", 40);
            }
            else System.out.println("  Invalid choice. Try again.");
        }
        scanner.close();
    }
    
    private static void printHeader() {
        System.out.println();
        System.out.println("+------------------------------------------+");
        System.out.println("|                                          |");
        System.out.println("|    AIRLINE SATISFACTION ANALYSIS         |");
        System.out.println("|    IT221 - Lab Activity 0                |");
        System.out.println("|                                          |");
        System.out.println("+------------------------------------------+");
        System.out.println();
    }
    
    private static boolean loadDataset() {
        System.out.print("  Loading " + CSV_FILE_PATH + "... ");
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
        System.out.println();
        System.out.println("+----------------------------------+");
        System.out.println("|           MAIN MENU              |");
        System.out.println("+----------------------------------+");
        System.out.println("|  [1]  Dataset Overview           |");
        System.out.println("|  [2]  Demographics               |");
        System.out.println("|  [3]  Flight Statistics          |");
        System.out.println("|  [4]  Service Ratings            |");
        System.out.println("|  [5]  Satisfaction Analysis      |");
        System.out.println("|  [6]  Search & Filter            |");
        System.out.println("|  [7]  Full Report                |");
        System.out.println("|  [0]  Exit                       |");
        System.out.println("+----------------------------------+");
    }
    
    private static void showOverview() {
        System.out.println();
        System.out.println("+----------------------------------+");
        System.out.println("|        DATASET OVERVIEW          |");
        System.out.println("+----------------------------------+");
        
        Map<String, Long> sat = processor.getSatisfactionDistribution();
        long satisfied = sat.getOrDefault("satisfied", 0L);
        long total = processor.getTotalRecords();
        
        System.out.println("  Total Records   : " + total);
        System.out.println("  Satisfied       : " + satisfied + " (" + pct(satisfied, total) + ")");
        System.out.println("  Dissatisfied    : " + (total - satisfied) + " (" + pct(total - satisfied, total) + ")");
        System.out.println("+----------------------------------+");
        
        pause();
    }
    
    private static void showDemographics() {
        boolean sub = true;
        while (sub) {
            System.out.println();
            System.out.println("+----------------------------------+");
            System.out.println("|          DEMOGRAPHICS            |");
            System.out.println("+----------------------------------+");
            System.out.println("|  [1]  Gender Distribution        |");
            System.out.println("|  [2]  Customer Type              |");
            System.out.println("|  [3]  Age Statistics             |");
            System.out.println("|  [4]  Age Groups                 |");
            System.out.println("|  [0]  Back to Main Menu          |");
            System.out.println("+----------------------------------+");
            
            int c = getIntInput("  Enter choice: ");
            if (c == 1) showDistribution("Gender Distribution", processor.getGenderDistribution());
            else if (c == 2) showDistribution("Customer Type", processor.getCustomerTypeDistribution());
            else if (c == 3) showStats("Age Statistics", processor.getAgeStatistics());
            else if (c == 4) showDistribution("Age Groups", processor.getAgeGroupDistribution());
            else if (c == 0) sub = false;
        }
    }
    
    private static void showFlightStats() {
        boolean sub = true;
        while (sub) {
            System.out.println();
            System.out.println("+----------------------------------+");
            System.out.println("|       FLIGHT STATISTICS          |");
            System.out.println("+----------------------------------+");
            System.out.println("|  [1]  Travel Type                |");
            System.out.println("|  [2]  Travel Class               |");
            System.out.println("|  [3]  Distance Statistics        |");
            System.out.println("|  [4]  Distance Categories        |");
            System.out.println("|  [5]  Departure Delays           |");
            System.out.println("|  [6]  Arrival Delays             |");
            System.out.println("|  [0]  Back to Main Menu          |");
            System.out.println("+----------------------------------+");
            
            int c = getIntInput("  Enter choice: ");
            if (c == 1) showDistribution("Travel Type", processor.getTravelTypeDistribution());
            else if (c == 2) showDistribution("Travel Class", processor.getTravelClassDistribution());
            else if (c == 3) showStats("Flight Distance", processor.getFlightDistanceStatistics());
            else if (c == 4) showDistribution("Distance Categories", processor.getFlightDistanceDistribution());
            else if (c == 5) showStats("Departure Delay", processor.getDepartureDelayStatistics());
            else if (c == 6) showStats("Arrival Delay", processor.getArrivalDelayStatistics());
            else if (c == 0) sub = false;
        }
    }
    
    private static void showServiceRatings() {
        boolean sub = true;
        while (sub) {
            System.out.println();
            System.out.println("+----------------------------------+");
            System.out.println("|        SERVICE RATINGS           |");
            System.out.println("+----------------------------------+");
            System.out.println("|  [1]  All Service Ratings        |");
            System.out.println("|  [2]  Top & Bottom Services      |");
            System.out.println("|  [0]  Back to Main Menu          |");
            System.out.println("+----------------------------------+");
            
            int c = getIntInput("  Enter choice: ");
            if (c == 1) {
                System.out.println();
                System.out.println("+--------------------------------------+");
                System.out.println("|    SERVICE RATINGS (Scale: 0-5)      |");
                System.out.println("+--------------------------------------+");
                Map<String, Double> ratings = processor.getAverageServiceRatings();
                for (String s : ratings.keySet()) {
                    System.out.printf("  %-22s : %.2f\n", s, ratings.get(s));
                }
                System.out.println("+--------------------------------------+");
                pause();
            }
            else if (c == 2) {
                System.out.println();
                System.out.println("+--------------------------------------+");
                System.out.println("|        SERVICE RANKING               |");
                System.out.println("+--------------------------------------+");
                System.out.println(processor.getServiceRankingSummary());
                System.out.println("+--------------------------------------+");
                pause();
            }
            else if (c == 0) sub = false;
        }
    }
    
    private static void showSatisfactionAnalysis() {
        boolean sub = true;
        while (sub) {
            System.out.println();
            System.out.println("+----------------------------------+");
            System.out.println("|     SATISFACTION ANALYSIS        |");
            System.out.println("+----------------------------------+");
            System.out.println("|  [1]  Overall Satisfaction       |");
            System.out.println("|  [2]  By Travel Class            |");
            System.out.println("|  [3]  By Customer Type           |");
            System.out.println("|  [4]  By Travel Type             |");
            System.out.println("|  [5]  By Age Group               |");
            System.out.println("|  [0]  Back to Main Menu          |");
            System.out.println("+----------------------------------+");
            
            int c = getIntInput("  Enter choice: ");
            if (c == 1) showDistribution("Satisfaction", processor.getSatisfactionDistribution());
            else if (c == 2) showRates("By Travel Class", processor.getSatisfactionRateByClass());
            else if (c == 3) showRates("By Customer Type", processor.getSatisfactionRateByCustomerType());
            else if (c == 4) showRates("By Travel Type", processor.getSatisfactionRateByTravelType());
            else if (c == 5) showRates("By Age Group", processor.getSatisfactionRateByAgeGroup());
            else if (c == 0) sub = false;
        }
    }
    
    private static void showSearchFilter() {
        boolean sub = true;
        while (sub) {
            System.out.println();
            System.out.println("+----------------------------------+");
            System.out.println("|        SEARCH & FILTER           |");
            System.out.println("+----------------------------------+");
            System.out.println("|  [1]  Search by ID               |");
            System.out.println("|  [2]  Filter by Class            |");
            System.out.println("|  [3]  Filter by Age Range        |");
            System.out.println("|  [4]  View Sample Records        |");
            System.out.println("|  [0]  Back to Main Menu          |");
            System.out.println("+----------------------------------+");
            
            int c = getIntInput("  Enter choice: ");
            if (c == 1) searchById();
            else if (c == 2) filterByClass();
            else if (c == 3) filterByAge();
            else if (c == 4) showSample();
            else if (c == 0) sub = false;
        }
    }
    
    private static void searchById() {
        System.out.print("  Enter ID (e.g., PAX0000001): ");
        String id = scanner.nextLine().trim();
        PassengerRecord r = processor.searchById(id);
        
        System.out.println();
        System.out.println("+--------------------------------------+");
        System.out.println("|           SEARCH RESULT              |");
        System.out.println("+--------------------------------------+");
        if (r != null) {
            System.out.println("  ID           : " + r.getId());
            System.out.println("  Gender       : " + r.getGender());
            System.out.println("  Age          : " + r.getAge());
            System.out.println("  Customer     : " + r.getCustomerType());
            System.out.println("  Class        : " + r.getTravelClass());
            System.out.println("  Distance     : " + r.getFlightDistance() + " miles");
            System.out.println("  Satisfaction : " + r.getSatisfaction());
            System.out.println("  Date         : " + r.getDate());
        } else {
            System.out.println("  Record not found.");
        }
        System.out.println("+--------------------------------------+");
        pause();
    }
    
    private static void filterByClass() {
        System.out.print("  Enter class (Business/Eco/Eco Plus): ");
        String cls = scanner.nextLine().trim();
        List<PassengerRecord> filtered = processor.filterByClass(cls);
        
        System.out.println();
        System.out.println("+--------------------------------------+");
        System.out.println("|          FILTER BY CLASS             |");
        System.out.println("+--------------------------------------+");
        System.out.println("  Class         : " + cls);
        System.out.println("  Records Found : " + filtered.size());
        
        if (filtered.size() > 0) {
            int sat = 0;
            for (PassengerRecord r : filtered) {
                if (r.isSatisfied()) sat++;
            }
            System.out.println("  Satisfied     : " + sat + " (" + pct(sat, filtered.size()) + ")");
        }
        System.out.println("+--------------------------------------+");
        pause();
    }
    
    private static void filterByAge() {
        int min = getIntInput("  Enter minimum age: ");
        int max = getIntInput("  Enter maximum age: ");
        List<PassengerRecord> filtered = processor.filterByAgeRange(min, max);
        
        System.out.println();
        System.out.println("+--------------------------------------+");
        System.out.println("|         FILTER BY AGE RANGE          |");
        System.out.println("+--------------------------------------+");
        System.out.println("  Age Range     : " + min + " - " + max);
        System.out.println("  Records Found : " + filtered.size());
        
        if (filtered.size() > 0) {
            int sat = 0;
            for (PassengerRecord r : filtered) {
                if (r.isSatisfied()) sat++;
            }
            System.out.println("  Satisfied     : " + sat + " (" + pct(sat, filtered.size()) + ")");
        }
        System.out.println("+--------------------------------------+");
        pause();
    }
    
    private static void showSample() {
        System.out.println();
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                        SAMPLE RECORDS (First 10)                           |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.printf("| %-12s | %-8s | %-4s | %-10s | %-10s | %-15s |\n", 
            "ID", "Gender", "Age", "Class", "Date", "Satisfaction");
        System.out.println("+----------------------------------------------------------------------------+");
        
        int count = 0;
        for (PassengerRecord r : records) {
            if (count >= 10) break;
            System.out.printf("| %-12s | %-8s | %-4d | %-10s | %-10s | %-15s |\n",
                r.getId(), r.getGender(), r.getAge(), 
                r.getTravelClass(), r.getDate(), r.getSatisfaction());
            count++;
        }
        System.out.println("+----------------------------------------------------------------------------+");
        pause();
    }
    
    private static void showReport() {
        System.out.println();
        System.out.println("+==========================================+");
        System.out.println("|          COMPREHENSIVE REPORT            |");
        System.out.println("+==========================================+");
        System.out.println(processor.generateComprehensiveSummary());
        System.out.println("+==========================================+");
        pause();
    }
    
    // Helper methods
    
    private static void printBox(String message, int width) {
        System.out.println();
        System.out.println("+" + repeat("-", width) + "+");
        int padding = (width - message.length()) / 2;
        System.out.println("|" + repeat(" ", padding) + message + repeat(" ", width - padding - message.length()) + "|");
        System.out.println("+" + repeat("-", width) + "+");
        System.out.println();
    }
    
    private static String repeat(String str, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
    
    private static void showDistribution(String title, Map<String, Long> data) {
        System.out.println();
        System.out.println("+--------------------------------------+");
        System.out.printf("|  %-36s|\n", title.toUpperCase());
        System.out.println("+--------------------------------------+");
        
        long total = 0;
        for (Long v : data.values()) total += v;
        
        for (String k : data.keySet()) {
            long v = data.get(k);
            System.out.printf("  %-22s : %6d (%s)\n", k, v, pct(v, total));
        }
        System.out.println("  --------------------------------");
        System.out.println("  Total                  : " + total);
        System.out.println("+--------------------------------------+");
        pause();
    }
    
    private static void showStats(String title, Map<String, Double> data) {
        System.out.println();
        System.out.println("+--------------------------------------+");
        System.out.printf("|  %-36s|\n", title.toUpperCase());
        System.out.println("+--------------------------------------+");
        
        for (String k : data.keySet()) {
            double v = data.get(k);
            if (k.contains("Count") || k.contains("Flights") || k.contains("Total")) {
                System.out.printf("  %-22s : %.0f\n", k, v);
            } else {
                System.out.printf("  %-22s : %.2f\n", k, v);
            }
        }
        System.out.println("+--------------------------------------+");
        pause();
    }
    
    private static void showRates(String title, Map<String, Double> data) {
        System.out.println();
        System.out.println("+--------------------------------------+");
        System.out.printf("|  SATISFACTION %s\n", title.toUpperCase());
        System.out.println("+--------------------------------------+");
        
        for (String k : data.keySet()) {
            System.out.printf("  %-22s : %.1f%% satisfied\n", k, data.get(k));
        }
        System.out.println("+--------------------------------------+");
        pause();
    }
    
    private static String pct(long val, long total) {
        return String.format("%.1f%%", val * 100.0 / total);
    }
    
    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("  Please enter a number: ");
            scanner.next();
        }
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
    }
    
    private static void pause() {
        System.out.print("\n  Press Enter to continue...");
        scanner.nextLine();
    }
}
