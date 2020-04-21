package view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import controller.*;
import model.*;

import static model.Leveller.LoadMap;

public class GameView {

    protected static final int TILE_SIZE = 48;

    protected static final int X_TILES = 18;
    protected static final int Y_TILES = 12;

    protected static final int MENU_X_TILES = 4;

    private Game game;

    private BorderPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    private GameQuitHandler gameQuitHandler;

    static TileGrid map = LoadMap("src/model/map");

    public GameView(Game game) {
        this.game = game;
        initializeStage();
        gameStage.show();
        this.gameQuitHandler = new GameQuitHandler(game);
//        gamePane.getChildren().add(new ImageView(new Image("view/resources/grass_tile.png")));
        drawMap();
        gameStage.setOnCloseRequest(gameQuitHandler);
    }

    private void initializeStage() {
        gamePane = new BorderPane();
        gameScene = new Scene(gamePane, (X_TILES + MENU_X_TILES) * TILE_SIZE, Y_TILES * TILE_SIZE);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }

    private void drawMap() {
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 12; j++) {
                ImageView tileImage = new ImageView(map.map[i][j].getType().getImage());
                tileImage.setLayoutX(i*48);
                tileImage.setLayoutY(j*48);
                gamePane.getChildren().add(tileImage);
            }
        }
    }

}
