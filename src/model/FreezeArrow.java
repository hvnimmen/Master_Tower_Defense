package model;

public class FreezeArrow extends Projectile {

    public FreezeArrow(ProjectileType type, Enemy target, float x, float y, int width, int height, int level) {
        super(type, target, x, y, width, height, level);
    }

    @Override
    public void damage(){
//        super.getTarget().setSpeed(getTarget().getSpeed()*0.75f);
        super.getTarget().freeze();
        super.damage();
    }

}
