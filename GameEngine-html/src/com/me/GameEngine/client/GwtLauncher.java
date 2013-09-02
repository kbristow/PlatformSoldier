package com.me.GameEngine.client;

import com.me.GameEngine.MyGdxGame;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class GwtLauncher extends GwtApplication {
	@Override
	public GwtApplicationConfiguration getConfig () {
		GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(480, 320);
		return cfg;
	}

	@Override
	public ApplicationListener getApplicationListener () {
		/*Settings settings = new Settings();
        settings.maxWidth = 512;
        settings.maxHeight = 512;
        TexturePacker2.process(settings, "../images", "../GameEngine-android/assets", "game");
		 */
		return new MyGdxGame();
	}
}