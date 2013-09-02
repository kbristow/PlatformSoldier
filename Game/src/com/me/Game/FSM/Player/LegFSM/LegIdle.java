package com.me.Game.FSM.Player.LegFSM;

import com.esotericsoftware.spine.AnimationState;
import com.me.Game.AnimationManagers.Player.PlayerAnimationManager;
import com.me.Game.FSM.Player.ArmFSM.ArmFSMState.ArmState;

public class LegIdle extends LegFSMState {
	
	public LegIdle(AnimationState animState){
		state = LegState.IDLE;
		animationState = animState;
		animationState.setAnimation(PlayerAnimationManager.LEG_IDLE_ANIMATION, true);
	}

	@Override
	public LegFSMState transition(LegFSMData data) {
isNewState = false;
		
		//IDLE TO IDLE
		LegFSMState transState = this;
		
		//IDLE TO DEAD
		if (data.isDead){
			transState = new LegDeath(animationState);
		}
		//IDLE TO JUMPING
		else if(!data.isGrounded){
			transState = new LegJumping(animationState);
		}
		//IDLE TO HIT
		else if (data.isHit){
			transState = new LegHit(animationState);
		}
		//IDLE TO COCK
		else if (data.isMouseDown && (data.armState == null || data.armState.state == ArmState.IDLE || (data.armState.state == ArmState.COCK && data.armState.isNewState))){
			transState = new LegCock(animationState);
		}
		//IDLE TO COCKED
		else if (data.isMouseDown && data.armState != null && data.armState.state == ArmState.COCKED){
			transState = new LegCocked(animationState, false);
		}
		//IDLE TO COMBO1COCK
		else if (data.armState != null && data.armState.state == ArmState.COMBO1COCK && data.armState.isNewState){
			transState = new LegCombo1Cock(animationState);
		}
		//IDLE TO RUNNING
		else if(data.hSpeed!=0 && data.isGrounded){
			transState = new LegRunning(animationState);
		}
		
		return transState;
	}

}
