package com.me.Game.FSM.Player.LegFSM;

import com.esotericsoftware.spine.AnimationState;
import com.me.Game.AnimationManagers.Player.PlayerAnimationManager;

public class LegCombo1Attack extends LegFSMState {

	boolean mouseClicked = false;
	
	public LegCombo1Attack(AnimationState animState){
		state = LegState.COMBO1ATTACK;
		animationState = animState;
		animationState.setAnimation(PlayerAnimationManager.LEG_COMBO1_ATTACK_ANIMATION, false);
		//System.out.println(COMBO1_COCK_ANIMATION);
		isNewState = true;
	}
	

	@Override
	public LegFSMState transition(LegFSMData data) {
isNewState = false;
		
		if (data.isMouseDown){
			mouseClicked = true;
		}
		
		//ATTACK TO ATTACK non new
		LegFSMState transState = this;

		//ATTACK TO DEAD
		if (data.isDead){
			transState = new LegDeath(animationState);
		}
		//ATTACK TO HIT
		else if (data.isHit){
			transState = new LegHit(animationState);
		}
		//ATTACK TO COCK new attack
		else if (mouseClicked && data.isGrounded && data.hSpeed == 0 && animationState.isComplete()){
			transState = new LegCock(animationState);
		}
		//ATTACK TO IDLE
		else if (data.isGrounded && data.hSpeed == 0 && animationState.isComplete()){
			transState = new LegIdle(animationState);
		}
		//ATTACK TO RUNNING
		else if (data.isGrounded && data.hSpeed != 0 && animationState.isComplete()){
			transState = new LegRunning(animationState);
		}
		//ATTACK TO JUMPING
		else if (!data.isGrounded && animationState.isComplete()){
			transState = new LegJumping(animationState);
		}

		return transState;
	}

}
