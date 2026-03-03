package Edu.Icet.Clothify_Store.Controller.AdminLogin.ForgetPassword;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class AdminPasswordReset {
    public AnchorPane ancherpane;
    public TextField txtUserName;
    public TextField txtEmail;
    public PasswordField txtNewPassword;
    public PasswordField txtReEnterNewPassword;

    public void BacktoMainMenu(ActionEvent actionEvent) {
        try {
            URL resource = getClass().getResource("/AdminPanel/AdminLoginPanel.fxml");
            assert resource!=null;
            Parent load = FXMLLoader.load(resource);
            ancherpane.getChildren().clear();
            ancherpane.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void StaffPanelLoginDash(ActionEvent actionEvent) {
    }
}
