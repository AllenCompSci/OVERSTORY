package com.mygdx.overstory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 * Created by chris on 1/17/2017.
 */
public class Player extends Character {
    float level;
    float xp;


    public Player(Sprite sprite, float health, float DMG, float x, float y, String Name) {
        super(sprite, health, DMG, x, y, Name);

        addListener(new InputListener(){

            @Override
            public boolean keyDown(InputEvent event, int keycode) {

                //Ill constrain movement later, just setting up classes.
                if(System.currentTimeMillis() > time) {
                    switch (keycode) {

                        case Input.Keys.W:
                            if (confineToMap(0f, 100f)) {
                                addAction(Actions.moveBy(0f, 100f, .2f));
                                time = System.currentTimeMillis() + 300;

                            }
                            break;

                        case Input.Keys.S:
                            if (confineToMap(0f, -100f)) {
                                addAction(Actions.moveBy(0f, -100f, .2f));
                                time = System.currentTimeMillis() + 300;
                            }
                            break;

                        case Input.Keys.A:
                            if (confineToMap(-100f, 0f)) {
                                addAction(Actions.moveBy(-100f, 0f, .2f));
                                time = System.currentTimeMillis() + 300;
                            }
                            break;

                        case Input.Keys.D:
                            if (confineToMap(100f, 0f)) {
                                addAction(Actions.moveBy(100f, 0f, .2f));
                                time = System.currentTimeMillis() + 300;
                            }
                            break;

                }
            }
            return false;
            }

        });
    }


}
