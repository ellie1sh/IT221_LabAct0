import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {
    private static final String CSV_SPLIT_BY = ",";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<FlightRecord> loadData(String csvFile) {
        List<FlightRecord> records = new ArrayList<>();
        String line = "";
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Skip header
            br.readLine();

            while ((line = br.readLine()) != null) {
                // Handle potential commas inside quotes if necessary, but this dataset seems simple.
                // Using simple split for now as per inspection.
                String[] data = line.split(CSV_SPLIT_BY);

                // Check if we have enough columns (at least 26 based on our inspection)
                if (data.length >= 26) {
                    try {
                        // Index 0 is the row index, start mapping from 1
                        int id = Integer.parseInt(data[1]);
                        String gender = data[2];
                        String customerType = data[3];
                        int age = Integer.parseInt(data[4]);
                        String typeOfTravel = data[5];
                        String flightClass = data[6];
                        int flightDistance = Integer.parseInt(data[7]);
                        // ... skip some ratings ...
                        int departureDelay = Integer.parseInt(data[22]);
                        double arrivalDelay = Double.parseDouble(data[23]);
                        String satisfaction = data[24];
                        LocalDate flightDate = LocalDate.parse(data[25], DATE_FORMATTER);

                        FlightRecord record = new FlightRecord(
                            id, gender, customerType, age, typeOfTravel, flightClass,
                            flightDistance, departureDelay, arrivalDelay, satisfaction, flightDate
                        );
                        records.add(record);
                    } catch (NumberFormatException | java.time.format.DateTimeParseException e) {
                        // System.err.println("Skipping invalid row: " + line);
                        // Continue to next row
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }
}
