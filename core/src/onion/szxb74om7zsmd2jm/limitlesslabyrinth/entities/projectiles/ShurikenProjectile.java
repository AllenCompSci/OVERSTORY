package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.Shuriken;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by chris on 2/6/2017.
 */


public class ShurikenProjectile extends Projectile {

    public ShurikenProjectile(float x1, float y1, float x2, float y2, float dmg){
        this.dmg = dmg;
        sprite = new Sprite(spriteTextures.shurikenProjectileTexture);
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
        }

    }

    public ShurikenProjectile(){

    }

    @Override
    public void contact() {
        //Play.getProjectiles().add(new Explosion(sprite.getX(), sprite.getY(), dmg));
        remove();
    }

    @Override
    public void draw() {
        theta = Math.atan(slope);
        sprite.setPosition(x, y);
        sprite.draw(Play.getRenderer().getBatch());
        sprite.rotate(30);
        if(direction){
            x += Math.cos(theta) * 10;
        }
        else{
            x -= Math.cos(theta) * 10;
        }

        y = slope * x + b;

    }

    public void createB(){
        b = y - slope * x;
    }
}
