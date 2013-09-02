package com.me.Game.FSM.Player.LegFSM;

import com.esotericsoftware.spine.AnimationState;
import com.me.Game.AnimationManagers.Player.PlayerAnimationManager;

public class LegJumping extends LegFSMState {
	
	public LegJumping(AnimationState animState){
		state = LegState.JUMPING;
		animationState = animState;
		animationState.setAnimation(PlayerAnimationManager.LEG_JUMPING_ANIMATION, false);
	}


	@Override
	public LegFSMState transition(LegFSMData data) {
		isNewState = false;
		
		//JUMPING TO JUMPING
		LegFSMState transState = this;
		
		//JUMPING TO DEAD
		if (data.isDead){
			transState = new LegDeath(animationState);
		}
		//JUMPING TO COCKED
		else if (data.isGrounded && data.hSpeed == 0 && data.isMouseDown){
			transState = new LegCocked(animationState, data.isMouseDown);
		}
		//JUMPING TO IDLE
		else if (data.isGrounded && data.hSpeed == 0){
			transState = new LegIdle(animationState);
		}
		//JUMPING TO RUNNING
		else if (data.isGrounded && data.hSpeed != 0){
			transState = new LegRunning(animationState);
		}
		
		return transState;
	}

}
