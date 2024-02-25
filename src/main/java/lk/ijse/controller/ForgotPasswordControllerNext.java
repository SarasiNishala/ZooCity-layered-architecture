package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ForgotPasswordControllerNext {
    @FXML
    private AnchorPane pwRoot;

    @FXML
    private PasswordField txtConfirmPw;

    @FXML
    private PasswordField txtPassword;

    @FXML
    public void btnRestPasswordOnAction(ActionEvent actionEvent) throws SQLException {
        if (txtPassword.getText().equals(txtConfirmPw.getText())){
            Connection connection = null;
            try {
                connection = DBConnection.getDbConnection().getConnection();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            String pw = txtConfirmPw.getText();

            String sql = "UPDATE Admin SET Password = ? WHERE AdminId = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, pw);
            pstm.setString(2, "admin");

            if(pstm.executeUpdate() > 0){
                new Alert(Alert.AlertType.INFORMATION, "Password Reset Successful").show();
                Stage stage = (Stage) this.pwRoot.getScene().getWindow();

                stage.close();

            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Password Reset not Successfully").show();
        }
    }

    String password = "";
}


