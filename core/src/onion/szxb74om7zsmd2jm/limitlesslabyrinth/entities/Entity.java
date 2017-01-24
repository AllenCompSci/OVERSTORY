package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics.Detection;

/**
 * Created by chris on 1/22/2017.
 */
public class Entity extends Actor implements InputProcessor {
    public Sprite getSprite() {
        return sprite;
    }

    protected Sprite sprite;
    protected long time = 0;
    protected float speed = 4f;
    protected Detection detection;

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    protected float health;


    protected TiledMapTileLayer collisionLayer;

    public Entity(Sprite sprite, float x, float y, float health, TiledMapTileLayer collisionLayer){
        this.sprite = sprite;
        this.collisionLayer = collisionLayer;
        this.health = health;
        sprite.setPosition(32 * x, 32 * y);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
    }


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

    public void move(){
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            if (checkCollision(0f, sprite.getHeight(), 0f, speed)) {
                sprite.setY(sprite.getY() + speed);
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            if (checkCollision(0f, -sprite.getHeight(), 0f, -speed)) {
                sprite.setY(sprite.getY() + -speed);
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            if (checkCollision(-sprite.getWidth(), 0f, -speed, 0f)) {
                sprite.setX(sprite.getX() + -speed);
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            if (checkCollision(sprite.getWidth(), 0f, speed, 0f)) {
                sprite.setX(sprite.getX() + speed);
            }
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
}
