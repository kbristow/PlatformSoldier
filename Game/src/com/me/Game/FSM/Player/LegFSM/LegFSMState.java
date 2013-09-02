package com.me.Game.FSM.Player.LegFSM;

import com.esotericsoftware.spine.AnimationState;

public abstract class LegFSMState {
	
	public LegState state = LegState.IDLE;
	public AnimationState animationState = null;
	public boolean isNewState = true;
	public boolean allowRunning = true;
	
	
	public enum LegState{
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

	public abstract LegFSMState transition(LegFSMData data);
}
