package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Game;
import view.GameView;
import view.SelectButton;
import view.SelectPanel;

public class TowerButtonHandler implements EventHandler<ActionEvent> {

    private GameView gameView;
    private SelectButton selectButton;

    public TowerButtonHandler(SelectButton selectButton, GameView gameView) {
        this.selectButton = selectButton;
        this.gameView = gameView;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        gameView.setCurrentTowerType(selectButton.getTowerType());
        gameView.setUpgrading(false);
        gameView.setSelling(false);
    }
}
