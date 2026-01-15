package processor;

import model.PassengerRecord;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Processes and analyzes passenger satisfaction data.
 * 
 * @author IT221 Student
 * @version 1.0
 */
public class DataProcessor {
    
    private List<PassengerRecord> records;
    
    public DataProcessor(List<PassengerRecord> records) {
        this.records = records;
    }
    
    /** @return total number of records */
    public int getTotalRecords() {
        return records.size();
    }
    
    /** @return gender count distribution */
    public Map<String, Long> getGenderDistribution() {
        Map<String, Long> counts = new HashMap<>();
        for (PassengerRecord r : records) {
            String key = r.getGender();
            counts.put(key, counts.getOrDefault(key, 0L) + 1);
        }
        return counts;
    }
    
    /** @return customer type count distribution */
    public Map<String, Long> getCustomerTypeDistribution() {
        Map<String, Long> counts = new HashMap<>();
        for (PassengerRecord r : records) {
            String key = r.getCustomerType();
            counts.put(key, counts.getOrDefault(key, 0L) + 1);
        }
        return counts;
    }
    
    /** @return travel class count distribution */
    public Map<String, Long> getTravelClassDistribution() {
        Map<String, Long> counts = new HashMap<>();
        for (PassengerRecord r : records) {
            String key = r.getTravelClass();
            counts.put(key, counts.getOrDefault(key, 0L) + 1);
        }
        return counts;
    }
    
    /** @return travel type count distribution */
    public Map<String, Long> getTravelTypeDistribution() {
        Map<String, Long> counts = new HashMap<>();
        for (PassengerRecord r : records) {
            String key = r.getTypeOfTravel();
            counts.put(key, counts.getOrDefault(key, 0L) + 1);
        }
        return counts;
    }
    
    /** @return satisfaction count distribution */
    public Map<String, Long> getSatisfactionDistribution() {
        Map<String, Long> counts = new HashMap<>();
        for (PassengerRecord r : records) {
            String key = r.getSatisfaction();
            counts.put(key, counts.getOrDefault(key, 0L) + 1);
        }
        return counts;
    }
    
    /** @return min, max, average age statistics */
    public Map<String, Double> getAgeStatistics() {
        Map<String, Double> stats = new HashMap<>();
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, total = 0;
        
        for (PassengerRecord r : records) {
            int age = r.getAge();
            if (age < min) min = age;
            if (age > max) max = age;
            total += age;
        }
        
        stats.put("Minimum Age", (double) min);
        stats.put("Maximum Age", (double) max);
        stats.put("Average Age", (double) total / records.size());
        return stats;
    }
    
    /** @return age group count distribution */
    public Map<String, Long> getAgeGroupDistribution() {
        Map<String, Long> counts = new HashMap<>();
        for (PassengerRecord r : records) {
            int age = r.getAge();
            String group;
            if (age < 18) group = "Under 18";
            else if (age < 30) group = "18-29";
            else if (age < 45) group = "30-44";
            else if (age < 60) group = "45-59";
            else group = "60+";
            counts.put(group, counts.getOrDefault(group, 0L) + 1);
        }
        return counts;
    }
    
    /** @return flight distance statistics */
    public Map<String, Double> getFlightDistanceStatistics() {
        Map<String, Double> stats = new HashMap<>();
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        long total = 0;
        
        for (PassengerRecord r : records) {
            int dist = r.getFlightDistance();
            if (dist < min) min = dist;
            if (dist > max) max = dist;
            total += dist;
        }
        
        stats.put("Minimum Distance", (double) min);
        stats.put("Maximum Distance", (double) max);
        stats.put("Average Distance", (double) total / records.size());
        stats.put("Total Flights", (double) records.size());
        return stats;
    }
    
    /** @return flight distance category distribution */
    public Map<String, Long> getFlightDistanceDistribution() {
        Map<String, Long> counts = new HashMap<>();
        for (PassengerRecord r : records) {
            int dist = r.getFlightDistance();
            String cat;
            if (dist < 500) cat = "Short (<500 mi)";
            else if (dist < 1500) cat = "Medium (500-1500 mi)";
            else if (dist < 3000) cat = "Long (1500-3000 mi)";
            else cat = "Very Long (3000+ mi)";
            counts.put(cat, counts.getOrDefault(cat, 0L) + 1);
        }
        return counts;
    }
    
    /** @return departure delay statistics */
    public Map<String, Double> getDepartureDelayStatistics() {
        Map<String, Double> stats = new HashMap<>();
        double min = Double.MAX_VALUE, max = Double.MIN_VALUE, total = 0;
        long delayed = 0;
        
        for (PassengerRecord r : records) {
            double delay = r.getDepartureDelayInMinutes();
            if (delay < min) min = delay;
            if (delay > max) max = delay;
            total += delay;
            if (delay > 0) delayed++;
        }
        
        stats.put("Minimum Delay (min)", min);
        stats.put("Maximum Delay (min)", max);
        stats.put("Average Delay (min)", total / records.size());
        stats.put("Flights with Delays", (double) delayed);
        stats.put("On-Time Flights", (double) (records.size() - delayed));
        return stats;
    }
    
    /** @return arrival delay statistics */
    public Map<String, Double> getArrivalDelayStatistics() {
        Map<String, Double> stats = new HashMap<>();
        double min = Double.MAX_VALUE, max = Double.MIN_VALUE, total = 0;
        
        for (PassengerRecord r : records) {
            double delay = r.getArrivalDelayInMinutes();
            if (delay < min) min = delay;
            if (delay > max) max = delay;
            total += delay;
        }
        
        stats.put("Minimum Delay (min)", min);
        stats.put("Maximum Delay (min)", max);
        stats.put("Average Delay (min)", total / records.size());
        return stats;
    }
    
    /** @return average rating for each service */
    public Map<String, Double> getAverageServiceRatings() {
        Map<String, Double> ratings = new HashMap<>();
        long w = 0, t = 0, b = 0, g = 0, f = 0, bo = 0, s = 0;
        long e = 0, o = 0, l = 0, ba = 0, c = 0, inf = 0, cl = 0;
        
        for (PassengerRecord r : records) {
            w += r.getInflightWifiService();
            t += r.getDepartureArrivalTimeConvenient();
            b += r.getEaseOfOnlineBooking();
            g += r.getGateLocation();
            f += r.getFoodAndDrink();
            bo += r.getOnlineBoarding();
            s += r.getSeatComfort();
            e += r.getInflightEntertainment();
            o += r.getOnBoardService();
            l += r.getLegRoomService();
            ba += r.getBaggageHandling();
            c += r.getCheckinService();
            inf += r.getInflightService();
            cl += r.getCleanliness();
        }
        
        int n = records.size();
        ratings.put("Inflight Wifi", (double) w / n);
        ratings.put("Time Convenient", (double) t / n);
        ratings.put("Online Booking", (double) b / n);
        ratings.put("Gate Location", (double) g / n);
        ratings.put("Food and Drink", (double) f / n);
        ratings.put("Online Boarding", (double) bo / n);
        ratings.put("Seat Comfort", (double) s / n);
        ratings.put("Entertainment", (double) e / n);
        ratings.put("On-board Service", (double) o / n);
        ratings.put("Leg Room", (double) l / n);
        ratings.put("Baggage Handling", (double) ba / n);
        ratings.put("Check-in Service", (double) c / n);
        ratings.put("Inflight Service", (double) inf / n);
        ratings.put("Cleanliness", (double) cl / n);
        return ratings;
    }
    
    /** @return summary of top and bottom rated services */
    public String getServiceRankingSummary() {
        Map<String, Double> ratings = getAverageServiceRatings();
        String[] services = ratings.keySet().toArray(new String[0]);
        
        // Sort by rating (descending)
        for (int i = 0; i < services.length - 1; i++) {
            for (int j = 0; j < services.length - i - 1; j++) {
                if (ratings.get(services[j]) < ratings.get(services[j + 1])) {
                    String temp = services[j];
                    services[j] = services[j + 1];
                    services[j + 1] = temp;
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("Top 3:\n");
        for (int i = 0; i < 3; i++) {
            sb.append("  ").append(i + 1).append(". ").append(services[i]);
            sb.append(": ").append(String.format("%.2f", ratings.get(services[i]))).append("\n");
        }
        sb.append("Bottom 3:\n");
        int len = services.length;
        for (int i = len - 3; i < len; i++) {
            sb.append("  ").append(len - i).append(". ").append(services[i]);
            sb.append(": ").append(String.format("%.2f", ratings.get(services[i]))).append("\n");
        }
        return sb.toString();
    }
    
    /** @return satisfaction rate by travel class */
    public Map<String, Double> getSatisfactionRateByClass() {
        Map<String, Integer> totals = new HashMap<>();
        Map<String, Integer> satisfied = new HashMap<>();
        
        for (PassengerRecord r : records) {
            String cls = r.getTravelClass();
            totals.put(cls, totals.getOrDefault(cls, 0) + 1);
            if (r.isSatisfied()) {
                satisfied.put(cls, satisfied.getOrDefault(cls, 0) + 1);
            }
        }
        
        Map<String, Double> rates = new HashMap<>();
        for (String cls : totals.keySet()) {
            rates.put(cls, satisfied.getOrDefault(cls, 0) * 100.0 / totals.get(cls));
        }
        return rates;
    }
    
    /** @return satisfaction rate by customer type */
    public Map<String, Double> getSatisfactionRateByCustomerType() {
        Map<String, Integer> totals = new HashMap<>();
        Map<String, Integer> satisfied = new HashMap<>();
        
        for (PassengerRecord r : records) {
            String type = r.getCustomerType();
            totals.put(type, totals.getOrDefault(type, 0) + 1);
            if (r.isSatisfied()) {
                satisfied.put(type, satisfied.getOrDefault(type, 0) + 1);
            }
        }
        
        Map<String, Double> rates = new HashMap<>();
        for (String type : totals.keySet()) {
            rates.put(type, satisfied.getOrDefault(type, 0) * 100.0 / totals.get(type));
        }
        return rates;
    }
    
    /** @return satisfaction rate by travel type */
    public Map<String, Double> getSatisfactionRateByTravelType() {
        Map<String, Integer> totals = new HashMap<>();
        Map<String, Integer> satisfied = new HashMap<>();
        
        for (PassengerRecord r : records) {
            String type = r.getTypeOfTravel();
            totals.put(type, totals.getOrDefault(type, 0) + 1);
            if (r.isSatisfied()) {
                satisfied.put(type, satisfied.getOrDefault(type, 0) + 1);
            }
        }
        
        Map<String, Double> rates = new HashMap<>();
        for (String type : totals.keySet()) {
            rates.put(type, satisfied.getOrDefault(type, 0) * 100.0 / totals.get(type));
        }
        return rates;
    }
    
    /** @return satisfaction rate by age group */
    public Map<String, Double> getSatisfactionRateByAgeGroup() {
        Map<String, Integer> totals = new HashMap<>();
        Map<String, Integer> satisfied = new HashMap<>();
        
        for (PassengerRecord r : records) {
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
        
        Map<String, Double> rates = new HashMap<>();
        for (String group : totals.keySet()) {
            rates.put(group, satisfied.getOrDefault(group, 0) * 100.0 / totals.get(group));
        }
        return rates;
    }
    
    /** @return records filtered by satisfaction */
    public List<PassengerRecord> filterBySatisfaction(boolean wantSatisfied) {
        List<PassengerRecord> result = new ArrayList<>();
        for (PassengerRecord r : records) {
            if (r.isSatisfied() == wantSatisfied) {
                result.add(r);
            }
        }
        return result;
    }
    
    /** @return records filtered by travel class */
    public List<PassengerRecord> filterByClass(String travelClass) {
        List<PassengerRecord> result = new ArrayList<>();
        for (PassengerRecord r : records) {
            if (r.getTravelClass().equalsIgnoreCase(travelClass)) {
                result.add(r);
            }
        }
        return result;
    }
    
    /** @return records filtered by age range */
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
    
    /** @return record with matching ID or null */
    public PassengerRecord searchById(int id) {
        for (PassengerRecord r : records) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }
    
    /** @return comprehensive data summary */
    public String generateComprehensiveSummary() {
        StringBuilder sb = new StringBuilder();
        int total = records.size();
        int satisfied = filterBySatisfaction(true).size();
        
        sb.append("\n=== DATA SUMMARY ===\n\n");
        sb.append("Total Records: ").append(total).append("\n");
        sb.append("Satisfied: ").append(satisfied).append(" (");
        sb.append(String.format("%.1f", satisfied * 100.0 / total)).append("%)\n");
        sb.append("Dissatisfied: ").append(total - satisfied).append(" (");
        sb.append(String.format("%.1f", (total - satisfied) * 100.0 / total)).append("%)\n\n");
        
        sb.append("Gender:\n");
        for (Map.Entry<String, Long> e : getGenderDistribution().entrySet()) {
            sb.append("  ").append(e.getKey()).append(": ").append(e.getValue()).append("\n");
        }
        
        sb.append("\nAge: Min=").append(getAgeStatistics().get("Minimum Age").intValue());
        sb.append(", Max=").append(getAgeStatistics().get("Maximum Age").intValue());
        sb.append(", Avg=").append(String.format("%.1f", getAgeStatistics().get("Average Age"))).append("\n");
        
        sb.append("\nFlight Distance: Avg=");
        sb.append(String.format("%.1f", getFlightDistanceStatistics().get("Average Distance")));
        sb.append(" miles\n");
        
        sb.append("\nService Ratings:\n");
        sb.append(getServiceRankingSummary());
        
        return sb.toString();
    }
}
