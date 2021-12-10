package dao;

import model.Destination;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DestinationDAO {
    private final String CREATE_DESTINATIONS = "INSERT INTO DESTINATIONS (destination) VALUES (?)";
    private final String READ_DESTINATIONS = "SELECT * FROM DESTINATIONS";

    public boolean createDestinationsTable() throws SQLException {
        try (Connection con = Connect.GetConnection();
             Statement stat = con.createStatement()) {
            return stat.execute("create table DESTINATIONS "+
                    "(id int generated always as identity "+
                    "constraint destinations_pk "+
                    "primary key, "+
                    "destination varchar(63) not null)");
        }catch(NullPointerException | SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public int loadtoDestinations(Destination destination){

        try (PreparedStatement stat = Connect.GetConnection().prepareStatement(CREATE_DESTINATIONS)) {
            stat.setString(1, destination.getDestination());
            return stat.executeUpdate();
        }
        catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
        return 0;
    }

    public List<Destination> readAll() throws SQLException {
        List<Destination> dests = new ArrayList<>();
        try (Connection connection = Connect.GetConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_DESTINATIONS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Destination onedestination = new Destination(resultSet.getInt(1),resultSet.getString(2));
                dests.add(onedestination);
            }
        }
        return dests;
    }
}
