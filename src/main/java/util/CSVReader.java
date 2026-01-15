package util;

import model.PassengerRecord;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility class to read data from a CSV file.
 * <p>
 * This class handles opening the file, reading it line by line,
 * and converting each line of text into a {@link PassengerRecord} object.
 * </p>
 * 
 * @author IT221 Student
 */
public class CSVReader {
    
    private String filePath;
    
    /**
     * Constructor for CSVReader.
     * 
     * @param filePath The path to the CSV file to be read.
     */
    public CSVReader(String filePath) {
        this.filePath = filePath;
    }
    
    /**
     * Reads all records from the CSV file.
     * 
     * @return A list of PassengerRecord objects containing the data.
     * @throws IOException If there is an error reading the file.
     */
    public List<PassengerRecord> readAllRecords() throws IOException {
        // We use an ArrayList to store the records because we don't know
        // how many records there are in advance.
        List<PassengerRecord> records = new ArrayList<>();
        
        // Use BufferedReader for efficient reading of large files
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        
        // Read the first line (header) and ignore it
        String headerLine = reader.readLine();
        
        String line;
        int lineNumber = 1; // Start counting from 1 (header is line 1)
        
        // Loop through each line in the file
        while ((line = reader.readLine()) != null) {
            lineNumber++;
            
            // Convert the CSV line text into a PassengerRecord object
            PassengerRecord record = parseLine(line);
            
            // If conversion was successful, add it to our list
            if (record != null) {
                records.add(record);
            }
        }
        
        // Close the file reader to free up system resources
        reader.close();
        
        return records;
    }
    
    /**
     * Parses a single line of text from the CSV file into a PassengerRecord object.
     * 
     * @param line The comma-separated string from the file.
     * @return A PassengerRecord object, or null if the line is invalid.
     */
    private PassengerRecord parseLine(String line) {
        // Split the line by commas to get individual values
        String[] values = line.split(",");
        
        // We expect at least 26 columns now (including the new Date column)
        // If the line is too short, it's probably corrupt or empty
        if (values.length < 26) {
            return null;
        }
        
        PassengerRecord record = new PassengerRecord();
        
        try {
            // Column 0: Row Index (ignore or store if needed)
            // Column 1: ID
            record.setId(Integer.parseInt(values[1]));
            
            // Column 2: Gender
            record.setGender(values[2]);
            
            // Column 3: Customer Type
            record.setCustomerType(values[3]);
            
            // Column 4: Age
            record.setAge(Integer.parseInt(values[4]));
            
            // Column 5: Type of Travel
            record.setTypeOfTravel(values[5]);
            
            // Column 6: Class
            record.setTravelClass(values[6]);
            
            // Column 7: Flight Distance
            record.setFlightDistance(Integer.parseInt(values[7]));
            
            // Columns 8-21: Service Ratings
            record.setInflightWifiService(Integer.parseInt(values[8]));
            record.setDepartureArrivalTimeConvenient(Integer.parseInt(values[9]));
            record.setEaseOfOnlineBooking(Integer.parseInt(values[10]));
            record.setGateLocation(Integer.parseInt(values[11]));
            record.setFoodAndDrink(Integer.parseInt(values[12]));
            record.setOnlineBoarding(Integer.parseInt(values[13]));
            record.setSeatComfort(Integer.parseInt(values[14]));
            record.setInflightEntertainment(Integer.parseInt(values[15]));
            record.setOnBoardService(Integer.parseInt(values[16]));
            record.setLegRoomService(Integer.parseInt(values[17]));
            record.setBaggageHandling(Integer.parseInt(values[18]));
            record.setCheckinService(Integer.parseInt(values[19]));
            record.setInflightService(Integer.parseInt(values[20]));
            record.setCleanliness(Integer.parseInt(values[21]));
            
            // Columns 22-23: Delays (Double values)
            record.setDepartureDelayInMinutes(parseDoubleSafe(values[22]));
            record.setArrivalDelayInMinutes(parseDoubleSafe(values[23]));
            
            // Column 24: Satisfaction
            record.setSatisfaction(values[24]);

            // Column 25: Flight Date
            // We trim() to remove any extra whitespace or newlines
            record.setFlightDate(values[25].trim());
            
            return record;
            
        } catch (NumberFormatException e) {
            // If a number column contains bad data (like text), we return null to skip this record
            System.out.println("Error parsing line: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Helper to parse double, returning 0.0 if empty.
     */
    private double parseDoubleSafe(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0.0;
        }
        return Double.parseDouble(value);
    }
}
