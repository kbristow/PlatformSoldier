package com.me.Game.FSM;

public class FSMData {
	public boolean isDead;
	public boolean isGrounded;
	public boolean isHit;
	public boolean isMouseDown;
	public float hSpeed;
	
	public FSMData(){
		isDead = false;
		isGrounded = false;
		isHit = false;
		isMouseDown = false;
		hSpeed = 0;
	}
}
