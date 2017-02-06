package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by 226812 on 2/6/2017.
 */
public class WizardOrb extends Projectile {
    Animation<TextureRegion> animation;
    Texture spriteSheet;
    float stateTime;

    public WizardOrb(float x1, float y1, float x2, float y2){
        sprite = new Sprite(new Texture(Gdx.files.internal("Frozen_Starlight.png")));
        spriteSheet = new Texture(Gdx.files.internal("mage-E-ani.png"));
        TextureRegion[][] tmp = TextureRegion.split(spriteSheet, spriteSheet.getWidth() / 4, spriteSheet.getHeight() / 3);
        TextureRegion[] spriteFrames = new TextureRegion[12];
        int index = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 4; j++){
                spriteFrames[index++] = tmp[i][j];
            }
        }
        animation = new Animation<TextureRegion>(0.025f, spriteFrames);
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

    public WizardOrb(){

    }

    @Override
    public void contact() {
        pl.getProjectiles().add(new Explosion(sprite.getX(), sprite.getY()));
        remove();
    }

    @Override
    public void draw() {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        pl.getRenderer().getBatch().draw(currentFrame, x, y);
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