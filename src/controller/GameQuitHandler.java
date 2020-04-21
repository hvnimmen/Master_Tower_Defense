package controller;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

import model.*;

public class GameQuitHandler implements EventHandler<WindowEvent> {

    private Game game;

    public GameQuitHandler(Game game) {
        this.game = game;
    }
    @Override
    public void handle(WindowEvent windowEvent) {
        game.exitGame();
    }
}
