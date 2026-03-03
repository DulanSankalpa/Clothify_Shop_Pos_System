package Edu.Icet.Clothify_Store.Controller.StaffLogin.ForgetPassword;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class PasswordReset {
    public TextField txtUserName;
    public TextField txtEmail;
    public PasswordField txtNewPassword;
    public PasswordField txtReEnterNewPassword;
    public AnchorPane ancherpane;

    public void StaffPanelLoginDash(ActionEvent actionEvent) {
    }

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
}
