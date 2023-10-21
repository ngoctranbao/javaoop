package com.dictionary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class YourWordsPageController extends BaseController implements Initializable {
    @FXML
    private Button SearchButton, EnterSettingButton, EnterDictionaryButton, EnterRecentWordsButton,
            AddWordButton, RemoveWordButton, Game1Button, Game2Button;

    @FXML
    private Label DateLabel, YourWordView;

    @FXML
    private TextField SearchBarTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateSetUp(DateLabel);

        ButtonSetUp(EnterSettingButton, SettingButtonImagePath, 142.6, 24, 432, 210);
        ButtonSetUp(EnterDictionaryButton, EnterDictionaryButtonImage, 17, 17, 442, 57);
        ButtonSetUp(SearchButton, SearchIconButtonImage, 38.0/3.0, 38.0/3.0);
        ButtonSetUp(EnterRecentWordsButton, RecentWordButtonImage, 142.6, 62.6, 432, 240);

        ButtonSetUp(AddWordButton, AddWordButtonImage, 196.0/3.0, 21, 280, 243);
        ButtonSetUp(RemoveWordButton, RemoveWordButtonImage, 196.0/3.0, 21, 348, 243);
        ButtonSetUp(Game1Button, Game1ButtonImage, 196.0/3.0, 21, 280, 270);
        ButtonSetUp(Game2Button, Game2ButtonImage, 196.0/3.0, 21, 348, 270);
    }

    public void searchForYourWord(ActionEvent event) throws IOException {
        String word = SearchBarTextField.getText();

        YourWordView.setText(word);
    }

    public void switchToDictionaryPage(ActionEvent event) throws IOException {
        switchToOtherPage("HomePage.fxml", event);
    }

    public void switchToRecentWordsPage(ActionEvent event) throws IOException {
        switchToOtherPage("RecentWordsPage.fxml", event);
    }
}
