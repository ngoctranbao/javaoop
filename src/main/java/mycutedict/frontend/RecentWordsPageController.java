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
        dateSetUp(DateLabel);
        buttonSetUp();
        listViewSetUp(recentWordsListView, Common.dictionaryManagement.requireShowUpRecentWord(),
                null, "RecentWordsPage.fxml", ScenePane);
        listViewSetUp(LookUpView, null,
                null,"RecentWordsPage.fxml", ScenePane);
        dictLookUp(LookUpView, SearchBarTextField);
    }

    private void buttonSetUp() {
        ButtonSetUp(SettingButton, Common.SettingButtonImage, 142.6, 24);
        ButtonSetUp(YourWordButton, Common.YourWordButtonImage, 142.6, 62.6);
        ButtonSetUp(EnterButton, Common.EnterDictionaryButtonImage, 17, 17);
        ButtonSetUp(DictSearchButton, Common.SearchIconButtonImage, 38.0/3.0, 38.0/3.0);
    }

    public void switchToHomePage(ActionEvent event) throws IOException {
        switchToOtherPage("HomePage.fxml", event);
    }

    public void switchToYourWordsPage(ActionEvent event) throws IOException {
        switchToOtherPage("YourWordsPage.fxml", event);
    }

    public void switchToDictionaryPage(ActionEvent event) throws IOException {
        String word_target = SearchBarTextField.getText();
        Word word = Common.dictionaryManagement.requireSearch(word_target);

        if(word != null) {
            switchToDictionaryPage("RecentWordsPage.fxml", event, word);
        }
    }

    public void logOut(ActionEvent event) throws IOException{
        Stage stage = (Stage) ScenePane.getScene().getWindow();
        Common.logOut(stage);
    }
}
