package model;

import javafx.scene.image.Image;

public enum ProjectileType {

    Archer(new Image("view/resources/shot.png"), 10, 20),
    Freeze(new Image("view/resources/freezing_shot.png"), 5, 15),
    Flaming(new Image("view/resources/flaming_shot.png"), 20, 15),
    Rocket(new Image("view/resources/rocket.png"), 50, 5);

    Image image;
    int damage;
    float speed;

    ProjectileType(Image image, int damage, float speed) {
        this.image = image;
        this.damage = damage;
        this.speed = speed;
    }
}