package Edu.Icet.Clothify_Store.Controller.AdminLogin;

import Edu.Icet.Clothify_Store.DB.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminLoginPanel {
    public TextField txtUseName;
    public PasswordField txtPassword;
    public AnchorPane ancgerpane;

    public void AdminPanelLoginDashBoard(ActionEvent actionEvent) {
        String email = txtUseName.getText();
        String pw = txtPassword.getText();

        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM admin WHERE email=? AND pw=?");

            preparedStatement.setString(1,email);
            preparedStatement.setString(2,pw);

            ResultSet resultSet = preparedStatement.executeQuery();


            if (resultSet.next()){
                try {
                    URL resource = getClass().getResource("/ADBoard/AdminDashBoard.fxml");
                    assert resource!=null;
                    Parent load = FXMLLoader.load(resource);
                    ancgerpane.getChildren().clear();
                    ancgerpane.getChildren().add(load);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }else {
                Alert Alert = new Alert(javafx.scene.control.Alert.AlertType.ERROR);
                Alert.setTitle("Login Error");
                Alert.setHeaderText(null);
                Alert.setContentText("Email or Password Incorrect!");
                Alert.showAndWait();
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void BackToMainMenu(ActionEvent actionEvent) {
        try {
            URL resource = getClass().getResource("/DashBoard/dashboardPanel.fxml");
            assert resource !=null;
            Parent load = FXMLLoader.load(resource);
            ancgerpane.getChildren().clear();
            ancgerpane.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ForgetPassword(ActionEvent actionEvent) {
        try {
            URL resource = getClass().getResource("/AdminPanel/ForgetPassword/AdminPasswordReset.fxml");
            assert resource!=null;
            Parent load = FXMLLoader.load(resource);
            ancgerpane.getChildren().clear();
            ancgerpane.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
