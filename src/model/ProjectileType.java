package model;

import javafx.scene.image.Image;

public enum ProjectileType {

    Archer(new Image("file:arrow.png"), 10, 15),
    Freeze(new Image("file:freeze-arrow-2.png"), 5, 15),
    Flaming(new Image("file:flaming-arrow-2.png"), 20, 15),
    Rocket(new Image("file:rocket.png"), 50, 5);

    Image image;
    int damage;
    float speed;

    ProjectileType(Image image, int damage, float speed) {
        this.image = image;
        this.damage = damage;
        this.speed = speed;
    }
}