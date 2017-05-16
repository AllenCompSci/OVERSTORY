package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics.Detection;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

import java.util.Iterator;

/**
 * Created by chris on 1/22/2017.
 */
public class Entity implements InputProcessor {
    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    protected Sprite sprite;
    protected long time = 0;
    protected float speed = 5f;
    protected Detection detection;
    protected String state = "still";
    public float getDmg() {
        return dmg;
    }
    public void setDmg(float dmg) {
        this.dmg = dmg;
    }
    protected float dmg;

    public TiledMapTileLayer getCollisionLayer() {
        return collisionLayer;
    }

    protected TiledMapTileLayer collisionLayer;
    public int getHEIGHT(){
        return (int)collisionLayer.getTileHeight();
    }
    public int getWIDTH(){
        return (int)collisionLayer.getTileWidth();
    }
    public static float collisionWidth;
    public static float collisionHeight;

    public int getLevel() {
        return level;
    }

    protected int level;

    public Entity(float x, float y, int level, TiledMapTileLayer collisionLayer){
        this.collisionLayer = collisionLayer;
        collisionWidth = collisionLayer.getTileWidth();
        collisionHeight = collisionLayer.getTileHeight();
        this.level = level;
    }

    public Entity(float x, float y){

    }

//checks three points in front of the character
    public boolean checkCollision(float width, float height, float Xspeed, float Yspeed, TiledMapTileLayer collisionLayer){
        int COLx1 = (int)((sprite.getX() + sprite.getWidth()/2 + width/2 + Xspeed) / collisionLayer.getTileWidth());
        int COLy1 = (int)((sprite.getY() + sprite.getHeight()/2 + height/2 + Yspeed) / collisionLayer.getTileHeight());
        int COLx2 = (int)((sprite.getX() + sprite.getWidth()/2 + width/2 + Xspeed) / collisionLayer.getTileWidth());
        int COLy2 = (int)((sprite.getY() + sprite.getHeight()/2 + height/2 + sprite.getHeight()/3 + Yspeed) / collisionLayer.getTileHeight());
        int COLx3 = (int)((sprite.getX() + sprite.getWidth()/2 + width/2 + Xspeed) / collisionLayer.getTileWidth());
        int COLy3 = (int)((sprite.getY() + sprite.getHeight()/2 + height/2 - sprite.getHeight()/3 + Yspeed) / collisionLayer.getTileHeight());

        if(Play.getWalls().size > 0){
           for(Wall e : Play.getWalls()){
               if(e != null)
                if(e.getCollision(COLx1, COLy1, COLx2, COLy2, COLx3, COLy3)){
                    return false;
                }
           }
        }

            if (height == 0f)
                return (!collisionLayer.getCell(COLx1, COLy1).getTile().getProperties().containsKey("blocked")
                        && !collisionLayer.getCell(COLx2, COLy2).getTile().getProperties().containsKey("blocked")
                        && !collisionLayer.getCell(COLx3, COLy3).getTile().getProperties().containsKey("blocked")
                );
            return (!collisionLayer.getCell(COLx1, COLy1).getTile().getProperties().containsKey("blocked")
                    && !collisionLayer.getCell(COLx2, COLy2).getTile().getProperties().containsKey("blocked")
                    && !collisionLayer.getCell(COLx3, COLy3).getTile().getProperties().containsKey("blocked")
            );
        }




    public void draw(Batch batch) {
        sprite.draw(batch);
        move();
    }

    //checks for entity movement input
    public void move(){
        Player.isWalking = false;
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            if (checkCollision(0f, sprite.getHeight(), 0f, speed, collisionLayer)) {
                sprite.setY(sprite.getY() + speed);
            }
             Player.charFace = Player.FACE.UP;
            Player.isWalking = true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            if (checkCollision(0f, -sprite.getHeight(), 0f, -speed, collisionLayer)) {
                sprite.setY(sprite.getY() + -speed);
                state = "down";
            }
             Player.charFace = Player.FACE.DOWN;
            Player.isWalking = true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            if (checkCollision(-sprite.getWidth(), 0f, -speed, 0f, collisionLayer)) {
                sprite.setX(sprite.getX() + -speed);
            }
             Player.charFace = Player.FACE.LEFT;
            Player.isWalking = true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            if (checkCollision(sprite.getWidth(), 0f, speed, 0f, collisionLayer)) {
                sprite.setX(sprite.getX() + speed);
            }
             Player.charFace = Player.FACE.RIGHT;
            Player.isWalking = true;
        }
        if(Player.isWalking && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
            speed = 8f;
            Player.takeDMG((int)(Player.getRegenRate() * 1.1));
        }
        else{
            speed = 5f;
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public void onDeath(){

    }


}
