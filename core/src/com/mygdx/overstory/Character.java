package com.mygdx.overstory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.overstory.OverstoryMain;

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
    long time = 0;

    public Character(Sprite sprite, float health, float x, float y, String Name) {
        super(sprite);
        this.health = health;
        setName(Name);
        setPosition(x,y);

    }
    public Character(Sprite sprite, float health, float DMG, float x, float y, String Name) {
        super(sprite);
        this.health = health;
        this.DMG = DMG;
        setName(Name);
        setPosition(x,y);

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
            sprite.getTexture().dispose();
        }
    }

    public boolean confineToMap(float x, float y){
        MyActor hitActor = (MyActor) om.getStage().hit(sprite.getX() + sprite.getWidth()/2 + x, sprite.getY() + sprite.getHeight()/2 + y, false);
        if(hitActor == null) return false;
        return true;
    }

}
