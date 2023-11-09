package mycutedict.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import mycutedict.backend.Word;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;


public class DictionaryPageController extends BaseController implements Initializable {
    @FXML
    private Button EnterRecentWordButton, SettingButton, YourWordButton,
            SoundButton, ReturnButton, SaveButton;

    @FXML
    private Label DateLabel, WordLabel;

    @FXML
    private AnchorPane ScenePane;

    @FXML
    private ListView<Integer> recentWordsListView;

    private String previousStage;
    private Word word;
    //private boolean saved;
    private static Synthesizer synthesizer;
    private static final String VOICES_KEY = "freetts.voices";
    private static final String VOICES_VALUE = "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory";
    private static final String CENTRAL_DATA = "com.sun.speech.freetts.jsapi.FreeTTSEngineCentral";

    public DictionaryPageController() {
        try {
            System.setProperty(VOICES_KEY, VOICES_VALUE);
            Central.registerEngineCentral(CENTRAL_DATA);
            SynthesizerModeDesc desc = new SynthesizerModeDesc(
                    null,
                    "general",
                    Locale.US,
                    null,
                    null);

            synthesizer = Central.createSynthesizer(desc);
            synthesizer.allocate();
            synthesizer.resume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setWord(Word word) {
        this.word = word;
        if(dictionaryManagement.isSaved(word.getWord_target()) == -1) {
            ButtonSetUp(SaveButton, UnsavedButtonImage, 35.0 / 3.0, 35.0 / 3.0, 392, 52);
        } else {
            ButtonSetUp(SaveButton, SaveButtonImage, 35.0 / 3.0, 35.0 / 3.0, 392, 52);
        }
    }

    public Word getWord() {
        return word;
    }

    public void setPreviousStage(String previousStage) {
        this.previousStage = previousStage;
    }

    public String getPreviousStage() {
        return previousStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateSetUp(DateLabel);
        buttonSetUp();
        listViewSetUp(recentWordsListView, dictionaryManagement.requireShowUpRecentWord(), null,"RecentWordsPage.fxml", ScenePane);
    }

    private void buttonSetUp() {
        ButtonSetUp(SettingButton, SettingButtonImagePath, 142.6, 24, 432, 210);
        ButtonSetUp(YourWordButton, YourWordButtonImagePath, 142.6, 62.6, 432, 240);
        ButtonSetUp(EnterRecentWordButton, EnterRecentWordButtonImage, 17, 17, 442, 57);
        ButtonSetUp(SoundButton, SoundButtonImage, 15, 15);
        ButtonSetUp(ReturnButton, ReturnButtonImage, 15, 15);
    }

    public void switchToRecentWordsPage(ActionEvent event) throws IOException, EngineException {
        switchToOtherPage("RecentWordsPage.fxml", event);
    }

    public void switchToYourWordsPage(ActionEvent event) throws IOException, EngineException {
        switchToOtherPage("YourWordsPage.fxml", event);
    }

    public void returnToPreviousPage(ActionEvent event) throws IOException, EngineException {
        switchToOtherPage(getPreviousStage(), event);
    }

    /** Save word to Your Words. */
    public void saveWord(ActionEvent event) throws IOException {
        if(dictionaryManagement.isSaved(word.getWord_target()) == -1) {
            ButtonSetUp(SaveButton, SaveButtonImage, 35.0/3.0, 35.0/3.0, 392, 52);
            dictionaryManagement.requireAdd(word.getWord_target());
        } else {
            ButtonSetUp(SaveButton, UnsavedButtonImage, 35.0/3.0, 35.0/3.0, 392, 52);
            dictionaryManagement.requireRemove(word.getWord_target());
        }
    }

    public void displayWord() {
        WordLabel.setText(word.toString());
    }

    public void pronounceWord(ActionEvent event) throws IOException {
        try {
            synthesizer.speakPlainText(word.getWord_target(), null);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cleanup() {
        try {
            if (synthesizer != null) {
                synthesizer.deallocate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logOut(ActionEvent event) throws IOException, EngineException {
        logOut(event, ScenePane);
    }
}
