package model;

import java.util.concurrent.CopyOnWriteArrayList;

import static view.GameView.SIZE;

public class ArcherTower extends Tower{

    public ArcherTower(TowerType type, Tile startTile, CopyOnWriteArrayList<Enemy> enemies){
        super(type, startTile, enemies);
    }

    @Override
    public void shoot(Enemy target) {
        super.getProjectiles().add(new Arrow(super.getProjectileType(), target, super.getX(), super.getY(),
                SIZE/2, SIZE/2, getLevel()));
    }

}
