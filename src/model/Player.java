package model;

import javafx.scene.canvas.GraphicsContext;

import java.util.concurrent.CopyOnWriteArrayList;

public class Player {

    private TileGrid grid;
    private WaveManager waveManager;
    private CopyOnWriteArrayList<Tower> towerList;
    static int hp, gold, score;
    private Tower tempTower;
    private boolean holdingTower, selling, upgrading;

    public Player(TileGrid grid, WaveManager waveManager) {
        this.grid = grid;
        this.waveManager = waveManager;
        this.towerList = new CopyOnWriteArrayList<Tower>();
        this.holdingTower = false;
        this.tempTower = null;
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

    public void update(GraphicsContext gc){
        for (Tower t : towerList){
            t.update();
            t.updateEnemyList(waveManager.getCurrentWave().getEnemyList());
        }
    }

    public void addTower(int x, int y){
        Tile currentTile = grid.getTile(x, y);
        System.out.println(tempTower.getTowerType());
        switch(tempTower.getTowerType()){
            case Archer:
                if (!currentTile.isOccupied() && changeGold(-tempTower.getCost())) {
                    towerList.add(new ArcherTower(TowerType.Archer, grid.getTile(x, y), waveManager.getCurrentWave().getEnemyList()));
                    currentTile.setOccupied(true);
                    holdingTower = false;
                    tempTower = null;
                }
                break;
            case Freeze:
                if (!currentTile.isOccupied() && changeGold(-tempTower.getCost())){
                    towerList.add(new FreezeTower(TowerType.Freeze, grid.getTile(x, y), waveManager.getCurrentWave().getEnemyList()));
                    currentTile.setOccupied(true);
                    holdingTower = false;
                    tempTower = null;
                }
                break;
            case Flaming:
                if (!currentTile.isOccupied() &&  changeGold(-tempTower.getCost())){
                    towerList.add(new FlamingTower(TowerType.Flaming, grid.getTile(x, y), waveManager.getCurrentWave().getEnemyList()));
                    currentTile.setOccupied(true);
                    holdingTower = false;
                    tempTower = null;
                }
                break;
            case Sniper:
                if (!currentTile.isOccupied() && changeGold(-tempTower.getCost())){
                    towerList.add(new SniperTower(TowerType.Sniper, grid.getTile(x, y), waveManager.getCurrentWave().getEnemyList()));
                    currentTile.setOccupied(true);
                    holdingTower = false;
                    tempTower = null;
                }
                break;
        }
    }

    public TileGrid getGrid() {
        return grid;
    }

    public void setGrid(TileGrid grid) {
        this.grid = grid;
    }

    public void selectTower(Tower t){
        tempTower = t;
        holdingTower = true;
    }

    public boolean isHoldingTower() {
        return holdingTower;
    }

    public void setHoldingTower(boolean b) {
        this.holdingTower = b;
    }

    public Tower getTempTower() {
        return tempTower;
    }

    public void setTempTower(Tower t) { this.tempTower = t; }

    public boolean isSelling() {
        return selling;
    }

    public void setSelling(boolean selling) {
        this.selling = selling;
    }

    public CopyOnWriteArrayList<Tower> getTowerList() {
        return towerList;
    }

    public void setTowerList(CopyOnWriteArrayList<Tower> towerList) {
        this.towerList = towerList;
    }

    public boolean isUpgrading() {
        return upgrading;
    }

    public void setUpgrading(boolean upgrading) {
        this.upgrading = upgrading;
    }
}
