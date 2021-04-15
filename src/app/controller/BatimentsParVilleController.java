package app.controller;

import app.DBConnection;
import app.MainApp;
import app.model.Batiment;
import app.model.BatimentModel;
import app.model.VilleModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class BatimentsParVilleController implements Initializable {

    Connection connection;
    Statement st;
    ResultSet rs;
    ObservableList<String> data = FXCollections.observableArrayList();

    private MainApp mainApp;
    private String username;
    public ObservableList<String> listeUser = FXCollections.observableArrayList();
    public VilleModel villeModel = new VilleModel();
    public BatimentModel batimentModel = new BatimentModel();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private Label message;

    @FXML
    private Label liste;
    @FXML
    private ComboBox<String> combo;
    @FXML
    private Button changeSelect;
    @FXML
    private TableView<Batiment> table;

    @FXML
    private TableColumn<Batiment, Integer> id;
    @FXML
    private TableColumn<Batiment, String> nom;
    @FXML
    private TableColumn<Batiment, String> adresse;
    @FXML
    private TableColumn<Batiment, String> coordonnees;
    @FXML
    private TableColumn<Batiment, String> protection;
    @FXML
    private TableColumn<Batiment, String> architecture;
    @FXML
    private TableColumn<Batiment, Integer> dateConstruction;
    @FXML
    private TableColumn<Batiment, String> image;

    private Batiment selectedBatiment;

    @FXML
    private void selectBatiment(MouseEvent event){
        selectedBatiment = table.getSelectionModel().getSelectedItem();
        System.out.println(selectedBatiment);
    }

    @FXML
    private void NewBatiment() {
        Batiment tempBatiment = new Batiment();
        String type = "new";
        boolean okClicked = mainApp.showBatimentEditWindow(tempBatiment, type);
        if (okClicked) {
            // BatimentModel.insertBatiment(id,nom,adresse);
            // BatimentModel.getVehiculeData().add(tempBatiment);

        }
    }

    @FXML
    private void EditBatiment() {
        String type = "edit";
        if (selectedBatiment != null) {
            boolean okClicked = mainApp.showBatimentEditWindow(selectedBatiment, type);
            if (okClicked) {
                showBatimentDetails(selectedBatiment);
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }

    private void showBatimentDetails(Batiment batiment) {
        if (batiment != null) {
            // Fill the labels with info from the person object.
            nom.setText(batiment.getNom());
            adresse.setText(batiment.getAdresse());
            coordonnees.setText(batiment.getCoordonnees());
            protection.setText(batiment.getProtection());
            architecture.setText(batiment.getArchitecture());
            dateConstruction.setText(Integer.toString(batiment.getDateConstruction()));
            /*etatLabel.setText(batiment.getEtat());
            kmLabel.setText(Integer.toString(batiment.getKm()));*/
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        villeModel.fillComboBox();
        combo.setItems(villeModel.getVilleData());
    }

    @FXML
    private void LoadingBatiment() throws SQLException {
        String ville = combo.getSelectionModel().getSelectedItem();
        if(ville == null) {
            message.setText("Vous devez choisir une ville :)");
        }else {

            try {
                batimentModel.fillTableView(ville);
                id.setCellValueFactory(new PropertyValueFactory<Batiment, Integer>("id"));
                nom.setCellValueFactory(new PropertyValueFactory<Batiment, String>("nom"));
                adresse.setCellValueFactory(new PropertyValueFactory<Batiment, String>("adresse"));
                coordonnees.setCellValueFactory(new PropertyValueFactory<Batiment, String>("coordonnees"));
                protection.setCellValueFactory(new PropertyValueFactory<Batiment, String>("protection"));
                architecture.setCellValueFactory(new PropertyValueFactory<Batiment, String>("architecture"));
                dateConstruction.setCellValueFactory(new PropertyValueFactory<Batiment, Integer>("dateConstruction"));
                image.setCellValueFactory(new PropertyValueFactory<Batiment, String>("image"));
                table.setItems(batimentModel.getDataBatiment());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isDbConnected() {
        try {
            return connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
