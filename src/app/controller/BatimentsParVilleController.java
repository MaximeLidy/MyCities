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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;

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
    private TableColumn<Batiment, String> image;
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
    private TextField name;
    @FXML
    private TextField adresse1;
    @FXML
    private TextField coordonnees1;
    @FXML
    private TextField protection1;
    @FXML
    private TextField architecture1;
    @FXML
    private TextField dateConstruction1;
    @FXML
    private ImageView photo;

    int index = -1;

    @FXML
    private void NewBatiment() {
        Batiment tempBatiment = new Batiment();
        boolean okClicked = mainApp.showBatimentEditWindow(tempBatiment);
        if (okClicked) {
            // BatimentModel.insertBatiment(id,nom,adresse);
            // BatimentModel.getVehiculeData().add(tempBatiment);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        villeModel.fillComboBox();
        combo.setItems(villeModel.getVilleData());
        table.setEditable(true);
    }

    @FXML
    private void LoadingBatiment() throws SQLException {
        String ville = combo.getSelectionModel().getSelectedItem();
        if (ville == null) {
            message.setText("Vous devez choisir une ville :)");
        } else {
            try {
                System.out.println(ville);
                batimentModel.fillTableView(ville);
                id.setCellValueFactory(new PropertyValueFactory<Batiment, Integer>("id"));
                nom.setCellValueFactory(new PropertyValueFactory<Batiment, String>("nom"));
                image.setCellValueFactory(new PropertyValueFactory<Batiment, String>("image"));
                adresse.setCellValueFactory(new PropertyValueFactory<Batiment, String>("adresse"));
                coordonnees.setCellValueFactory(new PropertyValueFactory<Batiment, String>("coordonnees"));
                protection.setCellValueFactory(new PropertyValueFactory<Batiment, String>("protection"));
                architecture.setCellValueFactory(new PropertyValueFactory<Batiment, String>("architecture"));
                dateConstruction.setCellValueFactory(new PropertyValueFactory<Batiment, Integer>("dateConstruction"));
                table.setItems(batimentModel.getDataBatiment());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML private void imageload(ActionEvent event) {

        System.out.println("mon image");
        //Image pix = new Image("resources/images/CathédraleNotre-Dame.jpg");
        Image pix = new Image("/resources/images/monument.jpg");
        System.out.println("mon image 2 " + pix);
        photo.setImage(pix);
    }

    @FXML
    void getSelectd(MouseEvent event) {
        index = table.getSelectionModel().getFocusedIndex();

        if (index <= -1) {
            return;
        }
        // String tesst = nom.getCellData(index).toString();
        // batimentModel.descriptionView(tesst);
        // String imageSelect = image.getCellData(index).toString();
        // System.out.println("mon image 2 " + imageSelect);

        // Image pix = new Image("/resources/images/" + imageSelect);
        // photo.setImage(pix);
        

        name.setText(nom.getCellData(index).toString());
        adresse1.setText(adresse.getCellData(index).toString());
        coordonnees1.setText(coordonnees.getCellData(index).toString());
        protection1.setText(protection.getCellData(index).toString());
        architecture1.setText(architecture.getCellData(index).toString());
        dateConstruction1.setText(dateConstruction.getCellData(index).toString());
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
