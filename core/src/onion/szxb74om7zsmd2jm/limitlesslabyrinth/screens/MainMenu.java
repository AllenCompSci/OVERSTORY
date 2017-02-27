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
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.LimitlessLabyrinth;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.mainmenu.BackGround;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.mainmenu.ExitButton;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.mainmenu.NewGameButton;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.mainmenu.PlayButton;

/**
 * Created by chris on 2/16/2017.
 */
public class MainMenu implements Screen {
    private static Stage stage;
    private static PlayButton playButton;
    private static BackGround backGround;
    private static ExitButton exitButton;
    private static NewGameButton newGameButton;

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
        if(!LimitlessLabyrinth.isPlayerDeath()) {
            playButton = new PlayButton();
        }
        exitButton = new ExitButton();
        newGameButton = new NewGameButton();

        stage.addActor(backGround);
        if(!LimitlessLabyrinth.isPlayerDeath()) {
            stage.addActor(playButton);
        }
        stage.addActor(exitButton);
        stage.addActor(newGameButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
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
