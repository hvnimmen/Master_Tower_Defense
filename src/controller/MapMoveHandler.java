package controller;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import view.GameView;

import java.util.List;

import static view.GameView.*;

public class MapMoveHandler implements EventHandler<MouseEvent> {

    private GameView gameView;
    private Image normal = new Image("view/resources/crosshair.png", SIZE, SIZE, false, false);
    private Image selling = new Image("view/resources/sell_crosshair.png", SIZE, SIZE, false, false);
    private Image upgrading = new Image("view/resources/upgrade_crosshair.png", SIZE, SIZE, false, false);
    private ImageView crosshair = new ImageView(normal);
    private ImageView currentTower = new ImageView(selling);

    public MapMoveHandler(GameView gameView){
        this.gameView = gameView;
    }
    @Override
    public void handle(MouseEvent event) {
        if (gameView.isSelling()) {
            crosshair.setImage(selling);
        } else if (gameView.isUpgrading()) {
            crosshair.setImage(upgrading);
        } else {
            crosshair.setImage(normal);
            if (gameView.isPlacing()) {
                currentTower.setImage(gameView.getCurrentTowerType().getTurretImage());
                if (event.getX() < X_TILES * SIZE) {
                    currentTower.setLayoutX((int)Math.floor(event.getX()/SIZE)*SIZE);
                    currentTower.setLayoutY((int)Math.floor(event.getY()/SIZE)*SIZE);
                } else {
                    currentTower.setLayoutX(event.getX() - SIZE / 2);
                    currentTower.setLayoutY(event.getY() - SIZE / 2);
                }
                if(!gameView.getGamePane().getChildren().contains(currentTower)){
                    gameView.getGamePane().getChildren().add(currentTower);
                }
            } else {
                if(gameView.getGamePane().getChildren().contains(currentTower)){
                    gameView.getGamePane().getChildren().remove(currentTower);
                }
            }
        }
        if(event.getX() < X_TILES * SIZE){
            crosshair.setLayoutX((int)Math.floor(event.getX()/SIZE)*SIZE);
            crosshair.setLayoutY((int)Math.floor(event.getY()/SIZE)*SIZE);
        }
        if (!gameView.getGamePane().getChildren().contains(crosshair)){
            gameView.getGamePane().getChildren().add(crosshair);
        }
    }
}
