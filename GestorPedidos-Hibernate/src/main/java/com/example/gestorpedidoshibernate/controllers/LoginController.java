package com.example.gestorpedidoshibernate.controllers;

import com.example.gestorpedidoshibernate.App;
import com.example.gestorpedidoshibernate.Session;
import com.example.gestorpedidoshibernate.domain.item.Item;
import com.example.gestorpedidoshibernate.domain.item.ItemDAO;
import com.example.gestorpedidoshibernate.domain.item.ItemDAOImplement;
import com.example.gestorpedidoshibernate.domain.user.User;
import com.example.gestorpedidoshibernate.domain.user.UserDAO;
import com.example.gestorpedidoshibernate.domain.user.UserDAOImplement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.List;

public class LoginController {

    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPssword;
    @FXML
    private Label lblInfo;
    @FXML
    private Button btnLogin1;
    @FXML
    private Button btnCancel;

    @FXML
    public void onLogin(ActionEvent actionEvent) {

        String user = txtUser.getText();
        String pass = txtPssword.getText();

        if( user.length()<4 || pass.length()<4 ){
            lblInfo.setText("Enter User&Password");
            lblInfo.setStyle("-fx-text-fill: #b957ac;");

        } else{

            /* ACCESO A BASE DE DATOS PARA LA VALIDACION */
            User u = (new UserDAOImplement()).validateUser( user, pass );

            if(u==null){
                lblInfo.setText("User or Password Unknown");
                lblInfo.setStyle(" -fx-text-fill: #b957ac;");
            }else {
                lblInfo.setText("User " + user + "(" + pass + ") Login Successfull!!!!!");
                lblInfo.setStyle("-fx-text-fill: #b957ac;");

                Session.setCurrentUser(u);


                /* Guardar usuario en sesión e ir a la proxima ventana */


                try {
                    App.changeScene("main-screen.fxml","Order View");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }



    }

    @FXML
    public void cancel(ActionEvent actionEvent) {
        txtPssword.setText("");
        txtUser.setText("");

    }
}






