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

public class HomePageController extends BaseController implements Initializable {
    @FXML
    private Button SettingButton, YourWordButton, EnterRecentWordButton, SearchIconButton;

    @FXML
    private Label DateLabel;

    @FXML
    private TextField SearchBarTextField;

    @FXML
    private AnchorPane ScenePane;

    @FXML
    private ListView<Integer> recentWordsListView, LookUpView;

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
        ButtonSetUp(SettingButton, Common.SettingButtonImage, 142.6, 24, 432, 210);

        ButtonSetUp(YourWordButton, Common.YourWordButtonImage, 142.6, 62.6, 432, 240);

        ButtonSetUp(EnterRecentWordButton, Common.EnterRecentWordButtonImage, 17, 17, 442, 57);

        ButtonSetUp(SearchIconButton, Common.SearchIconButtonImage, 38.0/3.0, 38.0/3.0);
    }

    /**
     * Set up all the list views needed in a page.
     */
    public void listViewsSetUp() {
        listViewSetUp(recentWordsListView, Common.dictionaryManagement.requireShowUpRecentWord(),
                null,"HomePage.fxml", ScenePane);
        listViewSetUp(LookUpView, null,
                null,"HomePage.fxml", ScenePane);
        dictLookUp(LookUpView, SearchBarTextField);
    }

    // Switch to Recent Words (Search History Page)
    public void switchToRecentWordsPage(ActionEvent event) throws IOException {
        switchToOtherPage("RecentWordsPage.fxml", event);
    }

    // Switch to Your Words (Saved Words Page)
    public void switchToYourWordsPage(ActionEvent event) throws IOException {
        switchToOtherPage("YourWordsPage.fxml", event);
    }

    // Switch to Display the specific word page - meaning, pronunciation, ...
    public void switchToDictionaryPage(ActionEvent event) throws IOException {
        String word_target = SearchBarTextField.getText();
        if(word_target != null) {
            Word word = Common.dictionaryManagement.requireSearch(word_target);
            if(word != null) {
                switchToDictionaryPage("HomePage.fxml", event, null, word);
            }
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