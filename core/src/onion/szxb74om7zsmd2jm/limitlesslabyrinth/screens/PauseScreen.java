package onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.mainmenu.BackGround;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.mainmenu.ExitButton;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.mainmenu.PlayButton;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.pausescreen.PauseBackGround;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.pausescreen.ResumeButton;

/**
 * Created by chris on 2/18/2017.
 */
public class PauseScreen implements Screen {
    private static Stage stage;
    private static ResumeButton resumeButton;
    private static PauseBackGround pauseBackGround;
    private static ExitButton exitButton;

    public static ScreenViewport getViewport() {
        return viewport;
    }

    private static ScreenViewport viewport;

    @Override
    public void show() {
        viewport = new ScreenViewport();
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        pauseBackGround = new PauseBackGround();
        resumeButton = new ResumeButton();
        exitButton = new ExitButton();

        stage.addActor(pauseBackGround);
        stage.addActor(resumeButton);
        stage.addActor(exitButton);
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
        resumeButton.updateSpritePosition();
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
