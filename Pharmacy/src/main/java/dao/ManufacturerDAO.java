package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Manufacturer;

public class ManufacturerDAO {

    private final String CREATE_MANUFACT = "insert into MANUFACTURERS (name, principal_name, principal_surname, phone) VALUES (?,?,?,?)";
    private final String DELETE_MANUFACT = "delete from MANUFACTURERS where id=?";
    private final String GET_ALL_MANUFACTS = "SELECT * FROM MANUFACTURERS";

    public boolean createManufacturersTable() throws SQLException {
        try (Connection con = Connect.GetConnection();
             Statement stat = con.createStatement()) {
            return stat.execute("create table MANUFACTURERS (" +
                    "id int generated always as identity " +
                    "constraint manufacturers_pk " +
                    "primary key, " +
                    "name varchar(63) not null, " +
                    "principal_name varchar(63) not null, " +
                    "principal_surname varchar(63) not null, " +
                    "phone varchar(15) not null" +
                    ");");
        }catch(NullPointerException | SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public int LoadtoDatabase(Manufacturer manufact) throws SQLException,NullPointerException{
        try(Connection conn = Connect.GetConnection();
            PreparedStatement stat = conn.prepareStatement(CREATE_MANUFACT)) {
            stat.setString(1,manufact.getName());
            stat.setString(2,manufact.getPrincipal_name());
            stat.setString(3,manufact.getPrincipal_surname());
            stat.setString(4,manufact.getTelephone_number());
            return stat.executeUpdate();
        }
        catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
        return 0;
    }
    public List<Manufacturer> readAll() throws SQLException {
        List<Manufacturer> manufacts = new ArrayList<>();
        try (Connection connection = Connect.GetConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_MANUFACTS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Manufacturer manufact = new Manufacturer(resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),resultSet.getString(5));
                manufacts.add(manufact);
            }
        }
        return manufacts;
    }

    public boolean deleteManufacturer(int id){
        try(Connection conn = Connect.GetConnection();
            PreparedStatement stat = conn.prepareStatement(DELETE_MANUFACT)) {
            stat.setInt(1,id);
            return stat.executeUpdate() != 0;
        }
        catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
        return false;

    }
}
