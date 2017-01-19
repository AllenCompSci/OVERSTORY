package com.mygdx.overstory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


/**
 * Created by chris on 1/17/2017.
 */
public class Enemy extends Character {
    float GLD; //Amount of gold enemy drops

    long time = 0; //(Needed for AI movement)
    int enrandmvmt = 0; //(Needed for AI movement)
    float xp = 1f; //Amount of xp enemy drops


    public Enemy(Sprite sprite, float health, float DMG, float x, float y, String Name) {
        super(sprite, health, DMG, x , y, Name);

    }

    public void isHit(Character character) {
        Gdx.app.log(getName(), "IS HIT");
        //Actions.color(Color.RED, 5f);
    }

    public void removeHealth(float amount) {
        health -= amount;
        Gdx.app.log(getName(), String.valueOf(health));
    }

    //AI movement
    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
        if(health <= 0){
            this.remove();
            sprite.getTexture().dispose();
        }
        //Cardinal Direction Detection
        //if(om.getPlayer().sprite.getX() - sprite.getX() <= 500 && om.getPlayer().sprite.getX() - sprite.getX() >= 0)





        if(System.currentTimeMillis() > time) {
            enrandmvmt = (int)(Math.random() * 5 + 1);
        /*    if(om.getPlayer().sprite.getX() < sprite.getX())
                enrandmvmt = 2;
            else {
                if(enrandmvmt == 1){

                }
                else {
                    enrandmvmt = (int)(Math.random() * 5 + 1);d
                }
            } */
            switch (enrandmvmt){
                case 1: // Move Right
                    addAction(Actions.moveBy(100f, 0f, .2f));
                    time = System.currentTimeMillis() + 400;
                    break;
                case 2: // Move Left
                    addAction(Actions.moveBy(-100f, 0f, .2f));
                    time = System.currentTimeMillis() + 400;
                    break;
                case 3: // Move Up
                    addAction(Actions.moveBy(0f, 100f, .2f));
                    time = System.currentTimeMillis() + 400;
                    break;
                case 4: // Move Down
                    addAction(Actions.moveBy(0f, -100f, .2f));
                    time = System.currentTimeMillis() + 400;
                    break;
                case 5: // Stay Still
                    addAction(Actions.moveBy(0f, 0f, .2f));
                    time = System.currentTimeMillis() + 400;
                break;
            }

        }
    }

    @Override
    public void isHit() { //Currently damages enemy for 50% to 150% of DMG (FEEL FREE TO MODIFY)
        removeHealth((float)(int)(om.getPlayer().DMG * (float)(Math.random() * 11 + 5)/10));
        //removeHealth((om.getPlayer().DMG);
    }


}
