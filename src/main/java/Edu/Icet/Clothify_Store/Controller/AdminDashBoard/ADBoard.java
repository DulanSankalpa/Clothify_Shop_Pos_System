package Edu.Icet.Clothify_Store.Controller.AdminDashBoard;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ADBoard implements Initializable {
    public AnchorPane ancherpane1;

    public void ManageUsers(ActionEvent actionEvent) {
        try {
            URL resource = getClass().getResource("/ADBoard/ManageUsers.fxml");
            assert resource !=null;
            Parent load = FXMLLoader.load(resource);
            ancherpane1.getChildren().clear();
            ancherpane1.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ManageProducts(ActionEvent actionEvent) {
        try {
            URL resource = getClass().getResource("/ADBoard/ManageProduct.fxml");
            assert resource !=null;
            Parent load = FXMLLoader.load(resource);
            ancherpane1.getChildren().clear();
            ancherpane1.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ViewRepoart(ActionEvent actionEvent) {
        try {
            URL resource = getClass().getResource("/ADBoard/ViewReport.fxml");
            assert resource !=null;
            Parent load = FXMLLoader.load(resource);
            ancherpane1.getChildren().clear();
            ancherpane1.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Suppliermanagement(ActionEvent actionEvent) {
        try {
            URL resource = getClass().getResource("/ADBoard/SupplierMangemnt.fxml");
            assert resource !=null;
            Parent load = FXMLLoader.load(resource);
            ancherpane1.getChildren().clear();
            ancherpane1.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void InventoryReports(ActionEvent actionEvent) {
        try {
            URL resource = getClass().getResource("/ADBoard/InventoryReport.fxml");
            assert resource !=null;
            Parent load = FXMLLoader.load(resource);
            ancherpane1.getChildren().clear();
            ancherpane1.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void UserStaffReports(ActionEvent actionEvent) {
        try {
            URL resource = getClass().getResource("/ADBoard/StaffReport.fxml");
            assert resource !=null;
            Parent load = FXMLLoader.load(resource);
            ancherpane1.getChildren().clear();
            ancherpane1.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void exit(ActionEvent actionEvent) {
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            URL resource = getClass().getResource("/ADBoard/ManageUsers.fxml");
            assert resource !=null;
            Parent load = FXMLLoader.load(resource);
            ancherpane1.getChildren().clear();
            ancherpane1.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
