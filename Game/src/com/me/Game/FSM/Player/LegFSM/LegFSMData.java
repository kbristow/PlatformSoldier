package com.me.Game.FSM.Player.LegFSM;

import com.me.Game.FSM.FSMData;
import com.me.Game.FSM.Player.ArmFSM.ArmFSMState;

public class LegFSMData extends FSMData {
	public ArmFSMState armState;
	
	public LegFSMData(){
		super();
		armState = null;
	}
}
