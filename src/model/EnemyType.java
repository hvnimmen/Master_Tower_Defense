package model;

import javafx.scene.image.Image;

public enum EnemyType {

    Zombie("view/resources/white_plane.png", "view/resources/white_plane_shadow.png", 2, 50, 2, 5),
    Spider("view/resources/green_plane.png", "view/resources/green_plane_shadow.png", 4, 25, 1, 3),
    Random("view/resources/white_plane.png", "view/resources/green_plane_shadow.png", 0, 0, 0, 0);

    Image image, shadowImage;
    float speed;
    int health, damage, bounty;

    EnemyType(String baseName, String shadowName, float speed, int health, int damage, int bounty){
        this.image = new Image(baseName);
        this.shadowImage = new Image(shadowName);
        this.speed = speed;
        this.health = health;
        this.damage = damage;
        this.bounty = bounty;
    }

}
