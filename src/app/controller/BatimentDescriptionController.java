package app.controller;

import app.MainApp;
import app.model.Batiment;
import app.model.BatimentModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class BatimentDescriptionController {
    public BatimentModel batimentModel = new BatimentModel();
    @FXML
    ImageView imageBatiment;
    @FXML
    private Label villeLabel;
    @FXML
    private Label MonumentLabel;
    @FXML
    private Label adresseLabel;
    @FXML
    private Label coordonneesLabel;
    @FXML
    private Label protectionLabel;
    @FXML
    private Label architectureLabel;
    @FXML
    private Label dateConstructionLabel;
    /*@FXML
    private Label imageLabel;*/

    private Stage stage;
    private Batiment batiment;


    private MainApp mainApp;

    @FXML
    private void initialize() {
    }

    public void setMainApp(MainApp mainApp) {this.mainApp = mainApp; }

    /**
     * Sets the stage of this dialog.
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Sets the batiment to be edited in the dialog.
     *
     * @param batiment
     */
    public Batiment getBatiment() {


       // this.batiment = batiment;

        villeLabel.setText(batiment.getNom());
        adresseLabel.setText(batiment.getAdresse());
        coordonneesLabel.setText(batiment.getCoordonnees());
        protectionLabel.setText(batiment.getProtection());
        architectureLabel.setText(batiment.getArchitecture());
        dateConstructionLabel.setText(Integer.toString(batiment.getDateConstruction()));
        //imageLabel.setText(batiment.getImage());
        return batiment;
    }




}
