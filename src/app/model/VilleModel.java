package app.model;

import app.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
public class VilleModel {

    Connection connection;

    private ObservableList<String> villeData = FXCollections.observableArrayList();
    public ObservableList<String> getVilleData() { return villeData; }

    public VilleModel() {}

    public void fillComboBox() {
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
}

