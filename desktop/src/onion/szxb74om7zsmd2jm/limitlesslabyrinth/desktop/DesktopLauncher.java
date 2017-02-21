package onion.szxb74om7zsmd2jm.limitlesslabyrinth.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.LimitlessLabyrinth;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1920;
		config.height = 1080;
		config.fullscreen = true;
		config.title = "Limitless Labyrinth";

		new LwjglApplication(new LimitlessLabyrinth(), config);
	}
}
