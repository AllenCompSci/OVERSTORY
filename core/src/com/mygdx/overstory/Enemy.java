package com.mygdx.overstory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by chris on 1/17/2017.
 */
public class Enemy extends Character {
    float GLD; //Amount of gold enemy drops

    public Enemy(Sprite sprite, float health) {
        super(sprite);
        this.health = health;
        //AI movement
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
        if(isHit){
            health -= 10;
            isHit = false;
            Gdx.app.log(getName(), "Health : " + getHealth());
        }
        if(health <= 0){
            Enemy.this.remove();
        }
    }

}
