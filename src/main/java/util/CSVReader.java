package util;

import model.PassengerRecord;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads and parses CSV files into PassengerRecord objects.
 * 
 * @author IT221 Student
 * @version 1.0
 */
public class CSVReader {
    
    private String filePath;
    private String[] headers;
    
    /** @param filePath path to CSV file */
    public CSVReader(String filePath) {
        this.filePath = filePath;
    }
    
    /**
     * Reads all records from the CSV file.
     * @return list of PassengerRecord objects
     * @throws IOException if file cannot be read
     */
    public List<PassengerRecord> readAllRecords() throws IOException {
        List<PassengerRecord> records = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        
        try {
            String headerLine = reader.readLine();
            if (headerLine != null) {
                headers = headerLine.split(",");
            }
            
            String line;
            int lineNumber = 0;
            
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                try {
                    PassengerRecord record = parseLine(line, lineNumber);
                    if (record != null) {
                        records.add(record);
                    }
                } catch (Exception e) {
                    System.out.println("Skipping line " + lineNumber + ": " + e.getMessage());
                }
            }
        } finally {
            reader.close();
        }
        
        return records;
    }
    
    /** Parses a CSV line into a PassengerRecord. */
    private PassengerRecord parseLine(String line, int lineNumber) {
        String[] values = line.split(",");
        
        if (values.length < 25) {
            return null;
        }
        
        PassengerRecord record = new PassengerRecord();
        
        try {
            record.setId(parseInteger(values[0], 0));
            record.setGender(values[1].trim());
            record.setCustomerType(values[2].trim());
            record.setAge(parseInteger(values[3], 0));
            record.setTypeOfTravel(values[4].trim());
            record.setTravelClass(values[5].trim());
            record.setFlightDistance(parseInteger(values[6], 0));
            record.setInflightWifiService(parseInteger(values[7], 0));
            record.setDepartureArrivalTimeConvenient(parseInteger(values[8], 0));
            record.setEaseOfOnlineBooking(parseInteger(values[9], 0));
            record.setGateLocation(parseInteger(values[10], 0));
            record.setFoodAndDrink(parseInteger(values[11], 0));
            record.setOnlineBoarding(parseInteger(values[12], 0));
            record.setSeatComfort(parseInteger(values[13], 0));
            record.setInflightEntertainment(parseInteger(values[14], 0));
            record.setOnBoardService(parseInteger(values[15], 0));
            record.setLegRoomService(parseInteger(values[16], 0));
            record.setBaggageHandling(parseInteger(values[17], 0));
            record.setCheckinService(parseInteger(values[18], 0));
            record.setInflightService(parseInteger(values[19], 0));
            record.setCleanliness(parseInteger(values[20], 0));
            record.setDepartureDelayInMinutes(parseDouble(values[21], 0.0));
            record.setArrivalDelayInMinutes(parseDouble(values[22], 0.0));
            record.setSatisfaction(values[23].trim());
            record.setDate(values[24].trim());
            
            return record;
        } catch (Exception e) {
            throw new RuntimeException("Parse error at line " + lineNumber);
        }
    }
    
    /** Safely parses integer, returns default if invalid. */
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
    
    /** Safely parses double, returns default if invalid. */
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
    
    public String[] getHeaders() { return headers; }
    public String getFilePath() { return filePath; }
}
