package model;

/**
 * Model class for passenger satisfaction data.
 * Stores flight details, service ratings, and satisfaction level.
 * 
 * @author IT221 Student
 * @version 1.0
 */
public class PassengerRecord {
    
    // Identification
    private String id;
    
    // Demographics
    private String gender;
    private String customerType;
    private int age;
    
    // Travel Info
    private String typeOfTravel;
    private String travelClass;
    private int flightDistance;
    private String date;
    
    // Service Ratings (0-5 scale)
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
    
    // Delays
    private double departureDelayInMinutes;
    private double arrivalDelayInMinutes;
    
    // Outcome
    private String satisfaction;
    
    /** Default constructor. */
    public PassengerRecord() {}
    
    // Getters
    public String getId() { return id; }
    public String getGender() { return gender; }
    public String getCustomerType() { return customerType; }
    public int getAge() { return age; }
    public String getTypeOfTravel() { return typeOfTravel; }
    public String getTravelClass() { return travelClass; }
    public int getFlightDistance() { return flightDistance; }
    public String getDate() { return date; }
    public int getInflightWifiService() { return inflightWifiService; }
    public int getDepartureArrivalTimeConvenient() { return departureArrivalTimeConvenient; }
    public int getEaseOfOnlineBooking() { return easeOfOnlineBooking; }
    public int getGateLocation() { return gateLocation; }
    public int getFoodAndDrink() { return foodAndDrink; }
    public int getOnlineBoarding() { return onlineBoarding; }
    public int getSeatComfort() { return seatComfort; }
    public int getInflightEntertainment() { return inflightEntertainment; }
    public int getOnBoardService() { return onBoardService; }
    public int getLegRoomService() { return legRoomService; }
    public int getBaggageHandling() { return baggageHandling; }
    public int getCheckinService() { return checkinService; }
    public int getInflightService() { return inflightService; }
    public int getCleanliness() { return cleanliness; }
    public double getDepartureDelayInMinutes() { return departureDelayInMinutes; }
    public double getArrivalDelayInMinutes() { return arrivalDelayInMinutes; }
    public String getSatisfaction() { return satisfaction; }
    
    // Setters
    public void setId(String id) { this.id = id; }
    public void setGender(String gender) { this.gender = gender; }
    public void setCustomerType(String customerType) { this.customerType = customerType; }
    public void setAge(int age) { this.age = age; }
    public void setTypeOfTravel(String typeOfTravel) { this.typeOfTravel = typeOfTravel; }
    public void setTravelClass(String travelClass) { this.travelClass = travelClass; }
    public void setFlightDistance(int flightDistance) { this.flightDistance = flightDistance; }
    public void setDate(String date) { this.date = date; }
    public void setInflightWifiService(int inflightWifiService) { this.inflightWifiService = inflightWifiService; }
    public void setDepartureArrivalTimeConvenient(int departureArrivalTimeConvenient) { this.departureArrivalTimeConvenient = departureArrivalTimeConvenient; }
    public void setEaseOfOnlineBooking(int easeOfOnlineBooking) { this.easeOfOnlineBooking = easeOfOnlineBooking; }
    public void setGateLocation(int gateLocation) { this.gateLocation = gateLocation; }
    public void setFoodAndDrink(int foodAndDrink) { this.foodAndDrink = foodAndDrink; }
    public void setOnlineBoarding(int onlineBoarding) { this.onlineBoarding = onlineBoarding; }
    public void setSeatComfort(int seatComfort) { this.seatComfort = seatComfort; }
    public void setInflightEntertainment(int inflightEntertainment) { this.inflightEntertainment = inflightEntertainment; }
    public void setOnBoardService(int onBoardService) { this.onBoardService = onBoardService; }
    public void setLegRoomService(int legRoomService) { this.legRoomService = legRoomService; }
    public void setBaggageHandling(int baggageHandling) { this.baggageHandling = baggageHandling; }
    public void setCheckinService(int checkinService) { this.checkinService = checkinService; }
    public void setInflightService(int inflightService) { this.inflightService = inflightService; }
    public void setCleanliness(int cleanliness) { this.cleanliness = cleanliness; }
    public void setDepartureDelayInMinutes(double departureDelayInMinutes) { this.departureDelayInMinutes = departureDelayInMinutes; }
    public void setArrivalDelayInMinutes(double arrivalDelayInMinutes) { this.arrivalDelayInMinutes = arrivalDelayInMinutes; }
    public void setSatisfaction(String satisfaction) { this.satisfaction = satisfaction; }
    
    /** @return true if passenger is satisfied */
    public boolean isSatisfied() {
        return "satisfied".equalsIgnoreCase(satisfaction);
    }
    
    /** @return average of all 14 service ratings */
    public double getAverageServiceRating() {
        int total = inflightWifiService + departureArrivalTimeConvenient 
                  + easeOfOnlineBooking + gateLocation + foodAndDrink 
                  + onlineBoarding + seatComfort + inflightEntertainment 
                  + onBoardService + legRoomService + baggageHandling 
                  + checkinService + inflightService + cleanliness;
        return total / 14.0;
    }
    
    @Override
    public String toString() {
        return "ID=" + id + ", " + gender + ", Age=" + age + ", " + travelClass + ", " + satisfaction;
    }
}
