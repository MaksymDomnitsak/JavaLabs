package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Destination;
import model.Manufacturer;
import model.Medicine;

public class MedicineDAO {

    private final String CREATE_MEDICINES = "INSERT INTO MEDICINES (name, manufacturer_id, destination, without_recipe,selfprice) VALUES (?,?,?,?,?)";
    private final String GET_ALL_MEDICINES = "SELECT * FROM MEDICINES JOIN DESTINATIONS ON DESTINATIONS.id=MEDICINES.destination " +
            "JOIN MANUFACTURERS ON MANUFACTURERS.id=MEDICINES.manufacturer_id";

    public boolean createMedicinesTable() throws SQLException {
        try (Connection con = Connect.GetConnection();
             Statement stat = con.createStatement()) {
            return stat.execute("create table MEDICINES "+
                    "(id int generated always as identity "+
                    "constraint medicines_pk "+
                    "primary key, "+
                    "name varchar(63) not null,"+
                    "manufacturer_id int not null,"+
                    "destination int not null, " +
                    "constraint fk_destination " +
                    "foreign key(destination) references DESTINATIONS(id) on update cascade,"+
                    "without_recipe boolean not null,"+
                    "selfprice double precision not null,"
                    +"price double precision generated always as (selfprice * 1.10) stored, "
                    +"constraint fk_manufacturer "+
                    "foreign key(manufacturer_id) references MANUFACTURERS(id) on update cascade )");
        }catch(NullPointerException | SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public int loadtoMedicines(Medicine medicine){

        try (PreparedStatement stat = Connect.GetConnection().prepareStatement(CREATE_MEDICINES)) {
            stat.setString(1, medicine.getName());
            stat.setInt(2, medicine.getManufacturer().getId());
            stat.setInt(3, medicine.getDestination().getId());
            stat.setBoolean(4, medicine.isWithout_recipe());
            stat.setDouble(5, medicine.getSelfprice());
            return stat.executeUpdate();
        }
        catch(SQLException  | NullPointerException e){
            e.printStackTrace();
        }
        return 0;
    }
    public List<Medicine> all() throws SQLException {
        List<Medicine> medicines = new ArrayList<>();
        try (Connection connection = Connect.GetConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_MEDICINES);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Medicine medicine = new Medicine();
                medicine.setDestination(new Destination(resultSet.getInt(8),resultSet.getString(9)));
                medicine.setName(resultSet.getString(2));
                medicine.setId(resultSet.getInt(1));
                medicine.setSelfprice(resultSet.getDouble(6));
                medicine.setWithout_recipe(resultSet.getBoolean(5));
                medicine.setManufacturer(new Manufacturer(resultSet.getInt(10),resultSet.getString(11),
                        resultSet.getString(12),resultSet.getString(13),resultSet.getString(14)));
                medicines.add(medicine);
            }
        }
        return medicines;
    }
}
