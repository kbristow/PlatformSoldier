package com.me.Game.FSM.Player.LegFSM;

import com.esotericsoftware.spine.AnimationState;
import com.me.Game.AnimationManagers.Player.PlayerAnimationManager;

public class LegCombo1Cock extends LegFSMState {

	public LegCombo1Cock(AnimationState animState){
		state = LegState.COMBO1COCK;
		animationState = animState;
		animationState.setAnimation(PlayerAnimationManager.LEG_COMBO1_COCK_ANIMATION, false);
		//System.out.println(COMBO1_COCK_ANIMATION);
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
		//COCK TO ATTACK
		else if (animationState.isComplete()){
			transState = new LegCombo1Attack(animationState);
		}

		return transState;
	}

}
