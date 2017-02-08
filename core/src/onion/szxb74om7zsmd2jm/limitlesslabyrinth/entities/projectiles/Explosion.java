package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by 226812 on 2/6/2017.
 */
public class Explosion extends Projectile {
    Animation<TextureRegion> animation;
    Texture spriteSheet;
    float stateTime;

    public Explosion(float x, float y, float dmg){
        this.dmg = dmg;
        this.x = x;
        this.y = y;
        sprite = new Sprite(new Texture(Gdx.files.internal("explosion.png")));
        spriteSheet = new Texture(Gdx.files.internal("explosionSprites.png"));
        TextureRegion[][] tmp = TextureRegion.split(spriteSheet, spriteSheet.getWidth() / 4, spriteSheet.getHeight() / 4);
        TextureRegion[] spriteFrames = new TextureRegion[16];
        int index = 0;
        for(int i = 3; i >= 0; i--){
            for(int j = 3; j >= 0; j--){
                spriteFrames[index++] = tmp[i][j];
            }
        }
        animation = new Animation<TextureRegion>(0.0001f, spriteFrames);
        stateTime = 0f;

        sprite.setPosition(x - sprite.getWidth()/2 ,y - sprite.getHeight()/2);
        time = System.currentTimeMillis() + 200;
    }

    @Override
    public void draw() {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        pl.getRenderer().getBatch().draw(currentFrame, sprite.getX(), sprite.getY());
    }

    @Override
    public void contact() {
    }
}
