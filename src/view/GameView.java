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
    private SelectPanel topPanel;
    private InfoLabel midPanel;
    private ImageView botPanel;

    private MapMoveHandler mapMoveHandler;
    private MapClickHandler mapClickHandler;
    private GameQuitHandler gameQuitHandler;

    private TowerType currentTowerType;
    private boolean selling, upgrading;

    public GameView(Game game) {
        this.game = game;
        initializeStage();
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
        mapMoveHandler = new MapMoveHandler(this);
        gamePane.setOnMouseMoved(mapMoveHandler);

        mapClickHandler = new MapClickHandler(game.getPlayer());
        gamePane.setOnMouseClicked(mapClickHandler);

        gameQuitHandler = new GameQuitHandler(game);
        gameStage.setOnCloseRequest(gameQuitHandler);
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

    private void drawUI(){

        topPanel = new SelectPanel(4 * SIZE, 3 * SIZE);
        topPanel.quickAdd(new SelectButton(TowerType.Turret, new Image("view/resources/green_turret.png"), this ));
        topPanel.quickAdd(new SelectButton(TowerType.Flaming, new Image("view/resources/red_turret.png"), this));
        topPanel.quickAdd(new SelectButton(TowerType.Freezing, new Image("view/resources/blue_turret.png"), this));
        topPanel.quickAdd(new SelectButton(TowerType.Launcher, new Image("view/resources/rocket_launcher.png"), this));
        topPanel.quickAdd(new SelectButton("upgrade", new Image("view/resources/upgrade.png"), this));
        topPanel.quickAdd(new SelectButton("sell", new Image("view/resources/sell.png"), this));

        midPanel = new InfoLabel("Gold : " + Player.getGold() + "\nWave : " + game.getWaveManager().getWaveNumber()
                + "\n Lives : " + Player.getHP());

        botPanel = new ImageView(new Image("view/resources/metal_panel.png", 4*SIZE, 4*SIZE, false, false));

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

    public void updatePlayerInfo() {
        midPanel.setText("Gold : " + Player.getGold() + "\nWave : " + game.getWaveManager().getWaveNumber()
                + "\n Lives : " + Player.getHP());
    }

    public TowerType getCurrentTowerType() {
        return currentTowerType;
    }

    public void setCurrentTowerType(TowerType currentTowerType) {
        this.currentTowerType = currentTowerType;
    }

    public boolean isSelling() {
        return selling;
    }

    public void setSelling(boolean selling) {
        this.selling = selling;
    }

    public boolean isUpgrading() {
        return upgrading;
    }

    public void setUpgrading(boolean upgrading) {
        this.upgrading = upgrading;
    }

    public Pane getGamePane() {
        return this.gamePane;
    }
}
