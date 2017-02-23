package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.Array;
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
    public enum DIRECTION{ NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST, NONE};
    private DIRECTION move = DIRECTION.NONE;
    private boolean keepMoving = false; //Checks if the enemy needs to move around an object
    public int getXpDrop() {
        return xpDrop;
    }
    protected int xpDrop;
    protected float dmgTaken;
    public float getDmgTaken() {
        return dmgTaken;
    }
    public void setDmgTaken(float dmgTaken) {
        this.dmgTaken = dmgTaken;
    }
    protected Sprite healthBar = new Sprite(spriteTextures.healthBar);
    protected Sprite lostHealthBar = new Sprite(spriteTextures.lostHealthBar);
    protected float healthBarX = 0;
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
    private int Ecnt = 0;

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

        Ecnt++;
        if(Ecnt % 10 == 0)move();

        DMGDETECT();

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
        if(!keepMoving) {
            TileX = (int) ((sprite.getX() + sprite.getWidth() / 2) / Play.tilePixelWidth);
            TileY = (int) ((sprite.getY() + sprite.getHeight() / 2) / Play.tilePixelHeight);
            int[] nextCell = test(Play.lvlTileWidth, Play.lvlTileHeight, TileX, TileY, (int) ((Play.getPlayer().getSprite().getX() + Play.getPlayer().getSprite().getWidth() / 2) / Play.tilePixelWidth), (int) ((Play.getPlayer().getSprite().getY() + Play.getPlayer().getSprite().getHeight() / 2) / Play.tilePixelHeight), Play.collisionTiles);
            if (nextCell[0] != TileX) {
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
            } else if (nextCell[0] ==- 1){
                move = DIRECTION.NONE;
            }


            if (!detection.isInSmallRadius(this)) {
                moveEnemy(move);
            }

        }
        else{
            moveEnemyCont(move);
            keepMoving = false;
        }
    }

    private void moveEnemyCont(DIRECTION move){
        if(move == DIRECTION.SOUTHWEST){ //Down & Left
            sprite.setPosition((float) (TileX * Play.tilePixelWidth), (float) (TileY * Play.tilePixelHeight));
            keepMoving = true;
        }
        if(move == DIRECTION.SOUTHEAST){ //Down & Right
            sprite.setPosition((float) (TileX * Play.tilePixelWidth), (float) (TileY * Play.tilePixelHeight ));
            keepMoving = true;
        }
        if(move == DIRECTION.NORTHWEST){ //Up & Left
            sprite.setPosition((float) (TileX * Play.tilePixelWidth), (float) (TileY * Play.tilePixelHeight));
            keepMoving = true;
        }
        if(move == DIRECTION.NORTHEAST){ //Up & Right
            sprite.setPosition((float) (TileX * Play.tilePixelWidth) , (float) (TileY * Play.tilePixelHeight));
            keepMoving = true;
        }
    }

    private void moveEnemy(DIRECTION move){
        if(move == DIRECTION.SOUTHWEST){ //Down & Left
            TileX--;
            TileY--;
            sprite.setPosition((float) (TileX * Play.tilePixelWidth - Play.tilePixelWidth/2), (float) (TileY * Play.tilePixelHeight - Play.tilePixelHeight/2));
            keepMoving = true;
        }
        if(move == DIRECTION.SOUTHEAST){ //Down & Right
            TileX++;
            TileY--;
            sprite.setPosition((float) (TileX * Play.tilePixelWidth - Play.tilePixelWidth/2), (float) (TileY * Play.tilePixelHeight - Play.tilePixelHeight/2));
            keepMoving = true;
        }
        if(move == DIRECTION.NORTHWEST){ //Up & Left
            TileX--;
            TileY++;
            sprite.setPosition((float) (TileX * Play.tilePixelWidth - Play.tilePixelWidth/2), (float) (TileY * Play.tilePixelHeight - Play.tilePixelHeight/2));
            keepMoving = true;
        }
        if(move == DIRECTION.NORTHEAST){ //Up & Right
            TileX++;
            TileY++;
            sprite.setPosition((float) (TileX * Play.tilePixelWidth - Play.tilePixelWidth/2) , (float) (TileY * Play.tilePixelHeight - Play.tilePixelHeight/2));
            keepMoving = true;
        }
        if (move == DIRECTION.NORTH) { //Up
            TileY++;
            sprite.setY((float) (TileY * Play.tilePixelHeight));
        }
        if (move == DIRECTION.SOUTH) { //Down
            TileY--;
            sprite.setY((float) (TileY * Play.tilePixelHeight));
        }
        if (move == DIRECTION.WEST) { //Left
            TileX--;
            sprite.setX((float) (TileX * Play.tilePixelWidth));
        }
        if (move == DIRECTION.EAST) { //Right
            TileX++;
            sprite.setX((float) (TileX * Play.tilePixelWidth));
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
        Play.getPlayer().setXp(Play.getPlayer().getXp() + this.getXpDrop());
        Play.getEnemies().set(Play.getEnemies().indexOf(this, true), null);
        Play.getEnemies().removeIndex(Play.getEnemies().indexOf(null, true));
    }

    public void takeDMG(float dmg){
        health -= dmg;
        healthBarX += ((dmg / fullHealth) * sprite.getWidth()) / 2;
        healthBar.setScale(healthBar.getScaleX() - dmg / fullHealth, healthBar.getScaleY());
    }
}
