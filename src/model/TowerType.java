package model;

import javafx.scene.image.Image;

import static view.GameView.SIZE;

public enum TowerType {

    Turret("view/resources/turret_base.png", "view/resources/green_turret.png",  ProjectileType.Archer, 3, 2, 20),
    Freezing("view/resources/turret_base.png", "file:freeze-bow.png",  ProjectileType.Freeze, 3, 2, 20),
    Flaming("view/resources/turret_base.png", "file:flaming-bow.png", ProjectileType.Flaming, 3, 2, 20),
    Sniper("view/resources/turret_base.png", "file:sniper-bow.png", ProjectileType.Rocket, 15, 4, 80);

    Image baseImage, turretImage;
    ProjectileType projectileType;
    int range, cost;
    float cooldown;

    TowerType(String baseName, String turretName, ProjectileType projectileType, int range, float cooldown, int cost){
        this.baseImage = new Image(baseName, SIZE, SIZE, false, false);
        this.turretImage = new Image(turretName, SIZE, SIZE, false, false);
        this.projectileType = projectileType;
        this.range = range;
        this.cooldown = cooldown;
        this.cost = cost;
    }

}