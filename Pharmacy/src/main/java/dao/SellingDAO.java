package dao;

import model.Destination;
import model.Manufacturer;
import model.Medicine;
import model.Selling;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SellingDAO {
    private final String CREATE_SELLING = "INSERT INTO SELLING (medicine_id, sold_amount, sold_at) VALUES (?,?,?)";
    private final String GET_ALL_SELLINGS = "SELECT * FROM SELLING JOIN MEDICINES ON SELLING.medicine_id=MEDICINES.id" +
            " JOIN DESTINATIONS ON DESTINATIONS.id=MEDICINES.destination" +
            " JOIN MANUFACTURERS ON MANUFACTURERS.id=MEDICINES.manufacturer_id";
    public boolean createSellingTable() throws SQLException {
        try (Connection con = Connect.GetConnection();
             Statement stat = con.createStatement()) {
            return stat.execute("create table SELLING "+
                    "(id int generated always as identity "+
                    "constraint selling_pk "+
                    "primary key, "+
                    "medicine_id int not null,"+
                    "sold_amount int not null, "+
                    "sold_at timestamp not null, "
                    +"constraint fk_medicineid "+
                    "foreign key(medicine_id) references MEDICINES(id) on update cascade );");
        }catch(NullPointerException | SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public int loadtoSelling(Selling selling){
        try (PreparedStatement stat = Connect.GetConnection().prepareStatement(CREATE_SELLING)) {
            stat.setInt(1, selling.getMedicine().getId());
            stat.setInt(2, selling.getSold_amount());
            stat.setTimestamp(3, Timestamp.valueOf(selling.getSold_at()));
            return stat.executeUpdate();
        }
        catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
        return 0;
    }
    public List<Selling> readAll() throws SQLException {
        List<Selling> sellings = new ArrayList<>();
        try (Connection connection = Connect.GetConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SELLINGS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Selling selling = new Selling();
                selling.setId(resultSet.getInt(1));
                selling.setSold_amount(resultSet.getInt(3));
                selling.setSold_at(resultSet.getTimestamp(4).toLocalDateTime());
                Medicine medicine = new Medicine();
                medicine.setDestination(new Destination(resultSet.getInt(12),resultSet.getString(13)));
                medicine.setName(resultSet.getString(6));
                medicine.setId(resultSet.getInt(5));
                medicine.setSelfprice(resultSet.getDouble(10));
                medicine.setWithout_recipe(resultSet.getBoolean(9));
                medicine.setManufacturer(new Manufacturer(resultSet.getInt(14),resultSet.getString(15),
                        resultSet.getString(16),resultSet.getString(17),resultSet.getString(18)));
                selling.setMedicine(medicine);
                sellings.add(selling);

            }
        }
        return sellings;
    }
}
