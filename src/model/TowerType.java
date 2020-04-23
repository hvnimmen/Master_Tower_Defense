package model;

import javafx.scene.image.Image;

import static view.GameView.SIZE;

public enum TowerType {

    Turret("view/resources/green_turret.png", "view/resources/double_green_turret.png", "view/resources/green_turret_3.png", "None", ProjectileType.Archer, 4, 2, 20),
    Freezing("view/resources/blue_turret.png", "view/resources/double_blue_turret.png", "view/resources/blue_turret_3.png", "Slow", ProjectileType.Freeze, 4, 2, 20),
    Flaming("view/resources/red_turret.png", "view/resources/double_red_turret.png", "view/resources/red_turret_3.png", "Speed up", ProjectileType.Flaming, 4, 2, 20),
    Launcher("view/resources/rocket_launcher.png", "view/resources/double_rocket_launcher.png", "view/resources/rocket_launcher_3.png", "Homing", ProjectileType.Rocket, 15, 4, 80);

    Image turretImage1, turretImage2, turretImage3;
    String effect;
    ProjectileType projectileType;
    int range, cost;
    float cooldown;

    TowerType(String turretName1, String turretName2, String turretName3, String effect, ProjectileType projectileType, int range, float cooldown, int cost){
        this.turretImage1 = new Image(turretName1, SIZE, SIZE, false, false);
        this.turretImage2 = new Image(turretName2, SIZE, SIZE, false, false);
        this.turretImage3 = new Image(turretName3, SIZE, SIZE, false, false);
        this.effect = effect;
        this.projectileType = projectileType;
        this.range = range;
        this.cooldown = cooldown;
        this.cost = cost;
    }

    public Image getTurretImage() {
        return this.turretImage1;
    }

    public int getCost() {
        return this.cost;
    }

    public int getRange() {
        return this.range;
    }

    public int getDamage() {
        return this.projectileType.damage;
    }

    public float getCooldown() { return this.cooldown; }

    public String getEffect() { return this.effect; }

}