package com.me.Game.FSM.Player.ArmFSM;

import com.esotericsoftware.spine.AnimationState;

public abstract class ArmFSMState {
	
	public ArmState state = ArmState.IDLE;
	public AnimationState animationState = null;
	public boolean isNewState = true;
	
	public enum ArmState{
		IDLE,
		RUNNING,
		JUMPING,
		HIT,
		DEAD,
		ATTACK,
		COCK,
		COCKED,
		COMBO1ATTACK,
		COMBO1COCK
	}
	
	public abstract ArmFSMState transition(ArmFSMData data);
	
}
