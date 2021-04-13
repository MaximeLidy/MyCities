package app.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ListeVilleController implements Initializable {
    @FXML
    ComboBox<String> combo;
    ObservableList<String> list = FXCollections.observableArrayList("Strasbourg","Mulhouse","Charleville");
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        combo.setItems(list);
    }
}
