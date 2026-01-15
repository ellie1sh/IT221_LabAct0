package processor;

import model.PassengerRecord;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DataProcessor - Processes and analyzes airline passenger data.
 * 
 * <p>This class contains methods to analyze passenger satisfaction data.
 * It can count, filter, and calculate statistics from the dataset.</p>
 * 
 * <h2>What is Data Processing?</h2>
 * <p>Data processing means taking raw data and turning it into useful information.
 * For example, counting how many passengers are satisfied vs dissatisfied.</p>
 * 
 * <h2>Common Operations:</h2>
 * <ul>
 *   <li><b>Counting</b> - How many records match a condition?</li>
 *   <li><b>Filtering</b> - Get only records that match a condition</li>
 *   <li><b>Calculating</b> - Find averages, min, max values</li>
 *   <li><b>Grouping</b> - Organize records by category</li>
 * </ul>
 * 
 * @author IT221 Student
 * @version 1.0
 */
public class DataProcessor {
    
    /**
     * The list of all passenger records to process.
     */
    private List<PassengerRecord> records;
    
    /**
     * Creates a new DataProcessor with the given records.
     * 
     * @param records the list of passenger records to process
     */
    public DataProcessor(List<PassengerRecord> records) {
        this.records = records;
    }
    
    /**
     * Gets the total number of records.
     * 
     * @return the total count of records
     */
    public int getTotalRecords() {
        return records.size();
    }
    
    // ================================================================
    // DISTRIBUTION METHODS (Counting records by category)
    // ================================================================
    
    /**
     * Counts passengers by gender.
     * 
     * <p>This method loops through all records and counts how many
     * are Male and how many are Female.</p>
     * 
     * @return a Map where key is gender and value is count
     */
    public Map<String, Long> getGenderDistribution() {
        // Create a map to store counts
        Map<String, Long> counts = new HashMap<>();
        
        // Loop through each record
        for (PassengerRecord record : records) {
            String gender = record.getGender();
            
            // If this gender is already in the map, add 1
            // If not, start at 1
            if (counts.containsKey(gender)) {
                counts.put(gender, counts.get(gender) + 1);
            } else {
                counts.put(gender, 1L);
            }
        }
        
        return counts;
    }
    
    /**
     * Counts passengers by customer type (Loyal vs Disloyal).
     * 
     * @return a Map where key is customer type and value is count
     */
    public Map<String, Long> getCustomerTypeDistribution() {
        Map<String, Long> counts = new HashMap<>();
        
        for (PassengerRecord record : records) {
            String type = record.getCustomerType();
            
            if (counts.containsKey(type)) {
                counts.put(type, counts.get(type) + 1);
            } else {
                counts.put(type, 1L);
            }
        }
        
        return counts;
    }
    
    /**
     * Counts passengers by travel class (Business, Eco, Eco Plus).
     * 
     * @return a Map where key is class and value is count
     */
    public Map<String, Long> getTravelClassDistribution() {
        Map<String, Long> counts = new HashMap<>();
        
        for (PassengerRecord record : records) {
            String travelClass = record.getTravelClass();
            
            if (counts.containsKey(travelClass)) {
                counts.put(travelClass, counts.get(travelClass) + 1);
            } else {
                counts.put(travelClass, 1L);
            }
        }
        
        return counts;
    }
    
    /**
     * Counts passengers by type of travel (Business vs Personal).
     * 
     * @return a Map where key is travel type and value is count
     */
    public Map<String, Long> getTravelTypeDistribution() {
        Map<String, Long> counts = new HashMap<>();
        
        for (PassengerRecord record : records) {
            String type = record.getTypeOfTravel();
            
            if (counts.containsKey(type)) {
                counts.put(type, counts.get(type) + 1);
            } else {
                counts.put(type, 1L);
            }
        }
        
        return counts;
    }
    
    /**
     * Counts passengers by satisfaction level.
     * 
     * @return a Map where key is satisfaction and value is count
     */
    public Map<String, Long> getSatisfactionDistribution() {
        Map<String, Long> counts = new HashMap<>();
        
        for (PassengerRecord record : records) {
            String satisfaction = record.getSatisfaction();
            
            if (counts.containsKey(satisfaction)) {
                counts.put(satisfaction, counts.get(satisfaction) + 1);
            } else {
                counts.put(satisfaction, 1L);
            }
        }
        
        return counts;
    }
    
    // ================================================================
    // AGE STATISTICS
    // ================================================================
    
    /**
     * Calculates age statistics (min, max, average).
     * 
     * <p>This shows how to find minimum, maximum, and average values
     * using simple loops.</p>
     * 
     * @return a Map with age statistics
     */
    public Map<String, Double> getAgeStatistics() {
        Map<String, Double> stats = new HashMap<>();
        
        // Initialize values
        int minAge = Integer.MAX_VALUE;  // Start with largest possible
        int maxAge = Integer.MIN_VALUE;  // Start with smallest possible
        int totalAge = 0;
        
        // Loop through all records to find min, max, and total
        for (PassengerRecord record : records) {
            int age = record.getAge();
            
            // Check if this is the new minimum
            if (age < minAge) {
                minAge = age;
            }
            
            // Check if this is the new maximum
            if (age > maxAge) {
                maxAge = age;
            }
            
            // Add to total for average calculation
            totalAge = totalAge + age;
        }
        
        // Calculate average (total divided by count)
        double averageAge = (double) totalAge / records.size();
        
        // Store results in the map
        stats.put("Minimum Age", (double) minAge);
        stats.put("Maximum Age", (double) maxAge);
        stats.put("Average Age", averageAge);
        
        return stats;
    }
    
    /**
     * Groups passengers by age range.
     * 
     * <p>Age groups: Under 18, 18-29, 30-44, 45-59, 60+</p>
     * 
     * @return a Map where key is age group and value is count
     */
    public Map<String, Long> getAgeGroupDistribution() {
        Map<String, Long> counts = new HashMap<>();
        
        for (PassengerRecord record : records) {
            int age = record.getAge();
            String ageGroup;
            
            // Determine which age group this person belongs to
            if (age < 18) {
                ageGroup = "Under 18";
            } else if (age < 30) {
                ageGroup = "18-29";
            } else if (age < 45) {
                ageGroup = "30-44";
            } else if (age < 60) {
                ageGroup = "45-59";
            } else {
                ageGroup = "60+";
            }
            
            // Add to count
            if (counts.containsKey(ageGroup)) {
                counts.put(ageGroup, counts.get(ageGroup) + 1);
            } else {
                counts.put(ageGroup, 1L);
            }
        }
        
        return counts;
    }
    
    // ================================================================
    // FLIGHT STATISTICS
    // ================================================================
    
    /**
     * Calculates flight distance statistics.
     * 
     * @return a Map with distance statistics
     */
    public Map<String, Double> getFlightDistanceStatistics() {
        Map<String, Double> stats = new HashMap<>();
        
        int minDist = Integer.MAX_VALUE;
        int maxDist = Integer.MIN_VALUE;
        long totalDist = 0;
        
        for (PassengerRecord record : records) {
            int dist = record.getFlightDistance();
            
            if (dist < minDist) minDist = dist;
            if (dist > maxDist) maxDist = dist;
            totalDist = totalDist + dist;
        }
        
        double avgDist = (double) totalDist / records.size();
        
        stats.put("Minimum Distance", (double) minDist);
        stats.put("Maximum Distance", (double) maxDist);
        stats.put("Average Distance", avgDist);
        stats.put("Total Flights", (double) records.size());
        
        return stats;
    }
    
    /**
     * Groups flights by distance category.
     * 
     * <p>Categories: Short, Medium, Long, Very Long</p>
     * 
     * @return a Map where key is category and value is count
     */
    public Map<String, Long> getFlightDistanceDistribution() {
        Map<String, Long> counts = new HashMap<>();
        
        for (PassengerRecord record : records) {
            int dist = record.getFlightDistance();
            String category;
            
            if (dist < 500) {
                category = "Short (<500 mi)";
            } else if (dist < 1500) {
                category = "Medium (500-1500 mi)";
            } else if (dist < 3000) {
                category = "Long (1500-3000 mi)";
            } else {
                category = "Very Long (3000+ mi)";
            }
            
            if (counts.containsKey(category)) {
                counts.put(category, counts.get(category) + 1);
            } else {
                counts.put(category, 1L);
            }
        }
        
        return counts;
    }
    
    // ================================================================
    // DELAY STATISTICS
    // ================================================================
    
    /**
     * Calculates departure delay statistics.
     * 
     * @return a Map with delay statistics
     */
    public Map<String, Double> getDepartureDelayStatistics() {
        Map<String, Double> stats = new HashMap<>();
        
        double minDelay = Double.MAX_VALUE;
        double maxDelay = Double.MIN_VALUE;
        double totalDelay = 0;
        long delayedFlights = 0;
        
        for (PassengerRecord record : records) {
            double delay = record.getDepartureDelayInMinutes();
            
            if (delay < minDelay) minDelay = delay;
            if (delay > maxDelay) maxDelay = delay;
            totalDelay = totalDelay + delay;
            
            if (delay > 0) {
                delayedFlights++;
            }
        }
        
        double avgDelay = totalDelay / records.size();
        
        stats.put("Minimum Delay (min)", minDelay);
        stats.put("Maximum Delay (min)", maxDelay);
        stats.put("Average Delay (min)", avgDelay);
        stats.put("Flights with Delays", (double) delayedFlights);
        stats.put("On-Time Flights", (double) (records.size() - delayedFlights));
        
        return stats;
    }
    
    /**
     * Calculates arrival delay statistics.
     * 
     * @return a Map with delay statistics
     */
    public Map<String, Double> getArrivalDelayStatistics() {
        Map<String, Double> stats = new HashMap<>();
        
        double minDelay = Double.MAX_VALUE;
        double maxDelay = Double.MIN_VALUE;
        double totalDelay = 0;
        
        for (PassengerRecord record : records) {
            double delay = record.getArrivalDelayInMinutes();
            
            if (delay < minDelay) minDelay = delay;
            if (delay > maxDelay) maxDelay = delay;
            totalDelay = totalDelay + delay;
        }
        
        double avgDelay = totalDelay / records.size();
        
        stats.put("Minimum Delay (min)", minDelay);
        stats.put("Maximum Delay (min)", maxDelay);
        stats.put("Average Delay (min)", avgDelay);
        
        return stats;
    }
    
    // ================================================================
    // SERVICE RATINGS
    // ================================================================
    
    /**
     * Calculates average ratings for all services.
     * 
     * <p>There are 14 different service categories, each rated 0-5.</p>
     * 
     * @return a Map where key is service name and value is average rating
     */
    public Map<String, Double> getAverageServiceRatings() {
        Map<String, Double> ratings = new HashMap<>();
        
        // Totals for each service
        long totalWifi = 0, totalTime = 0, totalBooking = 0, totalGate = 0;
        long totalFood = 0, totalBoarding = 0, totalSeat = 0, totalEntertain = 0;
        long totalOnboard = 0, totalLegroom = 0, totalBaggage = 0, totalCheckin = 0;
        long totalInflight = 0, totalClean = 0;
        
        // Add up all ratings
        for (PassengerRecord r : records) {
            totalWifi += r.getInflightWifiService();
            totalTime += r.getDepartureArrivalTimeConvenient();
            totalBooking += r.getEaseOfOnlineBooking();
            totalGate += r.getGateLocation();
            totalFood += r.getFoodAndDrink();
            totalBoarding += r.getOnlineBoarding();
            totalSeat += r.getSeatComfort();
            totalEntertain += r.getInflightEntertainment();
            totalOnboard += r.getOnBoardService();
            totalLegroom += r.getLegRoomService();
            totalBaggage += r.getBaggageHandling();
            totalCheckin += r.getCheckinService();
            totalInflight += r.getInflightService();
            totalClean += r.getCleanliness();
        }
        
        // Calculate averages
        int count = records.size();
        ratings.put("Inflight Wifi Service", (double) totalWifi / count);
        ratings.put("Departure/Arrival Time Convenient", (double) totalTime / count);
        ratings.put("Ease of Online Booking", (double) totalBooking / count);
        ratings.put("Gate Location", (double) totalGate / count);
        ratings.put("Food and Drink", (double) totalFood / count);
        ratings.put("Online Boarding", (double) totalBoarding / count);
        ratings.put("Seat Comfort", (double) totalSeat / count);
        ratings.put("Inflight Entertainment", (double) totalEntertain / count);
        ratings.put("On-board Service", (double) totalOnboard / count);
        ratings.put("Leg Room Service", (double) totalLegroom / count);
        ratings.put("Baggage Handling", (double) totalBaggage / count);
        ratings.put("Check-in Service", (double) totalCheckin / count);
        ratings.put("Inflight Service", (double) totalInflight / count);
        ratings.put("Cleanliness", (double) totalClean / count);
        
        return ratings;
    }
    
    /**
     * Gets a summary of top and bottom rated services.
     * 
     * @return a formatted string with service rankings
     */
    public String getServiceRankingSummary() {
        Map<String, Double> ratings = getAverageServiceRatings();
        
        // Find top 3 and bottom 3
        String[] services = ratings.keySet().toArray(new String[0]);
        
        // Simple bubble sort to rank services by rating
        for (int i = 0; i < services.length - 1; i++) {
            for (int j = 0; j < services.length - i - 1; j++) {
                if (ratings.get(services[j]) < ratings.get(services[j + 1])) {
                    // Swap
                    String temp = services[j];
                    services[j] = services[j + 1];
                    services[j + 1] = temp;
                }
            }
        }
        
        // Build the result string
        StringBuilder result = new StringBuilder();
        result.append("TOP 3 RATED SERVICES:\n");
        for (int i = 0; i < 3; i++) {
            result.append("  ").append(i + 1).append(". ");
            result.append(services[i]).append(": ");
            result.append(String.format("%.2f", ratings.get(services[i])));
            result.append("/5.00\n");
        }
        
        result.append("\nBOTTOM 3 RATED SERVICES:\n");
        int len = services.length;
        for (int i = len - 3; i < len; i++) {
            result.append("  ").append(len - i).append(". ");
            result.append(services[i]).append(": ");
            result.append(String.format("%.2f", ratings.get(services[i])));
            result.append("/5.00\n");
        }
        
        return result.toString();
    }
    
    // ================================================================
    // SATISFACTION ANALYSIS
    // ================================================================
    
    /**
     * Calculates satisfaction rate by travel class.
     * 
     * <p>Shows what percentage of each class is satisfied.</p>
     * 
     * @return a Map where key is class and value is satisfaction percentage
     */
    public Map<String, Double> getSatisfactionRateByClass() {
        Map<String, Double> rates = new HashMap<>();
        
        // Count totals and satisfied for each class
        Map<String, Integer> totals = new HashMap<>();
        Map<String, Integer> satisfied = new HashMap<>();
        
        for (PassengerRecord r : records) {
            String cls = r.getTravelClass();
            
            // Add to total count
            totals.put(cls, totals.getOrDefault(cls, 0) + 1);
            
            // Add to satisfied count if satisfied
            if (r.isSatisfied()) {
                satisfied.put(cls, satisfied.getOrDefault(cls, 0) + 1);
            }
        }
        
        // Calculate percentages
        for (String cls : totals.keySet()) {
            int total = totals.get(cls);
            int sat = satisfied.getOrDefault(cls, 0);
            double rate = (sat * 100.0) / total;
            rates.put(cls, rate);
        }
        
        return rates;
    }
    
    /**
     * Calculates satisfaction rate by customer type.
     * 
     * @return a Map where key is type and value is satisfaction percentage
     */
    public Map<String, Double> getSatisfactionRateByCustomerType() {
        Map<String, Double> rates = new HashMap<>();
        Map<String, Integer> totals = new HashMap<>();
        Map<String, Integer> satisfied = new HashMap<>();
        
        for (PassengerRecord r : records) {
            String type = r.getCustomerType();
            totals.put(type, totals.getOrDefault(type, 0) + 1);
            if (r.isSatisfied()) {
                satisfied.put(type, satisfied.getOrDefault(type, 0) + 1);
            }
        }
        
        for (String type : totals.keySet()) {
            int total = totals.get(type);
            int sat = satisfied.getOrDefault(type, 0);
            rates.put(type, (sat * 100.0) / total);
        }
        
        return rates;
    }
    
    /**
     * Calculates satisfaction rate by travel type.
     * 
     * @return a Map where key is type and value is satisfaction percentage
     */
    public Map<String, Double> getSatisfactionRateByTravelType() {
        Map<String, Double> rates = new HashMap<>();
        Map<String, Integer> totals = new HashMap<>();
        Map<String, Integer> satisfied = new HashMap<>();
        
        for (PassengerRecord r : records) {
            String type = r.getTypeOfTravel();
            totals.put(type, totals.getOrDefault(type, 0) + 1);
            if (r.isSatisfied()) {
                satisfied.put(type, satisfied.getOrDefault(type, 0) + 1);
            }
        }
        
        for (String type : totals.keySet()) {
            int total = totals.get(type);
            int sat = satisfied.getOrDefault(type, 0);
            rates.put(type, (sat * 100.0) / total);
        }
        
        return rates;
    }
    
    /**
     * Calculates satisfaction rate by age group.
     * 
     * @return a Map where key is age group and value is satisfaction percentage
     */
    public Map<String, Double> getSatisfactionRateByAgeGroup() {
        Map<String, Double> rates = new HashMap<>();
        Map<String, Integer> totals = new HashMap<>();
        Map<String, Integer> satisfied = new HashMap<>();
        
        for (PassengerRecord r : records) {
            // Determine age group
            int age = r.getAge();
            String group;
            if (age < 18) group = "Under 18";
            else if (age < 30) group = "18-29";
            else if (age < 45) group = "30-44";
            else if (age < 60) group = "45-59";
            else group = "60+";
            
            totals.put(group, totals.getOrDefault(group, 0) + 1);
            if (r.isSatisfied()) {
                satisfied.put(group, satisfied.getOrDefault(group, 0) + 1);
            }
        }
        
        for (String group : totals.keySet()) {
            int total = totals.get(group);
            int sat = satisfied.getOrDefault(group, 0);
            rates.put(group, (sat * 100.0) / total);
        }
        
        return rates;
    }
    
    // ================================================================
    // FILTERING METHODS
    // ================================================================
    
    /**
     * Filters records by satisfaction status.
     * 
     * <p>Returns only satisfied or dissatisfied passengers.</p>
     * 
     * @param wantSatisfied true for satisfied, false for dissatisfied
     * @return a List of matching records
     */
    public List<PassengerRecord> filterBySatisfaction(boolean wantSatisfied) {
        List<PassengerRecord> result = new ArrayList<>();
        
        for (PassengerRecord r : records) {
            if (r.isSatisfied() == wantSatisfied) {
                result.add(r);
            }
        }
        
        return result;
    }
    
    /**
     * Filters records by travel class.
     * 
     * @param travelClass the class to filter by (e.g., "Business")
     * @return a List of matching records
     */
    public List<PassengerRecord> filterByClass(String travelClass) {
        List<PassengerRecord> result = new ArrayList<>();
        
        for (PassengerRecord r : records) {
            // equalsIgnoreCase ignores uppercase/lowercase differences
            if (r.getTravelClass().equalsIgnoreCase(travelClass)) {
                result.add(r);
            }
        }
        
        return result;
    }
    
    /**
     * Filters records by age range.
     * 
     * @param minAge minimum age (inclusive)
     * @param maxAge maximum age (inclusive)
     * @return a List of matching records
     */
    public List<PassengerRecord> filterByAgeRange(int minAge, int maxAge) {
        List<PassengerRecord> result = new ArrayList<>();
        
        for (PassengerRecord r : records) {
            int age = r.getAge();
            if (age >= minAge && age <= maxAge) {
                result.add(r);
            }
        }
        
        return result;
    }
    
    /**
     * Searches for a passenger by ID.
     * 
     * @param id the passenger ID to search for
     * @return the matching record, or null if not found
     */
    public PassengerRecord searchById(int id) {
        for (PassengerRecord r : records) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }
    
    // ================================================================
    // COMPREHENSIVE REPORT
    // ================================================================
    
    /**
     * Generates a comprehensive summary of the dataset.
     * 
     * <p>This creates a formatted report with key statistics.</p>
     * 
     * @return a formatted summary string
     */
    public String generateComprehensiveSummary() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("\n========================================\n");
        sb.append("  AIRLINE SATISFACTION DATA SUMMARY\n");
        sb.append("========================================\n\n");
        
        // Basic counts
        int total = records.size();
        int satisfied = filterBySatisfaction(true).size();
        int dissatisfied = total - satisfied;
        
        sb.append("DATASET OVERVIEW\n");
        sb.append("----------------\n");
        sb.append("  Total Records: ").append(total).append("\n");
        sb.append("  Satisfied: ").append(satisfied);
        sb.append(" (").append(String.format("%.1f", satisfied * 100.0 / total)).append("%)\n");
        sb.append("  Dissatisfied: ").append(dissatisfied);
        sb.append(" (").append(String.format("%.1f", dissatisfied * 100.0 / total)).append("%)\n");
        
        // Gender
        sb.append("\nGENDER DISTRIBUTION\n");
        sb.append("-------------------\n");
        Map<String, Long> genders = getGenderDistribution();
        for (String gender : genders.keySet()) {
            long count = genders.get(gender);
            sb.append("  ").append(gender).append(": ").append(count);
            sb.append(" (").append(String.format("%.1f", count * 100.0 / total)).append("%)\n");
        }
        
        // Age
        sb.append("\nAGE STATISTICS\n");
        sb.append("--------------\n");
        Map<String, Double> ageStats = getAgeStatistics();
        sb.append("  Minimum Age: ").append(ageStats.get("Minimum Age").intValue()).append("\n");
        sb.append("  Maximum Age: ").append(ageStats.get("Maximum Age").intValue()).append("\n");
        sb.append("  Average Age: ").append(String.format("%.1f", ageStats.get("Average Age"))).append("\n");
        
        // Flight Distance
        sb.append("\nFLIGHT DISTANCE\n");
        sb.append("---------------\n");
        Map<String, Double> distStats = getFlightDistanceStatistics();
        sb.append("  Average: ").append(String.format("%.1f", distStats.get("Average Distance"))).append(" miles\n");
        sb.append("  Shortest: ").append(distStats.get("Minimum Distance").intValue()).append(" miles\n");
        sb.append("  Longest: ").append(distStats.get("Maximum Distance").intValue()).append(" miles\n");
        
        // Service Ratings Summary
        sb.append("\nSERVICE RATINGS\n");
        sb.append("---------------\n");
        Map<String, Double> ratings = getAverageServiceRatings();
        double overallAvg = 0;
        for (Double rating : ratings.values()) {
            overallAvg += rating;
        }
        overallAvg = overallAvg / ratings.size();
        sb.append("  Overall Average: ").append(String.format("%.2f", overallAvg)).append(" / 5.00\n");
        sb.append("\n").append(getServiceRankingSummary());
        
        return sb.toString();
    }
}
