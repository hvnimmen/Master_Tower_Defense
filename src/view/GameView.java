package view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import controller.*;
import model.*;

import java.util.concurrent.CopyOnWriteArrayList;

public class GameView {

    public static final int SIZE = 64;

    public static final int X_TILES = 18;
    public static final int Y_TILES = 12;

    public static final int MENU_X_TILES = 4;

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

        initializeListeners();

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

    private void initializeListeners() {
        gamePane.setOnMouseMoved(new MapMoveHandler(this));
        gamePane.setOnMouseClicked(new MapClickHandler(game.getPlayer()));
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
        SelectPanel topPanel = new SelectPanel(4 * SIZE, 3 * SIZE);
        topPanel.quickAdd("turret", new Image("view/resources/green_turret.png"));
        topPanel.quickAdd("flaming", new Image("view/resources/red_turret.png"));
        topPanel.quickAdd("freeze", new Image("view/resources/blue_turret.png"));
        topPanel.quickAdd("launcher", new Image("view/resources/rocket_launcher.png"));
        topPanel.quickAdd("upgrade", new Image("view/resources/upgrade.png"));
        topPanel.quickAdd("sell", new Image("view/resources/sell.png"));

        InfoLabel midPanel = new InfoLabel("Lorem ipsum");

        ImageView botPanel = new ImageView(new Image("view/resources/metal_panel.png", 4*SIZE, 4*SIZE, false, false));

        select.setBackground(new Background(new BackgroundImage(new Image("view/resources/grass_tile.png", SIZE,
                SIZE, false, false), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT, null)));
        select.getChildren().addAll(topPanel, midPanel, botPanel);
    }

    public void updateEnemies(CopyOnWriteArrayList<Enemy> enemyList){
        for (Enemy e : enemyList) {

            e.getShadowImageView().setLayoutX(e.getDisplayX() + SIZE/2);
            e.getShadowImageView().setLayoutY(e.getDisplayY() + SIZE/4);

            e.getImageView().setLayoutX(e.getDisplayX());
            e.getImageView().setLayoutY(e.getDisplayY());

            e.getHealthBackground().setLayoutX(e.getDisplayX());
            e.getHealthBackground().setLayoutY(e.getDisplayY());

            e.getHealthForeground().setLayoutX(e.getDisplayX());
            e.getHealthForeground().setLayoutY(e.getDisplayY());

            e.getHealthBorder().setLayoutX(e.getDisplayX());
            e.getHealthBorder().setLayoutY(e.getDisplayY());

            if (!gamePane.getChildren().contains(e.getImageView())){
                gamePane.getChildren().addAll(e.getShadowImageView(), e.getImageView(), e.getHealthBackground(), e.getHealthForeground(), e.getHealthBorder());
            }
            if (!e.isAlive()){
                gamePane.getChildren().removeAll(e.getShadowImageView(), e.getImageView(), e.getHealthBackground(), e.getHealthForeground(), e.getHealthBorder());
            }
        }
    }

    public void updateTowersAndProjectiles(CopyOnWriteArrayList<Tower> towerList) {
        for (Tower t : towerList) {
            t.getTurretImageView().setRotate(t.getAngle());
            if (!gamePane.getChildren().contains(t.getBaseImageView())) {
                gamePane.getChildren().addAll(t.getBaseImageView(), t.getTurretImageView());
            }
            for (Projectile p : t.getProjectiles()) {
                p.getImageView().setRotate(p.getAngle());
                p.getImageView().setLayoutX(p.getDisplayX());
                p.getImageView().setLayoutY(p.getDisplayY());
                if (!gamePane.getChildren().contains(p.getImageView())) {
                    gamePane.getChildren().add(p.getImageView());
                }
                if (p.HasCollided()) {
                    gamePane.getChildren().remove(p.getImageView());
                }
            }
        }
    }

    public Pane getGamePane() {
        return this.gamePane;
    }

}
