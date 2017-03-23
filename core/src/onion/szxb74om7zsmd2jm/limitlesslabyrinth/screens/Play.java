package onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.maps.MapProperties;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.LimitlessLabyrinth;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.*;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.enemies.Brute;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.enemies.Goblin;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.enemies.Orc;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.enemies.*;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.turrets.Turret;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics.Pathfinding;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.threads.Spawn;

import java.awt.geom.Point2D;
import java.util.*;
import java.util.function.BiFunction;

/**
 * Created by chris on 1/19/2017.
 */
public class Play implements Screen {

    public enum MonsterType {
        ASH, BRUTE, GOBLIN, ORC, DEMON, DRAGON, HYDRA, BRITTLESKELETON, BUNNY, DEMONSKELETON, ELITEORC, GHOST, GOZZLER, ORCBASE, ORCLEADER, PIRATEBUCANEER, PIRATECUTTHROAT, PRIESTESS, RIFTFRAGMENT, WATERELEMENT, SQUIRREL, VAMPIRE, WARLOCK, ZABWARLOCK,
        AMBASSADOR, ANCIENTSCARAB, BARBAXE, WRAITH, BOGRAIDER, BONEBEAST, TOPHATNOBLE, CARNIPHILA, UNDEADCAVEBEAR, CLIFFSTRIDER, CORLERIANBARB, CRAWLER, CRUSHEDWALKER,
        CRYSTALWOLF, DARKSTALKER, DEATHSTRIKE, DEEPTERROR, DESTROYER, DEVORGA, DIABOLICIMP, EARTHELMENTAL, GLOOTH, ENERGYELEMENTAL, OLDENERGYELEMENTAL, ENERGYSCORPION, ENRAGEDCRYSTALGOLEM, ENRAGEDGOLEM,
        ETERNALGUARD, FERMRUMBRAS, GHOSTFERMRUMBRAS, FIREELEMENTAL, GARGOYLE, GLOOMBRINGER, GLOOTHMASHER, GORGON, GRIM, GRIMLEECH, HELLFLAYER, HELLHOUND, HIVEROVERSEER, INFECTEDWEEPER, INFERNALIST, JACKOLANTERN, JUGGERNAUNT, LADYBUG, LIZARDCHOSEN,
        LIZARDHIGHGUARD, LIZARDLEGIONAIRE, LIZARDPRIEST, LIZARDZAOGUN, LOSTSOUL, MADMAGE, MASSIVERFIREELEMENTAL, MEDUSA, MIDNIGHTPANTHER, NIGHTMAREGAZ, NOBELLION, ORCMAURADER, ORCRIDER, OREWALKER, PHRODOMO, PINATADRAGON, PIRATECORSAIR, PIRATEGHOST,
        PIRATEMARAUDER, PITBATTLER1, PITBATTLER2, PLAGUESMITH, PURPLETURTLE, REALITYREAVER, RIFT, RORC, SANDSCORPION, SEACRESTSERPENT, SEASERPENT, SERPENTSPAWN, SLIME, SNAKEGOD, SPECTRE, SPIRIT, SPITTER, STONEGOLEM, SVENBARB, THORNFIREWOLF, UNDEADGLADIATOR,
        VEXCLAW, VULCONGRA, WASPOID, WATERELEMENTAL, WATERSERPENT, WEAKDEMON, WEREWOLF, WYVERN, YALAHAR,
        /* 32x32 (1x2) */
        ACOLYTE, BLOODFIST, BLOODHAND, BLOOMOFDOOM, DARKAPPRENTICE,  DOCTOR, DOOMSDAY, ENRAGEDGHOST, EYESERVANT, FIREDEVIL,
        NECROMANCER, ORCBERSERKER,  ORCWARLORD, ORCWARRIOR, SHADOWPUPIL, BANSHEE, MUMMY,
        /*32x32 (2x4)*/
        DAWNASURA, GOBLINBASIC, MIDNIGHTASURA,ORCSHAMAN,ORCSPEARMAN,
        /*32x32(1x3) */
        DEATHSLICER,
        /* 64  (1x2)*/
        ARMADILE, DARAKAN, FROSTSERVANT, HEARLDOFDOOM, LIZARDMAGE, MENACE, TIGER, WITCH, SHIVERSLEEP,

        ASHMUNRAH, ASKARADEMON, ASKARALORD, ASKARAPRINCE, BEHEMOTH, BLIGHTWALKER, BLUEDJINN, CRYSTALSPIDERS, CURSEDHAND,
        CYCLOPSSMITH, DAMAGEDWORKERGOLEM, DIAMONDSERVANT, DRAGONHATCH, DRAGONLORDHATCH, DRAKENABOMINATION, DRAKENELITE,
        DRAKENSPELLWEAVER, DRAKENWARMASTER, EFREET, ELDERWYRM, EYEOBSERVER, FROSTDRAGON, FROSTGIANT, FROSTGIANTESS,
        GHASTLYDRAGON, GLOOTHMINO, GNOMEEVIL, GOLDENSERVANT, GREENDJINN, LICH, MINOAMAZON, PHAROH, SHABURAKLORD,
        SHABURAKPRINCE, SILENCER, SPIDRISELITE, THEPALECOUNT, TYRN, UNDEADDRAGON, VAMPIREBRIDE, VAMPIREOVERLORD,
        VAMPIREVISCOUNT, WARGOLEM, WORMPRIESTESS, WYRM, YETI,
        MARID, METALGARGOYLE, MOOHTANT,
        BEHOLDER, ELDERBEHOLDER, EVILEYE,

        /* 64 (2x4) */
        DRYAD, HYPNOTOAD, IRONBLIGHT, LION, ORCRAVANGER, ORCLOPS, PITBERSERKER, PITBLACKLING,
        CYCLOPS, DRAGONGREEN, DRAGONLING, DRAGONWARDEN, DRAGONZYRTARCH, DRAPTOR,


        GIANTSPIDER, GRAVEDIGGER, OGREBRUTE, OGRESHAMAN, PROFESSORMAXXEN, TARANTULA, TAZHADUR, THEFIRSTDRAGON,
        WAILINGWIDOW, WALKER, ZORVORAX

    }


    public static int lvlTileWidth; //Width of map in tiles
    public static int lvlTileHeight; //Height of map in tiles
    public static int lvlPixelWidth; //Width of map in pixels
    public static int lvlPixelHeight; //Height of map in pixels
    public static int tilePixelWidth; //Width of tile in pixels
    public static int tilePixelHeight; //Height of tile in pixels

    private static long garbageTime = 0;
    public static TiledMap getMap() {
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
    private static Array<Enemy> enemiesEmpty = new Array<Enemy>();
    public static Array<Projectile> getProjectiles() {
        return projectiles;
    }
    private static Array<Projectile> projectiles = new Array<Projectile>();
    private static Array<Projectile> projectilesEmpty = new Array<Projectile>();
    public static Array<Projectile> getEnemyProjectiles() {
        return enemyProjectiles;
    }
    private static Array<Projectile> enemyProjectiles = new Array<Projectile>();
    private static Array<Projectile> enemyProjectilesEmpty = new Array<Projectile>();
    public static Array<Wall> getWalls(){return walls;}
    private static Array<Turret> turretsEmpty = new Array<Turret>();
    private int[][] spawnTiles;
    public static int[][] collisionTiles;
    private long time = 0;
    private long count;
    public static void addWall(Wall wallType){
        walls.add(wallType);
    }
    private static int spawnCount = 100;
    public static Gui getGui() {
        return gui;
    }
    private static Gui gui = new Gui();
    private static int CollisionLayerNum = 1;
    public static Pathfinding path;
    private static int movePaths;
    public static int getSpawnLimit() {
        return spawnLimit;
    }
    private static int spawnLimit = 0;
    public static int getSpawnGroupRange() {
        return spawnGroupRange;
    }
    private static int spawnGroupRange;
    public static int getSpawnGroupStart() {
        return spawnGroupStart;
    }
    public static void setSpawnGroupStart(int spawnGroupStart) {
        Play.spawnGroupStart = spawnGroupStart;
    }
    private static int spawnGroupStart;
    private static Array<TiledMapTile> SpawnTiles;
    public static String getSpawnArea() {
        return spawnArea;
    }
    public static void setSpawnArea(String spawnArea) {
        Play.spawnArea = spawnArea;
    }
    public static int[][] getPlayerPOS() {
        return playerPOS;
    }
    private static int[][] playerPOS;
    private static String spawnArea = "Area1";
    private static Array<Wall> wallsEmpty = new Array<Wall>();
    private static Array<Wall> walls = new Array<Wall>();
    public static String getMapPath() {
        return mapPath;
    }
    private static String mapPath;
    public static Map<String, Array<Turret>> getTurrets() {
        return turrets;
    }
    private static Map<String, Array<Turret>> turrets = new HashMap<String, Array<Turret>>();
    private static Map<String, Integer> KillCount = new HashMap<String, Integer>();


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

    public static void reset(){
        Play.player.reset();
        Play.gui.reset();
        Play.gui.getBackpack().reset();
        movePaths = 0;
        /** Reset the Play static variables */
        spawnArea = "Area1";
        Play.enemies = Play.enemiesEmpty;
        Play.projectiles = Play.projectilesEmpty;
        Play.enemyProjectiles = Play.enemyProjectilesEmpty;
        Play.walls = Play.wallsEmpty;
        turrets = new HashMap<String, Array<Turret>>();
        KillCount = new HashMap<String, Integer>();
        Play.spawnCount = 0;
        Play.garbageTime = 0;
    }

    public static void changeMap(){
        movePaths = 0;
        //path.update();
        spawnArea = "Area0";
        Play.enemies = Play.enemiesEmpty;
        Play.projectiles = Play.projectilesEmpty;
        Play.enemyProjectiles = Play.enemyProjectilesEmpty;
        Play.walls = Play.wallsEmpty;
        Play.spawnCount = 0;
        Play.garbageTime = 0;
    }

    public Play(String PathToMap, int spawnLimit, int spawnGroupRange, int spawnGroupStart){
        enemiesEmpty = new Array<>();
        projectilesEmpty = new Array<>();
        enemyProjectilesEmpty = new Array<>();
        turretsEmpty = new Array<>();
        wallsEmpty = new Array<>();
        Play.spawnLimit = spawnLimit;
        Play.spawnGroupRange = spawnGroupRange;
        Play.spawnGroupStart = spawnGroupStart;
        map = new TmxMapLoader().load(PathToMap);

        MapProperties properties = map.getProperties();
        lvlTileWidth = properties.get("width", Integer.class);
        lvlTileHeight = properties.get("height", Integer.class);
        tilePixelWidth = properties.get("tilewidth", Integer.class);
        tilePixelHeight = properties.get("tileheight", Integer.class);
        lvlPixelWidth = lvlTileWidth * tilePixelWidth;
        lvlPixelHeight = lvlTileHeight * tilePixelHeight;

        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.zoom = zoom;
        camera.setToOrtho(false);
        playerPOS = (checkMapLayerFor((TiledMapTileLayer) map.getLayers().get(2), "StartPosition"));
        player = new Player(20, 20, 1, (TiledMapTileLayer) map.getLayers().get(CollisionLayerNum));

        spawnTiles = (checkMapLayerFor((TiledMapTileLayer) map.getLayers().get(2), "spawnEnemy"));
        collisionTiles = (checkMapLayerFor((TiledMapTileLayer) map.getLayers().get(1), "blocked"));

        spawnArea = "Area1";
        spawnTiles = (checkMapLayerForSpawnArea((TiledMapTileLayer) map.getLayers().get(2), "spawnEnemy"));
        SpawnTiles = (checkMapLayerForArray((TiledMapTileLayer) map.getLayers().get(2), "spawnEnemy"));

        Gdx.input.setInputProcessor(null);
        //path = new Pathfinding();
        movePaths = 0;
        mapPath = LimitlessLabyrinth.getMapPath();

        turrets.putIfAbsent(mapPath, turretsEmpty);
        KillCount.putIfAbsent(mapPath, 1);


    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void render(float delta) {

        /** Check for change in spawnArea */
        checkSpawnArea((TiledMapTileLayer) map.getLayers().get(2));

        /** Check for portal to other realm */
        checkForPortal((TiledMapTileLayer) map.getLayers().get(1));

        /** Open the pauseMenu */
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            LimitlessLabyrinth.pauseScreen();
            //Gdx.app.exit();
        }

        if(System.currentTimeMillis() > garbageTime){
            Gdx.app.log("Enemies spawned so far", String.valueOf(count));
            garbageTime = System.currentTimeMillis() + 50000;
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setView(camera);
        renderer.render();
        renderer.getBatch().begin();

        camera.zoom = zoom;
        camera.position.set(player.getSprite().getX() + player.getSprite().getWidth()/2, player.getSprite().getY() + player.getSprite().getHeight()/2, 0);
        for(Wall e : walls){
            e.draw(renderer.getBatch());
            if(walls.indexOf(null, true) != -1) {
                walls.removeIndex(walls.indexOf(null, true));
            }
        }
        for(Projectile i : projectiles){
            i.draw();
            if(System.currentTimeMillis() > i.getTime()){
                i.remove();
            }
            if(projectiles.indexOf(null, true) != -1){
                Play.getProjectiles().removeIndex(Play.getProjectiles().indexOf(null, true));
            }
        }
        for(Projectile i : enemyProjectiles){
            i.draw();
            if(System.currentTimeMillis() > i.getTime()){
                i.remove();
            }
            if(enemyProjectiles.indexOf(null, true) != -1){
                Play.getEnemyProjectiles().removeIndex(Play.getEnemyProjectiles().indexOf(null, true));
            }
        }

        for(Turret i : turrets.get(mapPath)){
            i.draw();
        }


        //renders the enemies
        for(Enemy i : enemies){
            i.draw(renderer.getBatch());
            //checks if enemy is dead
            if(i.getHealth() <= 0) {
                spawnCount++;
                if(spawnCount >= 100){
                    KillCount.replace(mapPath, KillCount.get(mapPath),KillCount.get(mapPath) + 1);
                    spawnCount = 0;
                }
                i.onDeath();

            }
            if(enemies.indexOf(null, true) != -1) {
                enemies.removeIndex(enemies.indexOf(null, true));
            }
        }

        player.draw(renderer.getBatch());
        /**if(movePaths++ == 9 && getEnemies().size < 10){
            movePaths = 0;
            path.update();
        }*/

        gui.input();
        gui.update();
        camera.position.set(player.getSprite().getX() + player.getSprite().getWidth()/2, player.getSprite().getY() + player.getSprite().getHeight()/2, 0);
        camera.update();

        renderer.getBatch().end();
        if(getEnemies().size < spawnLimit && spawnTiles.length > 0) {
            MonsterType monster;
            //Spawning in enemies every n seconds
            Random rand = new Random();

            int num = rand.nextInt(spawnTiles.length);
            if (System.currentTimeMillis() > time) {
                if(SpawnTiles.get(num).getProperties().get("spawnEnemy").equals(spawnArea)) {
                    spawnEnemy(spawnTiles[num][0], spawnTiles[num][1], KillCount.get(mapPath), (TiledMapTileLayer) getMap().getLayers().get(CollisionLayerNum), (int) SpawnTiles.get(num).getProperties().get("SpawnRange"), (int) SpawnTiles.get(num).getProperties().get("SpawnStart"));
                }
                time = System.currentTimeMillis() + 500;

            }
        }

        if(player.getHealth() <= 0){
            LimitlessLabyrinth.setPlayerDeath(true);
            LimitlessLabyrinth.setMainMenu();
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
    public void spawnEnemy(float x, float y, int level, TiledMapTileLayer collisionLayer, int spawnRange, int spawnStart){
        count++;
        enemies.add(new RandomEnemySpawn(x,y,level,collisionLayer, .2f, spriteTextures.makeAMonster(spawnRange, spawnStart)));

        //enemies.add(new Brute(x, y, level, collisionLayer));
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

    public int[][] checkMapLayerForSpawnArea(TiledMapTileLayer layer, String string){
        int count = 0;
        for(int x = 0; x < layer.getWidth(); x++){
            for(int y = 0; y < layer.getHeight(); y++){
                if(layer.getCell(x, y).getTile().getProperties().containsKey(string) && (layer.getCell(x, y).getTile().getProperties().get(string)).equals(spawnArea)) count++;
            }
        }
        int[][] tiles = new int[count][2];
        count = 0;
        for(int x = 0; x < layer.getWidth(); x++){
            for(int y = 0; y < layer.getHeight(); y++){
                if(layer.getCell(x, y).getTile().getProperties().containsKey(string) && (layer.getCell(x, y).getTile().getProperties().get(string)).equals(spawnArea)) {
                    tiles[count][0] = x;
                    tiles[count++][1] = y;
                }
            }
        }
        return tiles;
    }

    public Array<TiledMapTile> checkMapLayerForArray(TiledMapTileLayer layer, String string){
        Array<TiledMapTile> tiledMapTiles = new Array<TiledMapTile>();
        for(int x = 0; x < layer.getWidth(); x++){
            for(int y = 0; y < layer.getHeight(); y++){
                if(layer.getCell(x, y).getTile().getProperties().containsKey(string) && (layer.getCell(x, y).getTile().getProperties().get(string)).equals(spawnArea)) tiledMapTiles.add(layer.getCell(x,y).getTile());
            }
        }
        return tiledMapTiles;
    }

    public void checkSpawnArea(TiledMapTileLayer collisionLayer){
        if(collisionLayer.getCell((int)((player.getSprite().getX() + player.getSprite().getWidth()/2)/collisionLayer.getTileWidth()), (int)((player.getSprite().getY() + player.getSprite().getHeight()/2)/collisionLayer.getTileHeight())).getTile().getProperties().containsKey("SpawnTrigger")){
            spawnArea = (String) collisionLayer.getCell((int)((player.getSprite().getX() + player.getSprite().getWidth()/2)/collisionLayer.getTileWidth()), (int)((player.getSprite().getY() + player.getSprite().getHeight()/2)/collisionLayer.getTileHeight())).getTile().getProperties().get("SpawnTrigger");
            spawnTiles = (checkMapLayerForSpawnArea((TiledMapTileLayer) map.getLayers().get(2), "spawnEnemy"));
            SpawnTiles = (checkMapLayerForArray((TiledMapTileLayer) map.getLayers().get(2), "spawnEnemy"));
        }
    }

    public void checkForPortal(TiledMapTileLayer collisionLayer) {
        if (collisionLayer.getCell((int) ((player.getSprite().getX() + player.getSprite().getWidth() / 2) / collisionLayer.getTileWidth()), (int) ((player.getSprite().getY() + player.getSprite().getHeight() / 2) / collisionLayer.getTileHeight())).getTile().getProperties().containsKey("portal")) {
            LimitlessLabyrinth.LoadingScreen((String)(collisionLayer.getCell((int) ((player.getSprite().getX() + player.getSprite().getWidth() / 2) / collisionLayer.getTileWidth()), (int) ((player.getSprite().getY() + player.getSprite().getHeight() / 2) / collisionLayer.getTileHeight())).getTile().getProperties().get("portal")));
        }
    }

    public ArrayList<MapObject> checkObjectFor(MapLayer layer, String property){
        ArrayList<MapObject> o = new ArrayList<MapObject>();
        MapObjects objects = layer.getObjects();
        for(MapObject object : objects)
        {
            if(object.getProperties().containsKey(property))
            {
                o.add(object);
            }

        }
        return o;

    }

    public List<Point2D> convertObjectToTiles(MapObject o, TiledMapTileLayer l)
    {
        ArrayList<Point2D> tiles = new ArrayList<Point2D>();
        int x = o.getProperties().get("X", Integer.class);
        int y = o.getProperties().get("Y", Integer.class);
        int width = o.getProperties().get("Width", Integer.class);
        int height = o.getProperties().get("Height", Integer.class);
        int maxwidth = x + width;
        int maxheight = y + height;
        for(int i = x/l.getWidth(); i <= maxwidth/l.getWidth(); i++ )
        {
            for(int j = y/l.getHeight(); i <= maxheight/l.getHeight(); j++)
            {
                tiles.add(new Point2D.Double(x, y));
            }
        }

        return tiles;
    }


}