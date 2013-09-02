package com.me.Game.Enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.me.Engine.Structures.GameSprite;
import com.me.Game.Structures.GameObjectDestroyable;

public class EnemyControl extends GameObjectDestroyable{

	final float GRAVITY_CONST = -1.6f;
	final float GRAVITY_VARIABLE_JUMP = -1.2f;
	final float JUMP_SPEED = -41f;
	final float H_MOVE_SPEED = 14.0f;
	final float MIN_Y = 30f;
	
	final float LIFEBAR_OFFSET_X=-66;
	final float LIFEBAR_OFFSET_Y=-200;
	final float LIFEBAR_YSCALE = 0.7f;
	final float LIFEBAR_XMARGIN = 5;
	final float LIFEBAR_YMARGIN = 4;
	
	final float COLLISION_MASK_WIDTH = 100;
	
	boolean isGrounded = false;
	public boolean isHit = false;
	
	EnemyAnimation playerAnimations;
	
	@Override
	public void onCreate(){
		super.onCreate();
		lifeBarOffsetX = LIFEBAR_OFFSET_X;
		lifeBarOffsetY = LIFEBAR_OFFSET_Y;
		lifeBar.yScale = LIFEBAR_YSCALE;
		lifeBar.setForeBackMarginX(LIFEBAR_XMARGIN);
		lifeBar.setForeBackMarginY(LIFEBAR_YMARGIN);
		
		objTypes.add("Enemy");
        
        GameSprite upright = new GameSprite();
        
        upright.setOrigin(COLLISION_MASK_WIDTH/2, 120);
        upright.setAABBCollisionMask(COLLISION_MASK_WIDTH / 3.0f - 5, 169, COLLISION_MASK_WIDTH / 3.0f + 5,0);
        upright.debug = false;

        sprites.add(upright);
        
        gravityDir = -1;
        gravity = GRAVITY_CONST;
        setCollides(true);
        
        playerAnimations = new EnemyAnimation();
        
        lifeBar.setGraphics("Interface/LifebarBack.png", "Interface/LifebarFront.png");  
	}
	
	@Override
	public void preStep(){
		//controlKeyboard();
		collisionHandling();
		controlAttachments();
		//Level.camera.position.set(x+hSpeed, y+vSpeed, Level.camera.position.z);
		super.preStep();
	}
	
	/**
	 * Handle collisions with world objects to determine where we can walk and whether we are falling on grounded
	 */
    public void collisionHandling()
    {
        float dirV = 1;
        if (vSpeed < 0)
        {
            dirV = -1;
        }

        if (checkCollision((int)x, (int)(Math.ceil(y + vSpeed + dirV)), "World"))
        {
            while (dirV * vSpeed > 0 && checkCollision((int)x, (int)(y + vSpeed + dirV), "World") || y + vSpeed + dirV < 0)
            {
                vSpeed += getVSpeedDelta(vSpeed) * dirV;
            }
            postCollisionSet(dirV);
        }
        else if (checkCollision((int)x, (int)(Math.ceil(y + vSpeed + dirV)), "DDoodad"))
        {
            while (dirV * vSpeed > 0 && checkCollision((int)x, (int)(y + vSpeed + dirV), "DDoodad"))
            {
            	vSpeed += getVSpeedDelta(vSpeed) * dirV;
            }
            postCollisionSet(dirV);
        }
        else if (y + vSpeed + dirV - 50 < 0 )//|| y + vSpeed + dirV > Level.Cameras[0].WorldHeight)
        {
            while (dirV * vSpeed > 0 &&( y + vSpeed + dirV -50 < 0))// || y + vSpeed + dirV > Level.Cameras[0].WorldHeight))
            {
                vSpeed += (-1) * dirV;
            }
            postCollisionSet(dirV);
        }
        else
        {
            isGrounded = false;
        }
        
        
        if (checkCollision((int)x, (int)y, "World")){
        	sprites.get(getSpriteIndex()).mask.lineColor = Color.RED;
        }
        else{
        	sprites.get(getSpriteIndex()).mask.lineColor = Color.MAGENTA;
        }
    }
    
    /**
     * Called after the object falls onto or hits head on an object
     * @param dirV The vertical direction
     */
    private void postCollisionSet(float dirV){
    	gravity = 0;
        if (dirV * vSpeed < 0)
        {
            vSpeed = 0;
        }
        if (dirV == 1)
        {
            isGrounded = true;
        }
    }
    
    /**
     * Used to decrease the speed enough such that the falling object does not fall through the ground. Values inside are somewhat arbitrarily chosen right now
     * @param vSpeed Current vertical speed
     * @return The amount to change the speed by
     */
    private float getVSpeedDelta(float vSpeed){
    	if (Math.abs(vSpeed) > 10){
    		return -5f;    		
    	}
    	else if (Math.abs(vSpeed) > 5){
    		return -1f;    		
    	}
    	else{
    		return -0.35f;    		
    	}
    }

    /**
     * Control the movement and jumping of the character and the associated animations
     */
    public void controlKeyboard()
    {
    	//Jumping
        if (Gdx.input.isKeyPressed(Keys.W) && isGrounded)
        {
        	//Make the character jump
            vSpeed =  JUMP_SPEED;
            gravity = GRAVITY_CONST;
            isGrounded = false;
        }
        else if (!isGrounded && !Gdx.input.isKeyPressed(Keys.W) && vSpeed<0)
        {
        	//This handles the variable jumping
            gravity = GRAVITY_CONST + GRAVITY_VARIABLE_JUMP;
        }
        else if (!isGrounded)
        {
        	//This applies normal gravity
            gravity = GRAVITY_CONST;
        }
        
        //Horizontal movement direction
        int dirH = 0;
        if (Gdx.input.isKeyPressed(Keys.D)){
            dirH = 1;
        }else if (Gdx.input.isKeyPressed(Keys.A)){
            dirH = -1;
        }
        
        //Set the movement speed
        hSpeed = dirH * H_MOVE_SPEED;
        
        //Check that we can actually move in the direction by the amount speed is set to
        if (checkCollision((int)(x + hSpeed + dirH), (int)y, "World"))
        {
            hSpeed = 0;
        }
        else if (checkCollision((int)(x + hSpeed + dirH), (int)y, "DDoodad"))
        {
            hSpeed = 0;
        }
        else if (hSpeed < 0 && x + hSpeed - 30 < 0)
        {
            hSpeed = 0;
        }
        else if (hSpeed > 0 )//&& x + hSpeed + 30 > Level.Cameras[0].WorldWidth)
        {
            //hSpeed = 0;
        }
    }
    
    /**
     * Control the attachments to the game object
     */
    private void controlAttachments(){
    	playerAnimations.x = x + hSpeed;
    	playerAnimations.y = y + vSpeed;
    	//playerAnimations.dirH = -(int) Math.signum(hSpeed);
    	//boolean isMouseDown = Gdx.input.isTouched();
    	
    	playerAnimations.manualUpdatePre(false, isGrounded, isHit, false, hSpeed);
    	
    	isHit = false;
    }
    
}
