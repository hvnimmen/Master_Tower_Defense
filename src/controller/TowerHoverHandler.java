package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.TowerType;
import view.GameView;
import view.SelectButton;

public class TowerHoverHandler implements EventHandler<MouseEvent> {

    private GameView gameView;
    private SelectButton selectButton;

    public TowerHoverHandler(SelectButton selectButton, GameView gameView) {
        this.selectButton = selectButton;
        this.gameView = gameView;
    }

    @Override
    public void handle(MouseEvent event) {
        TowerType towerType = selectButton.getTowerType();
        String name = towerType.toString();
        int damage = towerType.getDamage();
        int cost = towerType.getCost();
        int range = towerType.getRange();
        float cooldown = towerType.getCooldown();
        String effect = towerType.getEffect();
        gameView.getMidPanel().setText(name + "\nCost : " + cost + "\nDamage : " + damage + "\nCooldown : " + cooldown
                + "\nRange : " + range + "\nEffect : " + effect);
    }
}
