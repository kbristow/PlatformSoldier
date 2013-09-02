package com.me.Game.FSM.Player.ArmFSM;

import com.esotericsoftware.spine.AnimationState;
import com.me.Game.AnimationManagers.Player.PlayerAnimationManager;

public class ArmRunning extends ArmFSMState {

	public ArmRunning(AnimationState animState){
		state = ArmState.IDLE;
		animationState = animState;
		animationState.setAnimation(PlayerAnimationManager.ARM_RUNNING_ANIMATION, true);
	}

	@Override
	public ArmFSMState transition(ArmFSMData data) {
		isNewState = false;
		
		//RUNNING TO RUNNING
		ArmFSMState transState = this;
		
		//RUNNING TO DEAD
		if (data.isDead){
			transState = new ArmDeath(animationState);
		}
		//RUNNING TO HIT
		else if (data.isHit){
			transState = new ArmHit(animationState);
		}
		//RUNNING TO COCK
		else if (data.isMouseDown){
			transState = new ArmCock(animationState);
		}
		//RUNNING TO JUMPING
		else if (!data.isGrounded){
			transState = new ArmJumping(animationState);
		}
		//RUNNING TO IDLE
		else if (data.hSpeed == 0){
			transState = new ArmIdle(animationState);
		}
		
		return transState;
	}

}
