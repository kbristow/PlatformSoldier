package com.me.Game.World;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.Engine.Structures.GameObject;
import com.me.Engine.Structures.GameSprite;

public class Obj_FloorTile extends GameObject {
	
	//ShapeRenderer shapeRenderer = new ShapeRenderer();
	
	@Override
	public void onCreate(){
		objTypes.add("World");
		GameSprite main = new GameSprite(1, 1, 1, "World/Grass1.png");
		main.setAABBCollisionMask(128,128,0,0);
		main.imageSpeed = 0;
		main.debug = false;
		sprites.add(main);
		setCollides(true);
		xScale = 1f;
		yScale = 1f;
		depth = 100;
	}
	
	@Override
    public void onDraw(SpriteBatch spriteBatch){
    	//Circle temp = getBoundingCollisionCircle();
    	//Level.circleRenderer.circle(temp.x, temp.y, temp.radius);
    }
	
}
