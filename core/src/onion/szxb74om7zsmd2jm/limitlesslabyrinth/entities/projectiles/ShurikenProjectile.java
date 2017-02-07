package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by chris on 2/6/2017.
 */


/** WORK IN PROGRESS */
/** WORK IN PROGRESS */
/** WORK IN PROGRESS */
/** WORK IN PROGRESS */
/** WORK IN PROGRESS */
/** WORK IN PROGRESS */
/** WORK IN PROGRESS */
/** WORK IN PROGRESS */

public class ShurikenProjectile extends Projectile {
    public ShurikenProjectile(float x1, float y1, float x2, float y2){
        sprite = new Sprite(new Texture("shurikenProjectile.png"));
        slope = ((y2 - y1)/(x2 - x1));
        x = x1 - sprite.getWidth()/2;
        y = y1 - sprite.getHeight()/2;
        b = y1 - slope * x1;
        endX = x2;
        endY = y2;
        theta = Math.atan(slope);
        if(endX > x){
            direction = true;
        }
        else{
            direction = false;
        }


    }

    public ShurikenProjectile(){

    }

    @Override
    public void contact() {
        remove();
    }

    @Override
    public void draw() {
        sprite.setPosition(x, y);
        sprite.draw(pl.getRenderer().getBatch());
        if(direction){
            x += Math.cos(theta) * 10;
        }
        else{
            x -= Math.cos(theta) * 10;
        }

        y = slope * x + b;

    }
}
