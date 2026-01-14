import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String csvFile = "/workspace/data/dataset.csv";
        System.out.println("Loading data from " + csvFile + "...");
        
        CsvParser parser = new CsvParser();
        List<FlightRecord> records = parser.loadData(csvFile);
        
        if (records.isEmpty()) {
            System.out.println("No records found or failed to load data.");
            return;
        }

        DataManager manager = new DataManager(records);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Data loaded successfully! Total records: " + manager.getTotalRecords());

        while (running) {
            printMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Total Records: " + manager.getTotalRecords());
                    break;
                case "2":
                    System.out.printf("Average Arrival Delay: %.2f minutes%n", manager.getAverageArrivalDelay());
                    break;
                case "3":
                    System.out.print("Enter Class (Eco/Business/Eco Plus): ");
                    String flightClass = scanner.nextLine();
                    List<FlightRecord> classRecords = manager.getRecordsByClass(flightClass);
                    System.out.println("First 10 records for " + flightClass + ":");
                    classRecords.forEach(System.out::println);
                    if (classRecords.isEmpty()) System.out.println("No records found.");
                    break;
                case "4":
                    FlightRecord longest = manager.getLongestFlight();
                    System.out.println("Longest Flight: " + longest);
                    break;
                case "5":
                    System.out.print("Enter Flight ID to search: ");
                    try {
                        int searchId = Integer.parseInt(scanner.nextLine());
                        FlightRecord found = manager.findById(searchId);
                        if (found != null) {
                            System.out.println("Found: " + found);
                        } else {
                            System.out.println("Record not found.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID format.");
                    }
                    break;
                case "6":
                    System.out.println("Number of Satisfied Customers: " + manager.countSatisfiedCustomers());
                    break;
                case "0":
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("=== Flight Information System ===");
        System.out.println("1. Show Total Records");
        System.out.println("2. Show Average Arrival Delay");
        System.out.println("3. List Records by Class (Top 10)");
        System.out.println("4. Show Longest Flight Distance");
        System.out.println("5. Search by ID");
        System.out.println("6. Count Satisfied Customers");
        System.out.println("0. Exit");
        System.out.println("===============================");
    }
}
