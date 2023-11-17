package mycutedict.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
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
    private TextField SearchBarTextField, ManageWordTextField, DictSearchBarField;

    @FXML
    private AnchorPane ScenePane;

    @FXML
    private AnchorPane ManageWordPane;

    @FXML
    private ImageView popUpImageView;

    private String ManageWordPanePath;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Common.dateSetUp(DateLabel);
        buttonsSetUp();
        listViewsSetUp();

        ManageWordPane.setVisible(false);
        SearchWordView.setVisible(false);

        SearchBarTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            handleSearchTextField(newValue);
        });
    }

    /**
     * Set up all the buttons in a page.
     */
    public void buttonsSetUp() {
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

    /**
     * Set up all the list views needed in a page.
     */
    public void listViewsSetUp() {
        listViewSetUp(YourWordView, Common.dictionaryManagement.requireShowUpYourWord(), null,
                "YourWordsPage.fxml", ScenePane);
        listViewSetUp(SearchWordView, null, null,
                "YourWordsPage.fxml", ScenePane);

        listViewSetUp(LookUpView, null,
                null,"YourWordsPage.fxml", ScenePane);
        dictLookUp(LookUpView, DictSearchBarField);
    }

    // Switch to Home Page
    public void switchToHomePage(ActionEvent event) throws IOException {
        switchToOtherPage("HomePage.fxml", event);
    }

    // Switch to Recent Words (Search History Page)
    public void switchToRecentWordsPage(ActionEvent event) throws IOException {
        switchToOtherPage("RecentWordsPage.fxml", event);
    }

    // Switch to Display the specific Word page - meaning, pronunciation, ...
    public void switchToDictionaryPage(ActionEvent event) throws IOException {
        String word_target = DictSearchBarField.getText();
        Word word = Common.dictionaryManagement.requireSearch(word_target);

        if(word != null) {
            switchToDictionaryPage("YourWordsPage.fxml", event, null, word);
        }
    }

    /**
     * Switch to the dictionaryPage for the word typed in ManageWordPane if searchIcon clicked.
     */
    public void searchFromManageWord(ActionEvent event) throws IOException {
        String word_target = ManageWordTextField.getText();
        Word word = Common.dictionaryManagement.requireSearch(word_target);

        if(word != null) {
            switchToDictionaryPage("YourWordsPage.fxml", event, null, word);
        }
    }

    /**
     * Handel Ok button in the ManageWordPane to add or remove word.
     */
    public void handleOkButton(ActionEvent event) throws IOException {
        if(ManageWordTextField.getText() == null) {
            WordNotFound.setText("Word not found");
            return;
        }

        String word_target = ManageWordTextField.getText().trim();
        if(ManageWordPanePath.equals(Common.AddWordPaneImage)) {
            addWord(event, word_target);
        } else {
            removeWord(event, word_target);
        }
    }

    /**
     * Add word in the AddWordPane.
     */
    private void addWord(ActionEvent event, String word_target) throws IOException{
        Word word = Common.dictionaryManagement.requireSearch(word_target);

        if(word == null) {
            WordNotFound.setText("Word not found");
        } else if(Common.dictionaryManagement.isSaved(word_target) == -1){
            Common.dictionaryManagement.requireAdd(word_target);

            int index = Common.dictionaryManagement.isSaved(word_target);
            Integer integer = Common.dictionaryManagement.requireShowUpYourWord().get(index);

            YourWordView.getItems().add(integer);
            ManageWordPane.setVisible(false);
        } else {
            WordNotFound.setText("Word already added");
        }
    }

    /**
     * Remove word in the RemoveWordPane.
     */
    private void removeWord(ActionEvent event, String word_target) throws IOException {
        Word word = Common.dictionaryManagement.requireSearch(word_target);

        if(word == null) {
            WordNotFound.setText("Word not found");
        } else if(Common.dictionaryManagement.isSaved(word_target) != -1) {
            Integer indexInDict = Common.dictionaryManagement.search(word_target);
            int index = Common.dictionaryManagement.requireShowUpYourWord().indexOf(indexInDict);
            Common.dictionaryManagement.requireRemove(word_target);

            YourWordView.getItems().remove(index);
            ManageWordPane.setVisible(false);
        } else {
            WordNotFound.setText("Word hasn't been added");
        }
    }

    /**
     * Make sure when a new character is typed in the search text field a list of
     * words beginning with it being returned - showed.
     */
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

    /**
     * When the add word button being clicked the addWordPane shows up.
     */
    public void addWordShowUp(ActionEvent event) throws IOException{
        ManageWordPanePath = Common.AddWordPaneImage;
        ManageWordTextField.setText("");
        ManageWordPane.setVisible(true);
    }

    /**
     * When the remove word button being clicked the removeWordPane shows up.
     */
    public void removeWordShowUp(ActionEvent event) throws IOException {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(Common.RemoveWordPaneImage)));
        popUpImageView.setImage(image);
        ManageWordPanePath = Common.RemoveWordPaneImage;
        ManageWordTextField.setText("");
        ManageWordPane.setVisible(true);
    }

    /**
     * When user clicks cancel, the addWordPane set to invisible.
     */
    public void closeManageWordPane(ActionEvent event) throws IOException{
        ManageWordPane.setVisible(false);
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(Common.AddWordPaneImage)));
        popUpImageView.setImage(image);
        WordNotFound.setText("");
    }

    /**
     * Exit program.
     */
    public void logOut(ActionEvent event) throws IOException{
        Stage stage = (Stage) ScenePane.getScene().getWindow();
        Common.logOut(stage);
    }
}
