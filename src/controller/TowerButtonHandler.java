package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Game;
import model.Player;
import view.GameView;
import view.SelectButton;
import view.SelectPanel;

public class TowerButtonHandler implements EventHandler<ActionEvent> {

    private GameView gameView;
    private SelectButton selectButton;
    private Player player;

    public TowerButtonHandler(SelectButton selectButton, GameView gameView) {
        this.selectButton = selectButton;
        this.gameView = gameView;
        this.player = gameView.getGame().getPlayer();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (player.getGold() >= selectButton.getTowerType().getCost()) {
            gameView.setToPlacing(selectButton.getTowerType());
        }
    }
}
