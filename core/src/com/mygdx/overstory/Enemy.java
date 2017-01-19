package com.mygdx.overstory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by chris on 1/17/2017.
 */
public class Enemy extends Character {
    float GLD; //Amount of gold enemy drops

    public Enemy(final Sprite sprite, float health, float DMG, float x, float y, String Name) {
        super(sprite, health, DMG, x , y, Name);
        //AI movement

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                removeHealth(om.getPlayer().DMG);
            }
        });
    }

    public void isHit(Character character) {
        Gdx.app.log(getName(), "IS HIT");
        //Actions.color(Color.RED, 5f);
    }

    public void removeHealth(float amount) {
        health -= amount;
        Gdx.app.log(getName(), String.valueOf(health));
    }


}
