package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Enemy;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics.Detection;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by chris on 2/11/2017.
 */
public class LightningOrb extends Projectile {
    Animation<TextureRegion> animation;
    float stateTime;
    double distance;

    public LightningOrb(float x1, float y1, float x2, float y2, float dmg, Array<Enemy> hit){
        animation = Play.fourFrameAnimationCreator(spriteTextures.LightningOrbSprites, 4,  4, .1f);
        enemiesHit = hit;
        this.dmg = dmg;
        sprite = new Sprite(spriteTextures.LightningOrb);
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

    public LightningOrb(){

    }

    @Override
    public void contact() {
        Play.getProjectiles().add(new invisProjectile(sprite.getX(), sprite.getY(), dmg, enemiesHit));
        remove();
    }

    @Override
    public void draw() {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        Play.getRenderer().getBatch().draw(currentFrame, x, y);
        sprite.setPosition(x,y);
        if(direction){
            x += Math.cos(theta) * 13;
        }
        else{
            x -= Math.cos(theta) * 13;
        }

        y = slope * x + b;

    }
}