package onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.loadingscreen;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;

/**
 * Created by chris on 2/24/2017.
 */
public class LoadingScreenBackGround extends Actor {
    private static Sprite sprite;

    public LoadingScreenBackGround(){
        sprite = new Sprite(spriteTextures.LoadingScreenSprite);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
