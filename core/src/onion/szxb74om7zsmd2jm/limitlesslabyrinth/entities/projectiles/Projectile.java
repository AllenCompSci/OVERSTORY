package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Enemy;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Player;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Item;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;


/**
 * Created by 226812 on 2/2/2017.
 */
public class Projectile {
    public Array<Enemy> getEnemiesHit() {
        return enemiesHit;
    }
    protected Array<Enemy> enemiesHit = new Array<Enemy>();
    public Array<Player> getPlayersHit() {
        return playersHit;
    }
    protected Array<Player> playersHit = new Array<Player>();
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
    protected String name;
    Item fromItem;
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
    public String getName() {
        return name;
    }

    public Projectile(){
    }

    public void draw(){
        sprite.draw(Play.getRenderer().getBatch());
    }

    public void contact(){
        remove();
    }

    public void remove() {
        if (Play.getProjectiles().contains(this, true)) {
            Play.getProjectiles().set(Play.getProjectiles().indexOf(this, true), null);
            Play.getProjectiles().removeIndex(Play.getProjectiles().indexOf(null, true));
        }
        else if(Play.getEnemyProjectiles().contains(this, true)){
            Play.getEnemyProjectiles().set(Play.getEnemyProjectiles().indexOf(this, true), null);
            Play.getEnemyProjectiles().removeIndex(Play.getEnemyProjectiles().indexOf(null, true));
        }
    }
}
