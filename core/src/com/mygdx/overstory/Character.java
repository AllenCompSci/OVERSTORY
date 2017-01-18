package com.mygdx.overstory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 * Created by chris on 1/17/2017.
 */
public class Character extends MyActor{
    float health;
    float Mspeed; //Movement speed
    float SPD; //Attack speed
    float DMG; //Base Attack Damage
    float DEF; //Base Defence
    float LCK; //Chance for dodges and critical hits

    public Character(Sprite sprite, float health) {
        super(sprite);
        this.health = health;


    }
    public Character(Sprite sprite, float health, float DMG) {
        super(sprite);
        this.health = health;
        this.DMG = DMG;


    }

    public float getHealth(){
        Gdx.app.log(getName(), String.valueOf(health));
        return health;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
        if(health <= 0){
            this.remove();
        }
    }

}
