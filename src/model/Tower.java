package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import static model.Clock.Delta;
import static view.GameView.SIZE;

public abstract class Tower implements Entity{

    private int range, cost, level = 1, damageMultiplier = 1, speedMultiplier = 1;
    private float x, y, displayX, displayY, cooldown, timeSinceLastShot, offset, angle = 0;
    private ArrayList<Projectile> projectiles;
    private CopyOnWriteArrayList<Enemy> enemies;
    private boolean locked;
    private Image baseImage, turretImage;
    private ImageView baseImageView, turretImageView;
    private Enemy target;
    private TowerType type;
    private ProjectileType projectileType;

    public Tower(TowerType type, Tile startTile, CopyOnWriteArrayList<Enemy> enemies){
        this.type = type;
        this.projectileType = type.projectileType;
        this.level = 1;

        this.x = startTile.getX();
        this.y = startTile.getY();

        this.displayX = x * SIZE;
        this.displayY = y * SIZE;

        this.range = type.range + 1;
        this.cost = type.cost;

        this.cooldown = type.cooldown * 1000;
        this.timeSinceLastShot = this.cooldown * 1000;

        this.projectiles = new ArrayList<Projectile>();
        this.enemies = enemies;
        this.locked = false;

        this.baseImageView = new ImageView(new Image("view/resources/turret_base.png"));
        this.turretImageView = new ImageView(type.turretImage1);
        baseImageView.setLayoutX(displayX);
        baseImageView.setLayoutY(displayY);
        turretImageView.setLayoutX(displayX);
        turretImageView.setLayoutY(displayY);
    }

    private Enemy acquireTarget() {
        Enemy closestEnemy = null;
        double closestRange = range;
        for (Enemy e : enemies){
            if (isInRange(e) && getDistance(e) < closestRange && e.isAlive()) {
                closestEnemy = e;
                closestRange = getDistance(e);
            }
        }
        if (closestEnemy != null){
            locked = true;
        }

        return closestEnemy;
    }

    private boolean isInRange(Enemy e) {
        return getDistance(e) < range;
    }

    private double getDistance(Enemy e) {
        double xDistance = Math.abs(x - e.getX());
        double yDistance = Math.abs(y - e.getY());
        return Math.sqrt(xDistance * xDistance + yDistance * yDistance);
    }

    private float calculateAngle() {
        double tempAngle = Math.atan2(target.getY() - y, target.getX() - x);
        return (float) Math.toDegrees(tempAngle) + 90;
    }

    public abstract void shoot(Enemy target);

//    public void shoot(){
//        projectiles.add(new Arrow("file:arrow.png", target, x, y, 15, 10));
//    }

    public void updateEnemyList(CopyOnWriteArrayList<Enemy> newList){
        enemies = newList;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getDisplayX() {
        return displayX;
    }

    public float getDisplayY() {
        return displayY;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Enemy getTarget() {
        return target;
    }

    public void setTarget(Enemy target) {
        this.target = target;
    }

    public float getTimeSinceLastShot() {
        return timeSinceLastShot;
    }

    public void setTimeSinceLastShot(float timeSinceLastShot) {
        this.timeSinceLastShot = timeSinceLastShot;
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    public TowerType getTowerType() {
        return type;
    }

    public void setTowerType(TowerType towerType) {
        this.type = towerType;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public float getCooldown() {
        return cooldown;
    }

    public void setCooldown(float cooldown) {
        this.cooldown = cooldown;
    }

    public void update(){
        if (!locked) {
            target = acquireTarget();
        } else {

            angle = calculateAngle();

            if (timeSinceLastShot > cooldown){
                shoot(target);
                timeSinceLastShot = 0;
            }
        }

        if(target == null || !target.isAlive() || !isInRange(target)){
            locked = false;
        }

        timeSinceLastShot += Delta();

        for (Projectile p : projectiles){
            p.update();
        }
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(baseImage, (x * SIZE) - offset, (y * SIZE) - offset);
    }

    public void levelUp(){
        if (level == 1) {
            damageMultiplier = 2;
            this.turretImageView.setImage(type.turretImage2);
            level++;
        } else if (level == 2) {
            cooldown *= 0.5;
            speedMultiplier = 2;
            this.baseImageView.setImage(new Image("view/resources/turret_base_2.png"));
            this.turretImageView.setImage(type.turretImage3);
            level++;
        }
    }

    public ImageView getBaseImageView() {
        return this.baseImageView;
    }

    public ImageView getTurretImageView() {
        return this.turretImageView;
    }

    public float getAngle() {
        return this.angle;
    }

    public ProjectileType getProjectileType() {
        return projectileType;
    }

    public int getLevel() {
        return level;
    }
}
