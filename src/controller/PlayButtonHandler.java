package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import model.*;

public class PlayButtonHandler implements EventHandler<MouseEvent> {

    private MainMenu mainMenu;

    public PlayButtonHandler(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    @Override
    public void handle(MouseEvent event) {
        mainMenu.openNewGame();
    }
}
