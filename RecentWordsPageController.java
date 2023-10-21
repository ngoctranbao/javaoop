package com.dictionary;

import com.api.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RecentWordsPageController extends BaseController  implements Initializable {
    @FXML
    private Button SettingButton, YourWordButton, EnterButton;

    @FXML
    private Label DateLabel;

    @FXML
    private TreeView<String> treeView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateSetUp(DateLabel);
        buttonSetUp();
        treeViewSetUp();
    }

    private void buttonSetUp() {
        ButtonSetUp(SettingButton, SettingButtonImagePath, 142.6, 24);

        ButtonSetUp(YourWordButton, YourWordButtonImagePath, 142.6, 62.6);

        ButtonSetUp(EnterButton, EnterDictionaryButtonImage, 17, 17);
    }

    private void treeViewSetUp() {
        TreeItem<String> rootItem = new TreeItem<>("Words");

        for(Word word : recentWordsManagement.getDictionary()) {
            TreeItem<String> word_target = new TreeItem<>(word.getWordTarget());
            rootItem.getChildren().add(word_target);

            TreeItem<String> word_explain = new TreeItem<>(word.getWordExplain());
            word_target.getChildren().add(word_explain);
        }
        treeView.setShowRoot(false);
        treeView.setRoot(rootItem);
    }
    public void switchToHomePage(ActionEvent event) throws IOException {
        switchToOtherPage("HomePage.fxml", event);
    }

    public void switchToYourWordsPage(ActionEvent event) throws IOException {
        switchToOtherPage("YourWordsPage.fxml", event);
    }
}
