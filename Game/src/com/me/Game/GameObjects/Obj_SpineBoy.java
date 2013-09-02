package com.me.Game.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.esotericsoftware.spine.SkeletonRendererDebug;
import com.esotericsoftware.spine.attachments.RegionAttachment;
import com.me.Engine.SpineExtension.GameObjectAttachment;
import com.me.Engine.SpineExtension.SkeletonRendererExt;
import com.me.Engine.Structures.GameObject;
import com.me.Engine.Structures.Level;
import com.me.Game.Player.SwordAttachment;

public class Obj_SpineBoy extends GameObject {
	
	//TODO FIX THIS MESSY POOP. TESTING IS NOW COMPLETE
	
	SkeletonRendererExt renderer;
	SkeletonRendererDebug debugRenderer;

	TextureAtlas atlas;
	Skeleton skeleton;
	AnimationState generalState;
	AnimationState armState;
	
	
	public int dirH = 0;
	public int previousDirH = 10;
	
	float skeletonScaleY = 0.25f;
	float skeletonScaleX = 0.25f;
	
	public ControlState currentState;
	
	public ControlState previousState;
	
	public ArmState previousArmState;
	
	public ArmState currentArmState;
	
	RegionAttachment attach;
	
	GameObjectAttachment attachGO;
	
	
	public enum ControlState {
		Idle,
		Walking,
		Jumping
	}
	
	public enum ArmState {
		Idle,
		Running,
		LeftAttack
	}

	@Override
	public void onCreate () {
		renderer = new SkeletonRendererExt();
		debugRenderer = new SkeletonRendererDebug();
		
		SkeletonJson json = new SkeletonJson(Level.imageAtlas);
		SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal("FChar.json"), "FChar/");

		// Define mixing between animations.
		AnimationStateData stateData = new AnimationStateData(skeletonData);
		stateData.setMix("LegsRunning", "Jump", 0.2f);
		stateData.setMix("Jump", "LegsRunning", 0.2f);
		stateData.setMix("Jump", "Jump", 0.2f);
		stateData.setMix("Jump", "LegsIdle", 0.2f);
		stateData.setMix("LegsIdle", "Jump", 0.1f);
		stateData.setMix("LegsRunning", "LegsIdle", 0.1f);
		stateData.setMix("LegsIdle", "LegsRunning", 0.1f);
		
		
		stateData.setMix("ArmsRunning", "LeftArmAttack", 0.2f);
		stateData.setMix("ArmsIdle", "LeftArmAttack", 0.2f);
		stateData.setMix("LeftArmAttack", "ArmsIdle", 0.1f);
		stateData.setMix("LeftArmAttack", "ArmsRunning", 0.1f);
		stateData.setMix("ArmsRunning", "ArmsIdle", 0.1f);
		stateData.setMix("ArmsIdle", "ArmsRunning", 0.1f);
		

		generalState = new AnimationState(stateData);
		generalState.setAnimation("LegsIdle", true);
		
		armState = new AnimationState(stateData);
		armState.setAnimation("ArmsIdle", true);

		skeleton = new Skeleton(skeletonData);
		skeleton.setFlipY(true);
		skeleton.getRootBone().setScaleY(skeletonScaleY);
		skeleton.getRootBone().setScaleX(skeletonScaleX);
		skeleton.updateWorldTransform();
		
		currentState = ControlState.Idle;
		currentArmState = ArmState.Idle;
		previousArmState = ArmState.Idle;
		
		AtlasRegion reg = Level.imageAtlas.findRegion("Weapons/Swords/Weapon_Sword");
		//reg.flip(false, true);
		attach = new RegionAttachment("Sword");
		attach.setRegion(reg);
		
		attachGO = new GameObjectAttachment("SwordGO");
		SwordAttachment swordGO = new SwordAttachment();
		attachGO.setGameObject(swordGO);
		attachGO.setInitialRotation(90);
		
		
		
		skeleton.getData().getDefaultSkin().addAttachment(skeleton.findSlotIndex("WeaponLeft"), "sword", attach);

		//skeleton.findSlot("WeaponInnerLeft").getBone().boneFlipY = true;
		
		/*Array<Slot> drawO = skeleton.getDrawOrder();
		drawO.swap(1, drawO.size - 3);
		drawO.swap(2, drawO.size - 2);
		drawO.swap(3, drawO.size - 1);*/
		/*
		Slot temp;
		temp = drawO.get(1);
		drawO.set(1, drawO.get(drawO.size - 3));
		drawO.set(drawO.size - 3, temp);*/
		
		depth = 20;
	}
	
	public void manualUpdatePre(){
		skeleton.setY(y+63);
		skeleton.setX(x);
		
		if (previousState!=ControlState.Jumping && currentState==ControlState.Jumping){
			generalState.setAnimation("Jump", false);
			if (currentArmState==ArmState.Idle){
				armState.setAnimation("ArmsIdle", false);
			}
		}
		
		if (currentState != ControlState.Jumping && previousState == ControlState.Jumping){
			previousDirH = 10;
			currentState = ControlState.Idle;
		}
		
		if (previousDirH != dirH){
			if (dirH!=0){
				skeleton.setFlipX(dirH < 0);
			}
			if (currentState != ControlState.Jumping){
				if (dirH > 0){
					generalState.setAnimation("LegsRunning", true);
					if (currentArmState != ArmState.LeftAttack){
						armState.setAnimation("ArmsRunning", true);
					}
					currentState = ControlState.Walking;
				}else if (dirH < 0){
					generalState.setAnimation("LegsRunning", true);
					if (currentArmState != ArmState.LeftAttack){
						armState.setAnimation("ArmsRunning", true);
					}
					currentState = ControlState.Walking;
				}
			}

			if (dirH == 0 && currentState != ControlState.Jumping){
				generalState.setAnimation("LegsIdle", true);
				if (currentArmState != ArmState.LeftAttack){
					currentArmState = ArmState.Idle;
					armState.setAnimation("ArmsIdle", true);
				}
				currentState = ControlState.Idle;
			}
		}
		
	
       
        
        
        generalState.update(0.015f);
        generalState.apply(skeleton);
        if (currentArmState == ArmState.Idle){
        	armState.update(0.015f);
        	armState.apply(skeleton);
        }
        else{	
        	if (armState.isComplete() && !Gdx.input.isTouched()){
        		currentArmState=ArmState.Idle;
        		armState.setAnimation("ArmsIdle", true);
        	}
        	else if (currentArmState == ArmState.LeftAttack && armState.isComplete() && Gdx.input.isTouched())
        	{
        		armState.setTime(0);
        	}
        	
	        armState.update(0.015f);
        	
        	armState.apply(skeleton);
        }
        
        
		
		previousDirH = dirH;
        previousState = currentState;
        previousArmState = currentArmState;
        
        skeleton.findSlot("WeaponInnerLeft").setAttachment(attachGO);
		
		attach.setX(skeleton.findBone("LeftHandAttachmentBone").getX());
        attach.setY(skeleton.findBone("LeftHandAttachmentBone").getY());
        attach.setScaleX(2.0f);
		attach.setScaleY(2.0f);
		attach.setHeight(attach.getRegion().getRegionHeight());
		attach.setWidth(attach.getRegion().getRegionWidth());
		attach.setRotation(90);
		attach.updateOffset();
	}
	
	public void manualUpdatePreKin(){
		mouseUpdate();
        
        skeleton.getRootBone().setScaleY(skeletonScaleY);
		skeleton.getRootBone().setScaleX(skeletonScaleX);
		skeleton.updateWorldTransform();
	}
	
	public void mouseUpdate(){
		/*Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
		Level.camera.unproject(mouse);
		float dX = mouse.x - x;
		float dY = mouse.y - y;
		float angle = (float) Math.atan2(dX, dY);
		skeleton.findBone("left shoulder").setRotation(180 + (float) Math.toDegrees(angle));*/
		
		if(Gdx.input.isTouched() && previousArmState != ArmState.LeftAttack){
			currentArmState = ArmState.LeftAttack;
			armState.setAnimation("LeftArmAttack", false);
		}
	}
	
	public void setJumpState (boolean isGrounded){
		if (currentState != ControlState.Jumping && !isGrounded){
			currentState = ControlState.Jumping;
		}
		else if (previousState == ControlState.Jumping && isGrounded){
			currentState = ControlState.Idle;
		}
	}
	
	
	@Override
	public void onDraw(SpriteBatch spriteBatch){
		
		//skeleton.findSlot("WeaponInnerLeft").setAttachment(attach);
		renderer.draw(spriteBatch, skeleton);
	}
}
