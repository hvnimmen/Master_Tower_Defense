package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import controller.*;
import model.*;

import java.util.concurrent.CopyOnWriteArrayList;

public class GameView {

    public static final int SIZE = 48;

    public static final int X_TILES = 18;
    public static final int Y_TILES = 12;

    public static final int MENU_X_TILES = 4;

    private Game game;
    private Player player;

    private BorderPane gamePane;
    private Pane mapPane;
    private VBox select;
    private Scene gameScene;
    private Stage gameStage;
    private SelectPanel topPanel;
    private InfoLabel midPanel, botPanel1;
    private Pane botPanel2;
    private Button waveButton;
    private Text waveText, lossText;

    private MapMoveHandler mapMoveHandler;
    private MapClickHandler mapClickHandler;
    private GameQuitHandler gameQuitHandler;

    private TowerType currentTowerType;
    private boolean selling, upgrading, placing;

    public GameView(Game game) {
        this.game = game;
        this.player = game.getPlayer();
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
        gamePane.setBackground(new Background(new BackgroundImage(TileType.Grass.getImage(), BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null)));
        gameScene = new Scene(gamePane, (X_TILES + MENU_X_TILES) * SIZE, Y_TILES * SIZE);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
        gameStage.show();
    }

    private void initializeListeners() {
        mapMoveHandler = new MapMoveHandler(this);
        gamePane.setOnMouseMoved(mapMoveHandler);

        mapClickHandler = new MapClickHandler(game.getPlayer(), this);
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

        topPanel = new SelectPanel(4 * SIZE, 4 * SIZE);
        topPanel.quickAdd(new SelectButton(TowerType.Turret, new Image("view/resources/green_turret.png", SIZE, SIZE, false, false), this ));
        topPanel.quickAdd(new SelectButton(TowerType.Flaming, new Image("view/resources/red_turret.png", SIZE, SIZE, false, false), this));
        topPanel.quickAdd(new SelectButton(TowerType.Freezing, new Image("view/resources/blue_turret.png", SIZE, SIZE, false, false), this));
        topPanel.quickAdd(new SelectButton(TowerType.Launcher, new Image("view/resources/rocket_launcher.png", SIZE, SIZE, false, false), this));
        topPanel.quickAdd(new SelectButton("upgrade", new Image("view/resources/upgrade.png", SIZE, SIZE, false, false), this));
        topPanel.quickAdd(new SelectButton("sell", new Image("view/resources/sell.png", SIZE, SIZE, false, false), this));

        midPanel = new InfoLabel(4 * SIZE, 4 * SIZE, "");
        midPanel.setTextAlignment(TextAlignment.CENTER);
        midPanel.setAlignment(Pos.CENTER);

        botPanel1 = new InfoLabel(4 * SIZE, 2 * SIZE, "");
        botPanel1.setTextAlignment(TextAlignment.CENTER);
        botPanel1.setAlignment(Pos.CENTER);

        waveText = new Text(0, SIZE * 0.75, "Wave : " + game.getWaveManager().getWaveNumber());
        waveText.setTextAlignment(TextAlignment.CENTER);
        waveText.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, SIZE*0.35));
        waveText.setWrappingWidth(4 * SIZE);

        waveButton = new WaveButton("Send wave", (int)(SIZE * 2.15), (int)(SIZE * 0.7));
        waveButton.setLayoutX(2 * SIZE - waveButton.getPrefWidth() / 2);
        waveButton.setLayoutY(1.25 * SIZE - waveButton.getPrefHeight() / 2);
        waveButton.setOnMouseClicked(new WaveButtonHandler(game));

        botPanel2 = new Pane();
        botPanel2.getChildren().addAll(new ImageView(new Image("view/resources/metal_panel_half.png", 4 * SIZE,
                2 * SIZE, false, false)), waveText, waveButton);

        select.setBackground(new Background(new BackgroundImage(new Image("view/resources/grass_tile.png", SIZE,
                SIZE, false, false), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT, null)));

        select.getChildren().addAll(topPanel, midPanel, botPanel1, botPanel2);
    }

    public void updateEnemies(CopyOnWriteArrayList<Enemy> enemyList){
        for (Enemy e : enemyList) {

            ImageView shadow = e.getShadowImageView();
            shadow.setLayoutX(e.getDisplayX() + SIZE * 0.5);
            shadow.setLayoutY(e.getDisplayY() + SIZE * 0.25);

            ImageView enemy = e.getImageView();
            enemy.setLayoutX(e.getDisplayX());
            enemy.setLayoutY(e.getDisplayY());

            ImageView hpBackground = e.getHealthBackground();
            int offset = e.getOffset();
            hpBackground.setLayoutX(e.getDisplayX() + offset);
            hpBackground.setLayoutY(e.getDisplayY());

            ImageView hpForeground = e.getHealthForeground();
            hpForeground.setLayoutX(e.getDisplayX() + offset);
            hpForeground.setLayoutY(e.getDisplayY());

            ImageView hpBorder = e.getHealthBorder();
            hpBorder.setLayoutX(e.getDisplayX() + offset);
            hpBorder.setLayoutY(e.getDisplayY());

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
        waveText.setText("Wave : " + game.getWaveManager().getWaveNumber());
        botPanel1.setText("Lives : " + player.getHP() + "\nGold : " + player.getGold() + "\nScore: " + player.getScore());
    }

    public void displayLoss() {

        lossText = new Text(0, (Y_TILES * 0.5 - 0.5) * SIZE, "You have lost\nFinal Score: " + player.getScore());
        lossText.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, SIZE * 1.5));
        lossText.setFill(Color.RED);
        lossText.setTextAlignment(TextAlignment.CENTER);
        lossText.setWrappingWidth(X_TILES * SIZE);

        gamePane.getChildren().add(lossText);

        disableListeners();
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

    public void setPlacing(boolean placing) {
        this.placing = placing;
    }

    public boolean isPlacing() {
        return this.placing;
    }

    public Game getGame() {
        return this.game;
    }

    public InfoLabel getMidPanel() {
        return this.midPanel;
    }

    public void setToSelling() {
        selling = true;
        upgrading = false;
        placing = false;
        currentTowerType = null;
    }

    public void setToUpgrading() {
        upgrading = true;
        placing = false;
        currentTowerType = null;
        selling = false;
    }

    public void setToPlacing(TowerType towerType) {
        placing = true;
        currentTowerType = towerType;
        selling = false;
        upgrading = false;
    }

    public void disableListeners() {
        gamePane.setOnMouseMoved(null);

        gamePane.setOnMouseClicked(null);

    }
}
