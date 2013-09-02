package com.me.Game.FSM.Player.ArmFSM;

import com.esotericsoftware.spine.AnimationState;
import com.me.Game.AnimationManagers.Player.PlayerAnimationManager;

public class ArmCock extends ArmFSMState {
	
	public boolean mouseClicked = false;
	public boolean mouseRelease = false;

	public ArmCock(AnimationState animState){
		state = ArmState.COCK;
		animationState = animState;
		animationState.setAnimation(PlayerAnimationManager.ARM_COCK_ANIMATION, false);
		isNewState = true;
	}
	

	@Override
	public ArmFSMState transition(ArmFSMData data) {
		if (data.isMouseDown && mouseRelease){
			mouseClicked = true;
		}
		else if (!data.isMouseDown){
			mouseRelease = true;
		}
		
		
		isNewState = false;
		
		
		
		//ATTACK TO ATTACK non new
		ArmFSMState transState = this;

		//COCK TO DEAD
		if (data.isDead){
			transState = new ArmDeath(animationState);
		}
		//COCK TO HIT
		else if (data.isHit){
			transState = new ArmHit(animationState);
		}
		//COCK TO COCKED
		else if (animationState.isComplete()){
			transState = new ArmCocked(animationState, mouseClicked);
			
		}

		return transState;
	}

}
