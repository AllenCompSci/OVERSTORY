package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    public Spell(float x1, float y1, float x2, float y2, float dmg, int distance){
        count = (int)(Math.random() * 4);
        if(count == 0)
        animation = Play.fourFrameAnimationCreator(spriteTextures.magic, 3,  8, .01f);
        else if(count == 1)
        animation = Play.fourFrameAnimationCreator(spriteTextures.holy, 2, 4, .2f);
        else if(count == 2)
        animation = Play.fourFrameAnimationCreator(spriteTextures.ice, 2, 4, .2f);
        else if(count == 3)
        animation = Play.fourFrameAnimationCreator(spriteTextures.earth, 2, 4, .2f);
        this.dmg = dmg;

        this.distance = distance;
        count = distance + 8;
        endDist = 61 - distance;
        sprite = new Sprite(spriteTextures.basic64);
        stateTime = 0f;
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


    @Override
    public void contact() {



        if(count > 40) {
            remove();
        }
    }

    @Override
    public void draw() {
        distance ++;

        if(((int)distance == count)){
           //Need to change x and y to be following the y = m x + b format. 
            Play.getProjectiles().add(new Spell(sprite.getX(), sprite.getY()+64, x,y+64, dmg, distance));
            Play.getProjectiles().add(new Spell(sprite.getX(), sprite.getY()-64, x,y-64, dmg, distance));
        }
        if(distance > endDist){
            remove();
        }
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        Play.getRenderer().getBatch().draw(currentFrame, x, y);
        sprite.setPosition(x,y);
        if(direction){
            x += Math.cos(theta) * 10;
        }
        else{
            x -= Math.cos(theta) * 10;
        }

        y = slope * x + b;

    }
}
