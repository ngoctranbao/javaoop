package mycutedict.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class YourWordsPageController extends BaseController implements Initializable {
    @FXML
    private Button SearchButton, EnterSettingButton, EnterDictionaryButton, EnterRecentWordsButton,
            AddWordButton, RemoveWordButton, Game1Button, Game2Button, DictSearchButton,
            OkButton, CancelButton, AddWordSearchButton;

    @FXML
    private Label DateLabel, WordNotFound;

    @FXML
    private TreeView<String> YourWordView;

    @FXML
    private TextField SearchBarTextField, AddWordTextField;

    @FXML
    private AnchorPane ScenePane;

    @FXML
    private AnchorPane AddWordPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateSetUp(DateLabel);
        buttonSetUp();
        AddWordPane.setVisible(false);
    }

    private void buttonSetUp() {
        ButtonSetUp(EnterSettingButton, SettingButtonImagePath, 142.6, 24, 432, 210);
        ButtonSetUp(EnterDictionaryButton, EnterDictionaryButtonImage, 17, 17, 442, 57);
        ButtonSetUp(SearchButton, SearchIconButtonImage, 38.0/3.0, 38.0/3.0);
        ButtonSetUp(EnterRecentWordsButton, RecentWordButtonImage, 142.6, 62.6, 432, 240);

        ButtonSetUp(AddWordButton, AddWordButtonImage, 196.0/3.0, 21, 280, 243);
        ButtonSetUp(RemoveWordButton, RemoveWordButtonImage, 196.0/3.0, 21, 348, 243);
        ButtonSetUp(Game1Button, Game1ButtonImage, 196.0/3.0, 21, 280, 270);
        ButtonSetUp(Game2Button, Game2ButtonImage, 196.0/3.0, 21, 348, 270);
        ButtonSetUp(DictSearchButton, SearchIconButtonImage, 38.0/3.0, 38.0/3.0);
        ButtonSetUp(OkButton, OKButtonImage, 44, 17);
        ButtonSetUp(CancelButton, CancelButtonImage, 44, 17);
        ButtonSetUp(AddWordSearchButton, SearchIconButtonImage, 21, 21);
    }

    public void searchForYourWord(ActionEvent event) throws IOException {
        String word = SearchBarTextField.getText();
    }

    public void switchToHomePage(ActionEvent event) throws IOException {
        switchToOtherPage("HomePage.fxml", event);
    }

    public void switchToRecentWordsPage(ActionEvent event) throws IOException {
        switchToOtherPage("RecentWordsPage.fxml", event);
    }

    public void logOut(ActionEvent event) throws IOException {
        logOut(event, ScenePane);
    }

    public void addWordShowUp(ActionEvent event) throws IOException {
        AddWordPane.setVisible(true);
    }

    public void closeAddWordPane(ActionEvent event) throws IOException {
        AddWordPane.setVisible(false);
    }
}
