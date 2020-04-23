package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.Player;
import view.GameView;

import static view.GameView.SIZE;

public class MapClickHandler implements EventHandler<MouseEvent> {

    private Player player;
    private GameView gameView;

    public MapClickHandler(Player player, GameView gameView) {
        this.player = player;
        this.gameView = gameView;
    }

    @Override
    public void handle(MouseEvent event) {
        if (gameView.isPlacing()) {
            player.addTower(gameView.getCurrentTowerType(), (int)event.getX()/SIZE, (int)event.getY()/SIZE);
            gameView.setPlacing(false);
            gameView.setCurrentTowerType(null);
        } else if (gameView.isSelling()) {
            player.sellTower((int)event.getX()/SIZE, (int)event.getY()/SIZE);
            gameView.setSelling(false);
        } else if (gameView.isUpgrading()) {
            gameView.setUpgrading(false);
        }
    }
}
