package model;

public class Storage {
    private int id;
    private Medicine medicine;
    private int quantity;

    public Storage(Medicine medicine,int quantity){
        this.medicine=medicine;
        this.quantity=quantity;
    }

    public Storage(int id,Medicine medicine,int quantity){
        this(medicine,quantity);
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

