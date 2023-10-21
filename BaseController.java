package com.dictionary;

import com.api.DictionaryManagement;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class BaseController {
    public static final String SettingButtonImagePath = "/com/Image/SettingButton.png";
    public static final String YourWordButtonImagePath = "/com/Image/YourWordButton.png";
    public static final String EnterRecentWordButtonImage = "/com/Image/EnterButton.png";
    public static final String EnterDictionaryButtonImage = "/com/Image/EnterDictionary.png";
    public static final String SearchIconButtonImage = "/com/Image/searchIcon.png";
    public static final String SoundButtonImage = "/com/Image/SoundButton.png";
    public static final String RecentWordButtonImage = "/com/Image/RecentWordButton.png";
    public static final String AddWordButtonImage = "/com/Image/AddWordButton.png";
    public static final String RemoveWordButtonImage = "/com/Image/RemoveWordButton.png";
    public static final String Game1ButtonImage = "/com/Image/Game1Button.png";
    public static final String Game2ButtonImage = "/com/Image/Game2Button.png";

    protected static final String fileName = "C:\\Users\\khuatduchoa\\Desktop\\Projects\\Dictionary\\dict_data.txt";
    protected static final String recentWordsFileName = "C:\\Users\\khuatduchoa\\Desktop\\Projects\\Dictionary\\recent_words.txt";
    protected static final String yourWordsFileName = "C:\\Users\\khuatduchoa\\Desktop\\Projects\\Dictionary\\your_words.txt";
    protected DictionaryManagement dictionaryManagement = new DictionaryManagement(fileName);
    protected DictionaryManagement recentWordsManagement = new DictionaryManagement(recentWordsFileName);
    protected DictionaryManagement yourWordsManagement = new DictionaryManagement(yourWordsFileName);


    public BaseController() {
    }

    protected void switchToOtherPage(String fxmlFile, ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = fxmlLoader.load();

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void ButtonSetUp(Button button, String filePath, double width, double height) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(filePath)));
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        button.setGraphic(imageView);
        button.setPrefWidth(width);
        button.setPrefHeight(height);
    }

    public void ButtonSetUp(Button button, String filePath, double width, double height, double x, double y) {
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

    public void dateSetUp(Label label) {
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
}
