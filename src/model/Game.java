package model;

import javafx.animation.AnimationTimer;
import view.*;

import static model.Leveller.LoadMap;

public class Game {

    private MainMenu mainMenu;
    private GameView gameView;
    private WaveManager waveManager;
    private AnimationTimer gameLoop;

    static TileGrid grid = LoadMap("src/model/map");

    public Game(MainMenu mainMenu){
        this.mainMenu = mainMenu;
        this.gameView = new GameView(this);
        startGame();
    }

    private void startGame() {
        waveManager = new WaveManager(new Enemy(EnemyType.Random, grid.getTile(0, 10), grid), 0.5f, 1);
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                Clock.update();
                waveManager.update();
                gameView.updateEnemies(waveManager.getCurrentWave().getEnemyList());
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

}
