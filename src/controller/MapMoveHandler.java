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

    public MapMoveHandler(GameView gameView){
        this.gameView = gameView;
    }
    @Override
    public void handle(MouseEvent event) {
        if (gameView.isSelling()) {
            crosshair.setImage(selling) ;
        } else if (gameView.isUpgrading()) {
            crosshair.setImage(upgrading);
        } else if (gameView.getCurrentTowerType() != null) {
            crosshair.setImage(normal);
        } else {
            crosshair.setImage(normal);
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
