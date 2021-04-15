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
            String SQL = "SELECT ville_nom FROM ville";
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
        String SQL = "SELECT ville_id FROM ville WHERE ville_nom = ?";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, nom);
            resultSet = preparedStatement.executeQuery();
            return resultSet.getInt("ville_id");
        } catch (Exception e) {
            e.printStackTrace();
        }return 0;
    }
}

