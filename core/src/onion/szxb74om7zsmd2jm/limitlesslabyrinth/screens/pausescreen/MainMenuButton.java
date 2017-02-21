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

/**
 * Created by 226812 on 2/21/2017.
 */
public class MainMenuButton extends Actor {

    private static Sprite sprite;

    public MainMenuButton(){
        sprite = new Sprite(spriteTextures.MainMenuButtonSprite);
        sprite.setPosition(MainMenu.getViewport().getScreenWidth()/2 - sprite.getWidth()/2, MainMenu.getViewport().getScreenHeight()/2 - sprite.getHeight());
        setBounds(this.sprite.getX(), this.sprite.getY(), this.sprite.getWidth(), this.sprite.getHeight());
        setTouchable(Touchable.enabled);

        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(button == Input.Buttons.LEFT){
                    LimitlessLabyrinth.setMainMenu();
                }
                return true;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                sprite.setScale(1.1f);
                setBounds(sprite.getX() - (sprite.getWidth()/2 * (.1f)), sprite.getY() - (sprite.getHeight()/2 * (.1f)), sprite.getWidth() * 1.1f, sprite.getHeight() * 1.1f);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
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
        sprite.setPosition(MainMenu.getViewport().getScreenWidth()/2 - sprite.getWidth()/2, MainMenu.getViewport().getScreenHeight()/2 + sprite.getHeight()/2);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }
}
