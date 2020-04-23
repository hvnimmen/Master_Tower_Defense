package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Game;
import view.GameView;
import view.SelectButton;

public class ActionButtonHandler implements EventHandler<ActionEvent> {

    private SelectButton selectButton;
    private GameView gameView;

    public ActionButtonHandler(SelectButton selectButton, GameView gameView) {
        this.selectButton = selectButton;
        this.gameView = gameView;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (selectButton.getName().equals("sell")) {
            System.out.println("selling");
            gameView.setSelling(true);
            gameView.setUpgrading(false);
            gameView.setPlacing(false);
            gameView.setCurrentTowerType(null);
        } else if (selectButton.getName().equals("upgrade")) {
            System.out.println("upgrading");
            gameView.setUpgrading(true);
            gameView.setSelling(false);
            gameView.setPlacing(false);
            gameView.setCurrentTowerType(null);
        }
    }
}
