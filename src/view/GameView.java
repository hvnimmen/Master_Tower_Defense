package view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import controller.*;
import model.*;

import java.util.concurrent.CopyOnWriteArrayList;

import static model.Leveller.LoadMap;

public class GameView {

    public static final int SIZE = 64;

    protected static final int X_TILES = 18;
    protected static final int Y_TILES = 12;

    protected static final int MENU_X_TILES = 4;

    private Game game;

    private BorderPane gamePane;
    private Pane mapPane;
    private VBox select;
    private Scene gameScene;
    private Stage gameStage;

    private GameQuitHandler gameQuitHandler;

    public GameView(Game game) {

        this.game = game;

        initializeStage();

        addQuitHandler();

        drawMap();

        drawUI();

    }

    private void initializeStage() {
        gamePane = new BorderPane();
        mapPane = new Pane();
        select = new VBox();
        gamePane.setCenter(mapPane);
        gamePane.setRight(select);
        gameScene = new Scene(gamePane, (X_TILES + MENU_X_TILES) * SIZE, Y_TILES * SIZE);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
        gameStage.show();
    }

    private void drawMap() {
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 12; j++) {
                ImageView tileImage = new ImageView(game.getGrid().map[i][j].getType().getImage());
                tileImage.setLayoutX(i*SIZE);
                tileImage.setLayoutY(j*SIZE);
                gamePane.getChildren().add(tileImage);
            }
        }
    }

    private void addQuitHandler() {
        this.gameQuitHandler = new GameQuitHandler(game);
        gameStage.setOnCloseRequest(gameQuitHandler);
    }

    private void drawUI(){
        ImageView topPanel = new ImageView(new Image("view/resources/light_beige_panel.png", 4*SIZE, 4*SIZE, false, false));
        ImageView midPanel = new ImageView(new Image("view/resources/beige_panel.png", 4*SIZE, 4*SIZE, false, false));
        ImageView botPanel = new ImageView(new Image("view/resources/brown_panel.png", 4*SIZE, 4*SIZE, false, false));
        select.setBackground(new Background(new BackgroundImage(new Image("view/resources/grass_tile.png", SIZE,
                SIZE, false, false), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT, null)));
        select.getChildren().addAll(topPanel, midPanel, botPanel);
    }

    public void updateEnemies(CopyOnWriteArrayList<Enemy> enemyList){
        for (Enemy e : enemyList) {
            e.getHealthBackground().setLayoutX(e.getDisplayX());
            e.getHealthBackground().setLayoutY(e.getDisplayY());

            e.getHealthForeground().setLayoutX(e.getDisplayX());
            e.getHealthForeground().setLayoutY(e.getDisplayY());

            e.getHealthBorder().setLayoutX(e.getDisplayX());
            e.getHealthBorder().setLayoutY(e.getDisplayY());

            e.getImageView().setLayoutX(e.getDisplayX());
            e.getImageView().setLayoutY(e.getDisplayY());
            if (!gamePane.getChildren().contains(e.getImageView())){
                gamePane.getChildren().addAll(e.getImageView(), e.getHealthBackground(), e.getHealthForeground(), e.getHealthBorder());
            }
            if (!e.isAlive()){
                gamePane.getChildren().removeAll(e.getImageView(), e.getHealthBackground(), e.getHealthForeground(), e.getHealthBorder());
            }
        }
    }

}
