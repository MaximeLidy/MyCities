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

    Connection connection;
    Statement st;
    ResultSet rs;
    public ObservableList<String> listeUser = FXCollections.observableArrayList();
    public VilleModel villeModel = new VilleModel();

    @FXML
    private Label liste;

    @FXML
    private ComboBox<String> combo;

    @FXML
    private Button changeSelect;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        buildData();

        changeSelect.setOnAction((event) -> {
            System.out.println("Button clicked");
            handle(event);
        });

    }

    public void handle(ActionEvent e) {
        System.out.println("teste1");
        String userChoice = combo.getSelectionModel().getSelectedItem();
        System.out.println(userChoice);
        // do something with that string
    }


    //combobox sql connection
    public void buildData() {
        ObservableList<String> data = FXCollections.observableArrayList();
        //connection = SqlConnection.CustomerConnection();
        connection = DBConnection.Connector();
        try {
            String SQL = "Select ville_nom From ville";
            ResultSet rs = connection.createStatement().executeQuery(SQL);
            while(rs.next()){
                data.add(rs.getString("ville_nom"));
            }
            combo.setItems(data);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Building ComboBox Data");
        }
        if (connection == null) {
            System.exit(1);
            System.out.println("Connection failed");
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
