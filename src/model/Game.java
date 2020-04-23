package model;

import javafx.animation.AnimationTimer;
import view.*;

import static model.Leveller.LoadMap;

public class Game {

    private MainMenu mainMenu;
    private GameView gameView;
    private WaveManager waveManager;
    private Player player;
    private AnimationTimer gameLoop;

    static TileGrid grid;

    public Game(MainMenu mainMenu, String fileURL){
        this.mainMenu = mainMenu;
        try {
            this.grid = LoadMap(fileURL);
        } catch (Exception e) {
            this.grid = LoadMap("src/model/map");
        }
        startGame();
        waveManager = new WaveManager(new Enemy(EnemyType.Random, grid.getTile(0, 10), grid), 1, 20);
        player = new Player(grid, waveManager, this);
        this.gameView = new GameView(this);
    }

    private void startGame() {


        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {

                if (player.getHP() > 0) {
                    Clock.update();
                    waveManager.update();
                    player.update();

                    gameView.updateEnemies(waveManager.getCurrentWave().getEnemyList());
                    gameView.updateTowersAndProjectiles(player.getTowerList());
                    gameView.updatePlayerInfo();

                    for (Enemy e: waveManager.getCurrentWave().getEnemyList()) {
                        System.out.print(e.getType().toString());
                    }
                    System.out.println("");
                } else {
                    gameLoop.stop();
                    gameView.displayLoss();
                }
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

    public GameView getGameView() {
        return this.gameView;
    }

}
