package mycutedict.frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HomePage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("/mycutedict/Styling/Styling.css").toExternalForm());
        stage.setTitle("MyCuteDict");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> BaseController.logOut(stage));
    }

    public static void main(String[] args) {
        launch(args);
    }
}