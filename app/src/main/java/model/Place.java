package model;

public class Place {

    private final String location;
    private final String destination;

    public Place(String location, String destination) {
        this.location = location;
        this.destination = destination;
    }

    public String getLocation(){return location;}

    public String getDestination(){return destination;}


}
