package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import model.*;

public class PlayButtonHandler implements EventHandler<MouseEvent> {

    private MainMenu mainMenu;
    private String fileURL;

    public PlayButtonHandler(MainMenu mainMenu, String fileURL) {
        this.mainMenu = mainMenu;
        this.fileURL = fileURL;
    }

    @Override
    public void handle(MouseEvent event) {
        mainMenu.openNewGame(fileURL);
    }
}
