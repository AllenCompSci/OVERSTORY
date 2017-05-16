package onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.mainmenu.BackGround;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.mainmenu.ExitButton;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.mainmenu.PlayButton;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.pausescreen.MainMenuButton;
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
    private static MainMenuButton mainMenuButton;

    public static ScreenViewport getViewport() {
        return viewport;
    }

    private static ScreenViewport viewport;

    public static InputProcessor inp = new InputAdapter() {
        @Override
        public boolean scrolled(int amount) {
            if (Play.getGui().getIsBackpackOpen()) {
                if (amount == 1) {
                    Play.setBackpackup(true);
                    Play.setBackpackdn(false);
                } else if (amount == -1) {
                    Play.setBackpackdn(true);
                    Play.setBackpackup(false);
                }
            }

            return true;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            if (Play.getGui().getIsBackpackOpen()) {
                if (button == Input.Buttons.RIGHT) {
                    Play.setSwitchitem(true);
                } else if (button == Input.Buttons.MIDDLE) {
                    Play.setSwitchslot(true);
                }
                return true;
            }
            return true;
        }
    };

    @Override
    public void show() {
        viewport = new ScreenViewport();
        stage = new Stage(viewport);
        InputMultiplexer mult = new InputMultiplexer(stage, inp);
        Gdx.input.setInputProcessor(mult);

        pauseBackGround = new PauseBackGround();
        resumeButton = new ResumeButton();
        exitButton = new ExitButton();
        mainMenuButton = new MainMenuButton();

        stage.addActor(pauseBackGround);
        stage.addActor(resumeButton);
        stage.addActor(exitButton);
        stage.addActor(mainMenuButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        Play.getRenderer().getBatch().begin();
        Play.getGui().update();
        Play.getGui().input();
        Play.getRenderer().getBatch().end();
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
