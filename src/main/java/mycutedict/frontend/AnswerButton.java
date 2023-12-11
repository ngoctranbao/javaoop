package mycutedict.frontend;

import javafx.scene.control.Button;

public class AnswerButton {
    private Button button;
    private String text;

    public AnswerButton() {
        this.button = null;
        this.text = null;
    };
    public AnswerButton(Button button, String text) {
        this.button = button;
        this.text = text;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Button getButton() {
        return button;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
