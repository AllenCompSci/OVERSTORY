package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics.Detection;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by chris on 1/19/2017.
 */
public class Player extends Entity {
    public void setXp(int xp) {
        this.xp = xp;
    }
    public int getXp() {
        return xp;
    }
    private int xp = 0;
    private int level = 1;
    private int xpToLevel = 10;
    //private String state = "still";
    private float elapsedTime;
    private int waveAmount = 10;
    public int getEnemiesAlive() {
        return enemiesAlive;
    }
    public void setEnemiesAlive(int enemiesAlive) {
        this.enemiesAlive = enemiesAlive;
    }
    private int enemiesAlive = 0;

    private Animation playerWalkingDown;
    private Animation playerWalkingLeft;
    private Animation playerWalkingRight;
    private Animation playerWalkingUp;

    public Player(Sprite sprite, float x, float y, float dmg, float health, TiledMapTileLayer collisionLayer){
        super(sprite, x, y, dmg, health, collisionLayer);
        this.sprite = sprite;
        this.collisionLayer = collisionLayer;
        playerWalkingDown = Play.fourFrameAnimationCreator("knightwalkingdown.png");
    }

    @Override
    public void draw(Batch batch) {
        elapsedTime += Gdx.graphics.getDeltaTime();
        sprite.draw(batch);

        move();
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER) && enemiesAlive == 0){
            pl.setSpawnCount(waveAmount);
            enemiesAlive = waveAmount;
            waveAmount *= 2;
        }
        if(xpToLevel - xp <= 0){
            level++;
            xpToLevel *= 2;
            Gdx.app.log("Level", String.valueOf(level));
        }
        if(pl.getZoom() >= .2) {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                pl.setZoom(pl.getZoom() - .02f);
            }
        }
        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            pl.setZoom(pl.getZoom() + .02f);
        }

    }
}

