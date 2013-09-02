package com.me.Game.FSM.Player.ArmFSM;

import com.esotericsoftware.spine.AnimationState;
import com.me.Game.AnimationManagers.Player.PlayerAnimationManager;

public class ArmDeath extends ArmFSMState {

	public ArmDeath(AnimationState animState){
		state = ArmState.DEAD;
		animationState = animState;
		animationState.setAnimation(PlayerAnimationManager.ARM_DEAD_ANIMATION, false);
	}

	@Override
	public ArmFSMState transition(ArmFSMData data) {

		isNewState = false;
		
		//DEATH TO DEATH
		ArmFSMState transState = this;
		return transState;
	}

}
