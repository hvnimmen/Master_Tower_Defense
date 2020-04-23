package model;

import javafx.scene.image.Image;

import static view.GameView.SIZE;

public enum EnemyType {

    Normal("view/resources/white_plane.png", "view/resources/white_plane_shadow.png", 2, 50, 1, 4),
    Fast("view/resources/green_plane.png", "view/resources/green_plane_shadow.png", 4, 20, 1, 7),
    Tank("view/resources/yellow_plane.png", "view/resources/white_plane_shadow.png", 1, 100, 1, 7),
    Fighter("view/resources/red_plane.png", "view/resources/green_plane_shadow.png", 2, 50, 2, 9),
    Phantom("view/resources/phantom_plane.png", "view/resources/white_plane_shadow.png", 8, 100, 1, 1000),
    Random("view/resources/white_plane.png", "view/resources/green_plane_shadow.png", 0, 0, 0, 0);

    Image image, shadowImage;
    float speed;
    int health, damage, bounty;

    EnemyType(String baseName, String shadowName, float speed, int health, int damage, int bounty){
        this.image = new Image(baseName, SIZE, SIZE, false, false);
        this.shadowImage = new Image(shadowName, SIZE, SIZE, false, false);
        this.speed = speed;
        this.health = health;
        this.damage = damage;
        this.bounty = bounty;
    }

}
