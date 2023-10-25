package mycutedict.frontend;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import mycutedict.backend.DictionaryManagement;
import mycutedict.backend.Word;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class BaseController {
    protected static final String SettingButtonImagePath = "/mycutedict/Image/SettingButton.png";
    protected static final String YourWordButtonImagePath = "/mycutedict/Image/YourWordButton.png";
    protected static final String EnterRecentWordButtonImage = "/mycutedict/Image/EnterButton.png";
    protected static final String EnterDictionaryButtonImage = "/mycutedict/Image/EnterDictionary.png";
    protected static final String SearchIconButtonImage = "/mycutedict/Image/searchIcon.png";
    protected static final String SoundButtonImage = "/mycutedict/Image/SoundButton.png";
    protected static final String RecentWordButtonImage = "/mycutedict/Image/RecentWordButton.png";
    protected static final String AddWordButtonImage = "/mycutedict/Image/AddWordButton.png";
    protected static final String RemoveWordButtonImage = "/mycutedict/Image/RemoveWordButton.png";
    protected static final String Game1ButtonImage = "/mycutedict/Image/Game1Button.png";
    protected static final String Game2ButtonImage = "/mycutedict/Image/Game2Button.png";
    protected static final String ReturnButtonImage = "/mycutedict/Image/ReturnButton.png";
    protected static final String SaveButtonImage = "/mycutedict/Image/savedIcon.png";
    protected static final String UnsavedButtonImage = "/mycutedict/Image/unsavedIcon.png";
    protected static final String OKButtonImage = "/mycutedict/Image/OkButton.png";
    protected static final String CancelButtonImage = "/mycutedict/Image/CancelButton.png";

    protected static DictionaryManagement dictionaryManagement = new DictionaryManagement();

    public BaseController() {
    }

    protected void switchToOtherPage(String fxmlFile, ActionEvent Event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = fxmlLoader.load();

        Stage stage = (Stage) ((Node)Event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("/mycutedict/Styling/Styling.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> BaseController.logOut(stage));
    }

    protected void switchToDictionaryPage(String previousStage, ActionEvent Event, Word word) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DictionaryPage.fxml"));

        Parent root = fxmlLoader.load();

        DictionaryPageController controller = fxmlLoader.getController();
        controller.setWord(word);
        controller.setPreviousStage(previousStage);
        controller.displayWord();

        Stage stage = (Stage) ((Node)Event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("/mycutedict/Styling/Styling.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> BaseController.logOut(stage));
    }

    protected void switchToDictionaryPage(String previousStage, Pane pane, Word word) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DictionaryPage.fxml"));

        Parent root = fxmlLoader.load();

        DictionaryPageController controller = fxmlLoader.getController();
        controller.setWord(word);
        controller.setPreviousStage(previousStage);
        controller.displayWord();

        Stage stage = (Stage) pane.getScene().getWindow();
        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("/mycutedict/Styling/Styling.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> BaseController.logOut(stage));
    }

    protected void listViewSetUp(ListView<Integer> listView, ArrayList<Integer> arrayList, Integer integer,
                                 String previousStage, Pane pane) {
        listView.getItems().clear();
        if(integer != null) {
            listView.getItems().add(integer);
        } else if(arrayList != null) {
            listView.getItems().addAll(arrayList);
        }
        listView.setCellFactory(param -> new ListCell<Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(dictionaryManagement.requireSearch(item).getWord_target());
                }
            }
        });

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
                int current_word_index = listView.getSelectionModel().getSelectedItem();
                Word word = dictionaryManagement.requireSearch(current_word_index);
                try {
                    switchToDictionaryPage(previousStage, pane, word);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    protected void dictLookUp(ListView<Integer> listView, TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            listView.getItems().clear();
            if(newValue.isEmpty()) {
                listView.setVisible(false);
            } else {
                listView.setVisible(true);
                ArrayList<Integer> arrayList = dictionaryManagement.requireLookUp(newValue);
                listView.getItems().addAll(arrayList);
            }
        });
    }

    protected void ButtonSetUp(Button button, String filePath, double width, double height) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(filePath)));
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        button.setGraphic(imageView);
        button.setPrefWidth(width);
        button.setPrefHeight(height);
    }

    protected void ButtonSetUp(Button button, String filePath, double width, double height, double x, double y) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(filePath)));
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        button.setGraphic(imageView);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setPrefWidth(width);
        button.setPrefHeight(height);
    }

    protected void dateSetUp(Label label) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateDateTimeLabel(label)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateDateTimeLabel(Label label) {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = dateTimeFormat.format(new Date());

        label.setPrefWidth(117);
        label.setPrefHeight(14);
        label.setLayoutX(125);
        label.setLayoutY(117);
        Font font = new Font("Franklin Gothic Heavy", 12);
        label.setFont(font);

        label.setTextFill(Color.rgb(235, 106, 183));

        label.setText(currentDateTime);
    }

    protected void logOut(ActionEvent event, AnchorPane pane) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You are about to exit the program!");
        alert.setContentText("Do you really wanna exit?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            dictionaryManagement.requireExit();
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.close();
        }
    }
    public static void logOut(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You are about to exit the program!");
        alert.setContentText("Do you really wanna exit?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            dictionaryManagement.requireExit();
            stage.close();
        }
    }
}
