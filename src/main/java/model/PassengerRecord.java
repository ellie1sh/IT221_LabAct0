package model;

/**
 * This class represents a single passenger record from the dataset.
 * It holds all the information about one passenger's flight experience.
 * 
 * <p>
 * It includes:
 * <ul>
 *   <li>Personal details (Gender, Age, Customer Type)</li>
 *   <li>Flight details (Type, Class, Distance, Date)</li>
 *   <li>Ratings for various services (0-5 scale)</li>
 *   <li>Delays (in minutes)</li>
 *   <li>Overall satisfaction level</li>
 * </ul>
 * </p>
 * 
 * @author IT221 Student
 */
public class PassengerRecord {
    
    // --- Identification ---
    /** The row number in the CSV file */
    private int rowIndex;
    /** The unique ID of the passenger */
    private int id;
    
    // --- Personal Information ---
    /** Gender of the passenger (Male/Female) */
    private String gender;
    /** Type of customer (Loyal/Disloyal) */
    private String customerType;
    /** Age of the passenger */
    private int age;
    
    // --- Flight Information ---
    /** Purpose of travel (Business/Personal) */
    private String typeOfTravel;
    /** Class of travel (Business/Eco/Eco Plus) */
    private String travelClass;
    /** Distance of the flight in miles */
    private int flightDistance;
    /** Date of the flight (Month:Date:Year) */
    private String flightDate;
    
    // --- Service Ratings (0 to 5) ---
    private int inflightWifiService;
    private int departureArrivalTimeConvenient;
    private int easeOfOnlineBooking;
    private int gateLocation;
    private int foodAndDrink;
    private int onlineBoarding;
    private int seatComfort;
    private int inflightEntertainment;
    private int onBoardService;
    private int legRoomService;
    private int baggageHandling;
    private int checkinService;
    private int inflightService;
    private int cleanliness;
    
    // --- Delays ---
    /** Delay in departure in minutes */
    private double departureDelayInMinutes;
    /** Delay in arrival in minutes */
    private double arrivalDelayInMinutes;
    
    // --- Outcome ---
    /** Final satisfaction level (satisfied/neutral or dissatisfied) */
    private String satisfaction;
    
    /**
     * Default constructor.
     * Creates an empty record.
     */
    public PassengerRecord() {
    }
    
    // ================= GETTERS AND SETTERS =================
    // These methods allow us to read (get) and write (set) the private fields.

    public int getRowIndex() { return rowIndex; }
    public void setRowIndex(int rowIndex) { this.rowIndex = rowIndex; }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    
    public String getCustomerType() { return customerType; }
    public void setCustomerType(String customerType) { this.customerType = customerType; }
    
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    
    public String getTypeOfTravel() { return typeOfTravel; }
    public void setTypeOfTravel(String typeOfTravel) { this.typeOfTravel = typeOfTravel; }
    
    public String getTravelClass() { return travelClass; }
    public void setTravelClass(String travelClass) { this.travelClass = travelClass; }
    
    public int getFlightDistance() { return flightDistance; }
    public void setFlightDistance(int flightDistance) { this.flightDistance = flightDistance; }
    
    public String getFlightDate() { return flightDate; }
    public void setFlightDate(String flightDate) { this.flightDate = flightDate; }

    public int getInflightWifiService() { return inflightWifiService; }
    public void setInflightWifiService(int inflightWifiService) { this.inflightWifiService = inflightWifiService; }
    
    public int getDepartureArrivalTimeConvenient() { return departureArrivalTimeConvenient; }
    public void setDepartureArrivalTimeConvenient(int departureArrivalTimeConvenient) { this.departureArrivalTimeConvenient = departureArrivalTimeConvenient; }
    
    public int getEaseOfOnlineBooking() { return easeOfOnlineBooking; }
    public void setEaseOfOnlineBooking(int easeOfOnlineBooking) { this.easeOfOnlineBooking = easeOfOnlineBooking; }
    
    public int getGateLocation() { return gateLocation; }
    public void setGateLocation(int gateLocation) { this.gateLocation = gateLocation; }
    
    public int getFoodAndDrink() { return foodAndDrink; }
    public void setFoodAndDrink(int foodAndDrink) { this.foodAndDrink = foodAndDrink; }
    
    public int getOnlineBoarding() { return onlineBoarding; }
    public void setOnlineBoarding(int onlineBoarding) { this.onlineBoarding = onlineBoarding; }
    
    public int getSeatComfort() { return seatComfort; }
    public void setSeatComfort(int seatComfort) { this.seatComfort = seatComfort; }
    
    public int getInflightEntertainment() { return inflightEntertainment; }
    public void setInflightEntertainment(int inflightEntertainment) { this.inflightEntertainment = inflightEntertainment; }
    
    public int getOnBoardService() { return onBoardService; }
    public void setOnBoardService(int onBoardService) { this.onBoardService = onBoardService; }
    
    public int getLegRoomService() { return legRoomService; }
    public void setLegRoomService(int legRoomService) { this.legRoomService = legRoomService; }
    
    public int getBaggageHandling() { return baggageHandling; }
    public void setBaggageHandling(int baggageHandling) { this.baggageHandling = baggageHandling; }
    
    public int getCheckinService() { return checkinService; }
    public void setCheckinService(int checkinService) { this.checkinService = checkinService; }
    
    public int getInflightService() { return inflightService; }
    public void setInflightService(int inflightService) { this.inflightService = inflightService; }
    
    public int getCleanliness() { return cleanliness; }
    public void setCleanliness(int cleanliness) { this.cleanliness = cleanliness; }
    
    public double getDepartureDelayInMinutes() { return departureDelayInMinutes; }
    public void setDepartureDelayInMinutes(double departureDelayInMinutes) { this.departureDelayInMinutes = departureDelayInMinutes; }
    
    public double getArrivalDelayInMinutes() { return arrivalDelayInMinutes; }
    public void setArrivalDelayInMinutes(double arrivalDelayInMinutes) { this.arrivalDelayInMinutes = arrivalDelayInMinutes; }
    
    public String getSatisfaction() { return satisfaction; }
    public void setSatisfaction(String satisfaction) { this.satisfaction = satisfaction; }
    
    // ================= HELPER METHODS =================
    
    /**
     * Checks if the passenger was satisfied.
     * 
     * @return true if satisfaction is "satisfied", false otherwise
     */
    public boolean isSatisfied() {
        if (satisfaction != null && satisfaction.equalsIgnoreCase("satisfied")) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Calculates the average rating given by this passenger.
     * It adds up all 14 service ratings and divides by 14.
     * 
     * @return The average rating (0.0 to 5.0)
     */
    public double getAverageServiceRating() {
        int sum = 0;
        sum += inflightWifiService;
        sum += departureArrivalTimeConvenient;
        sum += easeOfOnlineBooking;
        sum += gateLocation;
        sum += foodAndDrink;
        sum += onlineBoarding;
        sum += seatComfort;
        sum += inflightEntertainment;
        sum += onBoardService;
        sum += legRoomService;
        sum += baggageHandling;
        sum += checkinService;
        sum += inflightService;
        sum += cleanliness;
        
        return sum / 14.0;
    }
    
    /**
     * Returns a string representation of the passenger record.
     * Useful for debugging and printing.
     */
    @Override
    public String toString() {
        return "Passenger #" + id + " (" + gender + ", " + age + " years old) - " + satisfaction;
    }
}
