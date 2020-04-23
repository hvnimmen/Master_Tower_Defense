package model;

import javafx.scene.canvas.GraphicsContext;
import view.GameView;

import java.util.concurrent.CopyOnWriteArrayList;

public class Player {

    private TileGrid grid;
    private WaveManager waveManager;
    private Game game;
    private CopyOnWriteArrayList<Tower> towerList;
    static int hp, gold, score;

    public Player(TileGrid grid, WaveManager waveManager, Game game) {
        this.grid = grid;
        this.waveManager = waveManager;
        this.towerList = new CopyOnWriteArrayList<Tower>();
        this.game = game;
        setup();
    }

    public void setup(){
        gold = 50;
        hp = 20;
        score = 0;
    }

    public static boolean changeGold(int change) {
        if (gold + change >= 0) {
            gold += change;
            if (change > 0)
                score += change;
            return true;
        }
        return false;
    }

    public static void changeHP(int change) {
        hp += change;
    }

    public void update(){
        for (Tower t : towerList){
            t.update();
            t.updateEnemyList(waveManager.getCurrentWave().getEnemyList());
        }
    }

    public void addTower(TowerType towerType, int x, int y){
        Tile currentTile = grid.getTile(x, y);
        switch (towerType) {
            case Turret:
                if (!currentTile.isOccupied() && changeGold(-towerType.cost)) {
                    towerList.add(new ArcherTower(towerType, grid.getTile(x, y), waveManager.getCurrentWave().getEnemyList()));
                    currentTile.setOccupied(true);
                }
            case Freezing:
                if (!currentTile.isOccupied() && changeGold(-towerType.cost)) {
                    towerList.add(new FreezeTower(towerType, grid.getTile(x, y), waveManager.getCurrentWave().getEnemyList()));
                    currentTile.setOccupied(true);
                }
                break;
            case Flaming:
                if (!currentTile.isOccupied() && changeGold(-towerType.cost)) {
                    towerList.add(new FlamingTower(towerType, grid.getTile(x, y), waveManager.getCurrentWave().getEnemyList()));
                    currentTile.setOccupied(true);
                }
                break;
            case Launcher:
                if (!currentTile.isOccupied() && changeGold(-towerType.cost)) {
                    towerList.add(new SniperTower(towerType, grid.getTile(x, y), waveManager.getCurrentWave().getEnemyList()));
                    currentTile.setOccupied(true);
                }
                break;
        }
    }

    public void sellTower(int x, int y) {
        for (Tower t : towerList) {
            if (t.getX() == x && t.getY() == y) {
                game.getGameView().getGamePane().getChildren().removeAll(t.getBaseImageView(), t.getTurretImageView());
                grid.getTile(x, y).setOccupied(false);
                towerList.remove(t);
                gold += t.getCost() / 2;
            }
        }
    }

    public void upgradeTower(int x, int y) {
        for (Tower t : towerList) {
            if (t.getX() == x && t.getY() == y) {
                if (t.getLevel() < 3) {
                    t.levelUp();
                    gold -= t.getCost();
                }
            }
        }
    }

    public TileGrid getGrid() {
        return grid;
    }

    public void setGrid(TileGrid grid) {
        this.grid = grid;
    }

    public CopyOnWriteArrayList<Tower> getTowerList() {
        return towerList;
    }

    public void setTowerList(CopyOnWriteArrayList<Tower> towerList) {
        this.towerList = towerList;
    }

    public int getGold() {
        return gold;
    }

    public int getHP() {
        return hp;
    }
}
