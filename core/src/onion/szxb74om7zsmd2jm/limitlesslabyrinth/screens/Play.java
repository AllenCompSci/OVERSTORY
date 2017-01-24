package onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Enemy;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Player;

/**
 * Created by chris on 1/19/2017.
 */
public class Play implements Screen {

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    public Player getPlayer() {
        return player;
    }
    private static Player player;
    private Array<Enemy> enemies = new Array<Enemy>();
    private InputMultiplexer im;
    private Stage stage;

    @Override
    public void show() {
        stage = new Stage();
        map = new TmxMapLoader().load("test.tmx");

        renderer = new OrthogonalTiledMapRenderer(map);

        camera = new OrthographicCamera();
        camera.zoom = .6f;
        camera.setToOrtho(false);

        player = new Player(new Sprite(new Texture("thor32.png")), 5, 5, 100f, (TiledMapTileLayer) map.getLayers().get(1));
        stage.addActor(player);

        Gdx.input.setInputProcessor(player);
        im = new InputMultiplexer(stage);
        Gdx.input.setInputProcessor(im);
        spawnEnemy(new Sprite(new Texture("still1.png")), 1, 1, 100f, (TiledMapTileLayer) map.getLayers().get(1));
        spawnEnemy(new Sprite(new Texture("still1.png")), 15, 15, 100f,  (TiledMapTileLayer) map.getLayers().get(1));
        spawnEnemy(new Sprite(new Texture("still1.png")), 16, 16, 100f,  (TiledMapTileLayer) map.getLayers().get(1));
        spawnEnemy(new Sprite(new Texture("still1.png")), 17, 17, 100f,  (TiledMapTileLayer) map.getLayers().get(1));
        spawnEnemy(new Sprite(new Texture("still1.png")), 18, 18, 100f,  (TiledMapTileLayer) map.getLayers().get(1));
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
        for(Enemy i : enemies){
            i.draw(renderer.getBatch());
            if(i.getHealth() <= 0) enemies.removeIndex(enemies.indexOf(i, true));
        }
        player.draw(renderer.getBatch());
        renderer.getBatch().end();
        stage.draw();
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

    public void spawnEnemy(Sprite sprite, float x, float y, float health, TiledMapTileLayer collisionLayer){
        enemies.add(new Enemy(sprite, x, y, health, collisionLayer));
        im.addProcessor(enemies.get(enemies.size - 1));
        stage.addActor(enemies.get(enemies.size - 1));
    }
}