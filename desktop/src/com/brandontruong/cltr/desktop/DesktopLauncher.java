package com.brandontruong.cltr.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.brandontruong.cltr.CltrGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 800;
        config.height = 400;
        config.title = "CLTR";
		new LwjglApplication(new CltrGame(), config);
	}
}
