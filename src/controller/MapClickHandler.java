package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.Player;

import static view.GameView.SIZE;

public class MapClickHandler implements EventHandler<MouseEvent> {

    private Player player;

    public MapClickHandler(Player player) {
        this.player = player;
    }

    @Override
    public void handle(MouseEvent event) {
        player.addTower((int)event.getX()/SIZE, (int)event.getY()/SIZE);
    }
}
