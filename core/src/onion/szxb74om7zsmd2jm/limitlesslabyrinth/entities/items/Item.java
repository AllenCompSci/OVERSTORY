package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Arrow;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;

/**
 * Created by chris on 1/31/2017.
 */
public class Item {
    public Sprite getSprite() {
        return sprite;
    }
    protected Sprite sprite;
    protected Sprite projectileSprite;
    protected Projectile projectile;
    protected float dmg;
    protected int lvl;
    protected String type;
    public float getDmg() {
        return dmg;
    }
    public String getType() {
        return type;
    }

    public Item(){

    }
    public Projectile getProjectile(float x1, float y1, float x2, float y2){
        return new Projectile();
    }


}
