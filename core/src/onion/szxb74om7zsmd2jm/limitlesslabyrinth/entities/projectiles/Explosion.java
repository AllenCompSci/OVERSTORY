package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Item;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by 226812 on 2/6/2017.
 */
public class Explosion extends Projectile {
    Animation<TextureRegion> animation;
    float stateTime;
    String weaponOrigin;

    public Explosion(float x, float y, float dmg, Item fromItem){

        this.fromItem = fromItem;
        animation = spriteTextures.ExplosionAnimation;
        this.dmg = dmg;
        this.x = x;
        this.y = y;
        sprite = new Sprite(spriteTextures.explosionTexture);
        stateTime = 0f;

        sprite.setPosition(x - sprite.getWidth()/2 ,y - sprite.getHeight()/2);
        time = System.currentTimeMillis() + 200;
    }




    @Override
    public void draw() {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        Play.getRenderer().getBatch().draw(currentFrame, sprite.getX(), sprite.getY());
    }

    @Override
    public void contact() {
    }
}
