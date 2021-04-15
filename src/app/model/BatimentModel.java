package app.model;
import app.DBConnection;
import java.sql.*;

public class BatimentModel {
    Connection connection;

    public BatimentModel(){
        connection = DBConnection.Connector();
    }

    public void insertBatiment(String nom, String adresse, String coordonnees, String protection, String architecture, int date, int ville) {

        String sql = "INSERT INTO BATIMENT (batiment_nom, adresse, coordonnees, protection, architecture, dateConstruction, image, ville_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, nom);
            ps.setString(2, adresse);
            ps.setString(3, coordonnees);
            ps.setString(4, protection);
            ps.setString(5, architecture);
            ps.setInt(6, date);
            ps.setString(7, "image");
            ps.setInt(8, ville);
            ps.executeUpdate();

            System.out.println("A new batiment was inserted successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());

            System.out.println("A new batiment was insertion failed!");
        }
    };

}
