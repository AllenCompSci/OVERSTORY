package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics.Detection;

/**
 * Created by chris on 1/19/2017.
 */
public class Player extends Entity {

    public Player(Sprite sprite, float x, float y, float health, TiledMapTileLayer collisionLayer){
        super(sprite, x, y, health, collisionLayer);
        this.sprite = sprite;
        this.collisionLayer = collisionLayer;
    }

    @Override
    public void draw(Batch batch) {
        sprite.draw(batch);
        move();
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) pl.setZoom(pl.getZoom() - .1f);
        if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) pl.setZoom(pl.getZoom() + .1f);
    }
}

