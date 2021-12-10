package model;

public class Manufacturer {
    private int id;
    private String name;
    private String telephone_number;
    private String principal_name;
    private String principal_surname;

    public Manufacturer(String name,String princ_name,String princ_surname,String telephone){
        this.name = name;
        this.telephone_number = telephone;
        this.principal_name = princ_name;
        this.principal_surname = princ_surname;
    }
    public Manufacturer(int id,String name,String princ_name,String princ_surname,String telephone){
        this(name,princ_name,princ_surname,telephone);
        this.id=id;
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

    public String getTelephone_number() {
        return telephone_number;
    }

    public void setTelephone_number(String telephone_number) {
        this.telephone_number = telephone_number;
    }

    public String getPrincipal_name() {
        return principal_name;
    }

    public void setPrincipal_name(String principal_name) {
        this.principal_name = principal_name;
    }

    public String getPrincipal_surname() {
        return principal_surname;
    }

    public void setPrincipal_surname(String principal_surname) {
        this.principal_surname = principal_surname;
    }
}
