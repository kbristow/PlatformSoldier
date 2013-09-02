package com.me.Game.Structures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.me.Engine.Structures.GameObject;
import com.me.Engine.Structures.Level;
import com.me.Game.Structures.GameObjectLifeBar;

public class GameObjectDestroyable extends GameObject {

	public boolean showLifeBars;
	
	public float lifeBarOffsetX;
	public float lifeBarOffsetY;
	
	public GameObjectLifeBar lifeBar;
	
	
	@Override
	public void onCreate(){
		showLifeBars = true;
		lifeBarOffsetX = 0;
		lifeBarOffsetY = 0;
		lifeBar = new GameObjectLifeBar();
	}
	
	public void setGraphics(String backDropImage, String foregroundImage){
		lifeBar.setGraphics(backDropImage, foregroundImage);
	}
	
	public void setBackDrop(String backDropImage){
		lifeBar.setBackDrop(backDropImage);
	}
	
	public void setForeground(String foregroundImage){
		lifeBar.setForeground(foregroundImage);
	}
	
	@Override
	public void preStep(){
		if (checkMouseOver() && showLifeBars){
			lifeBar.isActive = true;
			lifeBar.x = x+hSpeed;
			lifeBar.y = y+vSpeed;
			lifeBar.setOffsetX(lifeBarOffsetX*Math.abs(xScale));
			lifeBar.setOffsetY(lifeBarOffsetY*Math.abs(yScale));
			lifeBar.updateLifeBar();
		}
		else{
			lifeBar.isActive = false;
		}
	}
	
	public boolean checkMouseOver(){
		Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
		Level.camera.unproject(mouse);
		
		return collidesWith(mouse.x, mouse.y);
	}
	
}
