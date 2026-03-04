package Edu.Icet.Clothify_Store.Controller.StaffDashBoard;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class SFDashBoard {
    public AnchorPane ancherpane1;

    public void exit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void PlaseOrder(ActionEvent actionEvent) {
        try {
            URL resource = getClass().getResource("/SFDashBoard/PleaseOrder.fxml");
            assert resource!=null;
            Parent load = FXMLLoader.load(resource);
            ancherpane1.getChildren().clear();
            ancherpane1.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void SearchProduct(ActionEvent actionEvent) {
        try {
            URL resource = getClass().getResource("/ADBoard/StaffMemberReport.fxml");
            assert resource!=null;
            Parent load = FXMLLoader.load(resource);
            ancherpane1.getChildren().clear();
            ancherpane1.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void GenarateInvoice(ActionEvent actionEvent) {
        try {
            URL resource = getClass().getResource("/SFDashBoard/GenarateInvoice.fxml");
            assert resource!=null;
            Parent load = FXMLLoader.load(resource);
            ancherpane1.getChildren().clear();
            ancherpane1.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
