import java.time.LocalDate;

public class FlightRecord {
    private int id;
    private String gender;
    private String customerType;
    private int age;
    private String typeOfTravel;
    private String flightClass;
    private int flightDistance;
    private int departureDelay;
    private double arrivalDelay;
    private String satisfaction;
    private LocalDate flightDate;

    public FlightRecord(int id, String gender, String customerType, int age, String typeOfTravel, 
                       String flightClass, int flightDistance, int departureDelay, double arrivalDelay, 
                       String satisfaction, LocalDate flightDate) {
        this.id = id;
        this.gender = gender;
        this.customerType = customerType;
        this.age = age;
        this.typeOfTravel = typeOfTravel;
        this.flightClass = flightClass;
        this.flightDistance = flightDistance;
        this.departureDelay = departureDelay;
        this.arrivalDelay = arrivalDelay;
        this.satisfaction = satisfaction;
        this.flightDate = flightDate;
    }

    // Getters
    public int getId() { return id; }
    public String getGender() { return gender; }
    public String getCustomerType() { return customerType; }
    public int getAge() { return age; }
    public String getTypeOfTravel() { return typeOfTravel; }
    public String getFlightClass() { return flightClass; }
    public int getFlightDistance() { return flightDistance; }
    public int getDepartureDelay() { return departureDelay; }
    public double getArrivalDelay() { return arrivalDelay; }
    public String getSatisfaction() { return satisfaction; }
    public LocalDate getFlightDate() { return flightDate; }

    @Override
    public String toString() {
        return String.format("ID: %d | Date: %s | Class: %s | Delay: %.1f min | Satisfaction: %s", 
            id, flightDate, flightClass, arrivalDelay, satisfaction);
    }
}
