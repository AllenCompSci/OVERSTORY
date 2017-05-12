package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Item;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by chris on 5/12/2017.
 */
public class bullet extends Projectile {
    public bullet(float x1, float y1, float x2, float y2, float dmg, Item fromItem){
        this.fromItem = fromItem;
        this.dmg = dmg;
        sprite = new Sprite(spriteTextures.ArrowProjectileSprite);
        slope = ((y2 - y1)/(x2 - x1));
        x = x1;
        y = y1;
        b = y1 - slope * x1;
        endX = x2;
        endY = y2;
        theta = Math.atan(slope);
        sprite.rotate((float) Math.toDegrees(theta));
        sprite.flip(true, false);
        if(endX > x){
            direction = true;
        }
        else{
            direction = false;
            theta *= -1;
            sprite.flip(true, false);
        }
        if(endY < y){
            theta *= -1;
            sprite.flip(false, true);
        }


    }

    @Override
    public void contact() {

        fromItem.setItemXP(fromItem.getItemXP() + 1);
        /** Checks for item Level Up */
        if(fromItem.getItemXP() >= fromItem.getXPtoLVL()){
            fromItem.LVLup();
            fromItem.setXPtoLVL(fromItem.getXPtoLVL() * 2);
            System.out.println("ITEM LEVELED UP");
        }
        remove();
    }

    @Override
    public void draw() {
        sprite.setPosition(x, y);
        sprite.draw(Play.getRenderer().getBatch());
        if(direction){
            x += Math.cos(theta) * 15;
        }
        else{
            x -= Math.cos(theta) * 15;
        }

        y = slope * x + b;

    }
}
