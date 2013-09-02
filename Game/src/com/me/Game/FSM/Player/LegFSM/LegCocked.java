package com.me.Game.FSM.Player.LegFSM;

import com.esotericsoftware.spine.AnimationState;
import com.me.Game.AnimationManagers.Player.PlayerAnimationManager;

public class LegCocked extends LegFSMState {

	boolean mouseClicked;
	
	public LegCocked(AnimationState animState, boolean mouseClicked){
		state = LegState.COCKED;
		animationState = animState;
		animationState.setAnimation(PlayerAnimationManager.LEG_COCKED_ANIMATION, false);
		isNewState = true;
		this.mouseClicked = mouseClicked;
	}


	@Override
	public LegFSMState transition(LegFSMData data) {
isNewState = false;
		
		//COCKED TO COCKED
		LegFSMState transState = this;

		//COCKED TO DEAD
		if (data.isDead){
			transState = new LegDeath(animationState);
		}
		//COCKED TO HIT
		else if (data.isHit){
			transState = new LegHit(animationState);
		}
		//COCKED TO JUMPING
		else if (!data.isGrounded){
			transState = new LegJumping(animationState);
		}
		//COCKED TO RUNNING
		else if (data.hSpeed != 0){
			transState = new LegRunning(animationState);
		}
		//COCKED TO ATTACK
		else if (!data.isMouseDown){
			transState = new LegAttack(animationState, mouseClicked);
		}
		else{
			mouseClicked = false;
		}

		return transState;
	}

}
