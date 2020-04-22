package model;

import javafx.scene.canvas.GraphicsContext;

import java.util.concurrent.CopyOnWriteArrayList;

public class WaveManager {

    private float timeSinceLastWave, spawnTime;
    private int waveNumber, enemiesPerWave;
    private Enemy enemy;
    private Wave currentWave;

    public WaveManager(Enemy enemy, float spawnTime, int enemiesPerWave){
        this.enemy = enemy;
        this.spawnTime = spawnTime;
        this.enemiesPerWave = enemiesPerWave;
        this.timeSinceLastWave = 0;
        this.waveNumber = 0;

        this.currentWave = new Wave(enemy, spawnTime, enemiesPerWave);
        this.currentWave.setEnemyList(new CopyOnWriteArrayList<Enemy>());
        this.currentWave.setWaveCompleted(true);
    }

//    for true randomizer
//    public WaveManager(Enemy[] enemy, float spawnTime, int enemiesPerWave){
//        this.enemy = enemy;
//        this.spawnTime = spawnTime;
//        this.enemiesPerWave = enemiesPerWave;
//        this.timeSinceLastWave = 0;
//        this.waveNumber = 0;
//
//        this.currentWave = null;
//
//        newWave();
//    }

//    public void update(GraphicsContext gc) {
//        if (currentWave != null) {
//            if (!currentWave.isCompleted()) {
//                currentWave.update(gc);
//            }
//        }
//    }

    public void update() {
        if (!currentWave.isCompleted()) {
            currentWave.update();
        } else {
            newWave();
        }
    }

    public void newWave() {
        currentWave = new Wave(enemy, spawnTime, enemiesPerWave);
        waveNumber++;
        enemiesPerWave += 2;
        spawnTime *= 0.75;
    }

    public Wave getCurrentWave() {
        return currentWave;
    }

    public int getWaveNumber() { return waveNumber; }

}
