package com.me.Game.FSM.Player.LegFSM;

import com.esotericsoftware.spine.AnimationState;
import com.me.Game.AnimationManagers.Player.PlayerAnimationManager;

public class LegHit extends LegFSMState {

	public LegHit(AnimationState animState){
		state = LegState.HIT;
		animationState = animState;
		animationState.setAnimation(PlayerAnimationManager.LEG_HIT_ANIMATION, false);
	}
	

	@Override
	public LegFSMState transition(LegFSMData data) {
isNewState = false;
		
		//HIT TO HIT
		LegFSMState transState = this;
		
		//HIT TO DEAD
		if (data.isDead){
			transState = new LegDeath(animationState);
		}
		//HIT TO IDLE
		else if (animationState.isComplete()){
			transState = new LegIdle(animationState);
		}


		return transState;
	}

}
