package com.me.Game.FSM.Player.ArmFSM;

import com.esotericsoftware.spine.AnimationState;
import com.me.Game.AnimationManagers.Player.PlayerAnimationManager;

public class ArmHit extends ArmFSMState {

	public ArmHit(AnimationState animState){
		state = ArmState.IDLE;
		animationState = animState;
		animationState.setAnimation(PlayerAnimationManager.ARM_HIT_ANIMATION, false);
	}

	@Override
	public ArmFSMState transition(ArmFSMData data) {

		isNewState = false;
		
		//HIT TO HIT
		ArmFSMState transState = this;
		
		//HIT TO DEAD
		if (data.isDead){
			transState = new ArmDeath(animationState);
		}
		//HIT TO IDLE
		else if (animationState.isComplete()){
			transState = new ArmIdle(animationState);
		}


		return transState;
	}

}
