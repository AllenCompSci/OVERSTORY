package onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.pausescreen;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.LimitlessLabyrinth;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.MainMenu;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.PauseScreen;

/**
 * Created by chris on 2/18/2017.
 */
public class ResumeButton extends Actor {
    private static Sprite sprite;

    public ResumeButton(){
        sprite = new Sprite(spriteTextures.ResumeButton);
        sprite.setPosition(PauseScreen.getViewport().getScreenWidth()/2 - sprite.getWidth()/2, PauseScreen.getViewport().getScreenHeight()/2 + sprite.getHeight()/2);
        setBounds(this.sprite.getX(), this.sprite.getY(), this.sprite.getWidth(), this.sprite.getHeight());
        setTouchable(Touchable.enabled);

        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(button == Input.Buttons.LEFT){
                    LimitlessLabyrinth.setPlay();
                }
                return true;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                sprite.setPosition(PauseScreen.getViewport().getScreenWidth()/2 - sprite.getWidth()/2, PauseScreen.getViewport().getScreenHeight()/2 + sprite.getHeight()/2);
                sprite.setScale(1.1f);
                setBounds(sprite.getX() - (sprite.getWidth()/2 * (.1f)), sprite.getY() - (sprite.getHeight()/2 * (.1f)), sprite.getWidth() * 1.1f, sprite.getHeight() * 1.1f);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                sprite.setPosition(PauseScreen.getViewport().getScreenWidth()/2 - sprite.getWidth()/2, PauseScreen.getViewport().getScreenHeight()/2 + sprite.getHeight()/2);
                sprite.setScale(1f);
                setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public void updateSpritePosition(){
        sprite.setPosition(PauseScreen.getViewport().getScreenWidth()/2 - sprite.getWidth()/2, PauseScreen.getViewport().getScreenHeight()/2 + sprite.getHeight()/2);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }
}
