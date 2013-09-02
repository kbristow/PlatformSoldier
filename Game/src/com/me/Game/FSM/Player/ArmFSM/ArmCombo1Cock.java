package com.me.Game.FSM.Player.ArmFSM;

import com.esotericsoftware.spine.AnimationState;
import com.me.Game.AnimationManagers.Player.PlayerAnimationManager;

public class ArmCombo1Cock extends ArmFSMState {

	public ArmCombo1Cock(AnimationState animState){
		state = ArmState.COMBO1COCK;
		animationState = animState;
		animationState.setAnimation(PlayerAnimationManager.ARM_COMBO1_COCK_ANIMATION, false);
		isNewState = true;
	}

	@Override
	public ArmFSMState transition(ArmFSMData data) {

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
			transState = new ArmCombo1Attack(animationState);
		}

		return transState;
	}

}
