package mycutedict.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mycutedict.backend.Word;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class YourWordsPageController extends BaseController implements Initializable {
    @FXML
    private Button SearchButton, EnterSettingButton, EnterDictionaryButton, EnterRecentWordsButton,
            AddWordButton, RemoveWordButton, Game1Button, Game2Button, DictSearchButton,
            OkButton, CancelButton, AddWordSearchButton;

    @FXML
    private Label DateLabel, WordNotFound;

    @FXML
    private ListView<Integer> YourWordView, SearchWordView, LookUpView;

    @FXML
    private TextField SearchBarTextField, AddWordTextField, DictSearchBarField;

    @FXML
    private AnchorPane ScenePane;

    @FXML
    private AnchorPane AddWordPane;

    @FXML
    private ImageView popUpImageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateSetUp(DateLabel);
        buttonSetUp();
        listViewSetUp(YourWordView, Common.dictionaryManagement.requireShowUpYourWord(), null,
                "YourWordsPage.fxml", ScenePane);
        listViewSetUp(SearchWordView, null, null,
                "YourWordsPage.fxml", ScenePane);

        listViewSetUp(LookUpView, null,
                null,"YourWordsPage.fxml", ScenePane);
        dictLookUp(LookUpView, DictSearchBarField);

        AddWordPane.setVisible(false);
        SearchWordView.setVisible(false);

        SearchBarTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            handleSearchTextField(newValue);
        });
    }

    private void buttonSetUp() {
        ButtonSetUp(EnterSettingButton, Common.SettingButtonImage,
                142.6, 24, 432, 210);
        ButtonSetUp(EnterDictionaryButton, Common.EnterDictionaryButtonImage,
                17, 17, 442, 57);
        ButtonSetUp(SearchButton, Common.SearchIconButtonImage,
                38.0/3.0, 38.0/3.0);
        ButtonSetUp(EnterRecentWordsButton, Common.RecentWordButtonImage,
                142.6, 62.6, 432, 240);

        ButtonSetUp(AddWordButton, Common.AddWordButtonImage,
                196.0/3.0, 21, 280, 243);
        ButtonSetUp(RemoveWordButton, Common.RemoveWordButtonImage,
                196.0/3.0, 21, 348, 243);
        ButtonSetUp(Game1Button, Common.Game1ButtonImage,
                196.0/3.0, 21, 280, 270);
        ButtonSetUp(Game2Button, Common.Game2ButtonImage,
                196.0/3.0, 21, 348, 270);
        ButtonSetUp(DictSearchButton, Common.SearchIconButtonImage,
                38.0/3.0, 38.0/3.0);
        ButtonSetUp(OkButton, Common.OKButtonImage,
                44, 17);
        ButtonSetUp(CancelButton, Common.CancelButtonImage,
                44, 17);
        ButtonSetUp(AddWordSearchButton, Common.SearchIconButtonImage,
                21, 21);
    }

    public void switchToHomePage(ActionEvent event) throws IOException {
        switchToOtherPage("HomePage.fxml", event);
    }

    public void switchToRecentWordsPage(ActionEvent event) throws IOException {
        switchToOtherPage("RecentWordsPage.fxml", event);
    }

    public void switchToDictionaryPage(ActionEvent event) throws IOException {
        String word_target = DictSearchBarField.getText();
        Word word = Common.dictionaryManagement.requireSearch(word_target);

        if(word != null) {
            switchToDictionaryPage("YourWordsPage.fxml", event, word);
        }
    }

    public void searchFromAddWord(ActionEvent event) throws IOException {
        String word_target = AddWordTextField.getText();
        Word word = Common.dictionaryManagement.requireSearch(word_target);

        if(word != null) {
            switchToDictionaryPage("YourWordsPage.fxml", event, word);
        }
    }

    public void addWord(ActionEvent event) throws IOException{
        String word_target = AddWordTextField.getText();
        Word word = Common.dictionaryManagement.requireSearch(word_target);

        if(word == null) {
            WordNotFound.setText("Word not found");
        } else if(Common.dictionaryManagement.isSaved(word_target) == -1){
            Common.dictionaryManagement.requireAdd(word_target);

            int index = Common.dictionaryManagement.isSaved(word_target);
            Integer integer = Common.dictionaryManagement.requireShowUpYourWord().get(index);

            YourWordView.getItems().add(integer);
            AddWordPane.setVisible(false);
        } else {
            WordNotFound.setText("Word already added");
        }
    }

    public void removeWord(ActionEvent event) throws IOException {

    }

    public void isWordSaved(ActionEvent event) {
        String word_target = SearchBarTextField.getText();

        if(Common.dictionaryManagement.isSaved(word_target) != -1) {
            SearchWordView.getItems().clear();

            int index = Common.dictionaryManagement.isSaved(word_target);
            Integer integer = Common.dictionaryManagement.requireShowUpYourWord().get(index);

            SearchWordView.getItems().add(integer);
            YourWordView.setVisible(false);
            SearchWordView.setVisible(true);
        }
    }

    private void handleSearchTextField(String newValue) {
        SearchWordView.getItems().clear();
        if (newValue.isEmpty()) {
            // Text field is empty, so clear the SearchWordView, set it to invisible, and set YourWordView to visible
            SearchWordView.setVisible(false);
            YourWordView.setVisible(true);
        } else {
            YourWordView.setVisible(false);
            SearchWordView.setVisible(true);
            for(Integer item : Common.dictionaryManagement.requireShowUpYourWord()) {
                Word word = Common.dictionaryManagement.requireSearch(item);
                if(word.getWord_target().startsWith(newValue)) {
                    SearchWordView.getItems().add(item);
                }
            }
        }
    }

    public void logOut(ActionEvent event) throws IOException{
        Stage stage = (Stage) ScenePane.getScene().getWindow();
        Common.logOut(stage);
    }

    public void addWordShowUp(ActionEvent event) {
        AddWordTextField.setText("");
        AddWordPane.setVisible(true);
    }

    public void closeAddWordPane(ActionEvent event) {
        AddWordPane.setVisible(false);
    }
}
