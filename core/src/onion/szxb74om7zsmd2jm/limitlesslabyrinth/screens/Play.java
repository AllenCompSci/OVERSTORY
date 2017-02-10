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
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.enemies.*;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.threads.Spawn;

import java.util.Random;

/**
 * Created by chris on 1/19/2017.
 */
public class Play implements Screen {

    public enum MonsterType {
        ASH, BRUTE, GOBLIN, ORC, DEMON, DRAGON, HYDRA
    }

    private static long garbageTime = 0;
    public TiledMap getMap() {
        return map;
    }
    private static TiledMap map;
    public static OrthogonalTiledMapRenderer getRenderer() {
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
    public static OrthographicCamera getCamera() {
        return camera;
    }
    private static OrthographicCamera camera;
    public static Player getPlayer() {
        return player;
    }
    private static Player player;
    public static Array<Enemy> getEnemies() {
        return enemies;
    }
    private static Array<Enemy> enemies = new Array<Enemy>();
    public static Array<Projectile> getProjectiles() {
        return projectiles;
    }
    private static Array<Projectile> projectiles = new Array<Projectile>();
    private InputMultiplexer im;
    private int[][] spawnTiles;
    private long time = 0;
    public static int waveCount = 0;
    public static int getSpawnCount() {
        return spawnCount;
    }
    public static void setSpawnCount(int spawnCount) {
        waveCount++;
        Play.spawnCount = spawnCount;
    }
    private static int spawnCount = 0;
    public static Gui getGui() {
        return gui;
    }
    private static Gui gui = new Gui();


    public static Animation fourFrameAnimationCreator(String pathToSprite, int row, int col)
    {
        Texture img = new Texture(Gdx.files.internal(pathToSprite));

        /*
        Texture spriteSheet = new Texture(Gdx.files.internal("redDragon(64x64)(4col2row)(256x128).png"));
        TextureRegion[][] tmp = TextureRegion.split(spriteSheet, spriteSheet.getWidth() / 4, spriteSheet.getHeight() / 2);
        TextureRegion[] spriteFrames = new TextureRegion[8];
         */
        TextureRegion[][] tmpFrames = TextureRegion.split(img, img.getWidth()/col, img.getHeight()/row);

        TextureRegion[] animationFrames = new TextureRegion[row*col];
        int index = 0;

        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
               // System.out.println("i: " + i + ", j:" + j);
                animationFrames[index++] = tmpFrames[i][j];
            }
        }

        return new Animation(1f/4f, animationFrames);
    }
    public static Animation fourFrameAnimationCreator(Texture texture, int row, int col, float duration)
    {
        Texture img = texture;

        /*
        Texture spriteSheet = new Texture(Gdx.files.internal("redDragon(64x64)(4col2row)(256x128).png"));
        TextureRegion[][] tmp = TextureRegion.split(spriteSheet, spriteSheet.getWidth() / 4, spriteSheet.getHeight() / 2);
        TextureRegion[] spriteFrames = new TextureRegion[8];
         */
        TextureRegion[][] tmpFrames = TextureRegion.split(img, img.getWidth()/col, img.getHeight()/row);

        TextureRegion[] animationFrames = new TextureRegion[row*col];
        int index = 0;

        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                //System.out.println("i: " + i + ", j:" + j);
                animationFrames[index++] = tmpFrames[i][j];
            }
        }

        return new Animation(duration, animationFrames);
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

        if(System.currentTimeMillis() > garbageTime){
            System.gc();
            garbageTime = System.currentTimeMillis() + 10000;
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setView(camera);
        renderer.render();
        renderer.getBatch().begin();

        camera.zoom = zoom;
        camera.position.set(player.getSprite().getX() + player.getSprite().getWidth()/2, player.getSprite().getY() + player.getSprite().getHeight()/2, 0);

        for(Projectile i : projectiles){
            i.draw();
            if(System.currentTimeMillis() > i.getTime()){
                i.remove();
            }
            if(projectiles.indexOf(null, true) != -1){
                Play.getProjectiles().removeIndex(Play.getProjectiles().indexOf(null, true));
            }
        }

        //renders the enemies
        for(Enemy i : enemies){
            i.draw(renderer.getBatch());
            //checks if enemy is dead
            if(i.getHealth() <= 0) {
                player.setXp(player.getXp() + i.getXpDrop());
                enemies.set(enemies.indexOf(i, true), null);
                enemies.removeIndex(enemies.indexOf(null, true));
            }
            if(enemies.indexOf(null, true) != -1) {
                enemies.removeIndex(enemies.indexOf(null, true));
            }
        }
        player.draw(renderer.getBatch());
        gui.input();
        gui.update();
        camera.position.set(player.getSprite().getX() + player.getSprite().getWidth()/2, player.getSprite().getY() + player.getSprite().getHeight()/2, 0);
        camera.update();

        renderer.getBatch().end();
        if(spawnCount > 0 && getEnemies().size < 350 ) {
            MonsterType monster;
            monster = MonsterType.BRUTE;
            //Spawning in enemies every n seconds
            Random rand = new Random();
            
            
            int num = 0;
            num = rand.nextInt(spawnTiles.length);
            if (System.currentTimeMillis() > time) {
                int gen = rand.nextInt(4);
                 if(gen == 0)
                    monster = MonsterType.DEMON;
                else if(gen == 1)
                    monster = MonsterType.DRAGON;
                else if(gen ==2)
                    monster = MonsterType.BRUTE;
                else
                    monster = MonsterType.HYDRA;
                spawnEnemy(spawnTiles[num][0], spawnTiles[num][1], waveCount, (TiledMapTileLayer) getMap().getLayers().get(1), monster);
                time = System.currentTimeMillis() + 10;
            }
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
            case DEMON:
                enemies.add(new Demon(x,y,level, collisionLayer));
                break;
            case DRAGON:
                enemies.add(new Dragon(x,y,level,collisionLayer));
                break;
            case HYDRA:
                enemies.add(new Hydra(x,y,level,collisionLayer));
                break;
        }

        spawnCount--;

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
