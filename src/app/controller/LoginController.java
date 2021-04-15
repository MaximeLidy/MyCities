package app.controller;

import app.MainApp;
import app.model.LoginModel;
import app.model.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class LoginController {
    public LoginModel loginModel = new LoginModel();

    private MainApp mainApp;

    @FXML
    private Label isConnected;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;
    public LoginController(){}

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void Login(ActionEvent event){
        try{
            if(loginModel.isLogin(txtUsername.getText(),txtPassword.getText())){
                mainApp.startListeVille();
                //UserSession.getInstance(txtUsername.getText());
                //System.out.println(UserSession.getInstance(txtUsername.getText()).getUserName());
            }else{
                isConnected.setText("Wrong login");
            }
        }catch (SQLException e){
            isConnected.setText("Wrong login");
            e.printStackTrace();
        }

    }

}
