package mycutedict.frontend;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import mycutedict.backend.DictionaryManagement;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Common {
    /** Declare variable for Images Path. */
    public static final String SettingButtonImage = "/mycutedict/Image/SettingButton.png";
    public static final String YourWordButtonImage = "/mycutedict/Image/YourWordButton.png";
    public static final String EnterRecentWordButtonImage = "/mycutedict/Image/EnterButton.png";
    public static final String EnterDictionaryButtonImage = "/mycutedict/Image/EnterDictionary.png";
    public static final String SearchIconButtonImage = "/mycutedict/Image/searchIcon.png";
    public static final String SoundButtonImage = "/mycutedict/Image/SoundButton.png";
    public static final String RecentWordButtonImage = "/mycutedict/Image/RecentWordButton.png";
    public static final String AddWordButtonImage = "/mycutedict/Image/AddWordButton.png";
    public static final String RemoveWordButtonImage = "/mycutedict/Image/RemoveWordButton.png";
    public static final String Game1ButtonImage = "/mycutedict/Image/Game1Button.png";
    public static final String Game2ButtonImage = "/mycutedict/Image/Game2Button.png";
    public static final String ReturnButtonImage = "/mycutedict/Image/ReturnButton.png";
    public static final String SaveButtonImage = "/mycutedict/Image/savedIcon.png";
    public static final String UnsavedButtonImage = "/mycutedict/Image/unsavedIcon.png";
    public static final String OKButtonImage = "/mycutedict/Image/OkButton.png";
    public static final String CancelButtonImage = "/mycutedict/Image/CancelButton.png";
    public static final String RemoveWordPaneImage = "/mycutedict/Image/RemoveWordPage.png";
    public static final String AddWordPaneImage = "/mycutedict/Image/AddWordPage.png";

    public static final DictionaryManagement dictionaryManagement = new DictionaryManagement();

    /**
     Set up date for every page.
     */
    public static void dateSetUp(Label label) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateDateTimeLabel(label)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /** Set up a support function to handle render time. */
    private static void updateDateTimeLabel(Label label) {
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

    /**
     * Pop up an alert to confirm before existing the program
     */
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
