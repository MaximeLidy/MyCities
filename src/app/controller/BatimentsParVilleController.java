package app.controller;

import app.MainApp;
import app.model.Batiment;
import app.model.BatimentModel;
import javafx.fxml.FXML;

public class BatimentsParVilleController {

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {this.mainApp = mainApp; }

    @FXML
    private void NewBatiment() {
        Batiment tempBatiment = new Batiment();
        boolean okClicked = mainApp.showBatimentEditWindow(tempBatiment);
        if (okClicked) {
            //BatimentModel.insertBatiment(id,nom,adresse);
            //BatimentModel.getVehiculeData().add(tempBatiment);
        }
    }
}
