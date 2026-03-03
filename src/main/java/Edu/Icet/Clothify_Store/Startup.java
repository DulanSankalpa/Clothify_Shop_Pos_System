package Edu.Icet.Clothify_Store;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Startup extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/DashBoard/dashboardPanel.fxml"))));
        stage.show();
        stage.setTitle("Clothify POS System");
        stage.getIcons().add(
            new Image("img/logo.png")
        );
    }
}
