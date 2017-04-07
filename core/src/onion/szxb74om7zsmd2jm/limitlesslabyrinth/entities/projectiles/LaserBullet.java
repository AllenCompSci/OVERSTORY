package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Item;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by chris on 4/6/2017.
 */
public class LaserBullet extends Projectile {

    private String origin;

    public LaserBullet(float x1, float y1, float x2, float y2, float dmg, String Origin, Item fromItem){
        this.fromItem = fromItem;
        this.dmg = dmg;
        origin = Origin;
        sprite = new Sprite(spriteTextures.LaserBulletSprite);
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
            sprite.flip(true, false);
        }
        if(endY < y){
            sprite.flip(false, true);
        }


    }

    public LaserBullet(){

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

        if(origin != "Enemy") {
            Play.getProjectiles().add(new Explosion(sprite.getX(), sprite.getY(), dmg, fromItem));
            Play.getProjectiles().add(new invisProjectile(sprite.getX(), sprite.getY(), dmg, enemiesHit, fromItem, origin));
        }
        else{
            Play.getEnemyProjectiles().add(new Explosion(sprite.getX(), sprite.getY(), dmg, fromItem));
        }

        //remove();
    }

    @Override
    public void draw() {
        sprite.setPosition(x, y);
        sprite.draw(Play.getRenderer().getBatch());
        if(direction){
            x += Math.cos(theta) * 10;
        }
        else{
            x -= Math.cos(theta) * 10;
        }

        y = slope * x + b;

    }
}
