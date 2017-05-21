package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.Potion;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.regenPotion;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.traps.TurretItem;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics.Detection;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

import java.util.Random;

/**
 * Created by chris on 5/15/2017.
 */
public class BossEnemy extends Enemy {
    private Animation<TextureRegion> animation;
    float stateTime;
    public static DIRECTION ENEMYFACING;
    boolean is64;
    private boolean flipLEFT;
    public BossEnemy(float x, float y, int level, TiledMapTileLayer collisionLayer, int row, int col, float speed, Play.MonsterType monster, String Weapon)
    {
        super(x, y, level, collisionLayer);

        weapon = spriteTextures.giveAWeapon(level, Weapon);

        is64 = false;
        ENEMYFACING = DIRECTION.SOUTH;
        flipLEFT = false;
        this.xpDrop = determineXP(level);
        animation = createAnimation(spriteTextures.sheet(monster), col, row, speed);
        stateTime = 0f;
        this.sprite = new Sprite(spriteTextures.stand(monster));
        if(sprite.getWidth() == 64){
            is64 = true;
            sprite = new Sprite(spriteTextures.basic32);
        }
        this.health = determineHealth(level);
        this.fullHealth = health;
        this.dmg = 10f;
        sprite.setPosition(collisionLayer.getTileWidth() * x-1, collisionLayer.getTileHeight() * y+1);
        detection = new Detection(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight(), 100);
        this.x = sprite.getX();
        this.y = sprite.getY();
    }

    @Override
    public int determineXP(int level) {
        return weapon.getLvl();
    }

    @Override
    public float determineHealth(int level) {
        float h = 20f;
        for(int i = 1; i < level; i++){
            h *= 1.2;
        }
        return h;
    }

    @Override
    public void draw(Batch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        sprite.setPosition(x,y);
        rotateSprite(currentFrame);
        //sprite.draw(batch);
        healthBar.setPosition(sprite.getX() - healthBarX, sprite.getY() + sprite.getHeight());
        lostHealthBar.setPosition(sprite.getX(), sprite.getY() + sprite.getHeight());
        lostHealthBar.draw(batch);
        healthBar.draw(batch);

        move();
        //Enemy checking for player
        setDir();
        DMGDETECT();

        if(reloadTime < System.currentTimeMillis() && detection.isInBigRadius(this)){
            Play.getEnemyProjectiles().add(weapon.getProjectile(sprite.getX() + sprite.getWidth()/4, sprite.getY() + sprite.getHeight()/4, Play.getPlayer().getSprite().getX(), Play.getPlayer().getSprite().getY(), "Enemy"));
            reloadTime = (weapon.getCooldown() == 0) ? System.currentTimeMillis() + weapon.getCooldown() + 100 : System.currentTimeMillis() + weapon.getCooldown() + 1000;
        }


    }

    /*
  This method needs a transition smoothing effect but is generalized.
             */
    public void rotateSprite(TextureRegion currentFrame){

        if(ENEMYFACING == DIRECTION.SOUTH || ENEMYFACING == DIRECTION.SOUTHWEST){
            // currentFrame.flip(true, false); // MAKES ENEMIES FACE UPWARDS
        }
        else if(ENEMYFACING == DIRECTION.NORTH || ENEMYFACING == DIRECTION.NORTHEAST){
            //currentFrame.flip(false, true);
        }
        else if(ENEMYFACING == DIRECTION.EAST || ENEMYFACING == DIRECTION.SOUTHEAST){
            // System.out.println("WEST");
            //currentFrame.flip(true, false);
        }
        else{

        }
        Play.getRenderer().getBatch().draw(currentFrame, x-1,y+1);


    }

    public static Animation<TextureRegion> createAnimation(Texture spriteSheet, int col, int row, float speed){
        TextureRegion[][] tmp = TextureRegion.split(spriteSheet, spriteSheet.getWidth() / col, spriteSheet.getHeight() / row);
        TextureRegion[] spriteFrames = new TextureRegion[row*col];
        int index = 0;
        for(int i = 0; i < row; i++) { // rows
            for (int j = 0; j < col; j++) { // cols
                spriteFrames [index++] = tmp[i][j];
            }
        }
        return new Animation<TextureRegion>(speed, spriteFrames);
    }

    public void setDir() {
        float x1 = Player.CharX;
        float y1 = Player.CharY;
        x = sprite.getX();
        y = sprite.getY();
        float padding = 16f; //pixels padding
        if (x1 + padding > x || x1 - padding > x) {

            if (y1 + padding > y || y1 - padding > y) {
                ENEMYFACING = DIRECTION.NORTHEAST;
            } else if (y1 + padding < y || y1 - padding < y) {
                ENEMYFACING = DIRECTION.SOUTHEAST;
            } else {
                ENEMYFACING = DIRECTION.EAST;
            }
        } else if (x1 + padding < x || x1 - padding < x) {

            if ((y1 + padding) > y || (y1 - padding) > y) {
                ENEMYFACING = DIRECTION.NORTHWEST;
                //System.out.println("NORTHWEST");
            } else if ((y1+ padding) < y || (y1 - padding) < y ) {
                ENEMYFACING = DIRECTION.SOUTHWEST;
                //System.out.println("SOUTHWEST");
            } else {
                ENEMYFACING = DIRECTION.WEST;
                //System.out.println("POSS WEST");
            }
        } else {
            if (y1 + padding > y || y1 - padding > y) {
                ENEMYFACING = DIRECTION.NORTH;
            } else {
                ENEMYFACING = DIRECTION.SOUTH;
            }
        }
    }

    @Override
    public void onDeath() {
        /** Random chance of dropping weapon, Puts weapon into the player's inventory */
        Random rand = new Random();
        int VAL;
        VAL = rand.nextInt(1000);

        if(VAL == 4 || VAL == 44 || VAL == 444){
            Play.getGui().getBackpack().addToBackpack(new TurretItem());
        }
        if(VAL == 555){
            Play.getGui().getBackpack().addToBackpack(new regenPotion(level));
        }
        if(VAL == 7 || VAL == 77 || VAL == 777){
            Play.getGui().getBackpack().addToBackpack(new Potion(level));
        }
        if(VAL >= 300 && VAL <= 400){
            Play.getGui().getBackpack().addToBackpack(weapon);
        }

        Play.getIsBossMapComplete().replace(Play.getMapPath(), false, true);

        if((Play.getKillCount().get(Play.getMapPath()) <= (int)Play.getMap().getLayers().get(0).getProperties().get("LevelCap"))){
            Play.getPlayer().setXp(Play.getPlayer().getXp() + this.getXpDrop());
        }
        else{
            Play.getPlayer().setXp(Play.getPlayer().getXp() + 1);
        }
        Play.getEnemies().set(Play.getEnemies().indexOf(this, true), null);
        Play.getEnemies().removeIndex(Play.getEnemies().indexOf(null, true));
    }
}
