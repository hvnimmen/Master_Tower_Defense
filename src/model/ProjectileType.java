package model;

import javafx.scene.image.Image;

public enum ProjectileType {

    Archer(new Image("view/resources/shot.png"), new Image("view/resources/double_shot.png") ,10, 20),
    Freeze(new Image("view/resources/freezing_shot.png"), new Image("view/resources/double_freezing_shot.png"), 5, 15),
    Flaming(new Image("view/resources/flaming_shot.png"), new Image("view/resources/double_flaming_shot.png"), 20, 15),
    Rocket(new Image("view/resources/rocket.png"), new Image("view/resources/double_rocket.png"), 50, 5);

    Image singleImage, doubleImage;
    int damage;
    float speed;

    ProjectileType(Image singleImage, Image doubleImage, int damage, float speed) {
        this.singleImage = singleImage;
        this.doubleImage = doubleImage;
        this.damage = damage;
        this.speed = speed;
    }
}