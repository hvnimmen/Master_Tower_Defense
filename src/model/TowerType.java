package model;

import javafx.scene.image.Image;

import static view.GameView.SIZE;

public enum TowerType {

    Archer("file:bow.png", ProjectileType.Archer, 3, 2, 20),
    Freeze("file:freeze-bow.png", ProjectileType.Freeze, 3, 2, 20),
    Flaming("file:flaming-bow.png", ProjectileType.Flaming, 3, 2, 20),
    Sniper("file:sniper-bow.png", ProjectileType.Rocket, 15, 4, 80);

    Image image;
    ProjectileType projectileType;
    int range, cost;
    float cooldown;

    TowerType(String fileName, ProjectileType projectileType, int range, float cooldown, int cost){
        this.image = new Image(fileName, SIZE, SIZE, false, false);
        this.projectileType = projectileType;
        this.range = range;
        this.cooldown = cooldown;
        this.cost = cost;
    }

}