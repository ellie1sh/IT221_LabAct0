package model;

/**
 * PassengerRecord - A model class for airline passenger data.
 * 
 * <p>This class stores all information about a single passenger's
 * flight experience and satisfaction survey results.</p>
 * 
 * <h2>What is a Model Class?</h2>
 * <p>A model class is like a container that holds related data together.
 * Think of it as a form where each field stores one piece of information.</p>
 * 
 * <h2>Data Types Used:</h2>
 * <ul>
 *   <li><b>String</b> - Text data (names, categories)</li>
 *   <li><b>int</b> - Whole numbers (age, ratings 0-5)</li>
 *   <li><b>double</b> - Decimal numbers (delay times)</li>
 * </ul>
 * 
 * @author IT221 Student
 * @version 1.0
 */
public class PassengerRecord {
    
    // ============================================================
    // FIELDS (Variables that store passenger data)
    // ============================================================
    
    /**
     * Row number in the CSV file.
     */
    private int rowIndex;
    
    /**
     * Unique passenger ID number.
     */
    private int id;
    
    /**
     * Passenger's gender (Male or Female).
     */
    private String gender;
    
    /**
     * Customer loyalty status (Loyal Customer or disloyal Customer).
     */
    private String customerType;
    
    /**
     * Passenger's age in years.
     */
    private int age;
    
    /**
     * Purpose of travel (Business travel or Personal Travel).
     */
    private String typeOfTravel;
    
    /**
     * Travel class (Business, Eco, or Eco Plus).
     */
    private String travelClass;
    
    /**
     * Flight distance in miles.
     */
    private int flightDistance;
    
    /**
     * Date of the record in format month:date:year.
     */
    private String date;
    
    // Service Ratings (0 to 5 scale, where 5 is best)
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
    
    /**
     * Departure delay in minutes.
     */
    private double departureDelayInMinutes;
    
    /**
     * Arrival delay in minutes.
     */
    private double arrivalDelayInMinutes;
    
    /**
     * Overall satisfaction (satisfied or neutral or dissatisfied).
     */
    private String satisfaction;
    
    // ============================================================
    // CONSTRUCTOR
    // ============================================================
    
    /**
     * Creates a new empty PassengerRecord.
     * 
     * <p>A constructor is a special method that creates a new object.
     * This one creates an empty record that we fill in later.</p>
     */
    public PassengerRecord() {
        // Empty constructor - values are set using setter methods
    }
    
    // ============================================================
    // GETTER METHODS (Methods that retrieve data)
    // ============================================================
    
    /**
     * Gets the row index.
     * @return the row number in CSV file
     */
    public int getRowIndex() {
        return rowIndex;
    }
    
    /**
     * Gets the passenger ID.
     * @return the unique passenger ID
     */
    public int getId() {
        return id;
    }
    
    /**
     * Gets the passenger's gender.
     * @return "Male" or "Female"
     */
    public String getGender() {
        return gender;
    }
    
    /**
     * Gets the customer type.
     * @return "Loyal Customer" or "disloyal Customer"
     */
    public String getCustomerType() {
        return customerType;
    }
    
    /**
     * Gets the passenger's age.
     * @return age in years
     */
    public int getAge() {
        return age;
    }
    
    /**
     * Gets the type of travel.
     * @return "Business travel" or "Personal Travel"
     */
    public String getTypeOfTravel() {
        return typeOfTravel;
    }
    
    /**
     * Gets the travel class.
     * @return "Business", "Eco", or "Eco Plus"
     */
    public String getTravelClass() {
        return travelClass;
    }
    
    /**
     * Gets the flight distance.
     * @return distance in miles
     */
    public int getFlightDistance() {
        return flightDistance;
    }
    
    /**
     * Gets the record date.
     * @return date in month:date:year format
     */
    public String getDate() {
        return date;
    }
    
    /**
     * Gets inflight wifi service rating.
     * @return rating from 0 to 5
     */
    public int getInflightWifiService() {
        return inflightWifiService;
    }
    
    /**
     * Gets departure/arrival time convenience rating.
     * @return rating from 0 to 5
     */
    public int getDepartureArrivalTimeConvenient() {
        return departureArrivalTimeConvenient;
    }
    
    /**
     * Gets ease of online booking rating.
     * @return rating from 0 to 5
     */
    public int getEaseOfOnlineBooking() {
        return easeOfOnlineBooking;
    }
    
    /**
     * Gets gate location rating.
     * @return rating from 0 to 5
     */
    public int getGateLocation() {
        return gateLocation;
    }
    
    /**
     * Gets food and drink rating.
     * @return rating from 0 to 5
     */
    public int getFoodAndDrink() {
        return foodAndDrink;
    }
    
    /**
     * Gets online boarding rating.
     * @return rating from 0 to 5
     */
    public int getOnlineBoarding() {
        return onlineBoarding;
    }
    
    /**
     * Gets seat comfort rating.
     * @return rating from 0 to 5
     */
    public int getSeatComfort() {
        return seatComfort;
    }
    
    /**
     * Gets inflight entertainment rating.
     * @return rating from 0 to 5
     */
    public int getInflightEntertainment() {
        return inflightEntertainment;
    }
    
    /**
     * Gets on-board service rating.
     * @return rating from 0 to 5
     */
    public int getOnBoardService() {
        return onBoardService;
    }
    
    /**
     * Gets leg room service rating.
     * @return rating from 0 to 5
     */
    public int getLegRoomService() {
        return legRoomService;
    }
    
    /**
     * Gets baggage handling rating.
     * @return rating from 0 to 5
     */
    public int getBaggageHandling() {
        return baggageHandling;
    }
    
    /**
     * Gets check-in service rating.
     * @return rating from 0 to 5
     */
    public int getCheckinService() {
        return checkinService;
    }
    
    /**
     * Gets inflight service rating.
     * @return rating from 0 to 5
     */
    public int getInflightService() {
        return inflightService;
    }
    
    /**
     * Gets cleanliness rating.
     * @return rating from 0 to 5
     */
    public int getCleanliness() {
        return cleanliness;
    }
    
    /**
     * Gets departure delay time.
     * @return delay in minutes
     */
    public double getDepartureDelayInMinutes() {
        return departureDelayInMinutes;
    }
    
    /**
     * Gets arrival delay time.
     * @return delay in minutes
     */
    public double getArrivalDelayInMinutes() {
        return arrivalDelayInMinutes;
    }
    
    /**
     * Gets the satisfaction level.
     * @return "satisfied" or "neutral or dissatisfied"
     */
    public String getSatisfaction() {
        return satisfaction;
    }
    
    // ============================================================
    // SETTER METHODS (Methods that store data)
    // ============================================================
    
    /**
     * Sets the row index.
     * @param rowIndex the row number to set
     */
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }
    
    /**
     * Sets the passenger ID.
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Sets the gender.
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    /**
     * Sets the customer type.
     * @param customerType the customer type to set
     */
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }
    
    /**
     * Sets the age.
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }
    
    /**
     * Sets the type of travel.
     * @param typeOfTravel the travel type to set
     */
    public void setTypeOfTravel(String typeOfTravel) {
        this.typeOfTravel = typeOfTravel;
    }
    
    /**
     * Sets the travel class.
     * @param travelClass the class to set
     */
    public void setTravelClass(String travelClass) {
        this.travelClass = travelClass;
    }
    
    /**
     * Sets the flight distance.
     * @param flightDistance the distance to set
     */
    public void setFlightDistance(int flightDistance) {
        this.flightDistance = flightDistance;
    }
    
    /**
     * Sets the record date.
     * @param date the date to set (month:date:year format)
     */
    public void setDate(String date) {
        this.date = date;
    }
    
    /**
     * Sets inflight wifi service rating.
     * @param inflightWifiService rating to set (0-5)
     */
    public void setInflightWifiService(int inflightWifiService) {
        this.inflightWifiService = inflightWifiService;
    }
    
    /**
     * Sets departure/arrival time convenience rating.
     * @param departureArrivalTimeConvenient rating to set (0-5)
     */
    public void setDepartureArrivalTimeConvenient(int departureArrivalTimeConvenient) {
        this.departureArrivalTimeConvenient = departureArrivalTimeConvenient;
    }
    
    /**
     * Sets ease of online booking rating.
     * @param easeOfOnlineBooking rating to set (0-5)
     */
    public void setEaseOfOnlineBooking(int easeOfOnlineBooking) {
        this.easeOfOnlineBooking = easeOfOnlineBooking;
    }
    
    /**
     * Sets gate location rating.
     * @param gateLocation rating to set (0-5)
     */
    public void setGateLocation(int gateLocation) {
        this.gateLocation = gateLocation;
    }
    
    /**
     * Sets food and drink rating.
     * @param foodAndDrink rating to set (0-5)
     */
    public void setFoodAndDrink(int foodAndDrink) {
        this.foodAndDrink = foodAndDrink;
    }
    
    /**
     * Sets online boarding rating.
     * @param onlineBoarding rating to set (0-5)
     */
    public void setOnlineBoarding(int onlineBoarding) {
        this.onlineBoarding = onlineBoarding;
    }
    
    /**
     * Sets seat comfort rating.
     * @param seatComfort rating to set (0-5)
     */
    public void setSeatComfort(int seatComfort) {
        this.seatComfort = seatComfort;
    }
    
    /**
     * Sets inflight entertainment rating.
     * @param inflightEntertainment rating to set (0-5)
     */
    public void setInflightEntertainment(int inflightEntertainment) {
        this.inflightEntertainment = inflightEntertainment;
    }
    
    /**
     * Sets on-board service rating.
     * @param onBoardService rating to set (0-5)
     */
    public void setOnBoardService(int onBoardService) {
        this.onBoardService = onBoardService;
    }
    
    /**
     * Sets leg room service rating.
     * @param legRoomService rating to set (0-5)
     */
    public void setLegRoomService(int legRoomService) {
        this.legRoomService = legRoomService;
    }
    
    /**
     * Sets baggage handling rating.
     * @param baggageHandling rating to set (0-5)
     */
    public void setBaggageHandling(int baggageHandling) {
        this.baggageHandling = baggageHandling;
    }
    
    /**
     * Sets check-in service rating.
     * @param checkinService rating to set (0-5)
     */
    public void setCheckinService(int checkinService) {
        this.checkinService = checkinService;
    }
    
    /**
     * Sets inflight service rating.
     * @param inflightService rating to set (0-5)
     */
    public void setInflightService(int inflightService) {
        this.inflightService = inflightService;
    }
    
    /**
     * Sets cleanliness rating.
     * @param cleanliness rating to set (0-5)
     */
    public void setCleanliness(int cleanliness) {
        this.cleanliness = cleanliness;
    }
    
    /**
     * Sets departure delay time.
     * @param departureDelayInMinutes delay to set in minutes
     */
    public void setDepartureDelayInMinutes(double departureDelayInMinutes) {
        this.departureDelayInMinutes = departureDelayInMinutes;
    }
    
    /**
     * Sets arrival delay time.
     * @param arrivalDelayInMinutes delay to set in minutes
     */
    public void setArrivalDelayInMinutes(double arrivalDelayInMinutes) {
        this.arrivalDelayInMinutes = arrivalDelayInMinutes;
    }
    
    /**
     * Sets the satisfaction level.
     * @param satisfaction the satisfaction to set
     */
    public void setSatisfaction(String satisfaction) {
        this.satisfaction = satisfaction;
    }
    
    // ============================================================
    // HELPER METHODS (Useful calculations)
    // ============================================================
    
    /**
     * Checks if the passenger is satisfied.
     * 
     * <p>This method compares the satisfaction string to "satisfied".
     * The equalsIgnoreCase method ignores uppercase/lowercase differences.</p>
     * 
     * @return true if satisfied, false otherwise
     */
    public boolean isSatisfied() {
        return "satisfied".equalsIgnoreCase(satisfaction);
    }
    
    /**
     * Calculates the average service rating.
     * 
     * <p>This adds up all 14 service ratings and divides by 14
     * to get the average score (0 to 5 scale).</p>
     * 
     * @return average rating from 0 to 5
     */
    public double getAverageServiceRating() {
        // Add up all service ratings
        int total = inflightWifiService + departureArrivalTimeConvenient 
                  + easeOfOnlineBooking + gateLocation + foodAndDrink 
                  + onlineBoarding + seatComfort + inflightEntertainment 
                  + onBoardService + legRoomService + baggageHandling 
                  + checkinService + inflightService + cleanliness;
        
        // Divide by 14 to get average (14 different services)
        return total / 14.0;
    }
    
    /**
     * Returns a simple text description of this record.
     * 
     * <p>The toString method is called automatically when you
     * try to print an object.</p>
     * 
     * @return formatted string with key information
     */
    @Override
    public String toString() {
        return "Passenger ID=" + id + ", Gender=" + gender + ", Age=" + age 
               + ", Class=" + travelClass + ", Satisfaction=" + satisfaction
               + ", Date=" + date;
    }
}
