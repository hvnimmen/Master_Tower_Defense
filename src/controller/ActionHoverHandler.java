package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.TowerType;
import view.GameView;
import view.SelectButton;

public class ActionHoverHandler implements EventHandler<MouseEvent> {

    private GameView gameView;
    private SelectButton selectButton;

    public ActionHoverHandler(SelectButton selectButton, GameView gameView) {
        this.selectButton = selectButton;
        this.gameView = gameView;
    }

    @Override
    public void handle(MouseEvent event) {
        if (selectButton.getName().equals("sell")) {
            gameView.getMidPanel().setText("Sell\nSelect a tower to\nsell for half price");
        } else if (selectButton.getName().equals("upgrade")) {
            gameView.getMidPanel().setText("Upgrade\nImprove a Tower\nUpgrade 1: Double\ndamage\nUpgrade 2: Half\ncooldown, double\nprojectile speed");
        }
    }
}
