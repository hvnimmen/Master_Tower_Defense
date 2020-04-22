package model;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import view.*;

import static model.Leveller.LoadMap;

public class Game {

    private MainMenu mainMenu;
    private GameView gameView;
    private WaveManager waveManager;
    private Player player;
    private AnimationTimer gameLoop;

    static TileGrid grid = LoadMap("src/model/map");

    public Game(MainMenu mainMenu){
        this.mainMenu = mainMenu;
        startGame();
        this.gameView = new GameView(this);
    }

    private void startGame() {

        waveManager = new WaveManager(new Enemy(EnemyType.Random, grid.getTile(0, 10), grid), 0.5f, 1);
        player = new Player(grid, waveManager);

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                Clock.update();
                waveManager.update();
                player.update();

                gameView.updateEnemies(waveManager.getCurrentWave().getEnemyList());
                gameView.updateTowersAndProjectiles(player.getTowerList());
                gameView.updatePlayerInfo();
            }
        };
        gameLoop.start();
    }

    public void exitGame(){
        gameLoop.stop();
        mainMenu.getMainMenuView().getMainStage().show();
    }

    public TileGrid getGrid() {
        return this.grid;
    }

    public Player getPlayer() {
        return this.player;
    }

    public WaveManager getWaveManager() {
        return this.waveManager;
    }

}
