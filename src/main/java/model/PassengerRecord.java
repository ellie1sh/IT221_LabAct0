package model;

/**
 * Model class representing a passenger satisfaction record.
 * Contains information about flight details, service ratings, and satisfaction level.
 * 
 * Data Types:
 * - String: Gender, Customer Type, Type of Travel, Class, Satisfaction
 * - Integer: ID, Age, Flight Distance, Service Ratings (0-5)
 * - Double: Departure Delay, Arrival Delay
 */
public class PassengerRecord {
    
    // Identification
    private int rowIndex;
    private int id;
    
    // Demographics (String and Integer types)
    private String gender;
    private String customerType;
    private int age;
    
    // Travel Information (String and Integer types)
    private String typeOfTravel;
    private String travelClass;
    private int flightDistance;
    
    // Service Ratings (Integer type, scale 0-5)
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
    
    // Delays (Double type)
    private double departureDelayInMinutes;
    private double arrivalDelayInMinutes;
    
    // Satisfaction (String type)
    private String satisfaction;
    
    // Constructor
    public PassengerRecord() {}
    
    // Getters and Setters
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
    
    /**
     * Check if the passenger is satisfied
     * @return true if satisfied, false otherwise
     */
    public boolean isSatisfied() {
        return "satisfied".equalsIgnoreCase(satisfaction);
    }
    
    /**
     * Calculate the average service rating across all rated services
     * @return average rating (0-5 scale)
     */
    public double getAverageServiceRating() {
        int total = inflightWifiService + departureArrivalTimeConvenient + easeOfOnlineBooking +
                    gateLocation + foodAndDrink + onlineBoarding + seatComfort + 
                    inflightEntertainment + onBoardService + legRoomService + 
                    baggageHandling + checkinService + inflightService + cleanliness;
        return total / 14.0;
    }
    
    @Override
    public String toString() {
        return String.format("PassengerRecord[ID=%d, Gender=%s, Age=%d, Class=%s, Satisfaction=%s]",
                id, gender, age, travelClass, satisfaction);
    }
}
