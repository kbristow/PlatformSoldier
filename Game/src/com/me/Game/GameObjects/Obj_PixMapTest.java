package com.me.Game.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.me.Engine.Collision.ConcavePolygon;
import com.me.Engine.Collision.PolygonCreation.EdgeDetector;
import com.me.Engine.Data.OriginType;
import com.me.Engine.Structures.GameObject;
import com.me.Engine.Structures.GameSprite;
import com.badlogic.gdx.graphics.Color;

public class Obj_PixMapTest extends GameObject{
	
	Texture tex;
	TextureRegion reg;
	byte[] alphaMask;
	
	ConcavePolygon poly;
	float [] edgesVertices;
	
	@Override
	public void onCreate(){
		
		EdgeDetector edge = new EdgeDetector();
		Vector2 [][] edges = edge.doMarch("Weapons/Swords/Weapon_Sword01", 0,1);
		edgesVertices = new float [edges[0].length * 2];
		for (int i = 0; i < edges[0].length * 2; i +=2 ){
			edgesVertices[i] = edges[0][i/2].x;
			edgesVertices[i+1] = edges[0][i/2].y;
		}
		
		poly = new ConcavePolygon(edgesVertices);	
		
		GameSprite sword = new GameSprite(1, 1, 1, "Weapons/Swords/Weapon_Sword01.png");
		sword.setOrigin(OriginType.Center);
        sword.setGeneralPolygonCollisionMask(edgesVertices, 0, sword.frameHeight, 0, sword.frameWidth/2, -sword.frameHeight/2);
        sword.debug = false;
        sprites.add(sword);
        depth = 50f;
        setCollides(true);
	}
	
	@Override
	public void preStep(){
		setRotation(getRotation() + 2);
		if (collidesWith("World")){
			sprites.get(getSpriteIndex()).mask.lineColor = Color.RED;
		}
		else{
			sprites.get(getSpriteIndex()).mask.lineColor = Color.MAGENTA;
		}
	}
	
	@Override
	public void onDraw(SpriteBatch spriteBatch){
		/*Color [] colors = new Color []{Color.YELLOW,Color.GREEN,Color.RED,Color.MAGENTA,Color.PINK};
		
		for (int i = 0; i < edgesVertices.length; i += 2){
			Level.lineRenderer.setColor(colors[(i/2)%5]);
			Level.lineRenderer.line(edgesVertices[i]+x, edgesVertices[i+1]+y, edgesVertices[(i+2)%edgesVertices.length]+x, edgesVertices[(i+3)%edgesVertices.length]+y);
		}*/
	}
	
}
