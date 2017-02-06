package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by 226812 on 2/6/2017.
 */
public class Explosion extends Projectile {
    public Explosion(float x, float y){
        sprite = new Sprite(new Texture("explosion.png"));
        sprite.setPosition(x - sprite.getWidth()/2 ,y - sprite.getHeight()/2);
        time = System.currentTimeMillis() + 1000;
    }

    @Override
    public void draw() {
        sprite.draw(pl.getRenderer().getBatch());
    }

    @Override
    public void contact() {
    }
}
