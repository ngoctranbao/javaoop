package com.dictionary;

import com.api.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController extends BaseController implements Initializable {

    @FXML
    private Button SettingButton, YourWordButton, EnterRecentWordButton, SearchIconButton;

    @FXML
    private Label DateLabel;

    @FXML
    private TextField SearchBarTextField;

    @FXML
    private ListView<Word> recentWordsListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateSetUp(DateLabel);

        buttonSetUp();

        recentWordsListViewSetUp();
    }

    private void buttonSetUp() {
        ButtonSetUp(SettingButton, SettingButtonImagePath, 142.6, 24, 432, 210);

        ButtonSetUp(YourWordButton, YourWordButtonImagePath, 142.6, 62.6, 432, 240);

        ButtonSetUp(EnterRecentWordButton, EnterRecentWordButtonImage, 17, 17, 442, 57);

        ButtonSetUp(SearchIconButton, SearchIconButtonImage, 38.0/3.0, 38.0/3.0);
    }

    private void recentWordsListViewSetUp() {
        recentWordsListView.getItems().addAll(recentWordsManagement.getDictionary());
        recentWordsListView.setCellFactory(param -> new ListCell<Word>() {
            @Override
            protected void updateItem(Word item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getWordTarget());
                }
            }
        });
    }

    public void switchToRecentWordsPage(ActionEvent event) throws IOException {
        switchToOtherPage("RecentWordsPage.fxml", event);
    }

    public void switchToYourWordsPage(ActionEvent event) throws IOException {
        switchToOtherPage("YourWordsPage.fxml", event);
    }

    public void switchToDictionaryPage(ActionEvent event) throws IOException {
        String word_target = SearchBarTextField.getText();
        Word word = dictionaryManagement.dictionaryLookup(word_target);
        if(word != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DictionaryPage.fxml"));
            Parent root = fxmlLoader.load();

            recentWordsManagement.dictionaryAdd(word);

            DictionaryPageController scene2Controller = fxmlLoader.getController();
            scene2Controller.displayWord(word);

            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

}