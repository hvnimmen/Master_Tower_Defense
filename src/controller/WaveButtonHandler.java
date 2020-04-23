package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import model.*;

public class WaveButtonHandler implements EventHandler<MouseEvent> {

    private Game game;

    public WaveButtonHandler(Game game) {
        this.game = game;
    }

    @Override
    public void handle(MouseEvent event) {
        if (game.getWaveManager().getCurrentWave().isCompleted()) {
            game.getWaveManager().newWave();
        }
    }
}
