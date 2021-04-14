package app.controller;

import app.model.VilleModel;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import app.MainApp;

import app.DBConnection;
import java.sql.*;
import java.sql.SQLException;


public class ListeVilleController implements Initializable{

    Connection connection;
    Statement st;
    ResultSet rs;
    public ObservableList<String> listeUser = FXCollections.observableArrayList();
    public VilleModel villeModel = new VilleModel(); 

    public void setMainApp(MainApp mainApp) {}

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
            String SQL = "Select user_nom From user";
            ResultSet rs = connection.createStatement().executeQuery(SQL);
            while(rs.next()){
                data.add(rs.getString("user_nom"));
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