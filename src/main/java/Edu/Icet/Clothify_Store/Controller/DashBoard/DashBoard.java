package Edu.Icet.Clothify_Store.Controller.DashBoard;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class DashBoard {
    public AnchorPane ancherpane;

    public void StaffPanelLogin(ActionEvent actionEvent) {
        try {
            URL resource = getClass().getResource("/StaffPanel/StaffLoginPanel.fxml");
            assert resource!=null;
            Parent load = FXMLLoader.load(resource);
            ancherpane.getChildren().clear();
            ancherpane.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void AdminPanelLogin(ActionEvent actionEvent) {
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
}
