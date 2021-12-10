package dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import dao.*;

public class Connect {
    public static Connection GetConnection() throws SQLException {
        try {
            String Url = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=1111";
            Connection conn = DriverManager.getConnection(Url);
            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Connected to: " + dm.getDatabaseProductName());
            }
            return conn;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void CloseConnection(Connection conn){
        try {
            System.out.println("Closed connection to: " + conn.getMetaData().getDatabaseProductName());
            conn.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        //return;
    }

}
