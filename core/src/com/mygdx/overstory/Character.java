package com.mygdx.overstory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
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
    float ATK; //Base Attack Damage
    float DEF; //Base Defence
    float LCK; //Chance for dodges and critical hits

    public Character(String texture) {
        super(texture);


    }
}
