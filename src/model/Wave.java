package model;

import javafx.scene.canvas.GraphicsContext;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import static model.Clock.Delta;

public class Wave {

    private float timeSinceLastSpawn, spawnTime;
    private Enemy enemy;
    private EnemyType enemyType;
    private CopyOnWriteArrayList<Enemy> enemyList;
    private int enemiesPerWave, enemiesSpawned;
    private boolean waveCompleted;

    public Wave(Enemy enemy, float spawnTime, int enemiesPerWave) {
        this.enemy = enemy;
        this.spawnTime = spawnTime * 1000;  //convert into milliseconds
        this.enemiesPerWave = enemiesPerWave;
        this.enemiesSpawned = 0;
        this.timeSinceLastSpawn = 0;
        this.enemyList = new CopyOnWriteArrayList<>();
        this.waveCompleted = false;

        spawn();
    }

    public void update() {
        boolean allEnemiesDead = true;
        if (enemiesSpawned < enemiesPerWave) {
            allEnemiesDead = false;
            timeSinceLastSpawn += Delta();
            if (timeSinceLastSpawn > spawnTime) {
                spawn();
                timeSinceLastSpawn = 0;
            }
        }
        for (Enemy e : enemyList) {
            if (e.isAlive()){
                allEnemiesDead = false;
                e.update();
            } else {
                enemyList.remove(e);
            }
        }
        if (allEnemiesDead){
            waveCompleted = true;
        }
    }

    private void spawn() {
        if (enemy.getType() == EnemyType.Random) {
            int rng = new Random().nextInt(100);
            if (rng < 75) {
                enemyType = EnemyType.Normal;
            } else if (rng < 95) {
                enemyType = EnemyType.Fast;
            } else {
                enemyType = EnemyType.Phantom;
            }
        } else {
            enemyType = enemy.getType();
        }
        enemyList.add(new Enemy(enemyType, enemy.getStartTile(), enemy.getGrid()));
//        enemyList.add(new Enemy(enemy.getType(), enemy.getStartTile(), enemy.getGrid()));
        enemiesSpawned++;
    }

    public float getTimeSinceLastSpawn() {
        return timeSinceLastSpawn;
    }

    public void setTimeSinceLastSpawn(float timeSinceLastSpawn) {
        this.timeSinceLastSpawn = timeSinceLastSpawn;
    }

    public float getSpawnTime() {
        return spawnTime;
    }

    public void setSpawnTime(float spawnTime) {
        this.spawnTime = spawnTime;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemyType) {
        this.enemy = enemyType;
    }

    public CopyOnWriteArrayList<Enemy> getEnemyList() {
        return enemyList;
    }

    public void setEnemyList(CopyOnWriteArrayList<Enemy> enemyList) {
        this.enemyList = enemyList;
    }

    public boolean isWaveCompleted() {
        return waveCompleted;
    }

    public void setWaveCompleted(boolean waveCompleted) {
        this.waveCompleted = waveCompleted;
    }

    public boolean isCompleted() { return waveCompleted; }
}
