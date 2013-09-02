package com.me.Game.FSM.Player.LegFSM;

import com.esotericsoftware.spine.AnimationState;
import com.me.Game.AnimationManagers.Player.PlayerAnimationManager;
import com.me.Game.FSM.Player.ArmFSM.ArmFSMState.ArmState;

public class LegRunning extends LegFSMState {

	public LegRunning(AnimationState animState){
		state = LegState.RUNNING;
		animationState = animState;
		animationState.setAnimation(PlayerAnimationManager.LEG_RUNNING_ANIMATION, true);
	}

	@Override
	public LegFSMState transition(LegFSMData data) {
		isNewState = false;

		//RUNNING TO RUNNING
		LegFSMState transState = this;
		
		//RUNNING TO DEAD
		if (data.isDead){
			transState = new LegDeath(animationState);
		}
		//RUNNING TO HIT
		else if (data.isHit){
			transState = new LegHit(animationState);
		}
		//RUNNING TO JUMPING
		else if (!data.isGrounded){
			transState = new LegJumping(animationState);
		}
		//RUNNING TO COCKED
		else if (data.hSpeed == 0 && data.isMouseDown && (data.armState == null || data.armState.state == ArmState.IDLE || (data.armState.state == ArmState.COCK && data.armState.isNewState))){
			transState = new LegCocked(animationState, data.isMouseDown);
		}
		//RUNNING TO IDLE
		else if (data.hSpeed == 0){
			transState = new LegIdle(animationState);
		}
		
		return transState;
	}

}
