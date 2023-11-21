package mycutedict.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mycutedict.backend.Word;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RecentWordsPageController extends BaseController  implements Initializable {
    @FXML
    private Button SettingButton, YourWordButton, EnterButton, DictSearchButton;

    @FXML
    private Label DateLabel;

    @FXML
    private ListView<Integer> recentWordsListView, LookUpView;

    @FXML
    private TextField SearchBarTextField;

    @FXML
    private AnchorPane ScenePane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Common.dateSetUp(DateLabel);
        buttonsSetUp();
        listViewsSetUp();
    }

    /**
     * Set up all the buttons in a page.
     */
    public void buttonsSetUp() {
        ButtonSetUp(SettingButton, Common.SettingButtonImage, 142.6 * 1.5, 24 * 1.5,
                651, 210 * 1.5);
        ButtonSetUp(YourWordButton, Common.YourWordButtonImage, 142.6 * 1.5, 62.6 * 1.5,
                651, 240 * 1.5);
        ButtonSetUp(EnterButton, Common.EnterDictionaryButtonImage, 17 * 1.5, 17 * 1.5,
                442 * 1.5, 57 * 1.5);

        ButtonSetUp(DictSearchButton, Common.SearchIconButtonImage, 38.0 * 1.5/3, 38.0 * 1.5/3);
    }

    /**
     * Set up all the list views needed in a page.
     */
    public void listViewsSetUp() {
        listViewSetUp(recentWordsListView, Common.dictionaryManagement.requireShowUpRecentWord(),
                null, "RecentWordsPage.fxml", ScenePane);
        listViewSetUp(LookUpView, null,
                null,"RecentWordsPage.fxml", ScenePane);
        dictLookUp(LookUpView, SearchBarTextField);
    }

    // Switch to Home Page
    public void switchToHomePage(ActionEvent event) throws IOException {
        switchToOtherPage("HomePage.fxml", event);
    }

    // Switch to Your Words (Saved Words Page)
    public void switchToYourWordsPage(ActionEvent event) throws IOException {
        switchToOtherPage("YourWordsPage.fxml", event);
    }

    // Switch to Display the specific word page - meaning, pronunciation, ...
    public void switchToDictionaryPage(ActionEvent event) throws IOException {
        String word_target = SearchBarTextField.getText();
        Word word = Common.dictionaryManagement.requireSearch(word_target);

        if(word != null) {
            switchToDictionaryPage("RecentWordsPage.fxml", event, null, word);
        }
    }

    /**
     * Exit program.
     */
    public void logOut(ActionEvent event) throws IOException{
        Stage stage = (Stage) ScenePane.getScene().getWindow();
        Common.logOut(stage);
    }
}
