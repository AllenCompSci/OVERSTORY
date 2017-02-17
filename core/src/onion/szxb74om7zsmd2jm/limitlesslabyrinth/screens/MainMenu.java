package onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.mainmenu.BackGround;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.mainmenu.PlayButton;

/**
 * Created by chris on 2/16/2017.
 */
public class MainMenu implements Screen {
    private static Stage stage;
    private static PlayButton playButton;
    private static BackGround backGround;

    public static ScreenViewport getViewport() {
        return viewport;
    }

    private static ScreenViewport viewport;

    @Override
    public void show() {
        viewport = new ScreenViewport();
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        backGround = new BackGround();
        playButton = new PlayButton();

        stage.addActor(backGround);
        stage.addActor(playButton);
    }

    @Override
    public void render(float delta) {

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
        playButton.updateSpritePosition();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
