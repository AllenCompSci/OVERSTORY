package com.mygdx.overstory;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class OverstoryMain extends ApplicationAdapter implements InputProcessor{
	private Stage stage;
	private MyActor map;
	private Player player;
	private Enemy redeyes;

	@Override
	public void create () {
		stage = new Stage(new ScreenViewport());

		map = new MyActor(new Sprite(new Texture("spaceimg.jpg")));
		map.setName("Map");
		map.setTouchable(Touchable.disabled);

		redeyes = new Enemy(new Sprite(new Texture("RedEyes.png")), 100f);
		redeyes.setName("RedEyes Minion");
		redeyes.setPosition(400,400);
		redeyes.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				redeyes.removeHealth(player.DMG);
			}
		});

		player = new Player(new Sprite(new Texture("badlogic.jpg")), 100f, 10);
		player.setName("Player");
		player.setTouchable(Touchable.disabled);

		stage.addActor(map);
		stage.addActor(redeyes);
		stage.addActor(player);

		stage.setKeyboardFocus(player);

		InputMultiplexer im = new InputMultiplexer(stage, this);
		Gdx.input.setInputProcessor(im);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}
	
	@Override
	public void dispose () {
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	//Checks what it hit
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector2 coord = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
		MyActor hitActor = (MyActor) stage.hit(coord.x, coord.y, true);
		if(hitActor != null){
			hitActor.isHit();
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
