package com.mygdx.overstory.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.overstory.OverstoryMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1800;
		config.height = 900;
		config.title = "TRUMPS'S YUUUUGE ADVENTURE";
		new LwjglApplication(new OverstoryMain(), config);
	}
}
