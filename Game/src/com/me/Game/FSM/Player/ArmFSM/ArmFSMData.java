package com.me.Game.FSM.Player.ArmFSM;

import com.me.Game.FSM.FSMData;
import com.me.Game.FSM.Player.LegFSM.LegFSMState;

public class ArmFSMData extends FSMData {
	
	public LegFSMState legState;
	
	public ArmFSMData(){
		super();
		legState = null;
	}
}
