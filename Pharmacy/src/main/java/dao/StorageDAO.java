package dao;

import model.Destination;
import model.Manufacturer;
import model.Medicine;
import model.Storage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StorageDAO {
    private final String CREATE_STORAGE = "INSERT INTO STORAGE (medicine_id, quantity_in) VALUES (?,?)";
    private final String UPDATE_STORAGE = "UPDATE STORAGE SET quantity_in = ? WHERE medicine_id = ?";
    private final String GET_ALL_STORAGE = "SELECT * FROM STORAGE JOIN MEDICINES ON STORAGE.medicine_id=MEDICINES.id"+
            " JOIN DESTINATIONS ON DESTINATIONS.id=MEDICINES.destination" +
            " JOIN MANUFACTURERS ON MANUFACTURERS.id=MEDICINES.manufacturer_id";

    public boolean createStorageTable() throws SQLException {
        try (Connection con = Connect.GetConnection();
             Statement stat = con.createStatement()) {
            return stat.execute("create table STORAGE "+
                    "(id int generated always as identity "+
                    "constraint storage_pk "+
                    "primary key, "+
                    "medicine_id int not null,"+
                    "quantity int not null, "
                    +"constraint fk_medicine "+
                    "foreign key(medicine_id) references MEDICINES(id) on update cascade );");
        }catch(NullPointerException | SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public int loadtoStorage(Storage storage){

        try (PreparedStatement stat = Connect.GetConnection().prepareStatement(CREATE_STORAGE)) {
            stat.setInt(1, storage.getMedicine().getId());
            stat.setInt(2, storage.getQuantity());
            return stat.executeUpdate();
        }
        catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
        return 0;
    }

    public Storage updateStorage(Storage storage) throws SQLException {
        try (Connection con = Connect.GetConnection();
             PreparedStatement preparedStatement = con.prepareStatement(UPDATE_STORAGE)) {
            preparedStatement.setInt(1, storage.getQuantity());
            preparedStatement.setInt(2, storage.getMedicine().getId());
            preparedStatement.executeUpdate();
            return storage;
        }
    }
    public List<Storage> all() throws SQLException {
        List<Storage> storage = new ArrayList<>();
        try (Connection connection = Connect.GetConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_STORAGE);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Medicine medicine = new Medicine();
                medicine.setDestination(new Destination(resultSet.getInt(10),resultSet.getString(11)));
                medicine.setName(resultSet.getString(4));
                medicine.setId(resultSet.getInt(3));
                medicine.setSelfprice(resultSet.getDouble(8));
                medicine.setWithout_recipe(resultSet.getBoolean(7));
                medicine.setManufacturer(new Manufacturer(resultSet.getInt(12),resultSet.getString(13),
                        resultSet.getString(14),resultSet.getString(15),resultSet.getString(16)));
                Storage onestorage = new Storage(resultSet.getInt(1),medicine,resultSet.getInt(2));
                storage.add(onestorage);
            }
        }
        return storage;
    }
}
