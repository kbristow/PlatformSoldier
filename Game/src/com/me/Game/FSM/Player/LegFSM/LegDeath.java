package com.me.Game.FSM.Player.LegFSM;

import com.esotericsoftware.spine.AnimationState;
import com.me.Game.AnimationManagers.Player.PlayerAnimationManager;

public class LegDeath extends LegFSMState {

	public LegDeath(AnimationState animState){
		state = LegState.DEAD;
		animationState = animState;
		animationState.setAnimation(PlayerAnimationManager.LEG_DEAD_ANIMATION, false);
	}

	@Override
	public LegFSMState transition(LegFSMData data) {
		isNewState = false;
		//DEATH TO DEATH
		LegFSMState transState = this;
		return transState;
	}

}
