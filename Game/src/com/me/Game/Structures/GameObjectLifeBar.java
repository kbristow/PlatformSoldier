package com.me.Game.Structures;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.Engine.Structures.GameObject;
import com.me.Engine.Structures.GameSprite;

/**
 * @author Kieran
 *
 */
public class GameObjectLifeBar extends GameObject {

	GameSprite backDrop;
	GameSprite foreGround;
	
	private boolean fore = false;
	private boolean back = false;
	
	public float lifeMax;
	public float lifeCurrent;
	
	//public float xScale;
	//public float yScale;
	
	public float offsetX;
	public float offsetY;
	
	public float foreBackMarginX;
	public float foreBackMarginY;
	
	@Override
	public void onCreate(){
		lifeMax = 100;
		lifeCurrent = 100;
		xScale = 1;
		yScale = 1;
		offsetX = 0;
		offsetY = 0;
		foreBackMarginX = 0;
		foreBackMarginY = 0;
		depth = 1;
	}
	
	
	public void setGraphics(String backDropImage, String foregroundImage){
		setBackDrop(backDropImage);
		setForeground(foregroundImage);
	}
	
	public void setBackDrop(String backDropImage){
		backDrop = new GameSprite(1, 1, 1, backDropImage);
		backDrop.setOrigin(0,0);
		back = true;
	}
	
	public void setForeground(String foregroundImage){
		foreGround = new GameSprite(1, 1, 1, foregroundImage);
		foreGround.setOrigin(0,0);
		fore = true;
	}
	
	@Override
	public void preStep(){
		
	}
	
	public void updateLifeBar(){
		if (back && fore){
			if (lifeCurrent<0){
				lifeCurrent = 0;
			}
			
			backDrop.x = x + offsetX;
			backDrop.y = y + offsetY;
			
			foreGround.x = x + offsetX + foreBackMarginX*xScale;
			foreGround.y = y + offsetY + foreBackMarginY*yScale;
			
			float lifeScale = lifeCurrent/lifeMax;
			float u2 = foreGround.frameWidth * lifeScale;
			foreGround.setU2(u2);
			
			backDrop.xScale = xScale;
			backDrop.yScale = yScale;
			
			foreGround.xScale = xScale * lifeScale;
			foreGround.yScale = yScale;			
		}
	}
	
	@Override
	public void onDraw(SpriteBatch spriteBatch){
		if (back && fore){
			backDrop.draw(spriteBatch);
			foreGround.draw(spriteBatch);
		}
	}
	

	/**
	 * @return the offsetX
	 */
	public float getOffsetX() {
		return offsetX;
	}

	/**
	 * @param offsetX the offsetX to set
	 */
	public void setOffsetX(float offsetX) {
		this.offsetX = offsetX;
	}

	/**
	 * @return the offsetY
	 */
	public float getOffsetY() {
		return offsetY;
	}

	/**
	 * @param offsetY the offsetY to set
	 */
	public void setOffsetY(float offsetY) {
		this.offsetY = offsetY;
	}

	/**
	 * @return the xWorld
	 */
	public float getXWorld() {
		return y + offsetX;
	}

	/**
	 * @return the yWorld
	 */
	public float getYWorld() {
		return y + offsetY;
	}


	/**
	 * @return the lifeMax
	 */
	public float getLifeMax() {
		return lifeMax;
	}


	/**
	 * @param lifeMax the lifeMax to set
	 */
	public void setLifeMax(float lifeMax) {
		this.lifeMax = lifeMax;
	}


	/**
	 * @return the lifeCurrent
	 */
	public float getLifeCurrent() {
		return lifeCurrent;
	}


	/**
	 * @param lifeCurrent the lifeCurrent to set
	 */
	public void setLifeCurrent(float lifeCurrent) {
		this.lifeCurrent = lifeCurrent;
	}


	/**
	 * @return the foreBackMarginX
	 */
	public float getForeBackMarginX() {
		return foreBackMarginX;
	}


	/**
	 * @param foreBackMarginX the foreBackMarginX to set
	 */
	public void setForeBackMarginX(float foreBackMarginX) {
		this.foreBackMarginX = foreBackMarginX;
	}


	/**
	 * @return the foreBackMarginY
	 */
	public float getForeBackMarginY() {
		return foreBackMarginY;
	}


	/**
	 * @param foreBackMarginY the foreBackMarginY to set
	 */
	public void setForeBackMarginY(float foreBackMarginY) {
		this.foreBackMarginY = foreBackMarginY;
	}
	
	
	
	
	
	
}
