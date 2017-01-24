package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics.Detection;

import java.util.Random;

/**
 * Created by chris on 1/21/2017.
 */
public class Enemy extends Entity{

    public Enemy(Sprite sprite, float x, float y, float health, TiledMapTileLayer collisionLayer) {
        super(sprite, x, y, health, collisionLayer);
        detection = new Detection(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight(), 100);

        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(isInRadius()){
                    Vector2 v = new Vector2(x, Gdx.graphics.getHeight() - y);
                    Actor hitActor = getStage().hit(v.x, v.y, true);
                    if(hitActor != null){
                        setHealth(getHealth() - 10);
                        Gdx.app.log("Enemy Health", String.valueOf(getHealth()));
                    }
                }
                return true;
            }

        });
    }



    @Override
    public void draw(Batch batch) {
        sprite.draw(batch);
        move();
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

    public boolean isInRadius(){
        return detection.isInRadius(this);
    }

}
