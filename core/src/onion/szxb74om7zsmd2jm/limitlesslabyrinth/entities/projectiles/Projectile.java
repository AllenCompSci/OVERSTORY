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
    protected float x;
    protected float y;
    protected float endX;
    protected float endY;
    protected float b;
    protected boolean direction;
    protected Sprite sprite;
    protected float slope;
    public float getSlope() {
        return slope;
    }
    Play pl = new Play();

    public Projectile(){

    }

    public void draw(){}
}
