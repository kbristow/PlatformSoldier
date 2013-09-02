package com.me.Game.Levels;

import com.badlogic.gdx.Game;
import com.me.Engine.Structures.Level;
import com.me.Game.Enemy.EnemyControl;
import com.me.Game.GameObjects.Obj_PixMapTest;
import com.me.Game.Player.PlayerControl;
import com.me.Game.World.Obj_FloorTile;

public class LevelTest extends Level{

	public LevelTest(Game game, int levelWidth, int levelHeight,
			int screenWidth, int screenHeight, int collisionCellSize,
			float worldCellSize) {
		super(game, levelWidth, levelHeight, screenWidth, screenHeight,
				collisionCellSize, worldCellSize);
	}
	
	@Override
	public void onCreate(){
		logFPS = true;
		
		camera.extentMarginH = 200;
		camera.extentMarginV = 200;
		camera.zoom += 0.2;
		
		for (int i = 0; i < 14; i++)
        {
            Obj_FloorTile newFloorObj = new Obj_FloorTile();
            newFloorObj.x = i*worldCellSize + worldCellSize/2;
            newFloorObj.y = 9*worldCellSize + worldCellSize/2;
        }
		
		for (int i = 8; i < 14; i++)
        {
            Obj_FloorTile newFloorObj = new Obj_FloorTile();
            newFloorObj.x = i*worldCellSize + worldCellSize/2;
            newFloorObj.y = 6*worldCellSize + worldCellSize/2;
        }
		
        Obj_FloorTile newFloorObj = new Obj_FloorTile();
        newFloorObj.x = 14*worldCellSize + worldCellSize/2;
        newFloorObj.y = 7*worldCellSize + worldCellSize/2;
		
		/*Obj_PlayerControl objPlayerTest = new Obj_PlayerControl();
        objPlayerTest.x = worldCellSize*5;
        objPlayerTest.y = worldCellSize*8;*/
        
        PlayerControl objPlayerTest = new PlayerControl();
        objPlayerTest.x = worldCellSize*5;
        objPlayerTest.y = worldCellSize*8;
        
        Obj_PixMapTest objPixMap = new Obj_PixMapTest();
        objPixMap.x = 300;
        objPixMap.y = 500;
        objPixMap.setRotation(45);
        objPixMap.xScale = 1f;
        objPixMap.yScale = 1f;
        
        EnemyControl objEnemyTest = new EnemyControl();
        objEnemyTest.x = worldCellSize*7;
        objEnemyTest.y = worldCellSize*8;
        
        
	}

}
