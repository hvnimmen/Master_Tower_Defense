package controller;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import view.GameView;

import static view.GameView.*;

public class MapMoveHandler implements EventHandler<MouseEvent> {

    private GameView gameView;
    private ImageView imageView = new ImageView(new Image("view/resources/crosshair.png", SIZE, SIZE, false, false));

    public MapMoveHandler(GameView gameView){
        this.gameView = gameView;
    }
    @Override
    public void handle(MouseEvent event) {
        if(event.getX() < X_TILES * SIZE){
            imageView.setLayoutX((int)Math.floor(event.getX()/SIZE)*SIZE);
            imageView.setLayoutY((int)Math.floor(event.getY()/SIZE)*SIZE);
        }
        if (!gameView.getGamePane().getChildren().contains(imageView)){
            gameView.getGamePane().getChildren().add(imageView);
        }
    }
}
