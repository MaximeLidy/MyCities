package app.model;

import app.DBConnection;

import java.sql.*;
public class VilleModel {
    Connection connection;

    public VilleModel() {
        connection = DBConnection.Connector();
        if (connection == null) {
            System.out.println("connection not successful");
            System.exit(1);
        }
    }

    public boolean isVille(String ville) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select * from ville where ville_id = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ville);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
    }
}