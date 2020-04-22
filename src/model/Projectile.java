package model;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import static model.Clock.Delta;
import static view.GameView.SIZE;

public abstract class Projectile implements Entity {

    private float x, y, displayX, displayY, speed, xVelocity, yVelocity, angle, offset;
    private int damage, width, height, size = SIZE/2;
    private Image image;
    private ImageView imageView;
    private Enemy target;
    private boolean hasCollided;
    private ProjectileType type;

    public Projectile(ProjectileType type, Enemy target, float x, float y, int width, int height){
        this.type = type;

        this.x = x;
        this.y = y;
        this.speed = type.speed;
        this.damage = type.damage;
        this.target = target;
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.image = type.image;
        this.hasCollided = false;

        this.angle = calculateAngle();
        this.imageView = new ImageView(image);

        this.width = (int)this.image.getWidth();
        this.height = (int)this.image.getHeight();

        calculateDirection();
    }

    private float calculateAngle() {
        double tempAngle = Math.atan2(target.getY() - y, target.getX() - x);
        return (float) Math.toDegrees(tempAngle) + 90;
    }

    private void calculateDirection() {
        float totalAllowedMovement = 1.0f;
        float xDistance = (float) (target.getX() - x);
        float yDistance = (float) (target.getY() - y);

        float totalDistance = Math.abs(xDistance) + Math.abs(yDistance);
        float xMovementPercent = xDistance / totalDistance;
        float yMovementPercent = yDistance / totalDistance;

        xVelocity = xMovementPercent;
        yVelocity = yMovementPercent;
    }

    public void update(){
        if (!hasCollided) {

            calculateDirection();

            x += Delta() * 0.001 * xVelocity * speed;
            y += Delta() * 0.001 * yVelocity * speed;

            angle = calculateAngle();

            this.displayX = (x + 0.5f) * SIZE - 0.5f * width;
            this.displayY = (y + 0.5f) * SIZE - 0.5f * height;

            if (projectileHitTarget()){
                damage();
            }
        }
    }

    public boolean projectileHitTarget() {
        return (target.getDisplayX() < displayX + width && displayX < target.getDisplayX() + SIZE &&
                target.getDisplayY()  < displayY + height && displayY < target.getDisplayY() + SIZE);
    }

    public void damage(){
        target.damage(damage);
        hasCollided = true;
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, displayX, displayY);
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

    public float getDisplayX() {
        return displayX;
    }

    public void setDisplayX(float displayX) {
        this.displayX = displayX;
    }

    public float getDisplayY() {
        return displayY;
    }

    public void setDisplayY(float displayY) {
        this.displayY = displayY;
    }

    public Enemy getTarget() {
        return target;
    }

    public void setTarget(Enemy target) {
        this.target = target;
    }

    public void changeSpeed(float change) {
        this.speed = type.speed * change;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public float getAngle() {
        return angle;
    }

    public ProjectileType getType() {
        return type;
    }

    public boolean HasCollided() {
        return hasCollided;
    }
}
