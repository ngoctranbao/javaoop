package mycutedict.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import mycutedict.backend.Game;
import mycutedict.backend.Word;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

public class GamePageController extends BaseController implements Initializable {
    @FXML
    private Label DateLabel;

    @FXML
    private Button EnterSettingButton, EnterDictionaryButton,
            EnterRecentWordsButton, DictSearchButton;

    @FXML
    private ListView<Integer> LookUpView;

    @FXML
    private TextField DictSearchBarField;

    @FXML
    private Label QuestionLabel, ScoreLabel, FinalScoreLabel;

    @FXML
    private Button Answer1, Answer2, Answer3, Answer4,
            RestartGameButton, SoundButton, ReturnButton, TryAgainButton, QuitGameButton;

    @FXML
    private Pane ScenePane, ScorePane;

    private int score;
    private int questionIndex;
    private ArrayList<String> answers = new ArrayList<>();

    private AnswerButton answerButton1 = new AnswerButton();
    private AnswerButton answerButton2 = new AnswerButton();
    private AnswerButton answerButton3 = new AnswerButton();
    private AnswerButton answerButton4 = new AnswerButton();

    private static MediaPlayer mediaPlayer;
    private boolean isPlayingMusic;
    private Word firstWord, currentWord;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Common.dateSetUp(DateLabel);
        buttonsSetUp();
        listViewsSetUp();
        setUpFirstQuest();
        playMusic();
        ScorePane.setVisible(false);
    }

    // Switch to Home Page
    public void switchToHomePage(ActionEvent event) throws IOException {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        switchToOtherPage("HomePage.fxml", event);
    }

    // Switch to Recent Words (Search History Page)
    public void switchToRecentWordsPage(ActionEvent event) throws IOException {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        switchToOtherPage("RecentWordsPage.fxml", event);
    }

    // Switch to Display the specific Word page - meaning, pronunciation, ...
    public void switchToDictionaryPage(ActionEvent event) throws IOException {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        String word_target = DictSearchBarField.getText();
        Word word = Common.dictionaryManagement.requireSearch(word_target);

        if(word != null) {
            switchToDictionaryPage("YourWordsPage.fxml", event, null, word);
        }
    }

    // Switch to Your Words (Saved Words Page)
    public void switchToYourWordsPage(ActionEvent event) throws IOException {
        mediaPlayer.stop();
        switchToOtherPage("YourWordsPage.fxml", event);
    }

    /**
     * Restart game/play again
     */
    public void restartGame(ActionEvent event) throws IOException {
        ScorePane.setVisible(false);
        score = 0;
        setUpFirstQuest();
        ScoreLabel.setText(String.valueOf(score));
    }

    /**
     * Initialize mediaPlayer to play music.
     */
    private void playMusic() {
        if (mediaPlayer == null) {
            String musicFile = Objects.requireNonNull(getClass().getResource("/mycutedict/Music/Price_Tag.mp3")).toExternalForm();
            Media sound = new Media(musicFile);
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        } else {
            mediaPlayer.play(); // If mediaPlayer is already initialized, play the music again
        }
        isPlayingMusic = true;
    }

    /**
     * Turn on, turn off music.
     */
    public void handleSound(ActionEvent event) throws IOException {
        if(isPlayingMusic) {
            ButtonSetUp(SoundButton, Common.ExitSoundButtonImage, 21, 21,
                    565, 81);
            mediaPlayer.pause();
            isPlayingMusic = false;
        } else {
            ButtonSetUp(SoundButton, Common.GameSoundButtonImage, 21, 21,
                    565, 81);
            mediaPlayer.play();
            isPlayingMusic = true;
        }
    }

    /**
     * Setting up the first question when first start game.
     */
    private void setUpFirstQuest() {
        score = 0;
        questionIndex = 0;
        Common.dictionaryManagement.requireGame().random10Words();
        firstWord = Common.dictionaryManagement.getDatabase().get(Game.tenWords.get(0));
        setUpOneQuestion(firstWord);
        ScoreLabel.setText(String.valueOf(score));
    }

    /**
     * When an answer box is clicked, check answers, update the score and then
     * move to the next question.
     */
    public void moveToNextQuestion(ActionEvent event) throws IOException {
        if(questionIndex < 9) {
            Button clickedButton = (Button) event.getSource();
            Integer wordIndex = Game.tenWords.get(questionIndex);
            if(questionIndex == 0) {
                currentWord = firstWord;
            } else {
                currentWord = Common.dictionaryManagement.getDatabase().get(wordIndex);
            }
            boolean answer = checkAnswer(clickedButton, currentWord);
            if(answer) {
                score++;
            }
            ScoreLabel.setText(String.valueOf(score));
            questionIndex++;
            wordIndex = Game.tenWords.get(questionIndex);
            currentWord = Common.dictionaryManagement.getDatabase().get(wordIndex);
            setUpOneQuestion(currentWord);

        } else {
            Button clickedButton = (Button) event.getSource();
            boolean answer = checkAnswer(clickedButton, currentWord);
            if(answer) {
                score++;
            }
            ScoreLabel.setText(String.valueOf(score));
            FinalScoreLabel.setText(String.valueOf(score));
            ScorePane.setVisible(true);
        }
    }

    /** Set up for one question. */
    private void setUpOneQuestion(Word word) {
        Integer integer = Common.dictionaryManagement.search(word.getWord_target());
        QuestionLabel.setText("What is the meaning of\n" + word.getWord_target() + "?");
        Common.dictionaryManagement.requireGame().random4Answers(integer);
        answers.clear();
        for(int i = 0; i < 3; ++i) {
            answers.add(Common.dictionaryManagement.getDatabase().
                    get(Game.fourWordsEachQuestion.get(0)).getWord_explain());
        }
        answerButton1 = ButtonSetUp(answerButton1, Answer1, Common.AnswerBoxImage,
                120 * 1.5, 29 * 1.5, 440, 119 * 1.5, answers.get(0));
        answerButton2 = ButtonSetUp(answerButton2, Answer2, Common.AnswerBoxImage,
                120 * 1.5, 29 * 1.5, 440, 154 * 1.5, answers.get(1));
        answerButton3 = ButtonSetUp(answerButton3, Answer3, Common.AnswerBoxImage,
                120 * 1.5, 29 * 1.5, 440, 189 * 1.5, answers.get(2));
        answerButton4 = ButtonSetUp(answerButton4, Answer4, Common.AnswerBoxImage,
                120 * 1.5, 29 * 1.5, 440, 224 * 1.5, answers.get(3));
    }

    /**
     * Check if the answer for a question is correct.
     */
    private boolean checkAnswer(Button clickedButton, Word word) {
        String correctAnswer = word.getWord_explain();

        for (AnswerButton answerButton : Arrays.asList(answerButton1, answerButton2, answerButton3, answerButton4)) {
            if (clickedButton == answerButton.getButton() && correctAnswer.equals(answerButton.getText())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Set up all the buttons in a page.
     */
    @Override
    protected void buttonsSetUp() {
        ButtonSetUp(EnterSettingButton, Common.SettingButtonImage, 142.6 * 1.5,
                24 * 1.5, 651, 210 * 1.5);
        ButtonSetUp(EnterDictionaryButton, Common.EnterDictionaryButtonImage,
                17 * 1.5, 17 * 1.5, 442 * 1.5, 57 * 1.5);
        ButtonSetUp(EnterRecentWordsButton, Common.RecentWordButtonImage,
                142.6 * 1.5, 62.6 * 1.5, 651, 240 * 1.5);
        ButtonSetUp(DictSearchButton, Common.SearchIconButtonImage,
                38.0/2, 38.0/2);
        ButtonSetUp(RestartGameButton, Common.RestartButtonImage, 98, 31.5,
                425, 408);
        ButtonSetUp(SoundButton, Common.GameSoundButtonImage, 21, 21,
                565, 81);
        ButtonSetUp(ReturnButton, Common.GameReturnButtonImage, 22.5, 22.5);
        ButtonSetUp(TryAgainButton, Common.TryAgainButtonImage,
                44 * 1.5, 17 * 1.5);
        ButtonSetUp(QuitGameButton, Common.QuitGameButtonImage,
                44 * 1.5, 17 * 1.5);
    }

    /**
     * Set up all the list views needed in a page.
     */
    @Override
    protected void listViewsSetUp() {
        dictLookUp(LookUpView, DictSearchBarField);
    }

    /**
     * Exit program.
     */
    @Override
    public void logOut(ActionEvent event) throws IOException {
        Stage stage = (Stage) ScenePane.getScene().getWindow();
        Common.logOut(stage);
    }
}
