package onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Player;

/**
 * Created by chris on 1/19/2017.
 */
public class Play implements Screen {

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    private Player player;

    @Override
    public void show() {
        map = new TmxMapLoader().load("test.tmx");

        renderer = new OrthogonalTiledMapRenderer(map);

        camera = new OrthographicCamera();
        camera.zoom = .6f;
        camera.setToOrtho(false);

        player = new Player(new Sprite(new Texture("thor32.png")), (TiledMapTileLayer) map.getLayers().get(0));

        Gdx.input.setInputProcessor(player);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.set(player.getSprite().getX() + player.getSprite().getWidth()/2, player.getSprite().getY() + player.getSprite().getHeight()/2, 0);
        camera.update();

        renderer.setView(camera);
        renderer.render();
        renderer.getBatch().begin();
        player.draw(renderer.getBatch());
        renderer.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
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
        map.dispose();
        renderer.dispose();
    }
}