package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import controller.*;
import model.*;

public class MainMenuView {

    private static final int WIDTH = 1024;
    private static final int HEIGHT = 768;

    private MainMenu mainMenu;

    private AnchorPane mainPane;
    private Stage mainStage;

    private List<Button> mainMenuButtons;

    public MainMenuView(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        this.mainMenuButtons = new ArrayList<>();
        initializeStage();
        createButtons();
    }

    private void initializeStage() {

        mainPane = new AnchorPane();
        Scene mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        mainStage.setTitle("Tower Defense : Main Menu");
        mainStage.show();

    }

    private void createButtons() {
        createPlayButton();
        createEditorButton();
        createQuitButton();
    }

    private void createPlayButton() {
        Button playButton = new Button("PLAY");
        addMainMenuButton(playButton);

        PlayButtonHandler playButtonHandler = new PlayButtonHandler(mainMenu);
        playButton.setOnMouseClicked(playButtonHandler);
    }

    private void createEditorButton() {
        Button editorButton = new Button("EDITOR");
        addMainMenuButton(editorButton);

        EditorButtonHandler editorButtonHandler = new EditorButtonHandler();
        editorButton.setOnMouseClicked(editorButtonHandler);
    }

    private void createQuitButton() {
        Button quitButton = new Button("QUIT");
        addMainMenuButton(quitButton);

        QuitButtonHandler quitButtonHandler = new QuitButtonHandler();
        quitButton.setOnMouseClicked(quitButtonHandler);
    }

    private void addMainMenuButton(Button button) {
        button.setLayoutX(0);
        button.setLayoutY(mainMenuButtons.size() * 25);
        mainMenuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    public Stage getMainStage() {
        return this.mainStage;
    }

}
