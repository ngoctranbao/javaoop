package mycutedict.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mycutedict.backend.GoogleTranslateAPI;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

public class GoogleTranslatePageController extends BaseController implements Initializable {
    @FXML
    private Button OkButton, CancelButton, TranslateButton, ChooseLanguageButton, ReturnButton;

    @FXML
    private TextField EnterTextField;

    @FXML
    private Label DateLabel, MeaningLabel, targetLanguageLabel;

    @FXML
    private Pane ChooseLanguagePane, ScenePane;

    @FXML
    private ChoiceBox<String> ChooseLanguageChoiceBox;

    private Map<String, String> languages = new HashMap<>();

    private String targetLanguage = "vi";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Common.dateSetUp(DateLabel);
        buttonsSetUp();
        listViewsSetUp();
        choiceBoxSetUp();
        targetLanguageLabel.setText("Vietnamese");
        ChooseLanguagePane.setVisible(false);
    }

    @Override
    protected void buttonsSetUp() {
        ButtonSetUp(OkButton, Common.OKButtonImage,
                44 * 1.5, 17 * 1.5);
        ButtonSetUp(CancelButton, Common.CancelButtonImage,
                44 * 1.5, 17 * 1.5);
        ButtonSetUp(TranslateButton, Common.TranslateButtonImage, 84, 33);
        ButtonSetUp(ChooseLanguageButton, Common.ChooseLanguageButtonImage, 144, 33);
        ButtonSetUp(ReturnButton, Common.GameReturnButtonImage, 22.5, 22.5);
    }

    @Override
    protected void listViewsSetUp() {

    }

    private void choiceBoxSetUp() {
        languages.put("Vietnamese", "vi");
        languages.put("Italian", "it");
        languages.put("Chinese", "zh");
        languages.put("Japanese", "ja");
        languages.put("German", "de");
        languages.put("French", "fr");
        languages.put("Russian", "ru");
        languages.put("Thai", "th");
        languages.put("Indonesia", "id");

        Set<String> lan = languages.keySet();
        ChooseLanguageChoiceBox.getItems().addAll(lan);
    }

    public void switchToHomePage(ActionEvent event) throws IOException {
        switchToOtherPage("HomePage.fxml", event);
    }

    public void translateText(ActionEvent event) throws Exception {
        String text = EnterTextField.getText();
        String meaning = GoogleTranslateAPI.translate(text, targetLanguage);
        MeaningLabel.setText(meaning);
    }

    public void handleCancelButton(ActionEvent event) throws IOException {
        ChooseLanguagePane.setVisible(false);
        targetLanguageLabel.setVisible(true);
    }

    public void chooseTargetLanguage(ActionEvent event) throws IOException {
        String languageName = ChooseLanguageChoiceBox.getValue();
        targetLanguageLabel.setText(languageName);
        targetLanguage = languages.get(languageName);
        ChooseLanguagePane.setVisible(false);
        targetLanguageLabel.setVisible(true);
    }

    public void languagePaneShowUp(ActionEvent event) throws IOException {
        ChooseLanguagePane.setVisible(true);
        targetLanguageLabel.setVisible(false);
    }

    @Override
    protected void logOut(ActionEvent event) throws IOException {
        Stage stage = (Stage) ScenePane.getScene().getWindow();
        Common.logOut(stage);
    }
}
