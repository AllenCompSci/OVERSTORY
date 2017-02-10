package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

import java.util.ArrayList;
import java.util.Random;
import java.util.StringJoiner;


/**
 * Created by chris on 1/21/2017.
 */

public class Enemy extends Entity{
    private ArrayList<Integer> canmove = new ArrayList<Integer>(); //Specifies which directions an enemy can move
    private boolean isNavigating = false; //Checks if the enemy needs to move around an object
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
    private static AStar findPath = new AStar();
     private static Play pl = new Play();
     private static int[][] collideLocations = pl.getCollideLocations();

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

        dmgTaken = detection.projectileInRadiusDmg(this);

        //Enemy checking for player
        if(detection.isInRadius(this)){
            //Enemy is hit
            if(Play.getGui().getEquipped().getType() == "melee" && !Play.getGui().getIsRefreshing()[Play.getGui().getSelected()]) {
                if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
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
        if(detection.isProjectileInRadius(this)){
            health -= dmgTaken;
            healthBarX += ((dmgTaken / fullHealth) * sprite.getWidth()) / 2;
            healthBar.setScale(healthBar.getScaleX() - dmgTaken / fullHealth, healthBar.getScaleY());
        }

     }

    @Override
    public void move() {
       setDirection();
        int num;
        Random rand = new Random();

            if(canmove.size() == 0){ //If out of range
                num = 9;// rand.nextInt(8 + 1);
            }
            else{
                num = canmove.get(rand.nextInt(canmove.size()));
             //  Gdx.app.log("", String.valueOf(String.valueOf(collideLocations.length)));

                /**Need to address: Crashes when enemy sprite location is entered, gives inccorect player location **/
                //(int) (sprite.getX() + sprite.getWidth()/2)/ collisionLayer.getWidth()/2 , (int) (sprite.getY() + sprite.getHeight()/2)/ collisionLayer.getHeight()/2
                findPath.test(1, collisionLayer.getWidth()/2, collisionLayer.getHeight()/2, 2, 25, (int) (pl.getPlayer().getSprite().getX() + pl.getPlayer().getSprite().getWidth() /2) / collisionLayer.getWidth()/2,(int) (pl.getPlayer().getSprite().getY() + pl.getPlayer().getSprite().getHeight() /2) / collisionLayer.getHeight()/2, collideLocations);

            }

        if(!detection.isInSmallRadius(this)) {
           moveEnemy(num);
        }
        canmove.clear();
    }

    private void setDirection(){
        //Checks which direction enemy should go
        //Up
        if (sprite.getY() + sprite.getHeight() / 2 < Play.getPlayer().getSprite().getY() + Play.getPlayer().getSprite().getHeight() / 2) {
            canmove.add(4);
        }
        //Down
        if (sprite.getY() + sprite.getHeight() / 2 > Play.getPlayer().getSprite().getY() + Play.getPlayer().getSprite().getHeight() / 2) {
            canmove.add(3);
        }
        //Left
        if(sprite.getX() + sprite.getWidth()/2 > Play.getPlayer().getSprite().getX() + Play.getPlayer().getSprite().getWidth()/2){
            canmove.add(2);

        }
        //Right
        if(sprite.getX() + sprite.getWidth()/2 < Play.getPlayer().getSprite().getX() + Play.getPlayer().getSprite().getWidth()/2){
            canmove.add(1);
        }
        if(canmove.contains(1) && canmove.contains(4)) canmove.add(5); //Up & Right
        if(canmove.contains(2) && canmove.contains(4)) canmove.add(6); //Up & Left
        if(canmove.contains(1) && canmove.contains(3)) canmove.add(7); //Down & Right
        if(canmove.contains(2) && canmove.contains(3)) canmove.add(8); //Down & Left
    }

    private void moveEnemy(int num){
        if(num == 8){ //Down & Left
            if (checkCollision(-sprite.getWidth(), -sprite.getHeight(), -speed/2, -speed/2)) {
                sprite.setY(sprite.getY() - speed/2);
                sprite.setX(sprite.getX() - speed/2);
            }
        }
        if(num == 7){ //Down & Right
            if (checkCollision(sprite.getWidth(), -sprite.getHeight(), speed/2, -speed/2)) {
                sprite.setY(sprite.getY() - speed/2);
                sprite.setX(sprite.getX() + speed/2);
            }
        }
        if(num == 6){ //Up & Left
            if (checkCollision(-sprite.getWidth(), sprite.getHeight(), -speed/2, speed/2)) {
                sprite.setY(sprite.getY() + speed/2);
                sprite.setX(sprite.getX() - speed/2);
            }
        }
        if(num == 5){ //Up & Right
            if (checkCollision(sprite.getWidth(), sprite.getHeight(), speed/2, speed/2)) {
                sprite.setY(sprite.getY() + speed/2);
                sprite.setX(sprite.getX() + speed/2);
            }
        }
        if (num == 4) { //Up
            if (checkCollision(0f, sprite.getHeight(), 0f, speed)) {
                sprite.setY(sprite.getY() + speed);
            }
        }
        if (num == 3) { //Down
            if (checkCollision(0f, -sprite.getHeight(), 0f, -speed)) {
                sprite.setY(sprite.getY() + -speed);
            }
        }
        if (num == 2) { //Left
            if (checkCollision(-sprite.getWidth(), 0f, -speed, 0f)) {
                sprite.setX(sprite.getX() + -speed);
            }
        }
        if (num == 1) { //Right
            if (checkCollision(sprite.getWidth(), 0f, speed, 0f)) {
                sprite.setX(sprite.getX() + speed);
            }
        }

    }

    //Algorthim for pathfinding



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

}
