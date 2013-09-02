package com.me.Game.FSM.Player.ArmFSM;

import com.esotericsoftware.spine.AnimationState;
import com.me.Game.AnimationManagers.Player.PlayerAnimationManager;

public class ArmJumping extends ArmFSMState {

	public ArmJumping(AnimationState animState){
		state = ArmState.IDLE;
		animationState = animState;
		animationState.setAnimation(PlayerAnimationManager.ARM_JUMPING_ANIMATION, false);
	}

	@Override
	public ArmFSMState transition(ArmFSMData data) {

		isNewState = false;
		
		//JUMPING TO JUMPING
		ArmFSMState transState = this;
		
		//JUMPING TO DEAD
		if (data.isDead){
			transState = new ArmDeath(animationState);
		}
		//RUNNING TO COCK
		else if (data.isMouseDown){
			transState = new ArmCock(animationState);
		}
		//JUMPING TO IDLE
		else if (data.isGrounded && data.hSpeed == 0){
			transState = new ArmIdle(animationState);
		}
		//JUMPING TO RUNNING
		else if (data.isGrounded && data.hSpeed != 0){
			transState = new ArmRunning(animationState);
		}
		
		return transState;
	}

}
