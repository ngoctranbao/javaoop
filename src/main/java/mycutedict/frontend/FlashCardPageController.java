package mycutedict.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FlashCardPageController extends BaseController implements Initializable {
    @FXML
    private Label DateLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Common.dateSetUp(DateLabel);
        buttonsSetUp();
    }

    @Override
    protected void buttonsSetUp() {

    }

    @Override
    protected void listViewsSetUp() {
        //no list views set up in the flashcard page
    }

    @Override
    protected void logOut(ActionEvent event) throws IOException {

    }
}
