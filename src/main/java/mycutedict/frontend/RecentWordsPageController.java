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

public class RecentWordsPageController extends BaseController  implements Initializable {
    @FXML
    private Button SettingButton, YourWordButton, EnterButton, DictSearchButton;

    @FXML
    private Label DateLabel;

    @FXML
    private ListView<Integer> recentWordsListView;

    @FXML
    private TextField SearchBarTextField;

    @FXML
    private AnchorPane ScenePane;

    private Integer current_word_index;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateSetUp(DateLabel);
        buttonSetUp();
        listViewSetUp(recentWordsListView, "RecentWordsPage.fxml", ScenePane);
    }

    private void buttonSetUp() {
        ButtonSetUp(SettingButton, SettingButtonImagePath, 142.6, 24);
        ButtonSetUp(YourWordButton, YourWordButtonImagePath, 142.6, 62.6);
        ButtonSetUp(EnterButton, EnterDictionaryButtonImage, 17, 17);
        ButtonSetUp(DictSearchButton, SearchIconButtonImage, 38.0/3.0, 38.0/3.0);
    }

    public void switchToHomePage(ActionEvent event) throws IOException {
        switchToOtherPage("HomePage.fxml", event);
    }

    public void switchToYourWordsPage(ActionEvent event) throws IOException {
        switchToOtherPage("YourWordsPage.fxml", event);
    }

    public void switchToDictionaryPage(ActionEvent event) throws IOException {
        String word_target = SearchBarTextField.getText();
        Word word = dictionaryManagement.requireSearch(word_target);

        if(word != null) {
            switchToDictionaryPage("RecentWordsPage.fxml", event, word);
        }
    }

    public void logOut(ActionEvent event) throws IOException {
        logOut(event, ScenePane);
    }
}
