package app.controller;

import app.model.BatimentModel;
import app.model.VilleModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.scene.image.ImageView;

public class BatimentDescriptionController implements Initializable {

    Connection connection;
    Statement st;
    ResultSet rs;
    ObservableList<String> data = FXCollections.observableArrayList();

    public ObservableList<String> listeUser = FXCollections.observableArrayList();
    public VilleModel villeModel = new VilleModel();
    public BatimentModel batimentModel = new BatimentModel();

    @FXML private ImageView imageBatiment;
    @FXML private Label nameLable;
    @FXML private Label adressLabel;
    @FXML private Label coordonneesLabel;
    @FXML private Label protectionLabel;
    @FXML private Label architectureLabel;
    @FXML private Label dateConstructionLabel;
    @FXML private Label villeLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // String SQL = "SELECT * FROM ";

    }
}
