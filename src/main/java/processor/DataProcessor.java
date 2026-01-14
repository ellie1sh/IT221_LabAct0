package processor;

import model.PassengerRecord;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Data processing class for analyzing airline passenger satisfaction data.
 * Provides various statistical analysis and filtering capabilities.
 */
public class DataProcessor {
    
    private List<PassengerRecord> records;
    
    public DataProcessor(List<PassengerRecord> records) {
        this.records = records;
    }
    
    /**
     * Get total number of records
     * @return Record count
     */
    public int getTotalRecords() {
        return records.size();
    }
    
    // ==================== BASIC STATISTICS ====================
    
    /**
     * Get gender distribution
     * @return Map of gender to count
     */
    public Map<String, Long> getGenderDistribution() {
        return records.stream()
                .collect(Collectors.groupingBy(PassengerRecord::getGender, Collectors.counting()));
    }
    
    /**
     * Get customer type distribution
     * @return Map of customer type to count
     */
    public Map<String, Long> getCustomerTypeDistribution() {
        return records.stream()
                .collect(Collectors.groupingBy(PassengerRecord::getCustomerType, Collectors.counting()));
    }
    
    /**
     * Get travel class distribution
     * @return Map of travel class to count
     */
    public Map<String, Long> getTravelClassDistribution() {
        return records.stream()
                .collect(Collectors.groupingBy(PassengerRecord::getTravelClass, Collectors.counting()));
    }
    
    /**
     * Get travel type distribution
     * @return Map of travel type to count
     */
    public Map<String, Long> getTravelTypeDistribution() {
        return records.stream()
                .collect(Collectors.groupingBy(PassengerRecord::getTypeOfTravel, Collectors.counting()));
    }
    
    /**
     * Get satisfaction distribution
     * @return Map of satisfaction level to count
     */
    public Map<String, Long> getSatisfactionDistribution() {
        return records.stream()
                .collect(Collectors.groupingBy(PassengerRecord::getSatisfaction, Collectors.counting()));
    }
    
    // ==================== AGE STATISTICS ====================
    
    /**
     * Get age statistics
     * @return Map containing min, max, average age
     */
    public Map<String, Double> getAgeStatistics() {
        Map<String, Double> stats = new LinkedHashMap<>();
        
        IntSummaryStatistics ageStats = records.stream()
                .mapToInt(PassengerRecord::getAge)
                .summaryStatistics();
        
        stats.put("Minimum Age", (double) ageStats.getMin());
        stats.put("Maximum Age", (double) ageStats.getMax());
        stats.put("Average Age", ageStats.getAverage());
        
        return stats;
    }
    
    /**
     * Get age group distribution
     * @return Map of age group to count
     */
    public Map<String, Long> getAgeGroupDistribution() {
        return records.stream()
                .collect(Collectors.groupingBy(r -> {
                    int age = r.getAge();
                    if (age < 18) return "Under 18";
                    else if (age < 30) return "18-29";
                    else if (age < 45) return "30-44";
                    else if (age < 60) return "45-59";
                    else return "60+";
                }, Collectors.counting()));
    }
    
    // ==================== FLIGHT STATISTICS ====================
    
    /**
     * Get flight distance statistics
     * @return Map containing min, max, average distance
     */
    public Map<String, Double> getFlightDistanceStatistics() {
        Map<String, Double> stats = new LinkedHashMap<>();
        
        IntSummaryStatistics distStats = records.stream()
                .mapToInt(PassengerRecord::getFlightDistance)
                .summaryStatistics();
        
        stats.put("Minimum Distance", (double) distStats.getMin());
        stats.put("Maximum Distance", (double) distStats.getMax());
        stats.put("Average Distance", distStats.getAverage());
        stats.put("Total Flights", (double) distStats.getCount());
        
        return stats;
    }
    
    /**
     * Get flight distance category distribution
     * @return Map of distance category to count
     */
    public Map<String, Long> getFlightDistanceDistribution() {
        return records.stream()
                .collect(Collectors.groupingBy(r -> {
                    int dist = r.getFlightDistance();
                    if (dist < 500) return "Short (<500 mi)";
                    else if (dist < 1500) return "Medium (500-1500 mi)";
                    else if (dist < 3000) return "Long (1500-3000 mi)";
                    else return "Very Long (3000+ mi)";
                }, Collectors.counting()));
    }
    
    // ==================== DELAY STATISTICS ====================
    
    /**
     * Get departure delay statistics
     * @return Map containing min, max, average delay
     */
    public Map<String, Double> getDepartureDelayStatistics() {
        Map<String, Double> stats = new LinkedHashMap<>();
        
        DoubleSummaryStatistics delayStats = records.stream()
                .mapToDouble(PassengerRecord::getDepartureDelayInMinutes)
                .summaryStatistics();
        
        stats.put("Minimum Delay (min)", delayStats.getMin());
        stats.put("Maximum Delay (min)", delayStats.getMax());
        stats.put("Average Delay (min)", delayStats.getAverage());
        
        // Count flights with delays
        long delayedFlights = records.stream()
                .filter(r -> r.getDepartureDelayInMinutes() > 0)
                .count();
        stats.put("Flights with Delays", (double) delayedFlights);
        stats.put("On-Time Flights", (double) (records.size() - delayedFlights));
        
        return stats;
    }
    
    /**
     * Get arrival delay statistics
     * @return Map containing min, max, average delay
     */
    public Map<String, Double> getArrivalDelayStatistics() {
        Map<String, Double> stats = new LinkedHashMap<>();
        
        DoubleSummaryStatistics delayStats = records.stream()
                .mapToDouble(PassengerRecord::getArrivalDelayInMinutes)
                .summaryStatistics();
        
        stats.put("Minimum Delay (min)", delayStats.getMin());
        stats.put("Maximum Delay (min)", delayStats.getMax());
        stats.put("Average Delay (min)", delayStats.getAverage());
        
        return stats;
    }
    
    // ==================== SERVICE RATINGS ====================
    
    /**
     * Get average ratings for all services
     * @return Map of service name to average rating
     */
    public Map<String, Double> getAverageServiceRatings() {
        Map<String, Double> ratings = new LinkedHashMap<>();
        
        ratings.put("Inflight Wifi Service", records.stream().mapToInt(PassengerRecord::getInflightWifiService).average().orElse(0));
        ratings.put("Departure/Arrival Time Convenient", records.stream().mapToInt(PassengerRecord::getDepartureArrivalTimeConvenient).average().orElse(0));
        ratings.put("Ease of Online Booking", records.stream().mapToInt(PassengerRecord::getEaseOfOnlineBooking).average().orElse(0));
        ratings.put("Gate Location", records.stream().mapToInt(PassengerRecord::getGateLocation).average().orElse(0));
        ratings.put("Food and Drink", records.stream().mapToInt(PassengerRecord::getFoodAndDrink).average().orElse(0));
        ratings.put("Online Boarding", records.stream().mapToInt(PassengerRecord::getOnlineBoarding).average().orElse(0));
        ratings.put("Seat Comfort", records.stream().mapToInt(PassengerRecord::getSeatComfort).average().orElse(0));
        ratings.put("Inflight Entertainment", records.stream().mapToInt(PassengerRecord::getInflightEntertainment).average().orElse(0));
        ratings.put("On-board Service", records.stream().mapToInt(PassengerRecord::getOnBoardService).average().orElse(0));
        ratings.put("Leg Room Service", records.stream().mapToInt(PassengerRecord::getLegRoomService).average().orElse(0));
        ratings.put("Baggage Handling", records.stream().mapToInt(PassengerRecord::getBaggageHandling).average().orElse(0));
        ratings.put("Check-in Service", records.stream().mapToInt(PassengerRecord::getCheckinService).average().orElse(0));
        ratings.put("Inflight Service", records.stream().mapToInt(PassengerRecord::getInflightService).average().orElse(0));
        ratings.put("Cleanliness", records.stream().mapToInt(PassengerRecord::getCleanliness).average().orElse(0));
        
        return ratings;
    }
    
    /**
     * Get top and bottom rated services
     * @return String summary of best and worst services
     */
    public String getServiceRankingSummary() {
        Map<String, Double> ratings = getAverageServiceRatings();
        
        // Sort by rating
        List<Map.Entry<String, Double>> sorted = ratings.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toList());
        
        StringBuilder sb = new StringBuilder();
        sb.append("TOP 3 RATED SERVICES:\n");
        for (int i = 0; i < 3 && i < sorted.size(); i++) {
            sb.append(String.format("  %d. %s: %.2f/5.00\n", 
                    i + 1, sorted.get(i).getKey(), sorted.get(i).getValue()));
        }
        
        sb.append("\nBOTTOM 3 RATED SERVICES:\n");
        for (int i = sorted.size() - 3; i < sorted.size() && i >= 0; i++) {
            sb.append(String.format("  %d. %s: %.2f/5.00\n", 
                    sorted.size() - i, sorted.get(i).getKey(), sorted.get(i).getValue()));
        }
        
        return sb.toString();
    }
    
    // ==================== SATISFACTION ANALYSIS ====================
    
    /**
     * Get satisfaction rate by travel class
     * @return Map of class to satisfaction percentage
     */
    public Map<String, Double> getSatisfactionRateByClass() {
        Map<String, Double> rates = new LinkedHashMap<>();
        
        Map<String, List<PassengerRecord>> byClass = records.stream()
                .collect(Collectors.groupingBy(PassengerRecord::getTravelClass));
        
        for (Map.Entry<String, List<PassengerRecord>> entry : byClass.entrySet()) {
            long satisfied = entry.getValue().stream().filter(PassengerRecord::isSatisfied).count();
            double rate = (satisfied * 100.0) / entry.getValue().size();
            rates.put(entry.getKey(), rate);
        }
        
        return rates;
    }
    
    /**
     * Get satisfaction rate by customer type
     * @return Map of customer type to satisfaction percentage
     */
    public Map<String, Double> getSatisfactionRateByCustomerType() {
        Map<String, Double> rates = new LinkedHashMap<>();
        
        Map<String, List<PassengerRecord>> byType = records.stream()
                .collect(Collectors.groupingBy(PassengerRecord::getCustomerType));
        
        for (Map.Entry<String, List<PassengerRecord>> entry : byType.entrySet()) {
            long satisfied = entry.getValue().stream().filter(PassengerRecord::isSatisfied).count();
            double rate = (satisfied * 100.0) / entry.getValue().size();
            rates.put(entry.getKey(), rate);
        }
        
        return rates;
    }
    
    /**
     * Get satisfaction rate by travel type
     * @return Map of travel type to satisfaction percentage
     */
    public Map<String, Double> getSatisfactionRateByTravelType() {
        Map<String, Double> rates = new LinkedHashMap<>();
        
        Map<String, List<PassengerRecord>> byType = records.stream()
                .collect(Collectors.groupingBy(PassengerRecord::getTypeOfTravel));
        
        for (Map.Entry<String, List<PassengerRecord>> entry : byType.entrySet()) {
            long satisfied = entry.getValue().stream().filter(PassengerRecord::isSatisfied).count();
            double rate = (satisfied * 100.0) / entry.getValue().size();
            rates.put(entry.getKey(), rate);
        }
        
        return rates;
    }
    
    /**
     * Get satisfaction rate by age group
     * @return Map of age group to satisfaction percentage
     */
    public Map<String, Double> getSatisfactionRateByAgeGroup() {
        Map<String, Double> rates = new LinkedHashMap<>();
        
        Map<String, List<PassengerRecord>> byAge = records.stream()
                .collect(Collectors.groupingBy(r -> {
                    int age = r.getAge();
                    if (age < 18) return "Under 18";
                    else if (age < 30) return "18-29";
                    else if (age < 45) return "30-44";
                    else if (age < 60) return "45-59";
                    else return "60+";
                }));
        
        for (Map.Entry<String, List<PassengerRecord>> entry : byAge.entrySet()) {
            long satisfied = entry.getValue().stream().filter(PassengerRecord::isSatisfied).count();
            double rate = (satisfied * 100.0) / entry.getValue().size();
            rates.put(entry.getKey(), rate);
        }
        
        return rates;
    }
    
    // ==================== FILTERING ====================
    
    /**
     * Filter records by satisfaction status
     * @param satisfied true for satisfied, false for dissatisfied
     * @return List of filtered records
     */
    public List<PassengerRecord> filterBySatisfaction(boolean satisfied) {
        return records.stream()
                .filter(r -> r.isSatisfied() == satisfied)
                .collect(Collectors.toList());
    }
    
    /**
     * Filter records by travel class
     * @param travelClass Class name to filter by
     * @return List of filtered records
     */
    public List<PassengerRecord> filterByClass(String travelClass) {
        return records.stream()
                .filter(r -> r.getTravelClass().equalsIgnoreCase(travelClass))
                .collect(Collectors.toList());
    }
    
    /**
     * Filter records by age range
     * @param minAge Minimum age (inclusive)
     * @param maxAge Maximum age (inclusive)
     * @return List of filtered records
     */
    public List<PassengerRecord> filterByAgeRange(int minAge, int maxAge) {
        return records.stream()
                .filter(r -> r.getAge() >= minAge && r.getAge() <= maxAge)
                .collect(Collectors.toList());
    }
    
    /**
     * Search records by ID
     * @param id Passenger ID to search for
     * @return Optional containing the record if found
     */
    public Optional<PassengerRecord> searchById(int id) {
        return records.stream()
                .filter(r -> r.getId() == id)
                .findFirst();
    }
    
    // ==================== COMPREHENSIVE REPORT ====================
    
    /**
     * Generate a comprehensive dataset summary
     * @return Formatted summary string
     */
    public String generateComprehensiveSummary() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("\n╔════════════════════════════════════════════════════════════╗\n");
        sb.append("║       AIRLINE PASSENGER SATISFACTION - DATA SUMMARY        ║\n");
        sb.append("╚════════════════════════════════════════════════════════════╝\n\n");
        
        // Dataset Overview
        sb.append("─────────────────── DATASET OVERVIEW ───────────────────\n");
        sb.append(String.format("  Total Records: %,d\n", records.size()));
        sb.append(String.format("  Satisfied Passengers: %,d (%.1f%%)\n", 
                filterBySatisfaction(true).size(),
                (filterBySatisfaction(true).size() * 100.0) / records.size()));
        sb.append(String.format("  Dissatisfied Passengers: %,d (%.1f%%)\n", 
                filterBySatisfaction(false).size(),
                (filterBySatisfaction(false).size() * 100.0) / records.size()));
        
        // Gender Distribution
        sb.append("\n─────────────────── GENDER DISTRIBUTION ───────────────────\n");
        getGenderDistribution().forEach((k, v) -> 
                sb.append(String.format("  %s: %,d (%.1f%%)\n", k, v, (v * 100.0) / records.size())));
        
        // Age Statistics
        sb.append("\n─────────────────── AGE STATISTICS ───────────────────\n");
        getAgeStatistics().forEach((k, v) -> 
                sb.append(String.format("  %s: %.1f\n", k, v)));
        
        // Flight Distance
        sb.append("\n─────────────────── FLIGHT DISTANCE ───────────────────\n");
        Map<String, Double> distStats = getFlightDistanceStatistics();
        sb.append(String.format("  Average Distance: %.1f miles\n", distStats.get("Average Distance")));
        sb.append(String.format("  Shortest Flight: %.0f miles\n", distStats.get("Minimum Distance")));
        sb.append(String.format("  Longest Flight: %.0f miles\n", distStats.get("Maximum Distance")));
        
        // Overall Service Rating
        sb.append("\n─────────────────── SERVICE RATINGS SUMMARY ───────────────────\n");
        double overallAvg = getAverageServiceRatings().values().stream()
                .mapToDouble(Double::doubleValue).average().orElse(0);
        sb.append(String.format("  Overall Average Rating: %.2f / 5.00\n", overallAvg));
        sb.append(getServiceRankingSummary());
        
        return sb.toString();
    }
}
