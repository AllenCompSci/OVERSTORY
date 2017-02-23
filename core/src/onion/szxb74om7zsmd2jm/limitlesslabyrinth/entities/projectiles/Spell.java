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
 * Created by Taylor on 2/12/2017.
 */
public class Spell extends Projectile {
    Animation<TextureRegion> animation;
    float stateTime;
    int distance;
    int endDist;
    int count;
    int count1;
    Player.FACE dir;

    public Spell(float x1, float y1, Player.FACE dir, float dmg, int distance, Item fromItem, int count1, long time){
        this.fromItem = fromItem;
        this.count1 = count1;
        animation = spriteTextures.FX(count1);
        this.dmg = dmg;
        this.dir = dir;
        this.time = time;
        this.distance = distance;
        if(distance < 50) {
            count = distance + 8;
            endDist = 151 - distance;
        }
        else{
            endDist = 120 - distance;
        }
        sprite = new Sprite(spriteTextures.basic64);
        stateTime = 0f;
        x = x1;
        y = y1;
        updateX();
        updateY();

    }

    public void updateX(){
        if(dir == Player.FACE.LEFT){
            x-=16;
        }
        else if(dir == Player.FACE.RIGHT){
            x+=16;
        }
    }
    public void updateY(){
        if(dir == Player.FACE.DOWN){
            y-=16;
        }
        else if(dir == Player.FACE.UP){
            y+=16;
        }
    }
    public float createX(){
        if(dir == Player.FACE.DOWN || dir == Player.FACE.UP){
            return 32;
        }
        return 0;
    }
    public float createY(){
        if(dir == Player.FACE.LEFT || dir == Player.FACE.RIGHT){
            return 32;
        }
        return 0;
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
        distance++;

        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        Play.getRenderer().getBatch().draw(currentFrame, x, y);
        sprite.setPosition(x,y);
        updateX();
        updateY();
        if(distance > endDist){
            time = System.currentTimeMillis();
        }
        if(((int)distance == count && !(distance > endDist))){
            //Need to change x and y to be following the y = m x + b format.

            Play.getProjectiles().add(new Spell(sprite.getX() + createX(), sprite.getY() + createY(), dir, dmg, distance, fromItem, count1, time));
            Play.getProjectiles().add(new Spell(sprite.getX() - createX(), sprite.getY() - createY(), dir, dmg, distance, fromItem, count1, time));
        }
    }
}
