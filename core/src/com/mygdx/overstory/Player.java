package com.mygdx.overstory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 * Created by chris on 1/17/2017.
 */
public class Player extends Character {
    float level;
    float xp;


    public Player(Sprite sprite, float health) {
        super(sprite);
        this.health = health;

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

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
        if(isHit){
            health -= 10;
            isHit = false;
            Gdx.app.log(getName(), "Health : " + getHealth());
        }
        if(health <= 0){
            Player.this.remove();
        }
    }
}
