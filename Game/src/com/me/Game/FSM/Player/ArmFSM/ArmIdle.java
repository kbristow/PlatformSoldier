package com.me.Game.FSM.Player.ArmFSM;

import com.esotericsoftware.spine.AnimationState;
import com.me.Game.AnimationManagers.Player.PlayerAnimationManager;

public class ArmIdle extends ArmFSMState {
	
	public ArmIdle(AnimationState animState){
		state = ArmState.IDLE;
		animationState = animState;
		animationState.setAnimation(PlayerAnimationManager.ARM_IDLE_ANIMATION, true);
	}

	@Override
	public ArmFSMState transition(ArmFSMData data) {

		isNewState = false;
		
		//IDLE TO IDLE
		ArmFSMState transState = this;
		
		//IDLE TO DEAD
		if (data.isDead){
			transState = new ArmDeath(animationState);
		}
		//IDLE TO JUMPING
		else if(!data.isGrounded){
			transState = new ArmJumping(animationState);
		}
		//IDLE TO HIT
		else if (data.isHit){
			transState = new ArmHit(animationState);
		}
		//RUNNING TO COCK
		else if (data.isMouseDown){
			transState = new ArmCock(animationState);
		}
		//IDLE TO RUNNING
		else if(data.hSpeed!=0 && data.isGrounded){
			transState = new ArmRunning(animationState);
		}
		
		return transState;
	}

}
