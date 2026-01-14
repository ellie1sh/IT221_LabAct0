package util;

import model.PassengerRecord;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for reading and parsing CSV files.
 * Handles the airline satisfaction dataset format.
 */
public class CSVReader {
    
    private String filePath;
    private String[] headers;
    
    public CSVReader(String filePath) {
        this.filePath = filePath;
    }
    
    /**
     * Read all records from the CSV file
     * @return List of PassengerRecord objects
     * @throws IOException if file reading fails
     */
    public List<PassengerRecord> readAllRecords() throws IOException {
        List<PassengerRecord> records = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Read header line
            String headerLine = br.readLine();
            if (headerLine != null) {
                headers = headerLine.split(",");
            }
            
            // Read data lines
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                try {
                    PassengerRecord record = parseLine(line, lineNumber);
                    if (record != null) {
                        records.add(record);
                    }
                } catch (Exception e) {
                    System.err.println("Warning: Skipping line " + lineNumber + " due to parsing error: " + e.getMessage());
                }
            }
        }
        
        return records;
    }
    
    /**
     * Parse a single CSV line into a PassengerRecord
     * @param line CSV line to parse
     * @param lineNumber Line number for error reporting
     * @return PassengerRecord object or null if parsing fails
     */
    private PassengerRecord parseLine(String line, int lineNumber) {
        String[] values = line.split(",");
        
        if (values.length < 25) {
            return null;
        }
        
        PassengerRecord record = new PassengerRecord();
        
        try {
            // Parse each field with appropriate type handling
            record.setRowIndex(parseInteger(values[0], 0));
            record.setId(parseInteger(values[1], 0));
            record.setGender(values[2].trim());
            record.setCustomerType(values[3].trim());
            record.setAge(parseInteger(values[4], 0));
            record.setTypeOfTravel(values[5].trim());
            record.setTravelClass(values[6].trim());
            record.setFlightDistance(parseInteger(values[7], 0));
            record.setInflightWifiService(parseInteger(values[8], 0));
            record.setDepartureArrivalTimeConvenient(parseInteger(values[9], 0));
            record.setEaseOfOnlineBooking(parseInteger(values[10], 0));
            record.setGateLocation(parseInteger(values[11], 0));
            record.setFoodAndDrink(parseInteger(values[12], 0));
            record.setOnlineBoarding(parseInteger(values[13], 0));
            record.setSeatComfort(parseInteger(values[14], 0));
            record.setInflightEntertainment(parseInteger(values[15], 0));
            record.setOnBoardService(parseInteger(values[16], 0));
            record.setLegRoomService(parseInteger(values[17], 0));
            record.setBaggageHandling(parseInteger(values[18], 0));
            record.setCheckinService(parseInteger(values[19], 0));
            record.setInflightService(parseInteger(values[20], 0));
            record.setCleanliness(parseInteger(values[21], 0));
            record.setDepartureDelayInMinutes(parseDouble(values[22], 0.0));
            record.setArrivalDelayInMinutes(parseDouble(values[23], 0.0));
            record.setSatisfaction(values[24].trim());
            
            return record;
        } catch (Exception e) {
            throw new RuntimeException("Error parsing line " + lineNumber + ": " + e.getMessage());
        }
    }
    
    /**
     * Safely parse an integer value
     * @param value String value to parse
     * @param defaultValue Default value if parsing fails
     * @return Parsed integer or default value
     */
    private int parseInteger(String value, int defaultValue) {
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    
    /**
     * Safely parse a double value
     * @param value String value to parse
     * @param defaultValue Default value if parsing fails
     * @return Parsed double or default value
     */
    private double parseDouble(String value, double defaultValue) {
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    
    /**
     * Get the CSV headers
     * @return Array of column headers
     */
    public String[] getHeaders() {
        return headers;
    }
    
    /**
     * Get the file path
     * @return File path string
     */
    public String getFilePath() {
        return filePath;
    }
}
