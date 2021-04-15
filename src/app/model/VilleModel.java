package app.model;

import app.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
public class VilleModel {
    Connection connection;

    private ObservableList<String> villeData = FXCollections.observableArrayList();
    public ObservableList<String> getVilleData() { return villeData; }

    public VilleModel() {
        connection = DBConnection.Connector();
        if (connection == null) {
            System.out.println("connection not successful");
            System.exit(1);
        }
    }

    public void fillComboBox() {
        ObservableList<String> data = FXCollections.observableArrayList();
        //connection = SqlConnection.CustomerConnection();
        connection = DBConnection.Connector();
        try {
            String SQL = "Select ville_nom From ville";
            ResultSet rs = connection.createStatement().executeQuery(SQL);
            while(rs.next()){
                villeData.add(rs.getString("ville_nom"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Building ComboBox Data");
        }
        if (connection == null) {
            System.exit(1);
            System.out.println("Connection failed");
        }
    }

    public int getIdFromNom(String nom) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String SQL = "Select ville_id From ville WHERE ville_nom = ?";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, nom);
            resultSet = preparedStatement.executeQuery();
            int villeId = resultSet.getInt("ville_id");
            return villeId;
        } catch (Exception e) {
            e.printStackTrace();
        }return 0;
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

