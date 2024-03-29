package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public TextField txtPassword;
    public TextField txtUsername;
    public AnchorPane loginRoot;

    public void btnOnActionLogin(ActionEvent actionEvent) throws IOException {

        String userName = txtUsername.getText();
        String pw = txtPassword.getText();
        if(userName.equals("Nishala") && pw.equals("@1234") ){
            navigateToDashboardWindow();
       }
        else{
           new Alert(Alert.AlertType.ERROR,"Somthing Wrong").show();
       }
    }

    private void navigateToDashboardWindow() throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/DashBoard.fxml"));
        Scene scene = new Scene(rootNode);

        loginRoot.getChildren().clear();
        Stage primaryStage = (Stage) loginRoot.getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();

    }

    public void forgotOnAction(MouseEvent mouseEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/ForgotPassword.fxml"));
        Scene scene = new Scene(rootNode);

        Stage stage = new Stage();

        stage.setScene(scene);
        stage.centerOnScreen();

        stage.show();
    }
}
