package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.Array;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Item;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.Potion;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.regenPotion;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.traps.TurretItem;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.invisProjectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

import java.util.ArrayList;
import java.util.Random;
import java.util.StringJoiner;

import static onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics.AStar.test;


/**
 * Created by chris on 1/21/2017.
 */

public class Enemy extends Entity{
    public float getFullHealth() {
        return fullHealth;
    }
    protected float fullHealth;
    public float getHealth() {
        return health;
    }
    public void setHealth(float health) {
        this.health = health;
    }
    protected float health;
    public enum DIRECTION{ NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST, NONE};
    private DIRECTION move = DIRECTION.NONE;
    private boolean keepMoving = false;
    private int mvct = 0;
    public int getXpDrop() {
        return xpDrop;
    }
    protected int xpDrop;
    protected float dmgTaken;
    protected Item weapon;
    protected long reloadTime = 0;
    public float getDmgTaken() {
        return dmgTaken;
    }
    public void setDmgTaken(float dmgTaken) {
        this.dmgTaken = dmgTaken;
    }
    protected Sprite healthBar = new Sprite(spriteTextures.healthBar);
    protected Sprite lostHealthBar = new Sprite(spriteTextures.lostHealthBar);
    protected float healthBarX = 0;
    private int[] nextCell = new int[2];
    public void setNextCell(int ix, int jy){
        nextCell[0] = ix;
        nextCell[1] = jy;
    }
    public float getX() {
        return x;
    }

    protected float x;

    public float getY() {
        return y;
    }

    protected float y;
    private int TileX;
    private int TileY;
    private int Tilesmoothnessx;
    private int Tilesmoothnessy;

    private int smoothness = 4;

    public Enemy(float x, float y, int level, TiledMapTileLayer collisionLayer) {
        super(x, y, level, collisionLayer);
    }

    @Override
    public void draw(Batch batch) {
        sprite.draw(batch);
        healthBar.setPosition(sprite.getX() - healthBarX, sprite.getY() + sprite.getHeight());
        lostHealthBar.setPosition(sprite.getX(), sprite.getY() + sprite.getHeight());
        lostHealthBar.draw(batch);
        healthBar.draw(batch);

        move();

        DMGDETECT();

     }

     public double distanceFromPlayer(){
        return Math.sqrt(Math.pow((this.getSprite().getX() + this.getSprite().getWidth()/2) - (Play.getPlayer().getSprite().getX() + Play.getPlayer().getSprite().getWidth()/2), 2) + Math.pow((this.getSprite().getY() + this.getSprite().getHeight()/2) - (Play.getPlayer().getSprite().getY() + Play.getPlayer().getSprite().getHeight()/2), 2));
     }

    public void DMGDETECT(){
        dmgTaken = detection.projectileInRadiusDmg(this);

        //Enemy checking for player
        if(detection.isInRadius(this)){
            //Enemy is hit
            if(Play.getGui().getEquipped().getType() == "melee" && !Play.getGui().getIsRefreshing()[Play.getGui().getSelected()]) {
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                    //Enemy loses health and is represented on the health bar
                    health -= Play.getPlayer().getDmg();
                    healthBarX += ((Play.getPlayer().getDmg() / fullHealth) * sprite.getWidth()) / 2;
                    healthBar.setScale(healthBar.getScaleX() - Play.getPlayer().getDmg() / fullHealth, healthBar.getScaleY());
                    Play.getGui().getRefreshItem()[Play.getGui().getSelected()].setScale(1f);
                    Play.getGui().setIsRefreshing(true, Play.getGui().getSelected());
                }
            }
        }

        /** Checking if hit by projectile */

        for(Projectile i : Play.getProjectiles()){
            if(i.getName() == "invis"){
                if (!i.getEnemiesHit().contains(this, true)) {
                    if (detection.isInvisProjectileInRadius(this, (invisProjectile) i)) {
                        i.getEnemiesHit().add(this);
                        health -= dmgTaken;
                        healthBarX += ((dmgTaken / fullHealth) * sprite.getWidth()) / 2;
                        healthBar.setScale(healthBar.getScaleX() - dmgTaken / fullHealth, healthBar.getScaleY());
                    }
                }
            }
            else {
                if (!i.getEnemiesHit().contains(this, true)) {
                    if (detection.isProjectileInRadius(this, i)) {
                        i.getEnemiesHit().add(this);
                        takeDMG(dmgTaken);
                    }
                }
            }
        }
    }

    @Override
    public void move() {
        if (!keepMoving) {

            TileX = (int) ((sprite.getX() + sprite.getWidth() / 2) / Play.tilePixelWidth);
            TileY = (int) ((sprite.getY() + sprite.getHeight() / 2) / Play.tilePixelHeight);
            Tilesmoothnessx = TileX * (32/smoothness);
            Tilesmoothnessy = TileY * (32/smoothness);

            nextCell = test(Play.lvlTileWidth, Play.lvlTileHeight, TileX, TileY, (int) ((Play.getPlayer().getSprite().getX() + Play.getPlayer().getSprite().getWidth() / 2) / Play.tilePixelWidth), (int) ((Play.getPlayer().getSprite().getY() + Play.getPlayer().getSprite().getHeight() / 2) / Play.tilePixelHeight), Play.collisionTiles);

            //System.out.println(String.valueOf(nextCell[0]) + " " + String.valueOf(nextCell[1]));
            //System.out.println(String.valueOf(TileX) + " " + String.valueOf(TileY));
            if(nextCell[0] == -1){
              move = DIRECTION.NONE;

            }
            else if (nextCell[0] != TileX) {
                if (nextCell[0] > TileX) {
                    if (nextCell[1] > TileY) {
                        move = DIRECTION.NORTHEAST;
                    } else if (nextCell[1] < TileY) {
                        move = DIRECTION.SOUTHEAST;
                    } else {
                        move = DIRECTION.EAST;
                    }
                } else {
                    if (nextCell[1] > TileY) {
                        move = DIRECTION.NORTHWEST;
                    } else if (nextCell[1] < TileY) {
                        move = DIRECTION.SOUTHWEST;
                    } else {
                        move = DIRECTION.WEST;
                    }
                }
            } else if (nextCell[1] != TileY) {
                if (nextCell[1] > TileY) {
                    move = DIRECTION.NORTH;
                } else {
                    move = DIRECTION.SOUTH;
                }
            } else {
                move = DIRECTION.NONE;
            }

            //System.out.println(String.valueOf(move));

            if (!detection.isInSmallRadius(this)) {
                moveEnemy(move);
                //System.out.println(String.valueOf(Tilesmoothnessx) + " " + String.valueOf(Tilesmoothnessy));
            }

        } else {
            moveEnemyCont(move);
            mvct++;
            if(mvct == (32/smoothness)-1){
                keepMoving = false;
            }
            //System.out.println(String.valueOf(Tilesmoothnessx) + " " + String.valueOf(Tilesmoothnessy));
        }
    }

    private void moveEnemyCont(DIRECTION move){
        if(move == DIRECTION.SOUTHWEST){ //Down & Left
            Tilesmoothnessx--;
            Tilesmoothnessy--;
            sprite.setPosition((float) (Tilesmoothnessx * smoothness), (float) (Tilesmoothnessy * smoothness));

        }
        if(move == DIRECTION.SOUTHEAST){ //Down & Right
            Tilesmoothnessx++;
            Tilesmoothnessy--;
            sprite.setPosition((float) (Tilesmoothnessx * smoothness), (float) (Tilesmoothnessy * smoothness));

        }
        if(move == DIRECTION.NORTHWEST){ //Up & Left
            Tilesmoothnessx--;
            Tilesmoothnessy++;
            sprite.setPosition((float) (Tilesmoothnessx * smoothness), (float) (Tilesmoothnessy * smoothness));

        }
        if(move == DIRECTION.NORTHEAST){ //Up & Right
            Tilesmoothnessx++;
            Tilesmoothnessy++;
            sprite.setPosition((float) (Tilesmoothnessx * smoothness) , (float) (Tilesmoothnessy * smoothness));

        }
        if (move == DIRECTION.NORTH) { //Up
            Tilesmoothnessy++;
            sprite.setY((float) (Tilesmoothnessy * smoothness));
        }
        if (move == DIRECTION.SOUTH) { //Down
            Tilesmoothnessy--;
            sprite.setY((float) (Tilesmoothnessy * smoothness));
        }
        if (move == DIRECTION.WEST) { //Left
            Tilesmoothnessx--;
            sprite.setX((float) (Tilesmoothnessx * smoothness));
        }
        if (move == DIRECTION.EAST) { //Right
            Tilesmoothnessx++;
            sprite.setX((float) (Tilesmoothnessx * smoothness));
        }
    }

    private void moveEnemy(DIRECTION move) {
        keepMoving = true;
        mvct = 0;
        if (move == DIRECTION.SOUTHWEST) { //Down & Left
            TileX--;
            TileY--;
            Tilesmoothnessx--;
            Tilesmoothnessy--;
            sprite.setPosition((float) (Tilesmoothnessx * smoothness), (float) (Tilesmoothnessy * smoothness));
        }
        if (move == DIRECTION.SOUTHEAST) { //Down & Right
            TileX++;
            TileY--;
            Tilesmoothnessx++;
            Tilesmoothnessy--;
            sprite.setPosition((float) (Tilesmoothnessx * smoothness), (float) (Tilesmoothnessy * smoothness));
        }
        if (move == DIRECTION.NORTHWEST) { //Up & Left
            TileX--;
            TileY++;
            Tilesmoothnessx--;
            Tilesmoothnessy++;
            sprite.setPosition((float) (Tilesmoothnessx * smoothness), (float) (Tilesmoothnessy * smoothness));
        }
        if (move == DIRECTION.NORTHEAST) { //Up & Right
            TileX++;
            TileY++;
            Tilesmoothnessx++;
            Tilesmoothnessy++;
            sprite.setPosition((float) (Tilesmoothnessx * smoothness), (float) (Tilesmoothnessy * smoothness));
        }
        if (move == DIRECTION.NORTH) { //Up
            TileY++;
            Tilesmoothnessy++;
            sprite.setY((float) (Tilesmoothnessy * smoothness));
        }
        if (move == DIRECTION.SOUTH) { //Down
            TileY--;
            Tilesmoothnessy--;
            sprite.setY((float) (Tilesmoothnessy * smoothness));
        }
        if (move == DIRECTION.WEST) { //Left
            TileX--;
            Tilesmoothnessx--;
            sprite.setX((float) (Tilesmoothnessx * smoothness));
        }
        if (move == DIRECTION.EAST) { //Right
            TileX++;
            Tilesmoothnessx++;
            sprite.setX((float) (Tilesmoothnessx * smoothness));

        }
    }

    // Default method for xp drop - gives no xp. should override in other classes

    /** These functions allow for the placement of a formula in the subclass. Make sure to override. **/
    public int determineXP(int level)
    {
        return 0;
    }

    public float determineHealth(int level)
    {
        return 0;
    }

    public float determineDamage(int level) {return 0;}

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

        if((Play.getKillCount().get(Play.getMapPath()) <= (int)Play.getMap().getLayers().get(0).getProperties().get("LevelCap"))){
            Play.getPlayer().setXp(Play.getPlayer().getXp() + this.getXpDrop());
        }
        else{
            Play.getPlayer().setXp(Play.getPlayer().getXp() + 1);
        }
        Play.getEnemies().set(Play.getEnemies().indexOf(this, true), null);
        Play.getEnemies().removeIndex(Play.getEnemies().indexOf(null, true));
    }

    public void Despawn(){
        Play.getEnemies().set(Play.getEnemies().indexOf(this, true), null);
        Play.getEnemies().removeIndex(Play.getEnemies().indexOf(null, true));
    }

    public void takeDMG(float dmg){
        health -= dmg;
        healthBarX += ((dmg / fullHealth) * sprite.getWidth()) / 2;
        healthBar.setScale(healthBar.getScaleX() - dmg / fullHealth, healthBar.getScaleY());
    }
}
