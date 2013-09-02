package com.me.GameEngine;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;


public class Main {
	
	
	
	public static void main(String[] args) {
		/*String INP_DIR = "../GameEngine-android/assets/";
		String OUT_DIR = "../GameEngine-android/assets/";
		String FILE_NAME = "Game";
		
		Settings settings = new Settings();
        settings.maxWidth = 1024;
        settings.maxHeight = 1024;
        //settings.filterMag = TextureFilter.MipMapNearestNearest;
        //settings.filterMin = TextureFilter.MipMapNearestNearest;
        TexturePacker2.process(settings, INP_DIR , OUT_DIR , FILE_NAME);*/
        
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "GameEngine";
		cfg.useGL20 = true;
		cfg.width = 800;
		cfg.height = 640;
		//cfg.foregroundFPS = 61;
		//cfg.backgroundFPS = 60;
		cfg.vSyncEnabled = false;
		
		new LwjglApplication(new MyGdxGame(), cfg);
	}
}
