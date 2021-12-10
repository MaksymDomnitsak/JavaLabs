package model;

public class Destination {
    private int id;
    private String destination;

    public Destination(int id,String destination){
        this.id=id;
        this.destination=destination;
    }
    public Destination(String destination){
        this.destination=destination;
    }

    public Destination() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
