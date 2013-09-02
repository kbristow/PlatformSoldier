package com.me.GameEngine;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.me.Engine.Structures.Level;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
//import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;
//import com.me.GameEngine.Structures.Level;
import com.me.Game.Levels.LevelTest;

public class MyGdxGame extends Game {
	

	
	@Override
	public void create() {
		/*Settings settings = new Settings();
        settings.maxWidth = 1024;
        settings.maxHeight = 1024;
        TexturePacker2.process(settings, INP_DIR , OUT_DIR , FILE_NAME);*/

		//Level.imageAtlas = new TextureAtlas(Gdx.files.internal(OUT_DIR+FILE_NAME + ".atlas"));
		Level.imageAtlas = new TextureAtlas(Gdx.files.internal("Game.atlas"));
		
		//reg.
        //Level.imageAtlas = new TextureAtlas(Gdx.files.internal("../GameEngine-android/assets/Game.atlas"));
		this.setScreen(new LevelTest(this, 2000, 2000, 1200, 800, 400, 128));
	}
}
