package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import controller.*;

public class MainMenuView {

    private static final int WIDTH = 1024;
    private static final int HEIGHT = 768;

    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private List<Button> mainMenuButtons;

    private PlayButtonHandler playButtonHandler;
    private EditorButtonHandler editorButtonHandler;
    private QuitButtonHandler quitButtonHandler;

    public MainMenuView() {

        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        mainStage.setTitle("Tower Defense : Main Menu");

        mainMenuButtons = new ArrayList<>();

        playButtonHandler = new PlayButtonHandler();
        editorButtonHandler = new EditorButtonHandler();
        quitButtonHandler = new QuitButtonHandler();

        createButtons();

    }

    private void createButtons() {
        createPlayButton();
        createEditorButton();
        createQuitButton();
    }

    private void createPlayButton() {
        Button playButton = new Button("PLAY");
        addMainMenuButton(playButton);

        playButton.setOnMouseClicked(playButtonHandler);
    }

    private void createEditorButton() {
        Button editorButton = new Button("EDITOR");
        addMainMenuButton(editorButton);

        editorButton.setOnMouseClicked(editorButtonHandler);
    }

    private void createQuitButton() {
        Button quitButton = new Button("QUIT");
        addMainMenuButton(quitButton);

        quitButton.setOnMouseClicked(quitButtonHandler);
    }

    private void addMainMenuButton(Button button) {
        button.setLayoutX(0);
        button.setLayoutY(mainMenuButtons.size() * 25);
        mainMenuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    public Stage getMainStage() {
        return mainStage;
    }

}
