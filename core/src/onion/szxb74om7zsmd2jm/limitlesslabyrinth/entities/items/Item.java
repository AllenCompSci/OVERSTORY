package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by chris on 1/31/2017.
 */
public class Item {
    public Sprite getSprite() {
        return sprite;
    }
    protected Sprite sprite;
    protected float dmg;
    protected int lvl;
    public float getDmg() {
        return dmg;
    }

    public Item(){

    }

}
