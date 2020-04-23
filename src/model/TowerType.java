package model;

import javafx.scene.image.Image;

import static view.GameView.SIZE;

public enum TowerType {

    Turret("view/resources/turret_base.png", "view/resources/green_turret.png",  ProjectileType.Archer, 4, 2, 20),
    Freezing("view/resources/turret_base.png", "view/resources/blue_turret.png",  ProjectileType.Freeze, 4, 2, 20),
    Flaming("view/resources/turret_base.png", "view/resources/red_turret.png", ProjectileType.Flaming, 4, 2, 20),
    Launcher("view/resources/turret_base.png", "view/resources/rocket_launcher.png", ProjectileType.Rocket, 15, 4, 80);

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

    public Image getTurretImage() {
        return this.turretImage;
    }

    public int getCost() {
        return this.cost;
    }

}