package onion.szxb74om7zsmd2jm.limitlesslabyrinth;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.LoadingScreen;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.MainMenu;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.PauseScreen;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

public class LimitlessLabyrinth extends Game {
	private static boolean loadScreen = false;
	private static boolean changeMap = false;
	private static boolean isPauseScreen = false;
	private static boolean resetScreen = false;
	private static boolean loadCurrentGame = false;
	private static boolean mainMenuScreen = false;
	private static boolean PlayerDeath = true;
	private static String MapPath;
	private static MainMenu mainMenu = new MainMenu();
	private static PauseScreen pauseScreen = new PauseScreen();
	private static Play play;

	
	//restart play
	public static void setPlay(String PathToMap) {
		MapPath = PathToMap;
		resetScreen = true;
	}
	//start play
	public static void setPlay(){
		loadCurrentGame = true;
	}
	//initiates pause screen
	public static void pauseScreen(){
		isPauseScreen = true;
	}
	//changes map
	public static void ChangeMap(String PathToMap) {
		MapPath = PathToMap;
		changeMap = true;
	}
	//initiates loadingscreen
	public static void LoadingScreen(String PathToMap){
		MapPath = PathToMap;
		loadScreen = true;
	}
	
	//getter func
	//   for PlayerDeath
	public static boolean isPlayerDeath() {
		return PlayerDeath;
	}
	
	//setter func
	//   for PlayerDeath
	public static void setPlayerDeath(boolean playerDeath) {
		PlayerDeath = playerDeath;
	}
	
	//getter func
	//   for MapPath
	public static String getMapPath() {
		return MapPath;
	}
	
	//returns to main menu
	public static void setMainMenu(){
		mainMenuScreen = true;
	}
	
	//starts play
	public static void setPlayTo(Play play) {
		LimitlessLabyrinth.play = play;
	}


	@Override
	public void create () {
		MapPath = "test.tmx";
		play = new Play("test.tmx", 100, 10, -10, true);
		setScreen(mainMenu);

	}

	//handles game functions using the private variables in class
	@Override
	public void render () {
		if(loadScreen){
			//screen.dispose();
			System.gc();
			loadScreen = false;
			setScreen(new LoadingScreen(MapPath));
		}
		if(resetScreen){
			//screen.dispose();
			System.gc();
			resetScreen = false;
			Play.reset();
			play = new Play(MapPath, 100, 10, -10, true);
			setScreen(play);
		}
		if(changeMap){
			//screen.dispose();
			System.gc();
			changeMap = false;
			Play.changeMap();
			play = new Play(MapPath, 100, 10, -10, false);
			setScreen(play);
		}
		if(mainMenuScreen){
			//screen.dispose();
			System.gc();
			mainMenuScreen = false;
			setScreen(mainMenu);
		}
		if(isPauseScreen){
			//screen.dispose();
			System.gc();
			isPauseScreen = false;
			setScreen(pauseScreen);
		}
		if(loadCurrentGame){
			System.gc();
			loadCurrentGame = false;
			setScreen(play);
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
