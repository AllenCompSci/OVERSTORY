package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.Random;

/**
 * Created by chris on 1/21/2017.
 */
public class Enemy extends Player{

    public Enemy(Sprite sprite, float x, float y, TiledMapTileLayer collisionLayer) {
        super(sprite, x, y, collisionLayer);
    }

    @Override
    public void move() {
        Random rand = new Random();
        int num = rand.nextInt(5 + 1);
        if(num == 1){
            if (checkCollision(0f, sprite.getHeight(), 0f, speed)) {
                sprite.setY(sprite.getY() + speed);
            }
        }
        if(num == 2){
            if (checkCollision(0f, -sprite.getHeight(), 0f, -speed)) {
                sprite.setY(sprite.getY() + -speed);
            }
        }
        if(num == 3){
            if (checkCollision(-sprite.getWidth(), 0f, -speed, 0f)) {
                sprite.setX(sprite.getX() + -speed);
            }
        }
        if(num == 4){
            if (checkCollision(sprite.getWidth(), 0f, speed, 0f)) {
                sprite.setX(sprite.getX() + speed);
            }
        }
    }

}
