package com.me.Game.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.LongArray;
import com.me.Engine.Collision.ConcavePolygon;
import com.me.Engine.Data.OriginType;
import com.me.Engine.Structures.GameObject;
import com.me.Engine.Structures.GameSprite;
import com.me.Engine.Structures.Level;
import com.me.Game.Enemy.EnemyControl;

public class SwordAttachment extends GameObject{
	
	public WeaponState currentState;
	
	public LongArray currentAttackHit;
	
	public Array<String> weapons;
	
	public float damage = 10;
	
	boolean newKeyPress = true;
	
	private final boolean DEBUG = false;
	
	
	public enum WeaponState {
		ATTACK,
		IDLE
	}
	
	@Override
	public void onCreate(){
		weapons = new Array<String>();
		weapons.add("Weapons/Swords/Weapon_Sword01");
		weapons.add("Weapons/Swords/Weapon_Sword02");
		weapons.add("Weapons/Swords/Weapon_Sword03");
		weapons.add("Weapons/Swords/Weapon_Sword04");
		weapons.add("Weapons/Spears/Weapon_Spear01");
		
		int rand = (int) (Math.random()*5);
		
		String weapon = weapons.get(rand);
		//Spears/Weapon_Spear01
		//Swords/Weapon_Sword01
		ConcavePolygon poly = ConcavePolygon.createFromOutline(weapon, 0,1);
		GameSprite sword = new GameSprite(1, 1, 1, weapon + ".png");
		sword.setOrigin(OriginType.Center);
        sword.setGeneralPolygonCollisionMask(poly, 0, sword.frameHeight, 0, sword.frameWidth/2, -sword.frameHeight/2);
        sword.debug = DEBUG;
        sprites.add(sword);
        setCollides(true);
        autoDraw = false;
        currentState = WeaponState.IDLE;
        currentAttackHit = new LongArray();
	}
	
	@Override
	public void preStep(){
		long collisionIndex = getCollision("Enemy");
		EnemyControl collisionObject = (EnemyControl) Level.gameObjects.get(collisionIndex);
		if (collisionObject!=null && !currentAttackHit.contains(collisionIndex) && currentState == WeaponState.ATTACK){
			sprites.get(getSpriteIndex()).mask.lineColor = Color.RED;
			currentAttackHit.add(collisionIndex);
			collisionObject.lifeBar.lifeCurrent -= damage;
			collisionObject.isHit = true;
		}
		else{
			sprites.get(getSpriteIndex()).mask.lineColor = Color.BLUE;
			if (currentState != WeaponState.ATTACK){
				resetAttackArray();
			}
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_1) && newKeyPress){
			int rand = (int) (Math.random()*5);
			
			String weapon = weapons.get(rand);
			ConcavePolygon poly = ConcavePolygon.createFromOutline(weapon, 0,1);
			GameSprite sword = new GameSprite(1, 1, 1, weapon + ".png");
			sword.setOrigin(OriginType.Center);
	        sword.setGeneralPolygonCollisionMask(poly, 0, sword.frameHeight, 0, sword.frameWidth/2, -sword.frameHeight/2);
	        sword.debug = DEBUG;
	        sprites.clear();
	        sprites.add(sword);
	        
	        newKeyPress = false;
		}
		else if (!Gdx.input.isKeyPressed(Input.Keys.NUM_1)){
			newKeyPress = true;
		}
	}
	
	public void resetAttackArray(){
		currentAttackHit.clear();
	}
	
}
