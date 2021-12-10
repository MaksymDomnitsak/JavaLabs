package model;


public class Medicine {
    private int id;
    private String name;
    private Manufacturer manufacturer;
    private Destination destination;
    private boolean without_recipe;
    private double selfprice;
    //public double price = selfprice * 1.10;

    public Medicine(int id,String name, Manufacturer manufact, Destination destination_id,boolean recipe, double selfprice){
        this(name,manufact,destination_id,recipe,selfprice);
        this.id=id;
    }
    public Medicine(String name, Manufacturer manufact, Destination destination_id,boolean recipe, double selfprice){
        this.name = name;
        this.manufacturer = manufact;
        this.destination = destination_id;
        this.without_recipe = recipe;
        this.selfprice = selfprice;
    }

    public Medicine() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public boolean isWithout_recipe() {
        return without_recipe;
    }

    public void setWithout_recipe(boolean without_recipe) {
        this.without_recipe = without_recipe;
    }

    public double getSelfprice() {
        return selfprice;
    }

    public void setSelfprice(double selfprice) {
        this.selfprice = selfprice;
    }
}
