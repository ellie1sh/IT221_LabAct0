package processor;

import model.PassengerRecord;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is responsible for processing the passenger data.
 * It contains methods to calculate statistics and filter records.
 * 
 * <p>
 * Instead of complex algorithms, we use simple loops to go through the data.
 * </p>
 * 
 * @author IT221 Student
 */
public class DataProcessor {
    
    /** The list of all passenger records to process */
    private List<PassengerRecord> records;
    
    /**
     * Constructor.
     * 
     * @param records The list of records loaded from the file.
     */
    public DataProcessor(List<PassengerRecord> records) {
        this.records = records;
    }
    
    /**
     * Gets the total number of records in the dataset.
     * 
     * @return The count of records.
     */
    public int getTotalRecords() {
        return records.size();
    }
    
    // ================= DISTRIBUTIONS =================
    
    /**
     * Counts how many passengers are Male vs Female.
     * 
     * @return A Map where the key is the gender and the value is the count.
     */
    public Map<String, Integer> getGenderDistribution() {
        Map<String, Integer> counts = new HashMap<>();
        counts.put("Male", 0);
        counts.put("Female", 0);
        
        for (PassengerRecord record : records) {
            String gender = record.getGender();
            
            // If the gender is in our map, increment the count
            if (counts.containsKey(gender)) {
                int currentCount = counts.get(gender);
                counts.put(gender, currentCount + 1);
            }
        }
        
        return counts;
    }
    
    /**
     * Counts passengers by their customer type (Loyal vs Disloyal).
     * 
     * @return A Map of counts.
     */
    public Map<String, Integer> getCustomerTypeDistribution() {
        Map<String, Integer> counts = new HashMap<>();
        
        for (PassengerRecord record : records) {
            String type = record.getCustomerType();
            int currentCount = counts.getOrDefault(type, 0);
            counts.put(type, currentCount + 1);
        }
        
        return counts;
    }
    
    /**
     * Counts passengers by class (Business, Eco, Eco Plus).
     * 
     * @return A Map of counts.
     */
    public Map<String, Integer> getTravelClassDistribution() {
        Map<String, Integer> counts = new HashMap<>();
        
        for (PassengerRecord record : records) {
            String travelClass = record.getTravelClass();
            int currentCount = counts.getOrDefault(travelClass, 0);
            counts.put(travelClass, currentCount + 1);
        }
        
        return counts;
    }
    
    /**
     * Counts satisfied vs dissatisfied passengers.
     * 
     * @return A Map of counts.
     */
    public Map<String, Integer> getSatisfactionDistribution() {
        Map<String, Integer> counts = new HashMap<>();
        
        for (PassengerRecord record : records) {
            String satisfaction = record.getSatisfaction();
            int currentCount = counts.getOrDefault(satisfaction, 0);
            counts.put(satisfaction, currentCount + 1);
        }
        
        return counts;
    }

    // ================= STATISTICS =================
    
    /**
     * Calculates the average age of all passengers.
     * 
     * @return The average age.
     */
    public double getAverageAge() {
        if (records.isEmpty()) return 0.0;
        
        long totalAge = 0;
        for (PassengerRecord record : records) {
            totalAge += record.getAge();
        }
        
        return (double) totalAge / records.size();
    }
    
    /**
     * Calculates the average flight distance.
     * 
     * @return The average distance in miles.
     */
    public double getAverageFlightDistance() {
        if (records.isEmpty()) return 0.0;
        
        long totalDistance = 0;
        for (PassengerRecord record : records) {
            totalDistance += record.getFlightDistance();
        }
        
        return (double) totalDistance / records.size();
    }
    
    /**
     * Calculates average departure delay in minutes.
     * 
     * @return Average delay.
     */
    public double getAverageDepartureDelay() {
        if (records.isEmpty()) return 0.0;
        
        double totalDelay = 0;
        for (PassengerRecord record : records) {
            totalDelay += record.getDepartureDelayInMinutes();
        }
        
        return totalDelay / records.size();
    }
    
    /**
     * Calculates average arrival delay in minutes.
     * 
     * @return Average delay.
     */
    public double getAverageArrivalDelay() {
        if (records.isEmpty()) return 0.0;
        
        double totalDelay = 0;
        for (PassengerRecord record : records) {
            totalDelay += record.getArrivalDelayInMinutes();
        }
        
        return totalDelay / records.size();
    }
    
    // ================= FILTERING =================
    
    /**
     * Finds all passengers within a certain age range.
     * 
     * @param minAge Minimum age.
     * @param maxAge Maximum age.
     * @return A list of matching records.
     */
    public List<PassengerRecord> filterByAge(int minAge, int maxAge) {
        List<PassengerRecord> filtered = new ArrayList<>();
        
        for (PassengerRecord record : records) {
            if (record.getAge() >= minAge && record.getAge() <= maxAge) {
                filtered.add(record);
            }
        }
        
        return filtered;
    }
    
    /**
     * Finds a passenger by their unique ID.
     * 
     * @param id The ID to search for.
     * @return The record if found, or null if not found.
     */
    public PassengerRecord searchById(int id) {
        for (PassengerRecord record : records) {
            if (record.getId() == id) {
                return record;
            }
        }
        return null; // Not found
    }
    
    /**
     * Finds passengers who flew on a specific date.
     * 
     * @param date The date string to search for (e.g. "08:26:2023")
     * @return List of matching records.
     */
    public List<PassengerRecord> searchByDate(String date) {
        List<PassengerRecord> matching = new ArrayList<>();
        
        for (PassengerRecord record : records) {
            // Check if the date matches (handling potential nulls)
            if (record.getFlightDate() != null && record.getFlightDate().equals(date)) {
                matching.add(record);
            }
        }
        
        return matching;
    }
}
