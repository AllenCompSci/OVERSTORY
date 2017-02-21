package onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.pausescreen;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;

/**
 * Created by chris on 2/18/2017.
 */
public class PauseBackGround extends Actor{
    private static Sprite sprite;

    public PauseBackGround(){
        sprite = new Sprite(spriteTextures.PauseScreenBackground);
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
