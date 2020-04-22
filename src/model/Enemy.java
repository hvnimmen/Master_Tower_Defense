package model;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import static model.Clock.Delta;
import static view.GameView.SIZE;

public class Enemy implements Entity{

    private int damage, displayX, displayY, angle;
    private float x, y, speed, health, maxHealth, healthPercentage;
    private boolean first, alive, hugsLeft;
    private String status;
    private EnemyType type;
    private ImageView imageView;
    private Tile startTile;
    private TileGrid grid;
    private Image image;
    private ImageView healthBackground, healthForeground, healthBorder, frozen, aflame;
    private Tile endTile = new Tile(0, 0, TileType.Sand);

    private int[] dir;

    public Enemy(EnemyType type, Tile startTile, TileGrid grid){

        this.type = type;
        this.image = type.image;
        this.damage = type.damage;
        this.speed = (type.speed * 0.8f) + (float)Math.random() * (type.speed * 0.4f);
        this.health = type.health * 0.5f;
        this.maxHealth = type.health;
        this.status = "normal";

        this.startTile = startTile;
        this.x = startTile.getX();
        this.y = startTile.getY();

        this.displayX = (int)(x * SIZE);
        this.displayY = (int)(y * SIZE);

        this.grid = grid;

        this.first = true;
        this.alive = true;

        //randomizer
        this.hugsLeft = (Math.random() > 0.5);

        this.healthBackground = new ImageView(new Image("view/resources/health_background.png", SIZE, SIZE/8, false, false));
        this.healthForeground = new ImageView(new Image("view/resources/health_foreground.png", SIZE, SIZE/8, false, false));
        this.aflame = new ImageView(new Image("file:flaming-health_foreground.png"));
        this.frozen = new ImageView(new Image("file:frozen-health_foreground.png"));
        this.healthBorder = new ImageView(new Image("view/resources/health_border.png", SIZE, SIZE/8, false, false));

        this.dir = new int[2];
        this.dir[0] = 1;
        this.dir[1] = 0;

        calculateAngle();

        this.imageView = new ImageView(image);
        this.imageView.setRotate(angle);
    }

    public void draw(GraphicsContext gc) {

        //enemy texture
        gc.drawImage(image, displayX, displayY, SIZE, SIZE);

//        //health bar texture
//        float hpPercentage = health / maxHealth;
//        gc.drawImage(healthBackground, displayX, displayY, SIZE, SIZE/8);
//        switch (status){
//            case "normal":
//                gc.drawImage(healthForeground, displayX, displayY, SIZE*hpPercentage, SIZE/8);
//                break;
//            case "aflame":
//                gc.drawImage(aflame, displayX, displayY, SIZE*hpPercentage, SIZE/8);
//                break;
//            case "frozen":
//                gc.drawImage(frozen, displayX, displayY, SIZE*hpPercentage, SIZE/8);
//                break;
//        }
//        gc.drawImage(healthBorder, displayX, displayY, SIZE, SIZE/8);
    }

    public void calculateAngle() {
        if (dir[0] == 1 && dir[1] == 0) {
            angle = 0;
        } else if (dir[0] == 0 && dir[1] == 1) {
            angle = 90;
        } else if (dir[0] == -1 && dir[1] == 0) {
            angle = 180;
        } else if (dir[0] == 0 && dir[1] == -1) {
            angle = 270;
        }
        System.out.println(angle);
    }

    public void update() {
        //Check if it's the first time this class is updated, if so do nothing
        if (first) {
            first = false;
        } else {
            if (canGoForward()){
                x += Delta() * 0.001 * dir[0] * speed; //delta is in milliseconds
                y += Delta() * 0.001 * dir[1] * speed;
            } else {
                x = (int)(x+0.5);
                y = (int)(y+0.5);
                rotate();
                calculateAngle();
            }
            displayX = (int)(x * SIZE);
            displayY = (int)(y * SIZE);
        }
        this.getHealthForeground().setFitWidth(SIZE * health / maxHealth);
    }

    //pathfinding method
    private void rotate() {
        //inverted coordinates on this referential
        if (hugsLeft) {
            dir = new int[]{dir[1], -dir[0]};
            angle = 270;
            if (!canGoForward()){
                dir = new int[]{-dir[0], -dir[1]};
                angle = 90;
            }
        } else {
            dir = new int[]{-dir[1], dir[0]};
            angle = 90;
            if (!canGoForward()){
                dir = new int[]{-dir[0], -dir[1]};
                angle = 270;
            }
        }

        calculateAngle();
        this.imageView.setRotate(angle);

        if (!canGoForward()){
            getToEnd();
        }
    }

    //run when dead end thus end of path
    public void getToEnd(){
        Player.changeHP(-damage);
        die();
    }

    public boolean canGoForward() {
        return (withinBounds() && isPath());
    }

    //check if next tile is still in window/map
    public boolean withinBounds() {
        double nextX = x + Delta() * 0.001 * dir[0] * speed;
        double nextY = y + Delta() * 0.001 * dir[1] * speed;
        return (0 <= nextX && nextX < grid.xTiles-1 && 0 <= nextY && nextY < grid.yTiles-1);
    }

    //check if next tile is path
    public boolean isPath() {
        double nextX = x + Delta() * 0.001 * dir[0] * speed;
        double nextY = y + Delta() * 0.001 * dir[1] * speed;
        if (dir[0] == 1)
            nextX += 1;
        else if (dir[1] == 1)
            nextY += 1;
        Tile currentTile = grid.getTile((int)x, (int)y);
        Tile nextTile = grid.getTile((int)nextX, (int)nextY);
        return (currentTile.getType() == nextTile.getType());
    }

    //enemy takes damage from external source
    public void damage(int damage) {
        health -= damage;
        this.getHealthForeground().setFitWidth(SIZE * health / maxHealth);
        if (health <= 0 && alive) { //alive because otherwise midair projectiles would kill it and earn gold twice
            die();
            Player.changeGold(type.bounty);
        }
    }

    public void freeze() {
        this.speed = type.speed * 0.5f;
        this.status = "frozen";
    }

    public void burn() {
        this.speed = type.speed * 2;
        this.status = "aflame";
    }

    //kills the enemy
    private void die() {
        alive = false;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public EnemyType getType() {
        return type;
    }

    public void setType(EnemyType type) {
        this.type = type;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Tile getStartTile() {
        return startTile;
    }

    public void setStartTile(Tile startTile) {
        this.startTile = startTile;
    }

    public TileGrid getGrid() {
        return grid;
    }

    public void setGrid(TileGrid grid) {
        this.grid = grid;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public float getDisplayX() {
        return displayX;
    }

    public void setDisplayX(int displayX) {
        this.displayX = displayX;
    }

    public float getDisplayY() {
        return displayY;
    }

    public void setDisplayY(int displayY) {
        this.displayY = displayY;
    }

    public float getHealthPercentage() {
        return health/maxHealth;
    }

    public ImageView getHealthBackground() {
        return healthBackground;
    }

    public void setHealthBackground(ImageView healthBackground) {
        this.healthBackground = healthBackground;
    }

    public ImageView getHealthForeground() {
        return healthForeground;
    }

    public void setHealthForeground(ImageView healthForeground) {
        this.healthForeground = healthForeground;
    }

    public ImageView getHealthBorder() {
        return healthBorder;
    }

    public void setHealthBorder(ImageView healthBorder) {
        this.healthBorder = healthBorder;
    }
}
