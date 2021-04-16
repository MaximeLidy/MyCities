package app.model;

import app.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class BatimentModel {
    Connection connection;

    private ObservableList<Batiment> dataBatiment = FXCollections.observableArrayList();

    public ObservableList<Batiment> getDataBatiment() {
        return dataBatiment;
    }

    public BatimentModel() {
        connection = DBConnection.Connector();
    }

    public void insertBatiment(String nom, String adresse, String coordonnees, String protection, String architecture,
            int date, String image, String ville) {
        String sql = "INSERT INTO BATIMENT (batiment_nom, adresse, coordonnees, protection, architecture, dateConstruction, image, ville) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nom);
            ps.setString(2, adresse);
            ps.setString(3, coordonnees);
            ps.setString(4, protection);
            ps.setString(5, architecture);
            ps.setInt(6, date);
            ps.setString(7, image);
            ps.setString(8, ville);
            ps.executeUpdate();

            System.out.println("A new batiment was inserted successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());

            System.out.println("A new batiment was insertion failed!");
        }
    };


    public void updateBatiment(String nom, String adresse, String coordonnees, String protection, String architecture,
                               int date, String image, String ville, int id) {
        System.out.println(id);
        String sql = "UPDATE BATIMENT SET batiment_nom = ?, adresse = ?, coordonnees = ?, protection = ?, architecture = ?, dateConstruction = ?, image = ?, ville = ? WHERE batiment_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, nom);
            ps.setString(2, adresse);
            ps.setString(3, coordonnees);
            ps.setString(4, protection);
            ps.setString(5, architecture);
            ps.setInt(6, date);
            ps.setString(7, image);
            ps.setString(8, ville);
            ps.setInt(9, id);
            ps.executeUpdate();
            System.out.println("A new batiment was edited successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());

            System.out.println("A new batiment was edited failed!");

        }
    }

    public void fillTableView(String ville) throws SQLException{
        String SQL = "Select batiment_id, batiment_nom, adresse, coordonnees, protection, architecture, dateConstruction," + 
                            " image From batiment, ville WHERE ville_nom = ? AND batiment.ville = ville.ville_nom";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        dataBatiment.removeAll(dataBatiment);
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, ville);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                dataBatiment.add(new Batiment(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7),
                        resultSet.getString(8)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error Building ComboBox Data");
        }
        
    }

    public void descriptionView(String tesst) {
        System.out.println("model up");
        
    }

}
