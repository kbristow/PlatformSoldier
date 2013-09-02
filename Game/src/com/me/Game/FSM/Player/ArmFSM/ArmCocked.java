package com.me.Game.FSM.Player.ArmFSM;

import com.esotericsoftware.spine.AnimationState;
import com.me.Game.AnimationManagers.Player.PlayerAnimationManager;

public class ArmCocked extends ArmFSMState {

	public boolean mouseClicked = false;
	public boolean waitingOnLegs = false;
	
	public ArmCocked(AnimationState animState, boolean mouseClicked){
		state = ArmState.COCKED;
		animationState = animState;
		animationState.setAnimation(PlayerAnimationManager.ARM_COCKED_ANIMATION, false);
		isNewState = true;
		this.mouseClicked = mouseClicked;
	}
	
	@Override
	public ArmFSMState transition(ArmFSMData data) {
		
		isNewState = false;
		
		//COCKED TO COCKED
		ArmFSMState transState = this;

		//COCKED TO DEAD
		if (data.isDead){
			transState = new ArmDeath(animationState);
		}
		//COCKED TO HIT
		else if (data.isHit){
			transState = new ArmHit(animationState);
		}
		//COCKED TO ATTACK
		else if (!data.isMouseDown){// && (data.legState.state == LegState.RUNNING || data.legState.state == LegState.JUMPING)){
			transState = new ArmAttack(animationState, mouseClicked);
		}
		else{
			mouseClicked = false;
		}

		return transState;
	}

}
