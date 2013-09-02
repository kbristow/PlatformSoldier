package com.me.Game.FSM.Player.LegFSM;

import com.esotericsoftware.spine.AnimationState;
import com.me.Game.AnimationManagers.Player.PlayerAnimationManager;

public class LegCock extends LegFSMState {

	public boolean mouseClicked = false;
	public boolean mouseRelease = false;
	
	public LegCock(AnimationState animState){
		state = LegState.COCK;
		animationState = animState;
		animationState.setAnimation(PlayerAnimationManager.LEG_COCK_ANIMATION, false);
		isNewState = true;
	}
	

	@Override
	public LegFSMState transition(LegFSMData data) {
isNewState = false;
		
		//ATTACK TO ATTACK non new
		LegFSMState transState = this;

		//COCK TO DEAD
		if (data.isDead){
			transState = new LegDeath(animationState);
		}
		//COCK TO HIT
		else if (data.isHit){
			transState = new LegHit(animationState);
		}
		//COCK TO RUNNING
		else if (!data.isGrounded){
			transState = new LegJumping(animationState);
		}
		//COCK TO RUNNING
		else if (data.hSpeed!= 0){
			transState = new LegRunning(animationState);
		}
		//COCK TO COCKED
		else if (animationState.isComplete()){
			transState = new LegCocked(animationState, mouseClicked);
		}

		return transState;
	}

}
