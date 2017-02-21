package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Player;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Item;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by Taylor on 2/18/2017.
 */
public class singleMagicStrike extends Projectile {
    Animation<TextureRegion> animation;
    float stateTime;
    int distance;
    int count;
    int count1;

    public singleMagicStrike(float x1, float y1,float dmg, Item fromItem, int count1, float stateTime, int count){

        this.fromItem = fromItem;
        this.count1 = count1;
        animation = spriteTextures.FX(count1);
        this.dmg = dmg;
        this.distance = 0;
        this.count = count;
        sprite = new Sprite(spriteTextures.basic64);
        this.stateTime = stateTime;
        x = x1;
        y = y1;

    }
	 public singleMagicStrike(float x1, float y1,float dmg, Item fromItem, int lifetime, Animation<TextureRegion> animation){

        this.fromItem = fromItem;
        this.animation = animation;
        this.dmg = dmg;
        this.distance = 0;
        this.count = lifetime;
        sprite = new Sprite(spriteTextures.basic64);
        this.stateTime = 0f;
        x1 -= 16;
        y += 16;
        x = x1;
        y = y1;

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


    }

    @Override
    public void draw() {
        distance ++;
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        Play.getRenderer().getBatch().draw(currentFrame, x, y);
        sprite.setPosition(x,y);
        if(distance > count){
            remove();
        }
    }
}
