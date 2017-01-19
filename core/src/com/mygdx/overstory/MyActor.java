package com.mygdx.overstory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.badlogic.gdx.scenes.scene2d.utils.ScissorStack.getViewport;
import com.mygdx.overstory.OverstoryMain;

/**
 * Created by 226812 on 1/17/2017.
 */

/*
public class MyActor extends Actor {
    Sprite sprite;
    OverstoryMain om = new OverstoryMain();

    public MyActor(Sprite sprite){
        this.sprite = sprite;


        setBounds(this.sprite.getX(), this.sprite.getY(), this.sprite.getWidth(), this.sprite.getHeight());
        setTouchable(Touchable.enabled);


    }

    @Override
    protected void positionChanged() {
        sprite.setPosition(getX(), getY());
        super.positionChanged();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);

        //Grabs and moves the object in its Center
        //Refer to for centered mouse clicks
        /*if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            MyActor.this.addAction(Actions.moveTo(Gdx.input.getX() - sprite.getWidth()/2, Gdx.graphics.getHeight() - Gdx.input.getY() - sprite.getHeight()/2));
        }*/
/*
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public void isHit(){

    }



}
*/
