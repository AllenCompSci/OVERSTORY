package com.mygdx.overstory;

import com.badlogic.gdx.*;
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
	private static Stage stage;
	private static MyActor map;

	public Player getPlayer() {
		return player;
	}

	private static Player player;

	@Override
	public void create () {
		stage = new Stage(new ScreenViewport());

		map = new MyActor(new Sprite(new Texture("spaceimg.jpg")));
		map.setName("Map");
		map.setTouchable(Touchable.disabled);




		player = new Player(new Sprite(new Texture("thor.png")), 100f, 10f, 0f, 0f, "Player");
		player.setTouchable(Touchable.disabled);

		stage.addActor(map);
		//Must addActors after map to have them in front of map
		spawnEnemy(new Texture("RedEyes.png"), 100f, 10f, 400f, 400f, "RedEyes Minion");
		spawnEnemy(new Texture("RedEyes.png"), 100f, 10f, 800f, 800f, "RedEyes Minion2");
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
		player.sprite.getTexture().dispose();
		map.sprite.getTexture().dispose();
	}

	public Stage getStage() {
		return stage;
	}

	public void spawnEnemy(Texture texture, float health, float DMG, float x, float y, String Name){
		stage.addActor(new Enemy(new Sprite(texture), health, DMG, x, y, Name));
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
		switch (button) {
			case Input.Buttons.LEFT:
				Vector2 coord = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
				MyActor hitActor = (MyActor) stage.hit(coord.x, coord.y, true);
				if (hitActor != null) {
					hitActor.isHit();
				}
				break;
			case Input.Buttons.RIGHT:
				Texture texture = new Texture("RedEyes.png");
				spawnEnemy(texture, 100f, 10f, screenX - texture.getWidth()/2 ,Gdx.graphics.getHeight() - screenY - texture.getHeight()/2, "RedEyes Minion");
				break;
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
