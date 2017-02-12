package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Enemy;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;


/**
 * Created by 226812 on 2/2/2017.
 */
public class Projectile {
    public Array<Enemy> getEnemiesHit() {
        return enemiesHit;
    }
    protected Array<Enemy> enemiesHit = new Array<Enemy>();
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
    protected boolean searching;
    protected double theta;
    protected Sprite sprite;
    protected float slope;
    public String getName() {
        return name;
    }
    protected String name;
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
        Play.getProjectiles().set(Play.getProjectiles().indexOf(this, true), null);
        Play.getProjectiles().removeIndex(Play.getProjectiles().indexOf(null, true));
    }
}
