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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class BatimentsParVilleController implements Initializable {

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void NewBatiment() {
        Batiment tempBatiment = new Batiment();
        boolean okClicked = mainApp.showBatimentEditWindow(tempBatiment);
        if (okClicked) {
            // BatimentModel.insertBatiment(id,nom,adresse);
            // BatimentModel.getVehiculeData().add(tempBatiment);
        }
    }

    Connection connection;
    Statement st;
    ResultSet rs;
    public ObservableList<VilleModel> dataMonument = FXCollections.observableArrayList();
    public VilleModel villeModel = new VilleModel();
    ObservableList<String> data = FXCollections.observableArrayList();

    @FXML
    private Label message;

    @FXML
    private Label liste;
    @FXML
    private ComboBox<String> combo;
    @FXML
    private Button changeSelect;
    @FXML
    private TableView<VilleModel> table;

    @FXML private TableColumn<VilleModel, Integer> batiment_id;
    @FXML private TableColumn<VilleModel, String> batiment_nom;
    @FXML private TableColumn<VilleModel, String> adresse;
    @FXML private TableColumn<VilleModel, String> coordonnees;
    @FXML private TableColumn<VilleModel, String> protection;
    @FXML private TableColumn<VilleModel, String> architecture;
    @FXML private TableColumn<VilleModel, Integer> dateConstruction;
    @FXML private TableColumn<VilleModel, String> image;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buildData();
    }

    @FXML
    void handle() throws SQLException {
        
        String userChoice = combo.getSelectionModel().getSelectedItem();
        System.out.println(userChoice);
        PreparedStatement preparedStatement = null;
        System.out.println(userChoice);
        ResultSet resultSet = null;
        String SQL = "Select * From batiment, ville WHERE ville_nom = ? AND batiment.ville_id = ville.ville_id";
        

        if(userChoice == null) {
            message.setText("Vous devez choisir une ville :)) ");
        }else {
            System.out.println("enter hahahahah");
            dataMonument.removeAll(dataMonument);
            try {
                preparedStatement = connection.prepareStatement(SQL);
                preparedStatement.setString(1, userChoice);
                resultSet = preparedStatement.executeQuery();
    
                while (resultSet.next()) {
                    dataMonument.add(new VilleModel(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7),resultSet.getString(8) ));
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error Building ComboBox Data");
            }
            batiment_id.setCellValueFactory(new PropertyValueFactory<VilleModel, Integer>("batiment_id"));
            batiment_nom.setCellValueFactory(new PropertyValueFactory<VilleModel, String>("batiment_nom"));
            adresse.setCellValueFactory(new PropertyValueFactory<VilleModel, String>("adresse"));
            coordonnees.setCellValueFactory(new PropertyValueFactory<VilleModel, String>("coordonnees"));
            protection.setCellValueFactory(new PropertyValueFactory<VilleModel, String>("protection"));
            architecture.setCellValueFactory(new PropertyValueFactory<VilleModel, String>("architecture"));
            dateConstruction.setCellValueFactory(new PropertyValueFactory<VilleModel, Integer>("dateConstruction"));
            image.setCellValueFactory(new PropertyValueFactory<VilleModel, String>("image"));
            table.setItems(dataMonument);
            isDbConnected();
        }
    }

    // combobox sql connection
    public void buildData() {
        // connection = SqlConnection.CustomerConnection();
        connection = DBConnection.Connector();
        try {
            String SQL = "Select * From ville";
            ResultSet rs = connection.createStatement().executeQuery(SQL);

            while (rs.next()) {
                data.add(rs.getString("ville_nom"));
            }
             combo.setItems(data);

        } catch (Exception e) {
            System.out.println("je suis la ");
            e.printStackTrace();
            System.out.println("Error Building ComboBox Data");
        }
        isDbConnected();
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
    // String t = combo.getSelectionModel().getSelectedItem();
    // System.out.println(t + " / " + "teste" + ville);

    // }

    // public void SelectVille(ActionEvent event){
    // String s = combo.getSelectionModel().getSelectedItem().toString();
    // liste.setText(s);
    // }

    // @Override
    // public void initialize(URL location, ResourceBundle resources) {
    // ObservableList<String> list = FXCollections.observableArrayList("javaFX",
    // "ReactJS", "Laravel");
    // combo.setItems(list);
    // }
}
