package mycutedict.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mycutedict.backend.Word;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.speech.Central;
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
    private static Synthesizer synthesizer;
    private static final String VOICES_KEY = "freetts.voices";
    private static final String VOICES_VALUE = "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory";
    private static final String CENTRAL_DATA = "com.sun.speech.freetts.jsapi.FreeTTSEngineCentral";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Common.dateSetUp(DateLabel);
        buttonsSetUp();
        listViewsSetUp();
    }

    // Constructor
    public DictionaryPageController() {
        initializeSynthesizer();
    }

    public void setWord(Word word) {
        this.word = word;
        if(Common.dictionaryManagement.isSaved(word.getWord_target()) == -1) {
            ButtonSetUp(SaveButton, Common.UnsavedButtonImage, 35.0/2, 35.0/2, 588, 83);
        } else {
            ButtonSetUp(SaveButton, Common.SaveButtonImage, 35.0/2, 35.0/2, 588, 83);
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

    /**
     * Initialize the synthesizer - allocate...
     */
    private void initializeSynthesizer() {
        try {
            if(synthesizer == null) {
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Set up all the buttons in a page.
     */
    public void buttonsSetUp() {
        ButtonSetUp(SettingButton, Common.SettingButtonImage, 142.6 * 1.5, 24 * 1.5,
                651, 210 * 1.5);
        ButtonSetUp(YourWordButton, Common.YourWordButtonImage, 142.6 * 1.5, 62.6 * 1.5,
                651, 240 * 1.5);
        ButtonSetUp(EnterRecentWordButton, Common.EnterRecentWordButtonImage, 17 * 1.5, 17 * 1.5,
                442 * 1.5, 57 * 1.5);
        ButtonSetUp(SoundButton, Common.SoundButtonImage,
                22.5, 22.5);
        ButtonSetUp(ReturnButton, Common.ReturnButtonImage,
                22.5, 22.5);
    }

    /**
     * Set up all the list views needed in a page.
     */
    public void listViewsSetUp() {
        listViewSetUp(recentWordsListView, Common.dictionaryManagement.requireShowUpRecentWord(),
                null,"RecentWordsPage.fxml", ScenePane);
    }

    // Switch to Recent Words (Search History Page)
    public void switchToRecentWordsPage(ActionEvent event) throws IOException {
        switchToOtherPage("RecentWordsPage.fxml", event);
    }

    // Switch to Your Words (Saved Words Page)
    public void switchToYourWordsPage(ActionEvent event) throws IOException {
        switchToOtherPage("YourWordsPage.fxml", event);
    }

    /**
     * Return to previous page.
     */
    public void returnToPreviousPage(ActionEvent event) throws IOException {
        switchToOtherPage(getPreviousStage(), event);
    }

    /** Save word to Your Words. */
    public void saveWord(ActionEvent event) {
        if(Common.dictionaryManagement.isSaved(word.getWord_target()) == -1) {
            ButtonSetUp(SaveButton, Common.SaveButtonImage, 35.0/2, 35.0/2, 588, 83);
            Common.dictionaryManagement.requireAdd(word.getWord_target());
        } else {
            ButtonSetUp(SaveButton, Common.UnsavedButtonImage, 35.0/2, 35.0/2, 588, 83);
            Common.dictionaryManagement.requireRemove(word.getWord_target());
        }
    }

    /**
     * Display word_target, meaning, etc.
     */
    public void displayWord() {
        WordLabel.setText(word.toString());
    }

    /**
     * Pronounce word - text to speech.
     */
    public void pronounceWord(ActionEvent event) {
        try {
            synthesizer.speakPlainText(word.getWord_target(), null);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deallocate the synthesizer when the program stop
     */
    public static void cleanup() {
        try {
            if (synthesizer != null) {
                synthesizer.deallocate();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
