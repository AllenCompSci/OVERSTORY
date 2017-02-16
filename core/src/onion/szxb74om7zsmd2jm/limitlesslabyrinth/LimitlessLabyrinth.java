package onion.szxb74om7zsmd2jm.limitlesslabyrinth;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.MainMenu;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

public class LimitlessLabyrinth extends Game {
	public static void setPlay() {
		resetScreen = true;
	}
	private static boolean resetScreen = false;
	private static boolean mainMenuScreen = false;

	public static void setMainMenu(){
		mainMenuScreen = true;
	}

	private static MainMenu mainMenu = new MainMenu();

	@Override
	public void create () {
		setScreen(mainMenu);
	}

	@Override
	public void render () {
		if(resetScreen){
			screen.dispose();
			System.gc();
			resetScreen = false;
			setScreen(new Play());
		}
		if(mainMenuScreen){
			screen.dispose();
			System.gc();
			mainMenuScreen = false;
			setScreen(mainMenu);
		}
		super.render();
	}

	@Override
	public void dispose () {
		super.dispose();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
