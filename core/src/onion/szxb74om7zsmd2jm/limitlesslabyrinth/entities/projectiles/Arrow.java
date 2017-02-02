package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by 226812 on 2/2/2017.
 */
public class Arrow extends Projectile {
    public Arrow(float x1, float y1, float x2, float y2){
        sprite = new Sprite(new Texture("arrow.png"));
        slope = ((y2 - y1)/(x2 - x1));
        x = x1;
        y = y1;
        b = y1 - slope * x1;
        endX = x2;
        endY = y2;
        theta = Math.atan(slope);
        if(endX > x){
            direction = true;
        }
        else{
            direction = false;
            theta *= -1;
        }
        if(endY < y){
            theta *= -1;
        }
    }

    public Arrow(){

    }

    @Override
    public void draw() {
        sprite.setPosition(x, y);
        sprite.draw(pl.getRenderer().getBatch());
        if(direction){
            x += Math.cos(theta) * 6;
        }
        else{
            x -= Math.cos(theta) * 6;
        }
        y = slope * x + b;
    }
}
