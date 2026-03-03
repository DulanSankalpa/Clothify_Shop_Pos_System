package Edu.Icet.Clothify_Store.Controller.StaffLogin;

import Edu.Icet.Clothify_Store.DB.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.*;

public class StaffLoginPanel {

    public AnchorPane ancherpane;
    public PasswordField txtPassword;
    public TextField txtUseName;
    public TextField emailfild;
    public PasswordField pwfilds;


    public void BacktoMainMenu(ActionEvent actionEvent) {
        try {
            URL resource = getClass().getResource("/DashBoard/dashboardPanel.fxml");
            assert resource !=null;
            Parent load = FXMLLoader.load(resource);
            ancherpane.getChildren().clear();
            ancherpane.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void StaffPanelLoginDash(ActionEvent actionEvent) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM staff WHERE email = ? AND pw = ?");

            preparedStatement.setString(1,emailfild.getText());
            preparedStatement.setString(2,pwfilds.getText());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {


                try {
                    URL resource = getClass().getResource("/SFDashBoard/SFDashBoard.fxml");
                    Parent load = load = FXMLLoader.load(resource);
                    ancherpane.getChildren().clear();
                    ancherpane.getChildren().add(load);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText(null);
                alert.setContentText("Email or Password Incorrect!");
                alert.showAndWait();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void ForgetPassword(ActionEvent actionEvent) {

        try {
            URL resource = getClass().getResource("/StaffPanel/ForgetPassword/PasswordReset.fxml");
            assert resource!=null;
            Parent load = FXMLLoader.load(resource);
            ancherpane.getChildren().clear();
            ancherpane.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
