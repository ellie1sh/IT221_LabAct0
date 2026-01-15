package util;

import model.PassengerRecord;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CSVReader - A utility class for reading CSV files.
 * 
 * <p>CSV stands for "Comma Separated Values". It's a simple file format
 * where each line is a record and values are separated by commas.</p>
 * 
 * <h2>Example CSV line:</h2>
 * <pre>1,John,25,Male</pre>
 * <p>This has 4 values: 1, John, 25, Male</p>
 * 
 * <h2>How this class works:</h2>
 * <ol>
 *   <li>Opens the CSV file</li>
 *   <li>Reads each line one by one</li>
 *   <li>Splits each line by commas</li>
 *   <li>Creates a PassengerRecord object for each line</li>
 *   <li>Returns a list of all records</li>
 * </ol>
 * 
 * @author IT221 Student
 * @version 1.0
 */
public class CSVReader {
    
    /**
     * Path to the CSV file we want to read.
     */
    private String filePath;
    
    /**
     * Array to store column names from the first line.
     */
    private String[] headers;
    
    /**
     * Creates a new CSVReader for the given file.
     * 
     * @param filePath the path to the CSV file
     */
    public CSVReader(String filePath) {
        this.filePath = filePath;
    }
    
    /**
     * Reads all records from the CSV file.
     * 
     * <p>This method does the following:</p>
     * <ol>
     *   <li>Opens the file using BufferedReader</li>
     *   <li>Reads the header line (column names)</li>
     *   <li>Reads each data line and converts it to a PassengerRecord</li>
     *   <li>Returns a list of all records</li>
     * </ol>
     * 
     * @return a List containing all passenger records
     * @throws IOException if the file cannot be read
     */
    public List<PassengerRecord> readAllRecords() throws IOException {
        // Create an empty list to store records
        List<PassengerRecord> records = new ArrayList<>();
        
        // Try-with-resources: automatically closes the file when done
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        
        try {
            // Read the first line (header with column names)
            String headerLine = reader.readLine();
            if (headerLine != null) {
                headers = headerLine.split(",");
            }
            
            // Read each data line
            String line;
            int lineNumber = 0;
            
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                
                // Try to parse this line into a record
                try {
                    PassengerRecord record = parseLine(line, lineNumber);
                    if (record != null) {
                        records.add(record);
                    }
                } catch (Exception e) {
                    // If there's an error, print a warning and skip this line
                    System.out.println("Warning: Skipping line " + lineNumber 
                                     + " - Error: " + e.getMessage());
                }
            }
        } finally {
            // Always close the reader when done
            reader.close();
        }
        
        return records;
    }
    
    /**
     * Parses a single CSV line into a PassengerRecord.
     * 
     * <p>Each line contains values separated by commas. This method
     * splits the line and assigns each value to the correct field.</p>
     * 
     * @param line the CSV line to parse
     * @param lineNumber the line number (for error messages)
     * @return a PassengerRecord object, or null if parsing fails
     */
    private PassengerRecord parseLine(String line, int lineNumber) {
        // Split the line by commas
        String[] values = line.split(",");
        
        // Make sure we have enough values (26 columns including Date)
        if (values.length < 26) {
            return null;
        }
        
        // Create a new record and fill in the values
        PassengerRecord record = new PassengerRecord();
        
        // Use try-catch to handle any parsing errors
        try {
            // Column 0: Row index (integer)
            record.setRowIndex(parseInteger(values[0], 0));
            
            // Column 1: Passenger ID (integer)
            record.setId(parseInteger(values[1], 0));
            
            // Column 2: Gender (string)
            record.setGender(values[2].trim());
            
            // Column 3: Customer Type (string)
            record.setCustomerType(values[3].trim());
            
            // Column 4: Age (integer)
            record.setAge(parseInteger(values[4], 0));
            
            // Column 5: Type of Travel (string)
            record.setTypeOfTravel(values[5].trim());
            
            // Column 6: Travel Class (string)
            record.setTravelClass(values[6].trim());
            
            // Column 7: Flight Distance (integer)
            record.setFlightDistance(parseInteger(values[7], 0));
            
            // Columns 8-21: Service Ratings (integers, 0-5 scale)
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
            
            // Column 22: Departure Delay (decimal/double)
            record.setDepartureDelayInMinutes(parseDouble(values[22], 0.0));
            
            // Column 23: Arrival Delay (decimal/double)
            record.setArrivalDelayInMinutes(parseDouble(values[23], 0.0));
            
            // Column 24: Satisfaction (string)
            record.setSatisfaction(values[24].trim());
            
            // Column 25: Date (string in month:date:year format)
            record.setDate(values[25].trim());
            
            return record;
            
        } catch (Exception e) {
            throw new RuntimeException("Error at line " + lineNumber + ": " + e.getMessage());
        }
    }
    
    /**
     * Safely converts a string to an integer.
     * 
     * <p>If the string is empty or invalid, returns the default value
     * instead of crashing the program.</p>
     * 
     * @param value the string to convert
     * @param defaultValue the value to use if conversion fails
     * @return the integer value, or defaultValue if parsing fails
     */
    private int parseInteger(String value, int defaultValue) {
        // Check if the value is empty
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        
        // Try to convert the string to an integer
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            // If it's not a valid number, return the default
            return defaultValue;
        }
    }
    
    /**
     * Safely converts a string to a double (decimal number).
     * 
     * <p>If the string is empty or invalid, returns the default value
     * instead of crashing the program.</p>
     * 
     * @param value the string to convert
     * @param defaultValue the value to use if conversion fails
     * @return the double value, or defaultValue if parsing fails
     */
    private double parseDouble(String value, double defaultValue) {
        // Check if the value is empty
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        
        // Try to convert the string to a double
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            // If it's not a valid number, return the default
            return defaultValue;
        }
    }
    
    /**
     * Gets the column headers from the CSV file.
     * 
     * @return array of column names
     */
    public String[] getHeaders() {
        return headers;
    }
    
    /**
     * Gets the file path.
     * 
     * @return the path to the CSV file
     */
    public String getFilePath() {
        return filePath;
    }
}
