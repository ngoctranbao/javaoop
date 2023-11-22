package mycutedict.frontend;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mycutedict.backend.Word;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public abstract class BaseController {
    /**
     * Set up all the buttons in a page.
     */
    protected abstract void buttonsSetUp();

    /**
     * Set up all the list views needed in a page.
     */
    protected abstract void listViewsSetUp();

    /**
     * Every page would have a button to quit immediately.
     */
    protected abstract void logOut(ActionEvent event) throws IOException;

    /**
     * Switch to another page using an ActionEvent (Except dictionaryPage).
     */
    protected void switchToOtherPage(String fxmlFile, ActionEvent Event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = fxmlLoader.load();

        Stage stage = (Stage) ((Node)Event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("/mycutedict/Styling/Styling.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> Common.logOut(stage));
    }

    /**
     * Switch to dictionaryPage to display a word.
     */
    protected void switchToDictionaryPage(String previousStage, ActionEvent Event, Pane pane, Word word) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DictionaryPage.fxml"));

        Parent root = fxmlLoader.load();

        DictionaryPageController controller = fxmlLoader.getController();
        controller.setWord(word);
        controller.setPreviousStage(previousStage);
        controller.displayWord();

        Stage stage;
        if(Event == null) {
            stage = (Stage) pane.getScene().getWindow();
        } else {
            stage = (Stage) ((Node)Event.getSource()).getScene().getWindow();
        }
        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("/mycutedict/Styling/Styling.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> Common.logOut(stage));
    }

    /**
     * Set up a listView with integer type to display recent words, your words, etc.
     */
    protected void listViewSetUp(ListView<Integer> listView, ArrayList<Integer> arrayList, Integer integer,
                                 String previousStage, Pane pane) {
        listView.getItems().clear();
        if(integer != null) {
            listView.getItems().add(integer);
        } else if(arrayList != null) {
            listView.getItems().addAll(arrayList);
        }
        listView.setCellFactory(param -> new ListCell<Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(Common.dictionaryManagement.getDatabase().get(item).getWord_target());
                }
            }
        });

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
                int current_word_index = listView.getSelectionModel().getSelectedItem();
                Word word = Common.dictionaryManagement.requireSearch(current_word_index);
                try {
                    switchToDictionaryPage(previousStage, null, pane, word);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /**
     * Display the list of words when looking up for a word.
     * - For example: type tra - display a list view of word beginning with tra.
     */
    protected void dictLookUp(ListView<Integer> listView, TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            listView.getItems().clear();
            if(newValue.isEmpty()) {
                listView.setVisible(false);
            } else {
                listView.setVisible(true);
                ArrayList<Integer> arrayList = Common.dictionaryManagement.requireLookUp(newValue);
                listView.getItems().addAll(arrayList);
            }
        });
    }

    /**
     * Set up each button with an image, width, height.
     */
    protected void ButtonSetUp(Button button, String filePath, double width, double height) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(filePath)));
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        button.setGraphic(imageView);
        button.setPrefWidth(width);
        button.setPrefHeight(height);
    }

    /**
     * Set up each button with an image, width, height, lay out x, y.
     */
    protected void ButtonSetUp(Button button, String filePath, double width, double height, double x, double y) {
        ButtonSetUp(button, filePath, width, height);
        button.setLayoutX(x);
        button.setLayoutY(y);
    }

    protected AnswerButton ButtonSetUp(AnswerButton answerButton, Button button, String filePath, double width,
                               double height, double x, double y, String text) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(filePath)));
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        Label label = new Label(text);
        label.setFont(Font.font("Cambria Math", 11));
        label.setWrapText(true);
        label.setPadding(new Insets(0, 10, 0, 10));

        // Create a StackPane to layer the image and text
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView, label);

        button.setGraphic(stackPane);
        button.setPrefWidth(width);
        button.setPrefHeight(height);
        button.setLayoutX(x);
        button.setLayoutY(y);

        answerButton.setButton(button);
        answerButton.setText(text);

        return answerButton;
    }
}
