package model;

public class Arrow extends Projectile {

    public Arrow(ProjectileType type, Enemy target, float x, float y, int width, int height, int level) {
        super(type, target, x, y, width, height, level);
    }

    @Override
    public void damage(){
        super.damage();
    }

}
