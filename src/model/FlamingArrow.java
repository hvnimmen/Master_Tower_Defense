package model;

public class FlamingArrow extends Projectile {

    //    public FreezeArrow(Image image, Enemy target, float x, float y, float width, float height, float speed, int damage) {
    public FlamingArrow(ProjectileType type, Enemy target, float x, float y, int width, int height) {
        super(type, target, x, y, width, height);
    }

    @Override
    public void damage(){
        super.getTarget().burn();
        super.damage();
    }

}