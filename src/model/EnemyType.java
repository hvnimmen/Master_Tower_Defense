package model;

import javafx.scene.image.Image;

public enum EnemyType {

    Zombie(new Image("view/resources/white_plane.png"), 2, 50, 2, 5),
    Spider(new Image("view/resources/green_plane.png"), 4, 25, 1, 3),
    Random(new Image("view/resources/white_plane.png"), 0, 0, 0, 0);

    Image image;
    float speed;
    int health, damage, bounty;

    EnemyType(Image image, float speed, int health, int damage, int bounty){
        this.image = image;
        this.speed = speed;
        this.health = health;
        this.damage = damage;
        this.bounty = bounty;
    }

}
