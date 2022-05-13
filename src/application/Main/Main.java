package application.Main;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import java.util.Objects;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = (BorderPane)FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../FXML/Sample.fxml")));
            Scene scene = new Scene(root,586,328);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Clock");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}