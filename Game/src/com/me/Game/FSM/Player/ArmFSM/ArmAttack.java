package com.me.Game.FSM.Player.ArmFSM;

import com.esotericsoftware.spine.AnimationState;
import com.me.Game.AnimationManagers.Player.PlayerAnimationManager;

public class ArmAttack extends ArmFSMState {

	boolean mouseClicked = false;
	
	public ArmAttack(AnimationState animState, boolean mouseClicked){
		state = ArmState.ATTACK;
		animationState = animState;
		animationState.setAnimation(PlayerAnimationManager.ARM_ATTACK_ANIMATION, false);
		isNewState = true;
		this.mouseClicked = mouseClicked;
	}
	
	@Override
	public ArmFSMState transition(ArmFSMData data) {
isNewState = false;
		
		if (data.isMouseDown){
			mouseClicked = true;
		}
		
		//ATTACK TO ATTACK non new
		ArmFSMState transState = this;
		
		//ATTACK TO DEAD
		if (data.isDead){
			transState = new ArmDeath(animationState);
		}
		//ATTACK TO HIT
		else if (data.isHit){
			transState = new ArmHit(animationState);
		}
		//ATTACK TO COCK new attack
		else if (mouseClicked && animationState.isComplete()){
			transState = new ArmCombo1Cock(animationState);
		}
		//ATTACK TO IDLE
		else if (data.isGrounded && data.hSpeed == 0 && animationState.isComplete()){
			transState = new ArmIdle(animationState);
		}
		//ATTACK TO RUNNING
		else if (data.isGrounded && data.hSpeed != 0 && animationState.isComplete()){
			transState = new ArmRunning(animationState);
		}
		//ATTACK TO JUMPING
		else if (!data.isGrounded && animationState.isComplete()){
			transState = new ArmJumping(animationState);
		}

		return transState;
	}

}
