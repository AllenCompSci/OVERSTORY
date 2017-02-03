package onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Enemy;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Gui;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Player;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.enemies.Brute;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.enemies.Goblin;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.enemies.Orc;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.enemies.ash;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.threads.Spawn;

import java.util.Random;

/**
 * Created by chris on 1/19/2017.
 */
public class Play implements Screen {

    public enum MonsterType {
        ASH, BRUTE, GOBLIN, ORC
    }

    public TiledMap getMap() {
        return map;
    }
    private static TiledMap map;
    public OrthogonalTiledMapRenderer getRenderer() {
        return renderer;
    }
    private static OrthogonalTiledMapRenderer renderer;
    public float getZoom() {
        return zoom;
    }
    public void setZoom(float zoom) {
        this.zoom = zoom;
    }
    private static float zoom = 1f;
    public OrthographicCamera getCamera() {
        return camera;
    }
    private static OrthographicCamera camera;
    public Player getPlayer() {
        return player;
    }
    private static Player player;
    private Array<Enemy> enemies = new Array<Enemy>();
    public Array<Projectile> getProjectiles() {
        return projectiles;
    }
    private static Array<Projectile> projectiles = new Array<Projectile>();
    private InputMultiplexer im;
    private int[][] spawnTiles;
    private long time = 0;
    public int getSpawnCount() {
        return spawnCount;
    }
    public void setSpawnCount(int spawnCount) {
        this.spawnCount = spawnCount;
    }
    private static int spawnCount = 0;
    public Gui getGui() {
        return gui;
    }
    private static Gui gui = new Gui();


    public static Animation fourFrameAnimationCreator(String pathToSprite)
    {
        Texture img = new Texture(pathToSprite);

        TextureRegion[][] tmpFrames = TextureRegion.split(img, 64, 64);

        TextureRegion[] animationFrames = new TextureRegion[4];
        int index = 0;

        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                animationFrames[index++] = tmpFrames[j][i];
            }
        }

        return new Animation(1f/4f, animationFrames);
    }

    @Override
    public void show() {
        map = new TmxMapLoader().load("test.tmx");

        renderer = new OrthogonalTiledMapRenderer(map);

        camera = new OrthographicCamera();
        camera.zoom = zoom;
        camera.setToOrtho(false);

        player = new Player(20, 20, 1, (TiledMapTileLayer) map.getLayers().get(1));

        im = new InputMultiplexer(player);
        Gdx.input.setInputProcessor(im);
        spawnTiles = (checkMapLayerFor((TiledMapTileLayer) map.getLayers().get(2), "spawnEnemy"));



    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        for(Projectile i : projectiles){
            Gdx.app.log("Direction", String.valueOf(Math.atan(i.getSlope())));
        }

        renderer.setView(camera);
        renderer.render();
        renderer.getBatch().begin();

        camera.zoom = zoom;
        camera.position.set(player.getSprite().getX() + player.getSprite().getWidth()/2, player.getSprite().getY() + player.getSprite().getHeight()/2, 0);

        /** Renders the projectiles */
        for(Projectile i : projectiles){
            i.draw();
        }

        //renders the enemies
        for(Enemy i : enemies){
            i.draw(renderer.getBatch());
            //checks if enemy is dead
            if(i.getHealth() <= 0) {
                //gives player exp
                player.setXp(player.getXp() + i.getXpDrop());

                //removes the enemy from the render
                enemies.removeIndex(enemies.indexOf(i, true));

                //
                player.setEnemiesAlive(player.getEnemiesAlive() - 1);
            }
        }
        player.draw(renderer.getBatch());
        gui.input();
        gui.update();
        camera.position.set(player.getSprite().getX() + player.getSprite().getWidth()/2, player.getSprite().getY() + player.getSprite().getHeight()/2, 0);
        camera.update();

        renderer.getBatch().end();
        if(spawnCount > 0) {
            MonsterType monster;
            monster = MonsterType.BRUTE;
            //Spawning in enemies every n seconds
            Random rand = new Random();
            int num = 0;
            num = rand.nextInt(spawnTiles.length);
            if (System.currentTimeMillis() > time) {
                spawnEnemy(spawnTiles[num][0], spawnTiles[num][1], 1, (TiledMapTileLayer) getMap().getLayers().get(1), monster);
                time = System.currentTimeMillis() + 1;
            }
            spawnCount--;
        }
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

    //spawns in an enemy
    public void spawnEnemy(float x, float y, int level, TiledMapTileLayer collisionLayer, MonsterType monster){
        switch (monster){
            case ASH:
                enemies.add(new ash(x, y, level, collisionLayer));
                break;
            case BRUTE:
                enemies.add(new Brute(x, y, level, collisionLayer));
                break;
            case GOBLIN:
                enemies.add(new Goblin(x, y, level, collisionLayer));
                break;
            case ORC:
                enemies.add(new Orc(x, y, level, collisionLayer));
                break;
        }


        //enemies.add(new Brute(x, y, level, collisionLayer));
        im.addProcessor(enemies.get(enemies.size - 1));
    }

    //checks a TMX map layer tiles for a property
    public int[][] checkMapLayerFor(TiledMapTileLayer layer, String string){
        int count = 0;
        for(int x = 0; x < layer.getWidth(); x++){
            for(int y = 0; y < layer.getHeight(); y++){
                if(layer.getCell(x, y).getTile().getProperties().containsKey(string)) count++;
            }
        }
        int[][] tiles = new int[count][2];
        Gdx.app.log(String.valueOf(layer.getHeight()), string.valueOf(layer.getWidth()));
        count = 0;
        for(int x = 0; x < layer.getWidth(); x++){
            for(int y = 0; y < layer.getHeight(); y++){
                if(layer.getCell(x, y).getTile().getProperties().containsKey(string)) {
                    tiles[count][0] = x;
                    tiles[count++][1] = y;
                }
            }
        }
        return tiles;
    }
}