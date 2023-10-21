package com.dictionary;

import com.api.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DictionaryPageController extends BaseController implements Initializable {
    @FXML
    Button EnterRecentWordButton, SettingButton, YourWordButton, SoundButton;

    @FXML
    Label DateLabel, WordLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateSetUp(DateLabel);
        buttonSetUp();
    }

    private void buttonSetUp() {
        ButtonSetUp(SettingButton, SettingButtonImagePath, 142.6, 24, 432, 210);

        ButtonSetUp(YourWordButton, YourWordButtonImagePath, 142.6, 62.6, 432, 240);

        ButtonSetUp(EnterRecentWordButton, EnterRecentWordButtonImage, 17, 17, 442, 57);

        ButtonSetUp(SoundButton, SoundButtonImage, 15, 15);
    }

    public void displayWord(Word word) {
        String word_target = word.getWordTarget();
        String word_explain = word.getWordExplain();

        WordLabel.setText(word_target + "\n" + word_explain);
    }

    public void switchToRecentWordsPage(ActionEvent event) throws IOException {
        switchToOtherPage("RecentWordsPage.fxml", event);
    }

    public void switchToYourWordsPage(ActionEvent event) throws IOException {
        switchToOtherPage("YourWordsPage.fxml", event);
    }

}
