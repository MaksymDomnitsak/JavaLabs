package model;

import java.time.LocalDateTime;

public class Selling {
    private int id=1;
    private Medicine medicine;
    private int sold_amount;
    private LocalDateTime sold_at;

    public Selling(int id,Medicine medicine,int amount,LocalDateTime time){
        this(medicine,amount,time);
        this.id=id;

    }
    public Selling(Medicine medicine,int amount,LocalDateTime time){
        this.medicine=medicine;
        this.sold_amount=amount;
        this.sold_at=time;
    }

    public Selling() {

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

    public int getSold_amount() {
        return sold_amount;
    }

    public void setSold_amount(int sold_amount) {
        this.sold_amount = sold_amount;
    }

    public LocalDateTime getSold_at() {
        return sold_at;
    }

    public void setSold_at(LocalDateTime sold_at) {
        this.sold_at = sold_at;
    }
}
