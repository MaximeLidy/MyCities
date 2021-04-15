package app.controller;

import app.DBConnection;
import app.MainApp;
import app.model.Batiment;
import app.model.BatimentModel;
import app.model.VilleModel;
import app.util.DateUtil;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class BatimentWindowController {

    public BatimentModel batimentModel = new BatimentModel();
    public VilleModel villeModel = new VilleModel();
    Connection connection;
    @FXML
    private TextField nomField;
    @FXML
    private TextField adresseField;
    @FXML
    private TextField coordonneesField;
    @FXML
    private TextField protectionField;
    @FXML
    private TextArea architectureField;
    @FXML
    private TextField dateConstructionField;
    @FXML
    private Button imageField;
    @FXML
    private ComboBox<String> villeField;
    private String image;

    private Stage stage;
    private Batiment batiment;
    private boolean okClicked = false;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {this.mainApp = mainApp; }

    @FXML
    private void initialize() {
        villeModel.fillComboBox();
        villeField.setItems(villeModel.getVilleData());
    }

    //Sets the stage of this window
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    //Sets the batiment to be edited in the window
    public void setBatiment(Batiment batiment) {
        this.batiment = batiment;

        nomField.setText(batiment.getNom());
        adresseField.setText(batiment.getAdresse());
        coordonneesField.setText(batiment.getCoordonnees());
        protectionField.setText(batiment.getProtection());
        architectureField.setText(batiment.getArchitecture());
        dateConstructionField.setText(Integer.toString(batiment.getDateConstruction()));
        //imageField.setText(batiment.getImage());
        //villeField.setSelectionModel().getVille();
    }

    //Returns true if the user clicked OK, false otherwise.
    public boolean isOkClicked() {
        return okClicked;
    }

    public void setImageField(ActionEvent event){
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if(selectedFile != null){
            image = selectedFile.getName();
        }
    }

    //Called when the user clicks ok
    @FXML
    private void Ok(){
        if (isInputValid()) {
            batiment.setNom(nomField.getText());
            batiment.setAdresse(adresseField.getText());
            batiment.setCoordonnees(coordonneesField.getText());
            batiment.setProtection(protectionField.getText());
            batiment.setArchitecture(architectureField.getText());
            batiment.setDateConstruction(Integer.parseInt(dateConstructionField.getText()));
            batiment.setImage(imageField.getText());
            batiment.setVille(villeField.getSelectionModel().getSelectedItem());
            batimentModel.insertBatiment(batiment.getNom(),batiment.getAdresse(),batiment.getCoordonnees(), batiment.getProtection(),
                    batiment.getArchitecture(), batiment.getDateConstruction(), image, batiment.getVille());
            okClicked = true;
            stage.close();
        }
    }

    //Called when the user clicks cancel
    @FXML
    private void Cancel() {
        stage.close();
    }

    //Validates the user input in the text fields
    private boolean isInputValid() {
        String errorMessage = "";

        if (nomField.getText() == null || nomField.getText().length() == 0) {
            errorMessage += "Adresse invalide.\n";
        }
        if (adresseField.getText() == null || adresseField.getText().length() == 0) {
            errorMessage += "Adresse invalide.\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(stage);
            alert.setTitle("Champs invalides");
            alert.setHeaderText("Corrigez les champs invalides");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }


}
