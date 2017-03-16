package onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.LimitlessLabyrinth;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.loadingscreen.LoadingScreenBackGround;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.mainmenu.BackGround;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.mainmenu.PlayButton;

/**
 * Created by chris on 2/24/2017.
 */
public class LoadingScreen implements Screen {
    private static Stage stage;
    private static LoadingScreenBackGround backGround;

    public static ScreenViewport getViewport() {
        return viewport;
    }

    private static ScreenViewport viewport;
    private static String mapPath;

    public LoadingScreen(String mapPath){
        LoadingScreen.mapPath = mapPath;
    }
    @Override
    public void show() {
        viewport = new ScreenViewport();
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        backGround = new LoadingScreenBackGround();

        stage.addActor(backGround);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        if(mapPath.equals("reset")){
            Play.reset();
            LimitlessLabyrinth.ChangeMap("test.tmx");
        }
        else {
            LimitlessLabyrinth.ChangeMap(mapPath);
        }
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
