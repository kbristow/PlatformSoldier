package com.me.Game.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ObjectMap;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.esotericsoftware.spine.SkeletonRendererDebug;
import com.me.Engine.SpineExtension.GameObjectAttachment;
import com.me.Engine.SpineExtension.SkeletonRendererExt;
import com.me.Engine.Structures.GameObject;
import com.me.Engine.Structures.Level;
import com.me.Game.AnimationManagers.Player.PlayerAnimationManager;
import com.me.Game.FSM.Player.ArmFSM.ArmFSMData;
import com.me.Game.FSM.Player.ArmFSM.ArmFSMState;
import com.me.Game.FSM.Player.ArmFSM.ArmFSMState.ArmState;
import com.me.Game.FSM.Player.ArmFSM.ArmIdle;
import com.me.Game.FSM.Player.LegFSM.LegFSMData;
import com.me.Game.FSM.Player.LegFSM.LegFSMState;
import com.me.Game.FSM.Player.LegFSM.LegIdle;
import com.me.Game.Player.SwordAttachment.WeaponState;

public class PlayerAnimation extends GameObject {
	
	int DRAW_OFFSET_Y = 50;
	int DRAW_OFFSET_X = 2;
	
	SkeletonRendererExt renderer;
	SkeletonRendererDebug debugRenderer;

	Skeleton skeleton;
	
	AnimationState legState;
	AnimationState armState;	
	
	public int dirH = 0;
	
	float skeletonScaleY = 0.40f;
	float skeletonScaleX = 0.40f;
	
	public ArmFSMState currentStateArm;
	public LegFSMState currentStateLeg;
	
	public float armUpdateSpeed = 0.015f;
	public float legUpdateSpeed = 0.015f;
	
	SwordAttachment swordAttachment;
	
	ObjectMap<String,GameObjectAttachment> playerAttachments;

	@Override
	public void onCreate () {
		renderer = new SkeletonRendererExt();
		debugRenderer = new SkeletonRendererDebug();
		
		SkeletonJson json = new SkeletonJson(Level.imageAtlas);
		SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal("FChar.json"), "FChar/");
		
		PlayerAnimationManager animationManager = new PlayerAnimationManager();
		
		legState = new AnimationState(animationManager.setupLegAnimations(skeletonData));
		armState = new AnimationState(animationManager.setupArmAnimations(skeletonData));

		initialiseSkeleton(skeletonData);
		
		currentStateArm = new ArmIdle(armState);
		currentStateLeg = new LegIdle(legState);
		
		setupPlayerAttachments();
		
		depth = 20;
	}
	
	private void initialiseSkeleton(SkeletonData skeletonData){
		skeleton = new Skeleton(skeletonData);
		skeleton.setFlipY(true);
		skeleton.getRootBone().setScaleY(skeletonScaleY);
		skeleton.getRootBone().setScaleX(skeletonScaleX);
		skeleton.updateWorldTransform();
	}
	
	private void setupPlayerAttachments(){
		playerAttachments = new ObjectMap<String, GameObjectAttachment>();
		
		GameObjectAttachment rightWeaponAttachment = new GameObjectAttachment("SwordAttachment");
		swordAttachment = new SwordAttachment();
		rightWeaponAttachment.setGameObject(swordAttachment);
		rightWeaponAttachment.setInitialRotation(90);
		
		playerAttachments.put("RightWeapon", rightWeaponAttachment);
	}
	
	
	public void manualUpdatePre(boolean isDead, boolean isGrounded, boolean isHit, boolean isMouseDown, float hSpeed){
		updateSkeletonLocation();
		
        updateAnimations(isDead, isGrounded, isHit, isMouseDown, hSpeed);
        
        manageAttachments();
        
        finalSkeletonUpdate();
	}
	
	private void updateSkeletonLocation(){
		skeleton.setY(y+DRAW_OFFSET_Y);
		skeleton.setX(x+DRAW_OFFSET_X);
	}
	
	private void manageAttachments(){
		skeleton.findSlot("WeaponInnerRight").setAttachment(playerAttachments.get("RightWeapon",null));
	}
	
	private void finalSkeletonUpdate(){
		skeleton.getRootBone().setScaleY(skeletonScaleY);
		skeleton.getRootBone().setScaleX(skeletonScaleX);
		skeleton.updateWorldTransform();
	}
	
	@Override
	public void onDraw(SpriteBatch spriteBatch){
		renderer.draw(spriteBatch, skeleton);
	}
	
	public void updateAnimations(boolean isDead, boolean isGrounded, boolean isHit, boolean isMouseDown, float hSpeed){
		
		dirH = -(int) Math.signum(hSpeed);
		
		if (dirH != 0 && (currentStateArm.state != ArmState.ATTACK || currentStateArm.isNewState)){
			skeleton.setFlipX(dirH<0);
		}
		
		ArmFSMData fsmDataArm = new ArmFSMData();
		fsmDataArm.isDead = isDead;
		fsmDataArm.isGrounded = isGrounded;
		fsmDataArm.isHit = isHit;
		fsmDataArm.isMouseDown = isMouseDown;
		fsmDataArm.hSpeed = hSpeed;
		fsmDataArm.legState = currentStateLeg;
		currentStateArm = currentStateArm.transition(fsmDataArm);

		LegFSMData fsmDataLeg = new LegFSMData();
		fsmDataLeg.isDead = isDead;
		fsmDataLeg.isGrounded = isGrounded;
		fsmDataLeg.isHit = isHit;
		fsmDataLeg.isMouseDown = isMouseDown;
		fsmDataLeg.hSpeed = hSpeed;
		fsmDataLeg.armState = currentStateArm;
		currentStateLeg = currentStateLeg.transition(fsmDataLeg);
		
		if ((currentStateArm.state == ArmState.ATTACK || currentStateArm.state == ArmState.COMBO1ATTACK) && currentStateArm.isNewState){
			swordAttachment.currentState = WeaponState.ATTACK;
			swordAttachment.resetAttackArray();
		}
		else if ((currentStateArm.state != ArmState.ATTACK && currentStateArm.state != ArmState.COMBO1ATTACK)){
			swordAttachment.currentState = WeaponState.IDLE;
			swordAttachment.resetAttackArray();
		}
		
		armState.update(armUpdateSpeed);
		legState.update(legUpdateSpeed);
		
		armState.apply(skeleton);
		legState.apply(skeleton);
	}

}
