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
    protected float speed = 4f;
    protected Detection detection;
    protected String state = "still";
    public float getFullHealth() {
        return fullHealth;
    }
    protected float fullHealth;
    public float getDmg() {
        return dmg;
    }
    public void setDmg(float dmg) {
        this.dmg = dmg;
    }
    protected float dmg;
    public float getHealth() {
        return health;
    }
    public void setHealth(float health) {
        this.health = health;
    }
    protected float health;
    protected TiledMapTileLayer collisionLayer;
    protected int level;

    public Entity(float x, float y, int level, TiledMapTileLayer collisionLayer){
        this.collisionLayer = collisionLayer;
        this.level = level;
    }

    public Entity(float x, float y){

    }
//checks three points in front of the character
    public boolean checkCollision(float width, float height, float Xspeed, float Yspeed){

        if(height == 0f) return (!collisionLayer.getCell((int) ((sprite.getX() + sprite.getWidth()/2 + width/2 + Xspeed) / collisionLayer.getTileWidth()), (int) ((sprite.getY() + sprite.getHeight()/2 + height/2 + Yspeed) / collisionLayer.getTileHeight())).getTile().getProperties().containsKey("blocked")
                && !collisionLayer.getCell((int) ((sprite.getX() + sprite.getWidth()/2 + width/2 + Xspeed) / collisionLayer.getTileWidth()), (int) ((sprite.getY() + sprite.getHeight()/2 + height/2 + sprite.getHeight()/3 + Yspeed) / collisionLayer.getTileHeight())).getTile().getProperties().containsKey("blocked")
                && !collisionLayer.getCell((int) ((sprite.getX() + sprite.getWidth()/2 + width/2 + Xspeed) / collisionLayer.getTileWidth()), (int) ((sprite.getY() + sprite.getHeight()/2 + height/2 - sprite.getHeight()/3 + Yspeed) / collisionLayer.getTileHeight())).getTile().getProperties().containsKey("blocked")
        );
        return (!collisionLayer.getCell((int) ((sprite.getX() + sprite.getWidth()/2 + width/2 + Xspeed) / collisionLayer.getTileWidth()), (int) ((sprite.getY() + sprite.getHeight()/2 + height/2 + speed) / collisionLayer.getTileHeight())).getTile().getProperties().containsKey("blocked")
                && !collisionLayer.getCell((int) ((sprite.getX() + sprite.getWidth()/2 + width/2 + sprite.getWidth()/3 + Xspeed) / collisionLayer.getTileWidth()), (int) ((sprite.getY() + sprite.getHeight()/2 + height/2 + Yspeed) / collisionLayer.getTileHeight())).getTile().getProperties().containsKey("blocked")
                && !collisionLayer.getCell((int) ((sprite.getX() + sprite.getWidth()/2 + width/2 - sprite.getWidth()/3 + Xspeed) / collisionLayer.getTileWidth()), (int) ((sprite.getY() + sprite.getHeight()/2 + height/2 + Yspeed) / collisionLayer.getTileHeight())).getTile().getProperties().containsKey("blocked")
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
            if (checkCollision(0f, sprite.getHeight(), 0f, speed)) {
                sprite.setY(sprite.getY() + speed);
            }
             Player.charFace = Player.FACE.UP;
            Player.isWalking = true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            if (checkCollision(0f, -sprite.getHeight(), 0f, -speed)) {
                sprite.setY(sprite.getY() + -speed);
                state = "down";
            }
             Player.charFace = Player.FACE.DOWN;
            Player.isWalking = true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            if (checkCollision(-sprite.getWidth(), 0f, -speed, 0f)) {
                sprite.setX(sprite.getX() + -speed);
            }
             Player.charFace = Player.FACE.LEFT;
            Player.isWalking = true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            if (checkCollision(sprite.getWidth(), 0f, speed, 0f)) {
                sprite.setX(sprite.getX() + speed);
            }
             Player.charFace = Player.FACE.RIGHT;
            Player.isWalking = true;
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
