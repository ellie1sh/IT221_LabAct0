import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class DataManager {
    private List<FlightRecord> records;

    public DataManager(List<FlightRecord> records) {
        this.records = records;
    }

    public int getTotalRecords() {
        return records.size();
    }

    public double getAverageArrivalDelay() {
        return records.stream()
                .mapToDouble(FlightRecord::getArrivalDelay)
                .average()
                .orElse(0.0);
    }

    public List<FlightRecord> getRecordsByClass(String flightClass) {
        return records.stream()
                .filter(r -> r.getFlightClass().equalsIgnoreCase(flightClass))
                .limit(10) // Limit output for readability
                .collect(Collectors.toList());
    }

    public FlightRecord getLongestFlight() {
        return records.stream()
                .max(Comparator.comparingInt(FlightRecord::getFlightDistance))
                .orElse(null);
    }
    
    public FlightRecord findById(int id) {
        return records.stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public long countSatisfiedCustomers() {
        return records.stream()
                .filter(r -> "satisfied".equalsIgnoreCase(r.getSatisfaction()))
                .count();
    }
}
