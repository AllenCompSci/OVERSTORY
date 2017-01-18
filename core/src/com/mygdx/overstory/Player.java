package com.mygdx.overstory;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 * Created by chris on 1/17/2017.
 */
public class Player extends Character {
    float level;
    float xp;


    public Player(Sprite sprite) {
        super(sprite);

        addListener(new InputListener(){

            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                switch (keycode) {

                    //Ill constrain movement later, just setting up classes.

                    case Input.Keys.W:
                        addAction(Actions.moveBy(0f, 100f, 1f));
                        break;

                    case Input.Keys.S:
                        addAction(Actions.moveBy(0f, -100f, 1f));
                        break;

                    case Input.Keys.A:
                        addAction(Actions.moveBy(-100f, 0f, 1f));
                        break;

                    case Input.Keys.D:
                        addAction(Actions.moveBy(100f, 0f, 1f));
                        break;


                }
                return false;
            }

        });
    }
}
