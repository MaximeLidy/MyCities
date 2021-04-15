package app.controller;

import app.DBConnection;
import app.MainApp;
import app.model.Batiment;
import app.model.VilleModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class BatimentsParVilleController implements Initializable {

    private MainApp mainApp;
    private String username;
    @FXML
    private Label liste;
    @FXML
    private ComboBox<String> combo;
    @FXML
    private Button changeSelect;

    Connection connection;
    Statement st;
    ResultSet rs;

    public ObservableList<String> listeUser = FXCollections.observableArrayList();
    public VilleModel villeModel = new VilleModel();



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

      /*isUserAdmin(isAdmin){
        String admin = isAdmin;
        if(isAdmin, == "yes"){
            afficher button;
        }
    }*/

    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TODO
        villeModel.fillComboBox();
        combo.setItems(villeModel.getVilleData());

        changeSelect.setOnAction((event) -> {
            System.out.println("Button clicked");
            handle(event);
        });
    }

    public void handle(ActionEvent e) {
        String userChoice = combo.getSelectionModel().getSelectedItem();
        System.out.println(userChoice);
        // do something with that string
    }

    public boolean isDbConnected() {
        try {
            return connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //public void userName(String username) {}

    // public void SelectVille(Object ville){
    //     String t = combo.getSelectionModel().getSelectedItem();
    //     System.out.println(t + " / " + "teste" + ville);

    // }

    // public void SelectVille(ActionEvent event){
    //   String s = combo.getSelectionModel().getSelectedItem().toString();
    //   liste.setText(s);
    // }

    // @Override
    // public void initialize(URL location, ResourceBundle resources) {
    //     ObservableList<String> list = FXCollections.observableArrayList("javaFX", "ReactJS", "Laravel");
    //     combo.setItems(list);
    // }
}
