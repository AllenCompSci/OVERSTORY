package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by 226812 on 2/2/2017.
 */
public class Projectile {
    public Sprite getSprite() {
        return sprite;
    }
    protected float dmg;
    protected float x;
    protected float y;
    protected float endX;
    protected float endY;
    protected float b;
    protected boolean direction;
    protected boolean isContact;
    protected boolean firstHit = false;
    protected double theta;
    protected Sprite sprite;
    protected float slope;
    protected long time = System.currentTimeMillis() + 5000;
    public float getSlope() {
        return slope;
    }
    public void setSlope(float slope) {
        this.slope = slope;
    }
    public double getTheta() {
        return theta;
    }
    public long getTime() {
        return time;
    }
    public void setContact(boolean contact) {
        isContact = contact;
    }
    public float getDmg() {
        return dmg;
    }

    public Projectile(){
    }

    public void draw(){
        sprite.draw(Play.getRenderer().getBatch());
    }

    public void contact(){
        remove();
    }

    public void remove(){
        Play.getProjectiles().removeIndex(Play.getProjectiles().indexOf(this, true));
        //sprite.getTexture().dispose();
    }
}
