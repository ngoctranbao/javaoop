package mycutedict.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
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
    private ListView<Integer> recentWordsListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateSetUp(DateLabel);
        buttonSetUp();
        listViewSetUp(recentWordsListView, "HomePage.fxml", ScenePane);
    }

    private void buttonSetUp() {
        ButtonSetUp(SettingButton, SettingButtonImagePath, 142.6, 24, 432, 210);

        ButtonSetUp(YourWordButton, YourWordButtonImagePath, 142.6, 62.6, 432, 240);

        ButtonSetUp(EnterRecentWordButton, EnterRecentWordButtonImage, 17, 17, 442, 57);

        ButtonSetUp(SearchIconButton, SearchIconButtonImage, 38.0/3.0, 38.0/3.0);
    }

    public void switchToRecentWordsPage(ActionEvent event) throws IOException {
        switchToOtherPage("RecentWordsPage.fxml", event);
    }

    public void switchToYourWordsPage(ActionEvent event) throws IOException {
        switchToOtherPage("YourWordsPage.fxml", event);
    }

    public void switchToDictionaryPage(ActionEvent event) throws IOException {
        String word_target = SearchBarTextField.getText();
        Word word = dictionaryManagement.requireSearch(word_target);

        if(word != null) {
            switchToDictionaryPage("HomePage.fxml", event, word);
        }
    }

    public void logOut(ActionEvent event) throws IOException {
        logOut(event, ScenePane);
    }

}