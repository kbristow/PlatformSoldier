package com.me.Game.AnimationManagers.Player;

import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.SkeletonData;

public class PlayerAnimationManager {
	//Leg Animations
	public static final String LEG_IDLE_ANIMATION = "LegsIdle";
	public static final String LEG_JUMPING_ANIMATION = "LegsJumping";
	public static final String LEG_RUNNING_ANIMATION = "LegsRunning";
	public static final String LEG_DEAD_ANIMATION = "LegsIdle";
	public static final String LEG_HIT_ANIMATION = "AttackedLegs";
	public static final String LEG_ATTACK_ANIMATION = "ComboAttack1LegsAttack";
	public static final String LEG_COCK_ANIMATION = "ComboAttack1LegsCock";
	public static final String LEG_COCKED_ANIMATION = "ComboAttack1LegsCocked";
	
	public static final String LEG_COMBO1_ATTACK_ANIMATION = "ComboAttack2LegsAttack";
	public static final String LEG_COMBO1_COCK_ANIMATION = "ComboAttack2LegsCock";
	
	
	//Arm animations
	public static final String ARM_IDLE_ANIMATION = "ArmsIdle";
	public static final String ARM_JUMPING_ANIMATION = "ArmsIdle";
	public static final String ARM_RUNNING_ANIMATION = "ArmsRunning";
	public static final String ARM_DEAD_ANIMATION = "ArmsIdle";
	public static final String ARM_HIT_ANIMATION = "AttackedArms";
	public static final String ARM_ATTACK_ANIMATION = "ComboAttack1ArmsAttack";
	public static final String ARM_COCK_ANIMATION = "ComboAttack1ArmsCock";
	public static final String ARM_COCKED_ANIMATION = "ComboAttack1ArmsCocked";
	
	public static final String ARM_COMBO1_ATTACK_ANIMATION = "ComboAttack2ArmsAttack";
	public static final String ARM_COMBO1_COCK_ANIMATION = "ComboAttack2ArmsCock";
	
	public PlayerAnimationManager (){
		
	}
	
	public AnimationStateData setupArmAnimations(SkeletonData skeletonData){
		AnimationStateData stateData = new AnimationStateData(skeletonData);
		
		stateData.setMix(ARM_RUNNING_ANIMATION, ARM_COCK_ANIMATION, 0.2f);
		stateData.setMix(ARM_IDLE_ANIMATION, ARM_COCK_ANIMATION, 0.1f);
		stateData.setMix(ARM_ATTACK_ANIMATION, ARM_IDLE_ANIMATION, 0.1f);
		stateData.setMix(ARM_ATTACK_ANIMATION, ARM_RUNNING_ANIMATION, 0.1f);
		stateData.setMix(ARM_ATTACK_ANIMATION, ARM_COCK_ANIMATION, 0.1f);
		stateData.setMix(ARM_RUNNING_ANIMATION, ARM_IDLE_ANIMATION, 0.1f);
		stateData.setMix(ARM_IDLE_ANIMATION, ARM_RUNNING_ANIMATION, 0.1f);
		stateData.setMix(ARM_ATTACK_ANIMATION, ARM_COMBO1_COCK_ANIMATION, 0.1f);
		stateData.setMix(ARM_COMBO1_ATTACK_ANIMATION, ARM_IDLE_ANIMATION, 0.1f);
		stateData.setMix(ARM_COMBO1_ATTACK_ANIMATION, ARM_RUNNING_ANIMATION, 0.1f);
		stateData.setMix(ARM_COMBO1_ATTACK_ANIMATION, ARM_COCK_ANIMATION, 0.25f);
		
		return stateData;
	}
	
	
	public AnimationStateData setupLegAnimations(SkeletonData skeletonData){
		AnimationStateData stateData = new AnimationStateData(skeletonData);
		
		stateData.setMix(LEG_RUNNING_ANIMATION, LEG_JUMPING_ANIMATION, 0.2f);
		stateData.setMix(LEG_JUMPING_ANIMATION, LEG_RUNNING_ANIMATION, 0.2f);
		stateData.setMix(LEG_JUMPING_ANIMATION, LEG_JUMPING_ANIMATION, 0.2f);
		stateData.setMix(LEG_JUMPING_ANIMATION, LEG_IDLE_ANIMATION, 0.2f);
		stateData.setMix(LEG_IDLE_ANIMATION, LEG_JUMPING_ANIMATION, 0.1f);
		stateData.setMix(LEG_RUNNING_ANIMATION, LEG_IDLE_ANIMATION, 0.1f);
		stateData.setMix(LEG_IDLE_ANIMATION, LEG_RUNNING_ANIMATION, 0.1f);
		stateData.setMix(LEG_RUNNING_ANIMATION, LEG_COCK_ANIMATION, 0.1f);
		stateData.setMix(LEG_RUNNING_ANIMATION, LEG_COCKED_ANIMATION, 0.1f);
		stateData.setMix(LEG_COCKED_ANIMATION, LEG_RUNNING_ANIMATION, 0.1f);
		stateData.setMix(LEG_JUMPING_ANIMATION, LEG_COCKED_ANIMATION, 0.1f);
		stateData.setMix(LEG_COCKED_ANIMATION, LEG_JUMPING_ANIMATION, 0.2f);
		stateData.setMix(LEG_IDLE_ANIMATION, LEG_COCK_ANIMATION, 0.1f);
		stateData.setMix(LEG_IDLE_ANIMATION, LEG_COCKED_ANIMATION, 0.1f);
		stateData.setMix(LEG_ATTACK_ANIMATION, LEG_IDLE_ANIMATION, 0.1f);
		stateData.setMix(LEG_ATTACK_ANIMATION, LEG_RUNNING_ANIMATION, 0.1f);
		stateData.setMix(LEG_ATTACK_ANIMATION, LEG_COMBO1_COCK_ANIMATION, 0.1f);
		stateData.setMix(LEG_COMBO1_ATTACK_ANIMATION, LEG_IDLE_ANIMATION, 0.1f);
		stateData.setMix(LEG_COMBO1_ATTACK_ANIMATION, LEG_RUNNING_ANIMATION, 0.1f);
		stateData.setMix(LEG_COMBO1_ATTACK_ANIMATION, LEG_COCK_ANIMATION, 0.25f);
		
		return stateData;
	}
}
